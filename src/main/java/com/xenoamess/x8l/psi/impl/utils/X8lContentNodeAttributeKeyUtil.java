package com.xenoamess.x8l.psi.impl.utils;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.xenoamess.x8l.X8lTree;
import com.xenoamess.x8l.idea_plugin.X8lDataCenter;
import com.xenoamess.x8l.psi.X8lContentNodeAttributeKey;
import com.xenoamess.x8l.psi.X8lElementFactory;
import com.xenoamess.x8l.psi.X8lTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author XenoAmess
 */
public class X8lContentNodeAttributeKeyUtil {
    @SuppressWarnings({"SameReturnValue", "unused"})
    public static IElementType getTokenType(final X8lContentNodeAttributeKey element) {
        return X8lTypes.CONTENT_NODE_ATTRIBUTE_KEY;
    }

    public static PsiElement getNameIdentifier(X8lContentNodeAttributeKey element) {
        return element;
    }

    public static String getValue(X8lContentNodeAttributeKey element) {
        return X8lTree.untranscode(element.getText());
    }

    public static String getName(X8lContentNodeAttributeKey element) {
        return element.getText();
    }

    public static PsiElement setName(X8lContentNodeAttributeKey element, String newName) {
        X8lContentNodeAttributeKey x8lCommentNode =
                X8lElementFactory.createX8lContentNodeAttributeKey(element.getProject(), newName);
        element.getNode().getTreeParent().replaceChild(element.getNode(), x8lCommentNode.getNode());
        return element;
    }

    public static ItemPresentation getPresentation(final X8lContentNodeAttributeKey element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return element.getValue();
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
