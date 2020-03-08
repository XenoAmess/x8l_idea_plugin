package com.xenoamess.x8l.idea_plugin.psi.impl.utils;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.xenoamess.x8l.X8lTree;
import com.xenoamess.x8l.idea_plugin.X8lDataCenter;
import com.xenoamess.x8l.idea_plugin.psi.*;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class X8lContentNodeAttributeUtil {
    public static final String EMPTY_VALUE_STRING = "".intern();

    public static IElementType getTokenType(final X8lContentNodeAttribute element) {
        return X8lTypes.CONTENT_NODE_ATTRIBUTE;
    }

    public static PsiElement getNameIdentifier(X8lContentNodeAttribute element) {
        return element;
    }

    public static String getKey(X8lContentNodeAttribute element) {
        X8lContentNodeAttributeKey keyNode = element.getContentNodeAttributeKey();
        return X8lTree.untranscode(keyNode.getText());
    }

    public static String getValue(X8lContentNodeAttribute element) {
        X8lContentNodeAttributeValue valueNode = element.getContentNodeAttributeValue();
        if (valueNode != null) {
            return X8lTree.untranscode(valueNode.getText());
        } else {
            return EMPTY_VALUE_STRING;
        }
    }

    public static String getName(X8lContentNodeAttribute element) {
        return element.getText();
    }

    public static PsiElement setName(X8lContentNodeAttribute element, String newName) {
        X8lContentNodeAttribute x8lContentNodeAttribute = X8lElementFactory.createX8lContentNodeAttribute(element.getProject(), newName);
        element.getNode().getTreeParent().replaceChild(element.getNode(), x8lContentNodeAttribute.getNode());
        return element;
    }

    public static ItemPresentation getPresentation(final X8lContentNodeAttribute element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return element.getKey();
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
