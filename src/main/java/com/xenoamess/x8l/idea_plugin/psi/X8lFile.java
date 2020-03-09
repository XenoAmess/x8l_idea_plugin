package com.xenoamess.x8l.idea_plugin.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.xenoamess.x8l.idea_plugin.X8lFileType;
import com.xenoamess.x8l.idea_plugin.X8lLanguage;
import org.jetbrains.annotations.NotNull;

public class X8lFile extends PsiFileBase {
//    private X8lTree x8lTree = null;

    public X8lFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, X8lLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return X8lFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "X8l File";
    }

//    public X8lTree getX8lTree() {
//        if (this.x8lTree == null) {
//            this.x8lTree = buildX8lTreeFromPsi(this);
//        }
//        return this.x8lTree;
//    }
}
