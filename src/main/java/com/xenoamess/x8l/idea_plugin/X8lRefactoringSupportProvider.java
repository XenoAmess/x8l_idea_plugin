//// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
//
//package com.xenoamess.x8l.idea_plugin;
//
//import com.intellij.lang.refactoring.RefactoringSupportProvider;
//import com.intellij.psi.PsiElement;
//import com.xenoamess.x8l.idea_plugin.psi.X8lProperty;
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//
//public class X8lRefactoringSupportProvider extends RefactoringSupportProvider {
//    @Override
//    public boolean isMemberInplaceRenameAvailable(@NotNull PsiElement elementToRename, @Nullable PsiElement context) {
//        return (elementToRename instanceof X8lProperty);
//    }
//}