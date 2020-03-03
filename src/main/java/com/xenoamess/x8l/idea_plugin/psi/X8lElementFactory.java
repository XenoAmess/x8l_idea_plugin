package com.xenoamess.x8l.idea_plugin.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import com.xenoamess.x8l.idea_plugin.X8lFileType;
import com.xenoamess.x8l.idea_plugin.psi.X8lFile;
import com.xenoamess.x8l.idea_plugin.psi.X8lProperty;

public class X8lElementFactory {
    public static X8lProperty createProperty(Project project, String name) {
        final X8lFile file = createFile(project, name);
        return (X8lProperty) file.getFirstChild();
    }

    public static X8lFile createFile(Project project, String text) {
        String name = "dummy.x8l";
        return (X8lFile) PsiFileFactory.getInstance(project).createFileFromText(name, X8lFileType.INSTANCE, text);
    }

    public static X8lProperty createProperty(Project project, String name, String value) {
        final X8lFile file = createFile(project, name + " = " + value);
        return (X8lProperty) file.getFirstChild();
    }

    public static PsiElement createCRLF(Project project) {
        final X8lFile file = createFile(project, "\n");
        return file.getFirstChild();
    }
}