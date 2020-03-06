// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.xenoamess.x8l.idea_plugin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.SmartList;
import com.xenoamess.x8l.idea_plugin.psi.X8lFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class X8lUtil {

    public static List<PsiElement> findPsiElements(Project project, String string, IElementType iElementType) {
        List<PsiElement> result = new SmartList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(X8lFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            X8lFile x8lFile = (X8lFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (x8lFile != null) {
                result.addAll(getMostRemoteChildrenOfType(x8lFile, string, iElementType));
            }
        }
        return result;
    }

    /**
     * @param element      base element
     * @param string       text string
     * @param iElementType iElementType, if==null then can be any types.
     * @return
     */
    @NotNull
    public static List<PsiElement> getMostRemoteChildrenOfType(@Nullable PsiElement element, @NotNull String string, @Nullable IElementType iElementType) {
        if (element == null) return Collections.emptyList();
        List<PsiElement> result = null;
        PsiElement[] children = element.getChildren();
        for (PsiElement child : children) {
            List<PsiElement> childResult = getMostRemoteChildrenOfType(child, string, iElementType);
            if (!childResult.isEmpty()) {
                if (result == null) result = new SmartList<>();
                result.addAll(childResult);
            }
        }

        if (result != null) {
            return result;
        }

        for (PsiElement child = element.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (iElementType == null || child.getNode().getElementType().equals(iElementType)) {
                if (string.equals(child.getText())) {
                    if (result == null) result = new SmartList<>();
                    result.add(child);
                }
            }
        }
        return result == null ? Collections.emptyList() : result;
    }

//    public static List<X8lProperty> findProperties(Project project) {
//        List<X8lProperty> result = new ArrayList<X8lProperty>();
//        Collection<VirtualFile> virtualFiles =
//                FileTypeIndex.getFiles(X8lFileType.INSTANCE, GlobalSearchScope.allScope(project));
//        for (VirtualFile virtualFile : virtualFiles) {
//            X8lFile simpleFile = (X8lFile) PsiManager.getInstance(project).findFile(virtualFile);
//            if (simpleFile != null) {
//                X8lProperty[] properties = PsiTreeUtil.getChildrenOfType(simpleFile, X8lProperty.class);
//                if (properties != null) {
//                    Collections.addAll(result, properties);
//                }
//            }
//        }
//        return result;
//    }
}