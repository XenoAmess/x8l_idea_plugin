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

public class X8lContentNodeImpl extends ASTWrapperPsiElement implements X8lContentNode {

  public X8lContentNodeImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull X8lVisitor visitor) {
    visitor.visitContentNode(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof X8lVisitor) accept((X8lVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public X8lContentNodeChildrenArea getContentNodeChildrenArea() {
    return findNotNullChildByClass(X8lContentNodeChildrenArea.class);
  }

  @Override
  @NotNull
  public X8lContentNodeHeadArea getContentNodeHeadArea() {
    return findNotNullChildByClass(X8lContentNodeHeadArea.class);
  }

}
