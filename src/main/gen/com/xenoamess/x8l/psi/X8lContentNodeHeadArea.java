// This is a generated file. Not intended for manual editing.
package com.xenoamess.x8l.psi;

import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.NavigationItem;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface X8lContentNodeHeadArea extends PsiNameIdentifierOwner, NavigationItem {

    @NotNull
    List<X8lContentNodeAttribute> getContentNodeAttributeList();

    @NotNull
    List<X8lWhiteSpace> getWhiteSpaceList();

    IElementType getTokenType();

    PsiElement getNameIdentifier();

    String getValue();

    ItemPresentation getPresentation();

    String getName();

    PsiElement setName(String newName);

}
