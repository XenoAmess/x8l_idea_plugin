// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.xenoamess.x8l.idea_plugin.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.xenoamess.x8l.idea_plugin.psi.X8lNamedElement;
import org.jetbrains.annotations.NotNull;

public abstract class X8lNamedElementImpl extends ASTWrapperPsiElement implements X8lNamedElement {
    public X8lNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }
}