// This is a generated file. Not intended for manual editing.
package com.xenoamess.x8l.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.IElementType;
import com.xenoamess.x8l.psi.X8lTextNode;
import com.xenoamess.x8l.psi.X8lTextNodeContent;
import com.xenoamess.x8l.psi.X8lVisitor;
import org.jetbrains.annotations.NotNull;

public class X8lTextNodeImpl extends ASTWrapperPsiElement implements X8lTextNode {

    public X8lTextNodeImpl(@NotNull ASTNode node) {
        super(node);
    }

    public void accept(@NotNull X8lVisitor visitor) {
        visitor.visitTextNode(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof X8lVisitor) accept((X8lVisitor) visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public X8lTextNodeContent getTextNodeContent() {
        return findNotNullChildByClass(X8lTextNodeContent.class);
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
    public PsiElement setName(String newName) {
        return X8lPsiImplUtil.setName(this, newName);
    }

}
