package com.xenoamess.x8l.psi.impl.utils;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.xenoamess.x8l.X8lTree;
import com.xenoamess.x8l.idea_plugin.X8lDataCenter;
import com.xenoamess.x8l.psi.X8lCommentNodeContent;
import com.xenoamess.x8l.psi.X8lElementFactory;
import com.xenoamess.x8l.psi.X8lTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author XenoAmess
 */
public class X8lCommentNodeContentUtil {
    @SuppressWarnings({"SameReturnValue", "unused"})
    public static IElementType getTokenType(final X8lCommentNodeContent element) {
        return X8lTypes.COMMENT_NODE_CONTENT;
    }

    public static PsiElement getNameIdentifier(X8lCommentNodeContent element) {
        return element;
    }

    public static String getValue(X8lCommentNodeContent element) {
        return X8lTree.untranscode(element.getText());
    }

    public static String getName(X8lCommentNodeContent element) {
        return element.getText();
    }

    public static PsiElement setName(X8lCommentNodeContent element, String newName) {
        X8lCommentNodeContent x8lCommentNodeContent =
                X8lElementFactory.createX8lCommentNodeContent(element.getProject(), newName);
        element.getNode().getTreeParent().replaceChild(element.getNode(), x8lCommentNodeContent.getNode());
        return element;
    }

    public static ItemPresentation getPresentation(final X8lCommentNodeContent element) {
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
