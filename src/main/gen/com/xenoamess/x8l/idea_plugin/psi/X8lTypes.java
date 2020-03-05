// This is a generated file. Not intended for manual editing.
package com.xenoamess.x8l.idea_plugin.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.xenoamess.x8l.idea_plugin.psi.impl.*;

public interface X8lTypes {

  IElementType COMMENT_NODE = new X8lElementType("COMMENT_NODE");
  IElementType CONTENT_NODE = new X8lElementType("CONTENT_NODE");
  IElementType CONTENT_NODE_ATTRIBUTE = new X8lElementType("CONTENT_NODE_ATTRIBUTE");
  IElementType CONTENT_NODE_CHILDREN_AREA = new X8lElementType("CONTENT_NODE_CHILDREN_AREA");
  IElementType CONTENT_NODE_HEAD_AREA = new X8lElementType("CONTENT_NODE_HEAD_AREA");
  IElementType LEFT_BRACE = new X8lElementType("LEFT_BRACE");
  IElementType RIGHT_BRACE = new X8lElementType("RIGHT_BRACE");
  IElementType TEXT_NODE = new X8lElementType("TEXT_NODE");

  IElementType KEY = new X8lTokenType("KEY");
  IElementType SEPARATOR = new X8lTokenType("SEPARATOR");
  IElementType TEXT_STRING = new X8lTokenType("TEXT_STRING");
  IElementType VALUE = new X8lTokenType("VALUE");
  IElementType WHITE_SPACE = new X8lTokenType("WHITE_SPACE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == COMMENT_NODE) {
        return new X8lCommentNodeImpl(node);
      }
      else if (type == CONTENT_NODE) {
        return new X8lContentNodeImpl(node);
      }
      else if (type == CONTENT_NODE_ATTRIBUTE) {
        return new X8lContentNodeAttributeImpl(node);
      }
      else if (type == CONTENT_NODE_CHILDREN_AREA) {
        return new X8lContentNodeChildrenAreaImpl(node);
      }
      else if (type == CONTENT_NODE_HEAD_AREA) {
        return new X8lContentNodeHeadAreaImpl(node);
      }
      else if (type == LEFT_BRACE) {
        return new X8lLeftBraceImpl(node);
      }
      else if (type == RIGHT_BRACE) {
        return new X8lRightBraceImpl(node);
      }
      else if (type == TEXT_NODE) {
        return new X8lTextNodeImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
