package com.xenoamess.x8l.idea_plugin;

import com.intellij.navigation.ChooseByNameContributor;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import static com.xenoamess.x8l.idea_plugin.X8lUtil.createSet;

/**
 * @author XenoAmess
 */
public class X8lChooseByNameContributor implements ChooseByNameContributor {
    @NotNull
    @Override
    public String[] getNames(Project project, boolean includeNonProjectItems) {
        // TODO: include non project items
        List<PsiElement> psiElements = X8lUtil.findAllPsiElementsForClass(project,
                createSet(PsiNameIdentifierOwner.class));
        List<String> names = new ArrayList<>();
        for (PsiElement psiElement : psiElements) {
            names.add(((PsiNameIdentifierOwner) psiElement).getName());
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