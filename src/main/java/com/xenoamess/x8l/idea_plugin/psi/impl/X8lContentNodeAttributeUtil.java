package com.xenoamess.x8l.idea_plugin.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.xenoamess.x8l.X8lTree;
import com.xenoamess.x8l.idea_plugin.X8lDataCenter;
import com.xenoamess.x8l.idea_plugin.psi.X8lContentNodeAttribute;
import com.xenoamess.x8l.idea_plugin.psi.X8lElementFactory;
import com.xenoamess.x8l.idea_plugin.psi.X8lTypes;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Objects;

public class X8lContentNodeAttributeUtil {
    public static final String EMPTY_VALUE_STRING = "".intern();

    public static String getKey(X8lContentNodeAttribute element) {
        ASTNode keyNode = element.getNode().findChildByType(X8lTypes.KEY);
        Objects.requireNonNull(keyNode);
        return X8lTree.untranscode(keyNode.getText());
    }

    public static String getValue(X8lContentNodeAttribute element) {
        ASTNode valueNode = element.getNode().findChildByType(X8lTypes.VALUE);
        if (valueNode != null) {
            return valueNode.getText();
        } else {
            return EMPTY_VALUE_STRING;
        }
    }

    public static String getName(X8lContentNodeAttribute element) {
        return getKey(element);
    }

    public static PsiElement setName(X8lContentNodeAttribute element, String newName) {
        ASTNode keyNode = element.getNode().findChildByType(X8lTypes.KEY);
        Objects.requireNonNull(keyNode);
        X8lContentNodeAttribute x8lContentNodeAttribute = X8lElementFactory.createX8lContentNodeAttribute(element.getProject(), newName);
        ASTNode newKeyNode = x8lContentNodeAttribute.getFirstChild().getNode();
        //todo fix it!
        element.getNode().replaceChild(keyNode, newKeyNode);
        return element;
    }

    public static PsiElement getNameIdentifier(X8lContentNodeAttribute element) {
        ASTNode keyNode = element.getNode().findChildByType(X8lTypes.KEY);
        Objects.requireNonNull(keyNode);
        return keyNode.getPsi();
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
