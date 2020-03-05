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
import com.intellij.psi.tree.IElementType;

public class X8lCommentNodeImpl extends ASTWrapperPsiElement implements X8lCommentNode {

  public X8lCommentNodeImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull X8lVisitor visitor) {
    visitor.visitCommentNode(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof X8lVisitor) accept((X8lVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  public IElementType getTokenType() {
    return X8lPsiImplUtil.getTokenType(this);
  }

}
