//package com.xenoamess.x8l.idea_plugin;
//
//import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
//import com.intellij.lang.ASTNode;
//import com.intellij.openapi.application.ApplicationManager;
//import com.intellij.openapi.command.WriteCommandAction;
//import com.intellij.openapi.editor.Editor;
//import com.intellij.openapi.fileChooser.FileChooser;
//import com.intellij.openapi.fileChooser.FileChooserDescriptor;
//import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
//import com.intellij.openapi.fileEditor.FileEditorManager;
//import com.intellij.openapi.project.Project;
//import com.intellij.openapi.project.ProjectUtil;
//import com.intellij.openapi.vfs.VirtualFile;
//import com.intellij.pom.Navigatable;
//import com.intellij.psi.PsiFile;
//import com.intellij.psi.PsiManager;
//import com.intellij.psi.search.FileTypeIndex;
//import com.intellij.psi.search.GlobalSearchScope;
//import com.intellij.util.IncorrectOperationException;
//import com.xenoamess.x8l.idea_plugin.psi.X8lFile;
//import com.xenoamess.x8l.idea_plugin.psi.X8lProperty;
//import com.xenoamess.x8l.idea_plugin.psi.X8lElementFactory;
//import org.jetbrains.annotations.NotNull;
//
//import java.util.Collection;
//
//public class X8lCreatePropertyQuickFix extends BaseIntentionAction {
//    private String key;
//
//    X8lCreatePropertyQuickFix(String key) {
//        this.key = key;
//    }
//
//    @NotNull
//    @Override
//    public String getText() {
//        return "Create property";
//    }
//
//    @NotNull
//    @Override
//    public String getFamilyName() {
//        return "X8l properties";
//    }
//
//    @Override
//    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
//        return true;
//    }
//
//    @Override
//    public void invoke(@NotNull final Project project, final Editor editor, PsiFile file) throws
//            IncorrectOperationException {
//        ApplicationManager.getApplication().invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                Collection<VirtualFile> virtualFiles =
//                        FileTypeIndex.getFiles(X8lFileType.INSTANCE, GlobalSearchScope.allScope(project));
//                if (virtualFiles.size() == 1) {
//                    createProperty(project, virtualFiles.iterator().next());
//                } else {
//                    final FileChooserDescriptor descriptor =
//                            FileChooserDescriptorFactory.createSingleFileDescriptor(X8lFileType.INSTANCE);
//                    descriptor.setRoots(ProjectUtil.guessProjectDir(project));
//                    final VirtualFile file = FileChooser.chooseFile(descriptor, project, null);
//                    if (file != null) {
//                        createProperty(project, file);
//                    }
//                }
//            }
//        });
//    }
//
//    private void createProperty(final Project project, final VirtualFile file) {
//        WriteCommandAction.writeCommandAction(project).run(() -> {
//            X8lFile simpleFile = (X8lFile) PsiManager.getInstance(project).findFile(file);
//            ASTNode lastChildNode = simpleFile.getNode().getLastChildNode();
//            // TODO: Add another check for CRLF
//            if (lastChildNode != null/* && !lastChildNode.getElementType().equals(X8lTypes.CRLF)*/) {
//                simpleFile.getNode().addChild(X8lElementFactory.createCRLF(project).getNode());
//            }
//            // IMPORTANT: change spaces to escaped spaces or the new node will only have the first word for the key
//            X8lProperty property = X8lElementFactory.createProperty(project, key.replaceAll(" ", "\\\\ "), "");
//            simpleFile.getNode().addChild(property.getNode());
//            ((Navigatable) property.getLastChild().getNavigationElement()).navigate(true);
//            FileEditorManager.getInstance(project).getSelectedTextEditor().getCaretModel().moveCaretRelatively(2, 0, false, false, false);
//        });
//    }
//}