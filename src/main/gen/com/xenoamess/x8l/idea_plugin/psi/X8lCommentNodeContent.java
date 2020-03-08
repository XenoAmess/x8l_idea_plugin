// This is a generated file. Not intended for manual editing.
package com.xenoamess.x8l.idea_plugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteral;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.tree.IElementType;

public interface X8lCommentNodeContent extends PsiLiteral, PsiNameIdentifierOwner {

  IElementType getTokenType();

  PsiElement getNameIdentifier();

  String getValue();

  ItemPresentation getPresentation();

  String getName();

  PsiElement setName(String newName);

}
