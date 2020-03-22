package com.xenoamess.x8l.idea_plugin;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class X8lStructureViewElement implements StructureViewTreeElement, SortableTreeElement {
    private final NavigatablePsiElement myElement;

    public X8lStructureViewElement(NavigatablePsiElement element) {
        this.myElement = element;
    }

    @Override
    public Object getValue() {
        return myElement;
    }

    @Override
    public void navigate(boolean requestFocus) {
        myElement.navigate(requestFocus);
    }

    @Override
    public boolean canNavigate() {
        return myElement.canNavigate();
    }

    @Override
    public boolean canNavigateToSource() {
        return myElement.canNavigateToSource();
    }

    @NotNull
    @Override
    public String getAlphaSortKey() {
        String name = myElement.getName();
        return name != null ? name : "";
    }

    @NotNull
    @Override
    public ItemPresentation getPresentation() {
        ItemPresentation presentation = myElement.getPresentation();
        return presentation != null ? presentation : new PresentationData();
    }

    @NotNull
    @Override
    public TreeElement[] getChildren() {
        PsiElement[] properties = myElement.getChildren();
        List<TreeElement> treeElements = new ArrayList<>(properties.length);
        for (PsiElement property : properties) {
            if (property == myElement) {
                continue;
            }
            if (property instanceof NavigatablePsiElement) {
                treeElements.add(new X8lStructureViewElement((NavigatablePsiElement) property));
            }
        }
        return treeElements.toArray(new TreeElement[0]);
    }
}