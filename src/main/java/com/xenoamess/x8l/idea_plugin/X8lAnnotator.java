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

import static com.xenoamess.x8l.dealers.JsonDealer.ARRAY_ID_ATTRIBUTE;
import static com.xenoamess.x8l.idea_plugin.X8lSyntaxHighlighter.getTokenHighlightsStatic;
import static com.xenoamess.x8l.idea_plugin.X8lUtil.getStringFromElement;


public class X8lAnnotator implements Annotator {
    protected static final IElementType[] I_ELEMENT_TYPES = new IElementType[]{
            X8lTypes.CONTENT_NODE_ATTRIBUTE_KEY,
            X8lTypes.CONTENT_NODE_ATTRIBUTE_VALUE,
            X8lTypes.TEXT_NODE_CONTENT,
            X8lTypes.COMMENT_NODE_CONTENT,
            X8lTypes.COMMENT_NODE,
            X8lTypes.CONTENT_NODE_ATTRIBUTE,
            X8lTypes.CONTENT_NODE_HEAD_AREA,
            X8lTypes.CONTENT_NODE_CHILDREN_AREA,
            X8lTypes.CONTENT_NODE,
    };

    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
//        // Ensure the Psi Element is an expression
//        if (!(element instanceof PsiLiteralExpression)) return;
//
//        // Ensure the Psi element contains a string that starts with the key and separator
//        PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
//        String string = literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;

        String string = getStringFromElement(element);

        if (ifIllegalString(element, string)) {
            return;
        }

        for (IElementType iElementType : I_ELEMENT_TYPES) {
            if (tryAnnotate(holder, element, string, iElementType)) {
                return;
            }
        }
    }

    public static boolean tryAnnotate(@NotNull AnnotationHolder holder, PsiElement element, String string, IElementType iElementType) {
        Project project = element.getProject();
        List<PsiElement> properties = X8lUtil.findMostRemotePsiElementsIncludingTranscode(project, string, iElementType);
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

    protected static boolean ifIllegalString(PsiElement element, String string) {
        if (StringUtils.isBlank(string)) {
            return true;
        }

        if (!(element instanceof PsiLiteralExpression) && ARRAY_ID_ATTRIBUTE.equals(string)) {
            return true;
        }
        return false;
    }
}