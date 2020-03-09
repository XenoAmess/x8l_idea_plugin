// This is a generated file. Not intended for manual editing.
package com.xenoamess.x8l.idea_plugin.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.xenoamess.x8l.idea_plugin.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class X8lContentNodeAttributeImpl extends ASTWrapperPsiElement implements X8lContentNodeAttribute {

    public X8lContentNodeAttributeImpl(@NotNull ASTNode node) {
        super(node);
    }

    public void accept(@NotNull X8lVisitor visitor) {
        visitor.visitContentNodeAttribute(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof X8lVisitor) accept((X8lVisitor) visitor);
        else super.accept(visitor);
    }

    @Override
    @NotNull
    public X8lContentNodeAttributeKey getContentNodeAttributeKey() {
        return findNotNullChildByClass(X8lContentNodeAttributeKey.class);
    }

    @Override
    @Nullable
    public X8lContentNodeAttributeValue getContentNodeAttributeValue() {
        return findChildByClass(X8lContentNodeAttributeValue.class);
    }

    @Override
    @NotNull
    public List<X8lWhiteSpace> getWhiteSpaceList() {
        return PsiTreeUtil.getChildrenOfTypeAsList(this, X8lWhiteSpace.class);
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
