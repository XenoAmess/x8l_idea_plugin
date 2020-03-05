// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.xenoamess.x8l.idea_plugin;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.xenoamess.x8l.idea_plugin.psi.X8lCommentNode;
import com.xenoamess.x8l.idea_plugin.psi.X8lContentNode;
import com.xenoamess.x8l.idea_plugin.psi.X8lFile;
import com.xenoamess.x8l.idea_plugin.psi.X8lTextNode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class X8lStructureViewElement implements StructureViewTreeElement, SortableTreeElement {
    private NavigatablePsiElement myElement;

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

    @Override
    public TreeElement[] getChildren() {
        if (myElement instanceof X8lFile) {
            List<PsiElement> properties = PsiTreeUtil.getChildrenOfAnyType(myElement, X8lContentNode.class, X8lTextNode.class, X8lCommentNode.class);
            List<TreeElement> treeElements = new ArrayList<TreeElement>(properties.size());
            for (PsiElement property : properties) {
                treeElements.add(new X8lStructureViewElement((NavigatablePsiElement) property));
            }
            return treeElements.toArray(new TreeElement[treeElements.size()]);
        }
        return EMPTY_ARRAY;
    }
}