// This is a generated file. Not intended for manual editing.
package com.xenoamess.x8l.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.xenoamess.x8l.psi.X8lPsiElement;
import com.xenoamess.x8l.psi.X8lVisitor;
import com.xenoamess.x8l.psi.X8lWhiteSpace;
import org.jetbrains.annotations.NotNull;

public class X8lWhiteSpaceImpl extends X8lPsiElement implements X8lWhiteSpace {

    public X8lWhiteSpaceImpl(@NotNull ASTNode node) {
        super(node);
    }

    public void accept(@NotNull X8lVisitor visitor) {
        visitor.visitWhiteSpace(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof X8lVisitor)
            accept((X8lVisitor) visitor);
        else
            super.accept(visitor);
    }

}
