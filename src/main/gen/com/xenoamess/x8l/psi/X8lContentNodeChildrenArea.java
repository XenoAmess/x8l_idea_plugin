// This is a generated file. Not intended for manual editing.
package com.xenoamess.x8l.psi;

import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.NavigationItem;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.tree.IElementType;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public interface X8lContentNodeChildrenArea extends PsiNameIdentifierOwner, NavigationItem {

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
