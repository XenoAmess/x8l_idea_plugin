// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.xenoamess.x8l.idea_plugin.psi.impl;

import com.intellij.lang.ASTNode;
import com.xenoamess.x8l.X8lTree;
import com.xenoamess.x8l.idea_plugin.psi.X8lRootElement;
import org.jetbrains.annotations.NotNull;

public abstract class X8lRootElementImpl extends X8lNamedElementImpl implements X8lRootElement {
    private final X8lTree x8lTree;

    public X8lRootElementImpl(@NotNull ASTNode node) {
        super(node);
        this.x8lTree = X8lTree.load(this.getText());
    }

    @Override
    public X8lTree getPrimitiveX8lTree() {
        return x8lTree;
    }
}