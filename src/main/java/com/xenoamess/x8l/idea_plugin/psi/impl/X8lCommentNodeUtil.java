package com.xenoamess.x8l.idea_plugin.psi.impl;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.tree.IElementType;
import com.xenoamess.x8l.X8lTree;
import com.xenoamess.x8l.idea_plugin.X8lDataCenter;
import com.xenoamess.x8l.idea_plugin.psi.X8lCommentNode;
import com.xenoamess.x8l.idea_plugin.psi.X8lTextNode;
import com.xenoamess.x8l.idea_plugin.psi.X8lTypes;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class X8lCommentNodeUtil {
    public static IElementType getTokenType(final X8lCommentNode x8lCommentNode) {
        return X8lTypes.COMMENT_NODE;
    }

    public static String getValue(X8lCommentNode element) {
        return X8lTree.untranscode(element.getText());
    }

    public static ItemPresentation getPresentation(final X8lCommentNode element) {
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
