// This is a generated file. Not intended for manual editing.
package com.xenoamess.x8l.psi;

import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;

public class X8lVisitor extends PsiElementVisitor {

    public void visitCommentNode(@NotNull X8lCommentNode o) {
        visitPsiComment(o);
        // visitPsiLiteralValue(o);
        // visitPsiNameIdentifierOwner(o);
        // visitNavigationItem(o);
    }

    public void visitCommentNodeContent(@NotNull X8lCommentNodeContent o) {
        visitPsiLiteralValue(o);
        // visitPsiNameIdentifierOwner(o);
        // visitNavigationItem(o);
    }

    public void visitContentNode(@NotNull X8lContentNode o) {
        visitPsiNameIdentifierOwner(o);
        // visitNavigationItem(o);
    }

    public void visitContentNodeAttribute(@NotNull X8lContentNodeAttribute o) {
        visitPsiNameIdentifierOwner(o);
        // visitNavigationItem(o);
    }

    public void visitContentNodeAttributeKey(@NotNull X8lContentNodeAttributeKey o) {
        visitPsiLiteralValue(o);
        // visitPsiNameIdentifierOwner(o);
        // visitNavigationItem(o);
    }

    public void visitContentNodeAttributeValue(@NotNull X8lContentNodeAttributeValue o) {
        visitPsiLiteralValue(o);
        // visitPsiNameIdentifierOwner(o);
        // visitNavigationItem(o);
    }

    public void visitContentNodeChildrenArea(@NotNull X8lContentNodeChildrenArea o) {
        visitPsiNameIdentifierOwner(o);
        // visitNavigationItem(o);
    }

    public void visitContentNodeHeadArea(@NotNull X8lContentNodeHeadArea o) {
        visitPsiNameIdentifierOwner(o);
        // visitNavigationItem(o);
    }

    public void visitRootNodeChildrenArea(@NotNull X8lRootNodeChildrenArea o) {
        visitPsiElement(o);
    }

    public void visitTextNode(@NotNull X8lTextNode o) {
        visitPsiLiteralValue(o);
        // visitPsiNameIdentifierOwner(o);
        // visitNavigationItem(o);
    }

    public void visitTextNodeContent(@NotNull X8lTextNodeContent o) {
        visitPsiLiteralValue(o);
        // visitPsiNameIdentifierOwner(o);
        // visitNavigationItem(o);
    }

    public void visitWhiteSpace(@NotNull X8lWhiteSpace o) {
        visitPsiElement(o);
    }

    public void visitPsiComment(@NotNull PsiComment o) {
        visitElement(o);
    }

    public void visitPsiLiteralValue(@NotNull PsiLiteralValue o) {
        visitElement((PsiElement) o);
    }

    public void visitPsiNameIdentifierOwner(@NotNull PsiNameIdentifierOwner o) {
        visitElement(o);
    }

    public void visitPsiElement(@NotNull PsiElement o) {
        visitElement(o);
    }

}
