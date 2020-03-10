package com.xenoamess.x8l.psi.impl.utils;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.xenoamess.x8l.idea_plugin.X8lDataCenter;
import com.xenoamess.x8l.psi.X8lContentNodeHeadArea;
import com.xenoamess.x8l.psi.X8lElementFactory;
import com.xenoamess.x8l.psi.X8lTypes;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class X8lContentNodeHeadAreaUtil {
    public static IElementType getTokenType(final X8lContentNodeHeadArea element) {
        return X8lTypes.CONTENT_NODE_HEAD_AREA;
    }

    public static PsiElement getNameIdentifier(X8lContentNodeHeadArea element) {
        return element;
    }

    public static String getValue(X8lContentNodeHeadArea element) {
        return element.getText();
    }

    public static String getName(X8lContentNodeHeadArea element) {
        return element.getText();
    }

    public static PsiElement setName(X8lContentNodeHeadArea element, String newName) {
        X8lContentNodeHeadArea x8lContentNodeHeadArea = X8lElementFactory.createX8lContentNodeHeadArea(element.getProject(), newName);
        element.getNode().getTreeParent().replaceChild(element.getNode(), x8lContentNodeHeadArea.getNode());
        return element;
    }

    public static ItemPresentation getPresentation(final X8lContentNodeHeadArea element) {
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
