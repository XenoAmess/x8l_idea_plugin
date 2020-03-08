// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.xenoamess.x8l.idea_plugin;

import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.TokenSet;
import com.xenoamess.x8l.X8lTree;
import com.xenoamess.x8l.idea_plugin.psi.X8lTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class X8lFindUsagesProvider implements FindUsagesProvider {
    @Nullable
    @Override
    public WordsScanner getWordsScanner() {
        return new DefaultWordsScanner(new X8lLexerAdapter(),
                TokenSet.create(
                        X8lTypes.CONTENT_NODE_ATTRIBUTE_KEY
                        , X8lTypes.CONTENT_NODE_ATTRIBUTE_KEY_CONTENT_STRING
                        , X8lTypes.CONTENT_NODE_ATTRIBUTE_VALUE
                        , X8lTypes.CONTENT_NODE_ATTRIBUTE_VALUE_CONTENT_STRING
                        , X8lTypes.CONTENT_NODE_ATTRIBUTE
                        , X8lTypes.TEXT_NODE
                        , X8lTypes.TEXT_NODE_CONTENT
                        , X8lTypes.TEXT_NODE_CONTENT_STRING
                ),
                TokenSet.create(
                        X8lTypes.COMMENT_NODE
                        , X8lTypes.COMMENT_NODE_CONTENT
                        , X8lTypes.COMMENT_NODE_CONTENT_STRING
                        , X8lTypes.COMMENT_NODE_LEFT_BRACKET
                        , X8lTypes.COMMENT_NODE_RIGHT_BRACKET
                ),
                TokenSet.EMPTY);
    }

    @Override
    public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
        return psiElement instanceof PsiNamedElement;
    }

    @Nullable
    @Override
    public String getHelpId(@NotNull PsiElement psiElement) {
        return null;
    }

    @NotNull
    @Override
    public String getType(@NotNull PsiElement element) {
        return element.getNode().getElementType().toString();
    }

    @NotNull
    @Override
    public String getDescriptiveName(@NotNull PsiElement element) {
        return X8lTree.untranscode(X8lUtil.getStringFromElement(element));
    }

    @NotNull
    @Override
    public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
        return X8lUtil.getStringFromElement(element);
    }
}