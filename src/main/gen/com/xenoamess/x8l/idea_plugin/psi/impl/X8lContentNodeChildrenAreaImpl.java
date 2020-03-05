// This is a generated file. Not intended for manual editing.
package com.xenoamess.x8l.idea_plugin.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.xenoamess.x8l.idea_plugin.psi.X8lTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.xenoamess.x8l.idea_plugin.psi.*;

public class X8lContentNodeChildrenAreaImpl extends ASTWrapperPsiElement implements X8lContentNodeChildrenArea {

  public X8lContentNodeChildrenAreaImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull X8lVisitor visitor) {
    visitor.visitContentNodeChildrenArea(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof X8lVisitor) accept((X8lVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<X8lCommentNode> getCommentNodeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, X8lCommentNode.class);
  }

  @Override
  @NotNull
  public List<X8lContentNode> getContentNodeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, X8lContentNode.class);
  }

  @Override
  @NotNull
  public List<X8lTextNode> getTextNodeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, X8lTextNode.class);
  }

}
