// This is a generated file. Not intended for manual editing.
package com.xenoamess.x8l.idea_plugin.psi;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralValue;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public interface X8lCommentNode extends PsiComment, PsiLiteralValue, PsiNameIdentifierOwner {

    @NotNull
    X8lCommentNodeContent getCommentNodeContent();

    IElementType getTokenType();

    PsiElement getNameIdentifier();

    String getValue();

    ItemPresentation getPresentation();

    String getName();

    PsiElement setName(String newName);

}
