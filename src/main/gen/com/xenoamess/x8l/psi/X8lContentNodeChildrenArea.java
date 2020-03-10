// This is a generated file. Not intended for manual editing.
package com.xenoamess.x8l.psi;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface X8lContentNodeChildrenArea extends PsiNameIdentifierOwner {

    @NotNull
    List<X8lCommentNode> getCommentNodeList();

    @NotNull
    List<X8lContentNode> getContentNodeList();

    @NotNull
    List<X8lTextNode> getTextNodeList();

    IElementType getTokenType();

    PsiElement getNameIdentifier();

    String getValue();

    ItemPresentation getPresentation();

    String getName();

    PsiElement setName(String name);

}
