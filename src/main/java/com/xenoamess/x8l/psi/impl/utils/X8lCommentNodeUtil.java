package com.xenoamess.x8l.psi.impl.utils;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.xenoamess.x8l.X8lTree;
import com.xenoamess.x8l.idea_plugin.X8lDataCenter;
import com.xenoamess.x8l.psi.X8lCommentNode;
import com.xenoamess.x8l.psi.X8lElementFactory;
import com.xenoamess.x8l.psi.X8lTypes;
import javax.swing.Icon;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author XenoAmess
 */
public class X8lCommentNodeUtil {
    @SuppressWarnings({"SameReturnValue", "unused"})
    public static IElementType getTokenType(final X8lCommentNode element) {
        return X8lTypes.COMMENT_NODE;
    }

    public static PsiElement getNameIdentifier(X8lCommentNode element) {
        return element;
    }

    public static String getValue(X8lCommentNode element) {
        return X8lTree.untranscode(element.getCommentNodeContent().getText());
    }

    public static String getName(X8lCommentNode element) {
        return element.getText();
    }

    public static PsiElement setName(X8lCommentNode element, String newName) {
        X8lCommentNode x8lCommentNode = X8lElementFactory.createX8lCommentNode(element.getProject(), newName);
        element.getNode().getTreeParent().replaceChild(element.getNode(), x8lCommentNode.getNode());
        return element;
    }

    public static ItemPresentation getPresentation(final X8lCommentNode element) {
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
