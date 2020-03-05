package com.xenoamess.x8l.idea_plugin.psi.impl;

import com.intellij.navigation.ItemPresentation;
import com.xenoamess.x8l.X8lTree;
import com.xenoamess.x8l.idea_plugin.X8lDataCenter;
import com.xenoamess.x8l.idea_plugin.psi.X8lTextNode;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class X8lTextNodeUtil {
    public static String getValue(X8lTextNode element) {
        return X8lTree.untranscode(element.getText());
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
