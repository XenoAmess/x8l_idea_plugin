// This is a generated file. Not intended for manual editing.
package com.xenoamess.x8l.psi;

import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.NavigationItem;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralValue;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.tree.IElementType;

public interface X8lTextNodeContent extends PsiLiteralValue, PsiNameIdentifierOwner, NavigationItem {

    IElementType getTokenType();

    PsiElement getNameIdentifier();

    String getValue();

    ItemPresentation getPresentation();

    String getName();

    PsiElement setName(String name);

}
