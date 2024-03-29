package com.xenoamess.x8l.idea_plugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.xenoamess.x8l.X8lTree;
import com.xenoamess.x8l.psi.X8lFile;
import com.xenoamess.x8l.psi.X8lTreeBuildingUtil;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author XenoAmess
 */
public abstract class AbstractX8lFileAction extends AnAction {
    @Override
    public void update(AnActionEvent event) {
        // Set the availability based on whether a project is open
        PsiElement psiElement = event.getData(CommonDataKeys.PSI_ELEMENT);
        event.getPresentation().setEnabledAndVisible(psiElement instanceof X8lFile);
    }

    @Nullable
    public X8lTree loadX8lTreeFromEvent(@NotNull AnActionEvent event) {

        PsiElement psiElement = event.getData(CommonDataKeys.PSI_ELEMENT);

        if (!(psiElement instanceof X8lFile)) {
            return null;
        }

        PsiFile psiFile = event.getData(CommonDataKeys.PSI_FILE);

        if (psiFile == null) {
            return null;
        }

        return X8lTreeBuildingUtil.buildX8lTreeFromX8lFile((X8lFile) psiElement);
    }

    public void saveX8lTreeToFile(@Nullable X8lTree x8lTree, @NotNull AnActionEvent event) {
        if (x8lTree == null) {
            return;
        }

        PsiFile psiFile = event.getData(CommonDataKeys.PSI_FILE);

        if (psiFile == null) {
            return;
        }

        VirtualFile file = psiFile.getVirtualFile();

        try (
                OutputStream outputStream = file.getOutputStream(null);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream)
        ) {
            X8lTree.save(bufferedOutputStream, x8lTree);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        X8lTree x8lTree = this.loadX8lTreeFromEvent(event);
        if (x8lTree == null) {
            return;
        }
        doJob(x8lTree);
        ApplicationManager.getApplication().runWriteAction(
                () -> AbstractX8lFileAction.this.saveX8lTreeToFile(x8lTree, event)
        );
    }

    /**
     * what should this action do with the x8lTree
     * @param x8lTree x8lTree gotten from the event.
     */
    public abstract void doJob(@NotNull X8lTree x8lTree);
}
