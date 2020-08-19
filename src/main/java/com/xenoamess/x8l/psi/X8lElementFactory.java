package com.xenoamess.x8l.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;
import com.xenoamess.x8l.idea_plugin.X8lFileType;
import java.util.Objects;

/**
 * @author XenoAmess
 */
public class X8lElementFactory {
    public static X8lFile createFile(Project project, String text) {
        String name = "dummy.x8l";
        return (X8lFile) PsiFileFactory.getInstance(project).createFileFromText(name, X8lFileType.INSTANCE, text);
    }

    public static X8lContentNodeAttribute createX8lContentNodeAttribute(Project project, String name) {
        String fileContent = "<" + name + ">>";
        return Objects.requireNonNull(createFile(project, fileContent)
                .findChildByClass(X8lRootNodeChildrenArea.class))
                .getContentNodeChildrenArea()
                .getContentNodeList()
                .get(0)
                .getContentNodeHeadArea()
                .getContentNodeAttributeList()
                .get(0);
    }

    public static X8lCommentNode createX8lCommentNode(Project project, String name) {
        //noinspection UnnecessaryLocalVariable
        String fileContent = name;
        return Objects.requireNonNull(createFile(project, fileContent)
                .findChildByClass(X8lRootNodeChildrenArea.class))
                .getContentNodeChildrenArea()
                .getCommentNodeList()
                .get(0);
    }

    public static X8lCommentNodeContent createX8lCommentNodeContent(Project project, String name) {
        String fileContent = "<<" + name + ">";
        return Objects.requireNonNull(createFile(project, fileContent)
                .findChildByClass(X8lRootNodeChildrenArea.class))
                .getContentNodeChildrenArea()
                .getCommentNodeList()
                .get(0)
                .getCommentNodeContent();
    }

//    public static X8lContentNodeAttribute createX8lContentNodeAttribute(Project project, String name, String value) {
//        String fileContent = "<" + name + " = " + value + ">>";
//        final X8lFile file = createFile(project, fileContent);
//        //todo fix it!
//        return (X8lContentNodeAttribute) file.getFirstChild();
//    }


//    public static PsiElement createCRLF(Project project) {
//        final X8lFile file = createFile(project, "\n");
//        return file.getFirstChild();
//    }

    public static X8lContentNodeAttributeValue createX8lContentNodeAttributeValue(Project project, String name) {
        String fileContent = "<a=" + name + ">>";
        return Objects.requireNonNull(createFile(project, fileContent)
                .findChildByClass(X8lRootNodeChildrenArea.class))
                .getContentNodeChildrenArea()
                .getContentNodeList()
                .get(0)
                .getContentNodeHeadArea()
                .getContentNodeAttributeList()
                .get(0)
                .getContentNodeAttributeValue();
    }

    public static X8lContentNodeAttributeKey createX8lContentNodeAttributeKey(Project project, String name) {
        String fileContent = "<" + name + ">>";
        return Objects.requireNonNull(createFile(project, fileContent)
                .findChildByClass(X8lRootNodeChildrenArea.class))
                .getContentNodeChildrenArea()
                .getContentNodeList()
                .get(0)
                .getContentNodeHeadArea()
                .getContentNodeAttributeList()
                .get(0)
                .getContentNodeAttributeKey();
    }

    public static X8lTextNode createX8lTextNode(Project project, String name) {
        //noinspection UnnecessaryLocalVariable
        String fileContent = name;
        return Objects.requireNonNull(createFile(project, fileContent)
                .findChildByClass(X8lRootNodeChildrenArea.class))
                .getContentNodeChildrenArea()
                .getTextNodeList()
                .get(0);
    }

    public static X8lTextNodeContent createX8lTextNodeContent(Project project, String name) {
        return createX8lTextNode(project, name).getTextNodeContent();
    }

    public static X8lContentNode createX8lContentNode(Project project, String name) {
        //noinspection UnnecessaryLocalVariable
        String fileContent = name;
        return Objects.requireNonNull(createFile(project, fileContent)
                .findChildByClass(X8lRootNodeChildrenArea.class))
                .getContentNodeChildrenArea()
                .getContentNodeList()
                .get(0);
    }

    public static X8lContentNodeHeadArea createX8lContentNodeHeadArea(Project project, String name) {
        String fileContent = "<" + name + ">>";
        return Objects.requireNonNull(createFile(project, fileContent)
                .findChildByClass(X8lRootNodeChildrenArea.class))
                .getContentNodeChildrenArea()
                .getContentNodeList().get(0)
                .getContentNodeHeadArea();
    }

    public static X8lContentNodeChildrenArea createX8lContentNodeChildrenArea(Project project, String name) {
        String fileContent = "<>" + name + ">";
        return Objects.requireNonNull(createFile(project, fileContent)
                .findChildByClass(X8lRootNodeChildrenArea.class))
                .getContentNodeChildrenArea()
                .getContentNodeList().get(0)
                .getContentNodeChildrenArea();
    }
}