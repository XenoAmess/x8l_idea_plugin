// This is a generated file. Not intended for manual editing.
package com.xenoamess.x8l.idea_plugin.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import org.intellij.sdk.language.psi.SimpleNamedElement;

public class X8lVisitor extends PsiElementVisitor {

  public void visitProperty(@NotNull X8lProperty o) {
    visitSimpleNamedElement(o);
  }

  public void visitSimpleNamedElement(@NotNull SimpleNamedElement o) {
    visitElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
