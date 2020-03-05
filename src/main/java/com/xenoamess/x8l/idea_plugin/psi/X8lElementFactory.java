package com.xenoamess.x8l.idea_plugin.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import com.xenoamess.x8l.idea_plugin.X8lFileType;

public class X8lElementFactory {
    public static X8lContentNodeAttribute createX8lContentNodeAttribute(Project project, String name) {
        String fileContent = "<" + name + ">>";
        final X8lFile file = createFile(project, fileContent);
        //todo fix it!
        return (X8lContentNodeAttribute) file.getFirstChild();
    }

    public static X8lContentNodeAttribute createX8lContentNodeAttribute(Project project, String name, String value) {
        String fileContent = "<" + name + " = " + value + ">>";
        final X8lFile file = createFile(project, fileContent);
        //todo fix it!
        return (X8lContentNodeAttribute) file.getFirstChild();
    }

    public static X8lFile createFile(Project project, String text) {
        String name = "dummy.x8l";
        return (X8lFile) PsiFileFactory.getInstance(project).createFileFromText(name, X8lFileType.INSTANCE, text);
    }


    public static PsiElement createCRLF(Project project) {
        final X8lFile file = createFile(project, "\n");
        return file.getFirstChild();
    }
}