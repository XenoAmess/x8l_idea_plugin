package com.xenoamess.x8l.idea_plugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

public class X8lBeautifyAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project currentProject = event.getProject();
        StringBuffer dlgMsg = new StringBuffer("Testing!! : " + event.getPresentation().getText() + " Selected!");
        String dlgTitle = event.getPresentation().getDescription();

        PsiElement psiElement = event.getData(CommonDataKeys.PSI_ELEMENT);
        PsiFile psiFile = event.getData(CommonDataKeys.PSI_FILE);

        dlgMsg.append(psiElement.toString());
        dlgMsg.append(psiFile.toString());
        Messages.showMessageDialog(currentProject, dlgMsg.toString(), dlgTitle, Messages.getInformationIcon());
    }
}
