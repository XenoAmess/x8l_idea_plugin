// This is a generated file. Not intended for manual editing.
package com.xenoamess.x8l.psi;

import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.NavigationItem;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.tree.IElementType;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface X8lContentNodeAttribute extends PsiNameIdentifierOwner, NavigationItem {

    @NotNull
    X8lContentNodeAttributeKey getContentNodeAttributeKey();

    @Nullable
    X8lContentNodeAttributeValue getContentNodeAttributeValue();

    @NotNull
    List<X8lWhiteSpace> getWhiteSpaceList();

    IElementType getTokenType();

    PsiElement getNameIdentifier();

    //WARNING: getKey(...) is skipped
    //matching getKey(X8lContentNodeAttribute, ...)
    //methods are not found in X8lPsiImplUtil

    String getValue();

    ItemPresentation getPresentation();

    String getName();

    PsiElement setName(String newName);

}
