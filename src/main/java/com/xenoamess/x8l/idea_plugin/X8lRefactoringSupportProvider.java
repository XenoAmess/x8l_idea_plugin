package com.xenoamess.x8l.idea_plugin;

import com.intellij.lang.ASTNode;
import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author XenoAmess
 */
public class X8lRefactoringSupportProvider extends RefactoringSupportProvider {
    protected static final IElementType[] I_ELEMENT_TYPES = X8lAnnotator.I_ELEMENT_TYPES;

    @Override
    public boolean isMemberInplaceRenameAvailable(@NotNull PsiElement elementToRename, @Nullable PsiElement context) {
        ASTNode node = elementToRename.getNode();
        if (node == null) {
            return false;
        }
        IElementType type = node.getElementType();
        for (IElementType au : I_ELEMENT_TYPES) {
            if (au.equals(type)) {
                return true;
            }
        }
        return false;
    }
}