//// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
//
//package com.xenoamess.x8l.idea_plugin;
//
//import com.intellij.openapi.project.Project;
//import com.intellij.openapi.vfs.VirtualFile;
//import com.intellij.psi.PsiManager;
//import com.intellij.psi.search.FileTypeIndex;
//import com.intellij.psi.search.GlobalSearchScope;
//import com.intellij.psi.util.PsiTreeUtil;
//import com.xenoamess.x8l.idea_plugin.psi.X8lFile;
//import com.xenoamess.x8l.idea_plugin.psi.X8lProperty;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//
//public class X8lUtil {
//
//    // Searches the entire project for X8l language files with instances of the X8l property with the given key
//    public static List<X8lProperty> findProperties(Project project, String key) {
//        List<X8lProperty> result = new ArrayList<>();
//        Collection<VirtualFile> virtualFiles =
//                FileTypeIndex.getFiles(X8lFileType.INSTANCE, GlobalSearchScope.allScope(project));
//        for (VirtualFile virtualFile : virtualFiles) {
//            X8lFile simpleFile = (X8lFile) PsiManager.getInstance(project).findFile(virtualFile);
//            if (simpleFile != null) {
//                X8lProperty[] properties = PsiTreeUtil.getChildrenOfType(simpleFile, X8lProperty.class);
//                if (properties != null) {
//                    for (X8lProperty property : properties) {
//                        if (key.equals(property.getKey())) {
//                            result.add(property);
//                        }
//                    }
//                }
//            }
//        }
//        return result;
//    }
//
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
//}