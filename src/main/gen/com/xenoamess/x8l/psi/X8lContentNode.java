// This is a generated file. Not intended for manual editing.
package com.xenoamess.x8l.psi;

import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.NavigationItem;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public interface X8lContentNode extends PsiNameIdentifierOwner, NavigationItem {

    @NotNull
    X8lContentNodeChildrenArea getContentNodeChildrenArea();

    @NotNull
    X8lContentNodeHeadArea getContentNodeHeadArea();

    IElementType getTokenType();

    PsiElement getNameIdentifier();

    String getValue();

    ItemPresentation getPresentation();

    String getName();

    PsiElement setName(String newName);

}
