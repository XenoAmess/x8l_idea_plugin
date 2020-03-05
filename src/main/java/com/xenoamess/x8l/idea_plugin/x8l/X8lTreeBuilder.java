package com.xenoamess.x8l.idea_plugin.x8l;

import com.intellij.psi.PsiElement;
import com.xenoamess.x8l.X8lTree;
import com.xenoamess.x8l.idea_plugin.psi.X8lFile;

public class X8lTreeBuilder {
    public static X8lTree buildX8lTreeFromPsi(X8lFile x8lFile) {
        X8lTree x8lTree = new X8lTree();
        for (PsiElement psiElement : x8lFile.getChildren()) {

        }
        return x8lTree;
    }
}
