package com.xenoamess.x8l.idea_plugin.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.xenoamess.x8l.idea_plugin.X8lDataCenter;
import com.xenoamess.x8l.idea_plugin.psi.X8lElementFactory;
import com.xenoamess.x8l.idea_plugin.psi.X8lProperty;
import com.xenoamess.x8l.idea_plugin.psi.X8lTypes;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class X8lPsiImplUtil {
    public static String getKey(X8lProperty element) {
        ASTNode keyNode = element.getNode().findChildByType(X8lTypes.KEY);
        if (keyNode != null) {
            // IMPORTANT: Convert embedded escaped spaces to simple spaces
            return keyNode.getText().replaceAll("\\\\ ", " ");
        } else {
            return null;
        }
    }

    public static String getValue(X8lProperty element) {
        ASTNode valueNode = element.getNode().findChildByType(X8lTypes.VALUE);
        if (valueNode != null) {
            return valueNode.getText();
        } else {
            return null;
        }
    }

    public static String getName(X8lProperty element) {
        return getKey(element);
    }

    public static PsiElement setName(X8lProperty element, String newName) {
        ASTNode keyNode = element.getNode().findChildByType(X8lTypes.KEY);
        if (keyNode != null) {

            X8lProperty property = X8lElementFactory.createProperty(element.getProject(), newName);
            ASTNode newKeyNode = property.getFirstChild().getNode();
            element.getNode().replaceChild(keyNode, newKeyNode);
        }
        return element;
    }

    public static PsiElement getNameIdentifier(X8lProperty element) {
        ASTNode keyNode = element.getNode().findChildByType(X8lTypes.KEY);
        if (keyNode != null) {
            return keyNode.getPsi();
        } else {
            return null;
        }
    }

    public static ItemPresentation getPresentation(final X8lProperty element) {
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