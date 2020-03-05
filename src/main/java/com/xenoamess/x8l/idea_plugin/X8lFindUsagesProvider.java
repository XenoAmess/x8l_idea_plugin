//// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
//
//package com.xenoamess.x8l.idea_plugin;
//
//import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
//import com.intellij.lang.cacheBuilder.WordsScanner;
//import com.intellij.lang.findUsages.FindUsagesProvider;
//import com.intellij.psi.PsiElement;
//import com.intellij.psi.PsiNamedElement;
//import com.intellij.psi.tree.TokenSet;
//import com.xenoamess.x8l.idea_plugin.psi.X8lProperty;
//import com.xenoamess.x8l.idea_plugin.psi.X8lTypes;
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//
//public class X8lFindUsagesProvider implements FindUsagesProvider {
//    @Nullable
//    @Override
//    public WordsScanner getWordsScanner() {
//        return new DefaultWordsScanner(new X8lLexerAdapter(),
//                TokenSet.create(X8lTypes.KEY),
//                TokenSet.create(X8lTypes.COMMENT),
//                TokenSet.EMPTY);
//    }
//
//    @Override
//    public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
//        return psiElement instanceof PsiNamedElement;
//    }
//
//    @Nullable
//    @Override
//    public String getHelpId(@NotNull PsiElement psiElement) {
//        return null;
//    }
//
//    @NotNull
//    @Override
//    public String getType(@NotNull PsiElement element) {
//        if (element instanceof X8lProperty) {
//            return "simple property";
//        } else {
//            return "";
//        }
//    }
//
//    @NotNull
//    @Override
//    public String getDescriptiveName(@NotNull PsiElement element) {
//        if (element instanceof X8lProperty) {
//            return ((X8lProperty) element).getKey();
//        } else {
//            return "";
//        }
//    }
//
//    @NotNull
//    @Override
//    public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
//        if (element instanceof X8lProperty) {
//            return ((X8lProperty) element).getKey() + X8lAnnotator.X8L_SEPARATOR_STR + ((X8lProperty) element).getValue();
//        } else {
//            return "";
//        }
//    }
//}