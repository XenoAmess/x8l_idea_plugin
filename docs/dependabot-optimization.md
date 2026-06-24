# Dependabot 优化复盘

本文档记录 `x8l_idea_plugin` 仓库的 Dependabot 自动合并优化全过程、踩到的坑以及总结出的可复用经验。

仓库地址：https://github.com/XenoAmess/x8l_idea_plugin

---

## 1. 初始状态

| 项 | 现状 |
| --- | --- |
| `.github/dependabot.yml` | 2 个 ecosystem（gradle + github-actions），daily 调度，`open-pull-requests-limit: 100`，无分组、无标签 |
| `.github/workflows/build.yml` | `on: [ push ]`，matrix `os × java:11 × experimental:false` |
| `.github/workflows/auto-merge.yml` | 用 `GITHUB_TOKEN`，仅匹配 `semver-patch` / `semver-minor`，事件类型 `[opened, synchronize]` |
| 仓库 secret | `MYTOKEN`（PAT，含 `workflow` scope）— 已存在但未被使用 |
| 仓库设置 | `allow_auto_merge: false`，无 master 分支保护 |
| 当前 open PR | #198 `actions/cache 5→6`（major，CLEAN）/ #197 `gradle-wrapper 8→9`（major，build FAILURE）/ #196 `actions/checkout 6→7`（major，CLEAN） |

### 1.1 现状看上去"半跑通"的假象

最值得注意的一点：`build.yml` 只触发 `push`，但 PR 检查页却能看到 `build (..., 11, false)` 通过。这是依赖了 **Dependabot 每次 rebase 都会 push 到 PR 分支**这个副作用，让 push 事件顺带触发了 CI 跑在 PR 分支上、然后再被 PR 的 ref 视图"借"过去展示。表面上看起来工作良好，但：
- 任何 push 到非 master 分支都会触发 CI（浪费 runner 时间）
- 实际上 PR 没有走 `pull_request` 事件路径，**不构成正式的门控**
- 一旦 Dependabot 改变 push 策略（例如改用 cherry-pick），整个机制就静默失效

—— 这是 **Pitfall 2 + Pitfall 4 同时中招**的典型形态。

---

## 2. 改了哪些东西

| # | 文件 | 改动 |
| --- | --- | --- |
| 1 | `.github/workflows/build.yml` | `on: [push]` → `on: { push: {branches:[master]}, pull_request: }` |
| 2 | `.github/workflows/auto-merge.yml` | `GITHUB_TOKEN` → `MYTOKEN`；加入 github-actions semver-major 分支匹配；事件类型加 `reopened`；`if:` 同时匹配 `dependabot[bot]` 和 `app/dependabot` |
| 3 | `.github/dependabot.yml` | 两个 ecosystem 都加入 `groups: { minor-and-patch, major }`；加入 `labels`；PR limit 100 → 10 |
| 4 | 仓库设置 `allow_auto_merge` | `false` → `true` |
| 5 | master 分支保护 | 新增；strict + 3 个 matrix 检查作为必需；`enforce_admins: false`；`required_linear_history: true` |

提交：
- `b55ab5a` ci(dependabot): optimize auto-merge and reduce PR noise
- `6b6be66` chore(deps): bump the major group with 2 updates（自动合并验证用）
- 后续 `fd420ed` build: upgrade from JDK 11 to JDK 17

---

## 3. 端到端验证

| 检查 | 结果 |
| --- | --- |
| `allow_auto_merge == true` | ✓ |
| CI 在 PR 上以 `pull_request` 事件正确触发 | ✓（三条 matrix 检查全部出现） |
| 新分组 PR `#199` (major group, github-actions) | ✓ 自动 review + auto-merge + rebase 合并 |
| 旧 PR `#197`（gradle-wrapper 8→9） | ✓ 保持开启供人工审查（gradle 9 真实破坏构建） |
| 旧 PR `#198`、`#196` | ✓ 被 dependabot 自动 close（被新分组 PR 取代） |
| `auto-merge.yml` 用的是 `MYTOKEN` 而非 `GITHUB_TOKEN` | ✓（运行日志中 `GH_TOKEN: ***` 不是 `***` 占位符的形式） |
| master 上现在带 actions/checkout v7 + actions/cache v6 | ✓ |

---

## 4. 学到的经验（可复用）

### 4.1 `gh pr list` 显示的 `author.login` 与 workflow 上下文里的 `user.login` **可能不同**

本仓库的 PR 在 `gh pr list --json author` 中显示 `app/dependabot`，但 workflow 跑的时候 `github.event.pull_request.user.login` 实际是 `dependabot[bot]`（GitHub 把 GitHub App 形态的 Dependabot 在事件上下文里规范化成了旧字符串）。也就是说：

- 单 `dependabot[bot]` 的 `if:` 在本仓库"碰巧"能跑
- 但**显式同时匹配两者**仍然是更稳的写法（防御未来 GitHub 调整规范化策略）

→ 经验：永远写 `||` 匹配，不要赌规范化。

### 4.2 `build.yml` 只触发 `push` 也能"看起来工作"——这正是反模式

- 表面：CI 检查出现在 PR 上、状态绿、auto-merge 顺利
- 实际：依赖 Dependabot 的 push 副作用；任何 push 到其他分支也会触发；PR 没有走正式事件路径；branch protection 的 required check 实际是 push 事件产生的同名 check，**不是 pull_request 事件的标准产物**
- 诊断：去看 `gh run list --workflow="Java CI"`。如果每次 PR 同步都出现**两组**运行（一条 push + 一条 pull_request 同名 job），说明 build.yml 同时被两边触发；或者**只有 push 触发**说明压根没走 pull_request 路径

→ 经验：把 `push:` 限定到 master，再加 `pull_request:`，让 PR 走正路。

### 4.3 Matrix 变化会让 branch protection 的 required check 名失效

升级 JDK 11 → 17 时，矩阵里的 java 从 11 变成 17，check 名从 `build (..., 11, false)` 变成 `build (..., 17, false)`。如果忘了同步更新 branch protection，会出现：
- CI 在新 PR 上跑出新名字
- 旧名字作为 required check 永远不再触发任何 PR
- 表面上"required check 失效"，PR 处于 BLOCKED 状态（其实是因为新 PR 上的 check 没有被任何 required 项匹配上）

→ 经验：改 build.yml 的 matrix 维度**之前**，先把 branch protection 的 required check 也一起更新；或先用 GraphQL `isRequired` 查一遍真实存在的 check 名。

### 4.4 `migration push` 会让所有现存 Dependabot PR 瞬间变成 BEHIND

本次优化一次性 push 了 workflow 改动 + 仓库设置改动。push 完成的那一秒：
- 之前 `mergeStateStatus: CLEAN` 的 #198、#196 立刻变成 `BEHIND`
- branch protection `strict: true` 阻止它们 auto-merge
- Dependabot 自动 rebase 是**每小时一次**，不是即时的
- 如果用 `close+reopen`，只能触发 `reopened` 事件，不会重新 base

→ 经验：要么在 push 之前对所有 open Dependabot PR 做 `@dependabot rebase`，要么 push 之后**批量**触发 rebase（脚本见 dependabot-automerge-skill 的 Quick Reference）。本次用了 `@dependabot rebase` + 等依赖自动重试，效果一样。

### 4.5 分组的副作用：dependabot 会自动关闭被合并的旧 PR

加入 `groups:` 之后，Dependabot 把 N 个独立 PR 关掉、改开一个分组 PR。**这是官方预期行为**，不是 bug：
- 关 PR：`gh pr list --state closed` 能看到（保留 history）
- 新 PR：标题变成 `chore(deps): bump the <group> with N updates`
- update-type 输出也变了：原本每个 PR 各报一个 type，现在分组 PR 报**最高严重度**的那个 type

→ 经验：在 verification 步骤里看到"老 PR 没了"不要慌，看新 PR 是不是 grouping PR。

### 4.6 gradle-wrapper 这种 breaking change 不该被 auto-merge

#197 gradle-wrapper 8.0.2 → 9.6.0 实际破坏了构建（`build (ubuntu-latest, 11, false): FAILURE`）。原因不是配置错，而是 Gradle 9 改了 wrapper 协议和 API。

本次策略：
- gradle ecosystem 的 major **不**加入自动合并
- github-actions ecosystem 的 major 加入（Node runtime bumps 一般安全）

→ 经验：默认策略表里 gradle/maven 的 major 留给人看，github-actions major 可以自动合并。这与 dependabot-automerge-skill SKILL.md 的 Decision Table 一致。

### 4.7 `required_linear_history: true` 配合 `--rebase`

auto-merge 用 `--rebase` 时，master 上每个 PR 都应该是 rebase 进去的——开 `required_linear_history` 可以阻止 squash merge / merge commit 把 history 弄花。本次顺手加上。

→ 经验：用 `--rebase` 就把 `required_linear_history` 打开，两者天然配对。

### 4.8 AdoptOpenJDK 已经被 GitHub Actions 标记为迁移到 Temurin

CI 运行后出现 warning：`AdoptOpenJDK has moved to Eclipse Temurin ... please consider changing to the 'temurin' distribution type in your setup-java configuration.`

当前 `build.yml` 还是 `distribution: adopt`。短期能用，长期建议改成 `distribution: temurin`。

→ 经验：检查 `actions/setup-java` 的 distribution 字段。

---

## 5. 调试工具箱

碰到 auto-merge 不工作时，按这个顺序查：

1. **`gh api repos/<owner>/<repo> --jq '.allow_auto_merge'`** — 必须 `true`，否则 `gh pr merge --auto` 静默 422。
2. **`gh pr list --state open --json number,headRefName,mergeStateStatus,author`** — 看 author.login 决定 if 怎么写；看 mergeStateStatus 决定卡在哪一步（CLEAN/BEHIND/BLOCKED/DIRTY/UNSTABLE/UNKNOWN 各有含义）。
3. **`gh api repos/<owner>/<repo>/branches/master/protection | jq .required_status_checks.checks`** — 看 required check 名是否与实际 matrix 输出匹配。
4. **`gh run list --workflow=auto-merge.yml --limit 3 --json databaseId,conclusion,displayTitle`** — 看自动合并工作流是否真的跑了；conclusion=success 但 PR 没合并 → 大概率 allow_auto_merge=false 或 token scope 错。
5. **`gh run view <id> --log | grep -A 3 'Enable auto-merge'`** — 看 step 是不是 `skipped`（allow_auto_merge off）还是 `failure`（token scope）。
6. **`gh api repos/<owner>/<repo>/branches/master 2>&1 | grep '"protected"'`** — 确认是否启用了保护。

---

## 6. 本次未做、留给后续的事

- [ ] `build.yml` 的 `distribution: adopt` → `distribution: temurin`（AdoptOpenJDK 已迁移）
- [ ] Gradle wrapper 升级（PR #197 仍未合并，gradle 9 兼容性需逐项排查）
- [ ] IntelliJ Platform 升级（当前 2022.3，2022.1 → 2022.3 是为支持 JDK 17 的最小改动；后续可继续升到 2024.x）
- [ ] `org.jetbrains.intellij` 插件升级（当前 1.17.4，2.x 支持更新的平台）

---

## 7. 相关文件

- `.github/workflows/auto-merge.yml` — 自动合并工作流
- `.github/workflows/build.yml` — CI 工作流
- `.github/dependabot.yml` — Dependabot 配置
- 仓库设置：master 分支保护 + `allow_auto_merge=true`
- 仓库 secret：`MYTOKEN`（PAT，含 `repo` + `workflow` scope）