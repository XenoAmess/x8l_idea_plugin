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
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.tree.IElementType;

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

  @Override
  public IElementType getTokenType() {
    return X8lPsiImplUtil.getTokenType(this);
  }

  @Override
  public PsiElement getNameIdentifier() {
    return X8lPsiImplUtil.getNameIdentifier(this);
  }

  @Override
  public String getValue() {
    return X8lPsiImplUtil.getValue(this);
  }

  @Override
  public ItemPresentation getPresentation() {
    return X8lPsiImplUtil.getPresentation(this);
  }

  @Override
  public String getName() {
    return X8lPsiImplUtil.getName(this);
  }

  @Override
  public PsiElement setName(String name) {
    return X8lPsiImplUtil.setName(this, name);
  }

}
