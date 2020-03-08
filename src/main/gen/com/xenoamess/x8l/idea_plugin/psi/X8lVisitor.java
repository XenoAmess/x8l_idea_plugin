// This is a generated file. Not intended for manual editing.
package com.xenoamess.x8l.idea_plugin.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiLiteral;

public class X8lVisitor extends PsiElementVisitor {

  public void visitCommentNode(@NotNull X8lCommentNode o) {
    visitPsiComment(o);
    // visitPsiLiteral(o);
    // visitPsiNameIdentifierOwner(o);
  }

  public void visitCommentNodeContent(@NotNull X8lCommentNodeContent o) {
    visitPsiLiteral(o);
    // visitPsiNameIdentifierOwner(o);
  }

  public void visitContentNode(@NotNull X8lContentNode o) {
    visitPsiNameIdentifierOwner(o);
  }

  public void visitContentNodeAttribute(@NotNull X8lContentNodeAttribute o) {
    visitPsiNameIdentifierOwner(o);
  }

  public void visitContentNodeAttributeKey(@NotNull X8lContentNodeAttributeKey o) {
    visitPsiLiteral(o);
    // visitPsiNameIdentifierOwner(o);
  }

  public void visitContentNodeAttributeValue(@NotNull X8lContentNodeAttributeValue o) {
    visitPsiLiteral(o);
    // visitPsiNameIdentifierOwner(o);
  }

  public void visitContentNodeChildrenArea(@NotNull X8lContentNodeChildrenArea o) {
    visitPsiNameIdentifierOwner(o);
  }

  public void visitContentNodeHeadArea(@NotNull X8lContentNodeHeadArea o) {
    visitPsiNameIdentifierOwner(o);
  }

  public void visitRootNodeChildrenArea(@NotNull X8lRootNodeChildrenArea o) {
    visitPsiElement(o);
  }

  public void visitTextNode(@NotNull X8lTextNode o) {
    visitPsiLiteral(o);
    // visitPsiNameIdentifierOwner(o);
  }

  public void visitTextNodeContent(@NotNull X8lTextNodeContent o) {
    visitPsiLiteral(o);
    // visitPsiNameIdentifierOwner(o);
  }

  public void visitPsiComment(@NotNull PsiComment o) {
    visitElement(o);
  }

  public void visitPsiLiteral(@NotNull PsiLiteral o) {
    visitElement(o);
  }

  public void visitPsiNameIdentifierOwner(@NotNull PsiNameIdentifierOwner o) {
    visitElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
