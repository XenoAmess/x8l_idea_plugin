package com.xenoamess.x8l.idea_plugin.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.xenoamess.x8l.X8lTree;
import com.xenoamess.x8l.dealers.XmlDealer;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author XenoAmess
 */
public class X8lGenerateXmlAction extends AbstractX8lFileAction {

    @Override
    public void doJob(@NotNull X8lTree x8lTree) {
        x8lTree.setLanguageDealer(XmlDealer.INSTANCE);
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
        String genFilePath = originalVirtualFile.getPath() + ".gen.xml";
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
            X8lTree.save(bufferedOutputStream, x8lTree);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LocalFileSystem.getInstance().refreshAndFindFileByIoFile(genFile);
    }
}
