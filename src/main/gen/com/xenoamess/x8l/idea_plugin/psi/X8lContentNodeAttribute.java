// This is a generated file. Not intended for manual editing.
package com.xenoamess.x8l.idea_plugin.psi;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface X8lContentNodeAttribute extends PsiNameIdentifierOwner {

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
