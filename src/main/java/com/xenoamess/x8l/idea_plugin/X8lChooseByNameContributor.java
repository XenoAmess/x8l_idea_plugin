package com.xenoamess.x8l.idea_plugin;

import com.intellij.navigation.ChooseByNameContributor;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XenoAmess
 */
public class X8lChooseByNameContributor implements ChooseByNameContributor {
    @NotNull
    @Override
    public String[] getNames(Project project, boolean includeNonProjectItems) {
        // TODO: include non project items
        List<PsiElement> psiElements = X8lUtil.findAllPsiElements(project);
        List<String> names = new ArrayList<>();
        for (PsiElement psiElement : psiElements) {
            if (psiElement instanceof PsiNameIdentifierOwner) {
                names.add(((PsiNameIdentifierOwner) psiElement).getName());
            }
        }
        return names.toArray(new String[0]);
    }

    @NotNull
    @Override
    public NavigationItem[] getItemsByName(String name, String pattern, Project project,
                                           boolean includeNonProjectItems) {
        // TODO: include non project items
        List<PsiElement> psiElements = X8lUtil.findMostRemotePsiElementsIncludingTranscode(project, name, null);
        List<NavigationItem> navigationItems = new ArrayList<>();
        for (PsiElement psiElement : psiElements) {
            PsiElement nowPsiElement = psiElement;
            while (!(nowPsiElement instanceof PsiNameIdentifierOwner && nowPsiElement instanceof NavigationItem)) {
                nowPsiElement = nowPsiElement.getParent();
                if (nowPsiElement == null) {
                    break;
                }
            }
            if (nowPsiElement != null) {
                navigationItems.add((NavigationItem) nowPsiElement);
            }
        }
        return navigationItems.toArray(new NavigationItem[0]);
    }
}