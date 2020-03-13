// This is a generated file. Not intended for manual editing.
package com.xenoamess.x8l.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.xenoamess.x8l.psi.X8lContentNodeChildrenArea;
import com.xenoamess.x8l.psi.X8lPsiElement;
import com.xenoamess.x8l.psi.X8lRootNodeChildrenArea;
import com.xenoamess.x8l.psi.X8lVisitor;
import org.jetbrains.annotations.NotNull;

public class X8lRootNodeChildrenAreaImpl extends X8lPsiElement implements X8lRootNodeChildrenArea {

    public X8lRootNodeChildrenAreaImpl(@NotNull ASTNode node) {
        super(node);
    }

    public void accept(@NotNull X8lVisitor visitor) {
        visitor.visitRootNodeChildrenArea(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof X8lVisitor)
            accept((X8lVisitor) visitor);
        else
            super.accept(visitor);
    }

    @Override
    @NotNull
    public X8lContentNodeChildrenArea getContentNodeChildrenArea() {
        return findNotNullChildByClass(X8lContentNodeChildrenArea.class);
    }

}
