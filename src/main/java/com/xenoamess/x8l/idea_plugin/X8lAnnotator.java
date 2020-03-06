// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.xenoamess.x8l.idea_plugin;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import com.intellij.psi.tree.IElementType;
import com.xenoamess.x8l.idea_plugin.psi.X8lTypes;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.xenoamess.x8l.idea_plugin.X8lSyntaxHighlighter.getTokenHighlightsStatic;


public class X8lAnnotator implements Annotator {

    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        // Ensure the Psi Element is an expression
        if (!(element instanceof PsiLiteralExpression)) return;

        // Ensure the Psi element contains a string that starts with the key and separator
        PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
        String string = literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;
        if (StringUtils.isBlank(string)) {
            return;
        }
        IElementType[] iElementTypes = new IElementType[]{
                X8lTypes.KEY,
                X8lTypes.VALUE,
                X8lTypes.TEXT_NODE_CONTENT,
                X8lTypes.COMMENT_NODE_CONTENT,
        };
        for (IElementType iElementType : iElementTypes) {
            if (tryAnnotate(holder, element, string, iElementType)) {
                return;
            }
        }
    }

    public static boolean tryAnnotate(@NotNull AnnotationHolder holder, PsiElement element, String string, IElementType iElementType) {
        Project project = element.getProject();
        List<PsiElement> properties = X8lUtil.findPsiElements(project, string, iElementType);
        if (properties.isEmpty()) {
            return false;
        }
        // Found at least one property
        Annotation annotation = holder.createInfoAnnotation(element.getTextRange(), null);
        for (TextAttributesKey textAttributesKey : getTokenHighlightsStatic(iElementType)) {
            annotation.setTextAttributes(textAttributesKey);
        }
        return true;
    }
}