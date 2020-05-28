package com.xenoamess.x8l.idea_plugin;

import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewModelBase;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.intellij.psi.PsiFile;
import com.xenoamess.x8l.psi.X8lFile;
import org.jetbrains.annotations.NotNull;

/**
 * @author XenoAmess
 */
public class X8lStructureViewModel extends StructureViewModelBase implements
        StructureViewModel.ElementInfoProvider {
    public X8lStructureViewModel(PsiFile psiFile) {
        super(psiFile, new X8lStructureViewElement(psiFile));
    }

    @Override
    @NotNull
    public Sorter[] getSorters() {
        return new Sorter[]{Sorter.ALPHA_SORTER};
    }


    @Override
    public boolean isAlwaysShowsPlus(StructureViewTreeElement element) {
        return false;
    }

    @Override
    public boolean isAlwaysLeaf(StructureViewTreeElement element) {
        return element instanceof X8lFile;
    }
}