package com.xenoamess.x8l.idea_plugin;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.xenoamess.x8l.idea_plugin.psi.X8lProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class X8lReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {
    private String key;

    public X8lReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
        key = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        Project project = myElement.getProject();
        final List<X8lProperty> properties = X8lUtil.findProperties(project, key);
        List<ResolveResult> results = new ArrayList<>();
        for (X8lProperty property : properties) {
            results.add(new PsiElementResolveResult(property));
        }
        return results.toArray(new ResolveResult[results.size()]);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        Project project = myElement.getProject();
        List<X8lProperty> properties = X8lUtil.findProperties(project);
        List<LookupElement> variants = new ArrayList<LookupElement>();
        for (final X8lProperty property : properties) {
            if (property.getKey() != null && property.getKey().length() > 0) {
                variants.add(LookupElementBuilder
                        .create(property).withIcon(X8lDataCenter.X8L_LANGUAGE_ICON)
                        .withTypeText(property.getContainingFile().getName())
                );
            }
        }
        return variants.toArray();
    }
}