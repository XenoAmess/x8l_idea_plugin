package com.xenoamess.x8l.psi;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

/**
 * @author XenoAmess
 */
public class X8lPsiElement extends com.intellij.extapi.psi.ASTWrapperPsiElement {

    public X8lPsiElement(@NotNull ASTNode node) {
        super(node);
    }
}
