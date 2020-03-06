package com.xenoamess.x8l.idea_plugin.psi.impl;

import com.intellij.psi.tree.IElementType;
import com.xenoamess.x8l.idea_plugin.psi.X8lCommentNode;
import com.xenoamess.x8l.idea_plugin.psi.X8lTypes;

public class X8lCommentNodeUtil {
    public static IElementType getTokenType(final X8lCommentNode x8lCommentNode) {
        return X8lTypes.COMMENT_NODE;
    }
}
