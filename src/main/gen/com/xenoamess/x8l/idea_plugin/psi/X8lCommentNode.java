// This is a generated file. Not intended for manual editing.
package com.xenoamess.x8l.idea_plugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiLiteral;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.tree.IElementType;

public interface X8lCommentNode extends PsiComment, PsiLiteral {

  @NotNull
  X8lCommentNodeContent getCommentNodeContent();

  IElementType getTokenType();

  String getValue();

  ItemPresentation getPresentation();

}
