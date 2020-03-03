// This is a generated file. Not intended for manual editing.
package com.xenoamess.x8l.idea_plugin.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.xenoamess.x8l.idea_plugin.psi.impl.*;

public interface X8lTypes {

  IElementType PROPERTY = new X8lElementType("PROPERTY");

  IElementType COMMENT = new X8lTokenType("COMMENT");
  IElementType CRLF = new X8lTokenType("CRLF");
  IElementType KEY = new X8lTokenType("KEY");
  IElementType SEPARATOR = new X8lTokenType("SEPARATOR");
  IElementType VALUE = new X8lTokenType("VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == PROPERTY) {
        return new X8lPropertyImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
