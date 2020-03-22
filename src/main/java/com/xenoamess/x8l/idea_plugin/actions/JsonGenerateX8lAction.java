package com.xenoamess.x8l.idea_plugin.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.xenoamess.x8l.X8lTree;
import com.xenoamess.x8l.dealers.JsonDealer;
import com.xenoamess.x8l.dealers.X8lDealer;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;

public class JsonGenerateX8lAction extends X8lFileAction {
    public static final String ORIGINAL_PATH_END = ".json";

    @Override
    public void update(AnActionEvent event) {
        PsiFile psiFile = event.getData(CommonDataKeys.PSI_FILE);
        event.getPresentation().setEnabledAndVisible(
                psiFile != null && psiFile.getVirtualFile().getName().endsWith(ORIGINAL_PATH_END)
        );
    }

    @Override
    @Nullable
    public X8lTree loadX8lTreeFromEvent(@NotNull AnActionEvent event) {
        PsiFile psiFile = event.getData(CommonDataKeys.PSI_FILE);
        if (psiFile == null) {
            return null;
        }

        if (!psiFile.getVirtualFile().getName().endsWith(ORIGINAL_PATH_END)) {
            return null;
        }

        X8lTree result = null;
        try (
                InputStream inputStream = psiFile.getVirtualFile().getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)
        ) {
            result = X8lTree.load(bufferedInputStream, JsonDealer.INSTANCE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void saveX8lTreeToFile(@Nullable X8lTree x8lTree, @NotNull AnActionEvent event) {
        if (x8lTree == null) {
            return;
        }

        PsiFile psiFile = event.getData(CommonDataKeys.PSI_FILE);

        if (psiFile == null) {
            return;
        }

        VirtualFile originalVirtualFile = psiFile.getVirtualFile();
        String genFilePath = originalVirtualFile.getPath() + ".gen.x8l";
        File genFile = null;
        boolean result = true;
        try {
            genFile = new File(genFilePath);
            genFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            result = false;
        }

        if (!result) {
            return;
        }

        try (
                OutputStream outputStream = FileUtils.openOutputStream(genFile);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream)
        ) {
            x8lTree.setLanguageDealer(X8lDealer.INSTANCE);
            X8lTree.save(bufferedOutputStream, x8lTree);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LocalFileSystem.getInstance().refreshAndFindFileByIoFile(genFile);
    }

    @Override
    public void doJob(@NotNull X8lTree x8lTree) {
        //do nothing
    }
}
