package com.xenoamess.x8l.psi.impl.utils;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.xenoamess.x8l.idea_plugin.X8lDataCenter;
import com.xenoamess.x8l.psi.X8lContentNodeAttribute;
import com.xenoamess.x8l.psi.X8lElementFactory;
import com.xenoamess.x8l.psi.X8lTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author XenoAmess
 */
public class X8lContentNodeAttributeUtil {
    @SuppressWarnings({"SameReturnValue", "unused"})
    public static IElementType getTokenType(final X8lContentNodeAttribute element) {
        return X8lTypes.CONTENT_NODE_ATTRIBUTE;
    }

    public static PsiElement getNameIdentifier(X8lContentNodeAttribute element) {
        return element.getContentNodeAttributeKey();
    }

    public static String getValue(X8lContentNodeAttribute element) {
        return element.getText();
    }

    public static String getName(X8lContentNodeAttribute element) {
        return element.getText();
    }

    public static PsiElement setName(X8lContentNodeAttribute element, String newName) {
        X8lContentNodeAttribute x8lContentNodeAttribute =
                X8lElementFactory.createX8lContentNodeAttribute(element.getProject(), newName);
        element.getNode().getTreeParent().replaceChild(element.getNode(), x8lContentNodeAttribute.getNode());
        return element;
    }

    public static ItemPresentation getPresentation(final X8lContentNodeAttribute element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return element.getName();
            }

            @NotNull
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
