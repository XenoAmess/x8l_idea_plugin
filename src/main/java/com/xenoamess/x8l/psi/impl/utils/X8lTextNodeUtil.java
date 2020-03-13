package com.xenoamess.x8l.psi.impl.utils;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.xenoamess.x8l.X8lTree;
import com.xenoamess.x8l.idea_plugin.X8lDataCenter;
import com.xenoamess.x8l.psi.X8lElementFactory;
import com.xenoamess.x8l.psi.X8lTextNode;
import com.xenoamess.x8l.psi.X8lTypes;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class X8lTextNodeUtil {
    public static IElementType getTokenType(final X8lTextNode element) {
        return X8lTypes.TEXT_NODE;
    }

    public static PsiElement getNameIdentifier(X8lTextNode element) {
        return element.getTextNodeContent();
    }

    public static String getValue(X8lTextNode element) {
        return X8lTree.untranscode(element.getText());
    }

    public static String getName(X8lTextNode element) {
        return element.getText();
    }

    public static PsiElement setName(X8lTextNode element, String newName) {
        X8lTextNode x8lTextNode = X8lElementFactory.createX8lTextNode(element.getProject(), newName);
        element.getNode().getTreeParent().replaceChild(element.getNode(), x8lTextNode.getNode());
        return element;
    }

    public static ItemPresentation getPresentation(final X8lTextNode element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return element.getValue();
            }

            @Nullable
            @Override
            public String getLocationString() {
                return element.getContainingFile().getName();
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return X8lDataCenter.X8L_LANGUAGE_ICON;
            }
        };
    }
}
