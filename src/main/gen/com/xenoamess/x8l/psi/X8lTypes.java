// This is a generated file. Not intended for manual editing.
package com.xenoamess.x8l.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.xenoamess.x8l.psi.impl.*;

public interface X8lTypes {

    IElementType COMMENT_NODE = new X8lElementType("COMMENT_NODE");
    IElementType COMMENT_NODE_CONTENT = new X8lElementType("COMMENT_NODE_CONTENT");
    IElementType CONTENT_NODE = new X8lElementType("CONTENT_NODE");
    IElementType CONTENT_NODE_ATTRIBUTE = new X8lElementType("CONTENT_NODE_ATTRIBUTE");
    IElementType CONTENT_NODE_ATTRIBUTE_KEY = new X8lElementType("CONTENT_NODE_ATTRIBUTE_KEY");
    IElementType CONTENT_NODE_ATTRIBUTE_VALUE = new X8lElementType("CONTENT_NODE_ATTRIBUTE_VALUE");
    IElementType CONTENT_NODE_CHILDREN_AREA = new X8lElementType("CONTENT_NODE_CHILDREN_AREA");
    IElementType CONTENT_NODE_HEAD_AREA = new X8lElementType("CONTENT_NODE_HEAD_AREA");
    IElementType ROOT_NODE_CHILDREN_AREA = new X8lElementType("ROOT_NODE_CHILDREN_AREA");
    IElementType TEXT_NODE = new X8lElementType("TEXT_NODE");
    IElementType TEXT_NODE_CONTENT = new X8lElementType("TEXT_NODE_CONTENT");
    IElementType WHITE_SPACE = new X8lElementType("WHITE_SPACE");

    IElementType COMMENT_NODE_CONTENT_STRING = new X8lTokenType("COMMENT_NODE_CONTENT_STRING");
    IElementType COMMENT_NODE_LEFT_BRACKET = new X8lTokenType("COMMENT_NODE_LEFT_BRACKET");
    IElementType COMMENT_NODE_RIGHT_BRACKET = new X8lTokenType("COMMENT_NODE_RIGHT_BRACKET");
    IElementType CONTENT_NODE_ATTRIBUTE_KEY_CONTENT_STRING = new X8lTokenType("CONTENT_NODE_ATTRIBUTE_KEY_CONTENT_STRING");
    IElementType CONTENT_NODE_ATTRIBUTE_VALUE_CONTENT_STRING = new X8lTokenType("CONTENT_NODE_ATTRIBUTE_VALUE_CONTENT_STRING");
    IElementType LEFT_BRACKET = new X8lTokenType("LEFT_BRACKET");
    IElementType RIGHT_BRACKET = new X8lTokenType("RIGHT_BRACKET");
    IElementType SEPARATOR = new X8lTokenType("SEPARATOR");
    IElementType TEXT_NODE_CONTENT_STRING = new X8lTokenType("TEXT_NODE_CONTENT_STRING");
    IElementType WHITE_SPACE_CONTENT_STRING = new X8lTokenType("WHITE_SPACE_CONTENT_STRING");

    class Factory {
        public static PsiElement createElement(ASTNode node) {
            IElementType type = node.getElementType();
            if (type == COMMENT_NODE) {
                return new X8lCommentNodeImpl(node);
            } else if (type == COMMENT_NODE_CONTENT) {
                return new X8lCommentNodeContentImpl(node);
            } else if (type == CONTENT_NODE) {
                return new X8lContentNodeImpl(node);
            } else if (type == CONTENT_NODE_ATTRIBUTE) {
                return new X8lContentNodeAttributeImpl(node);
            } else if (type == CONTENT_NODE_ATTRIBUTE_KEY) {
                return new X8lContentNodeAttributeKeyImpl(node);
            } else if (type == CONTENT_NODE_ATTRIBUTE_VALUE) {
                return new X8lContentNodeAttributeValueImpl(node);
            } else if (type == CONTENT_NODE_CHILDREN_AREA) {
                return new X8lContentNodeChildrenAreaImpl(node);
            } else if (type == CONTENT_NODE_HEAD_AREA) {
                return new X8lContentNodeHeadAreaImpl(node);
            } else if (type == ROOT_NODE_CHILDREN_AREA) {
                return new X8lRootNodeChildrenAreaImpl(node);
            } else if (type == TEXT_NODE) {
                return new X8lTextNodeImpl(node);
            } else if (type == TEXT_NODE_CONTENT) {
                return new X8lTextNodeContentImpl(node);
            } else if (type == WHITE_SPACE) {
                return new X8lWhiteSpaceImpl(node);
            }
            throw new AssertionError("Unknown element type: " + type);
        }
    }
}
