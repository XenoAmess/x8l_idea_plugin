package com.xenoamess.x8l.idea_plugin;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import static com.xenoamess.x8l.idea_plugin.X8lAnnotator.X8L_PREFIX_STR;
import static com.xenoamess.x8l.idea_plugin.X8lAnnotator.X8L_SEPARATOR_STR;

public class X8lReferenceContributor extends PsiReferenceContributor {
    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(PsiLiteralExpression.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element,
                                                                 @NotNull ProcessingContext context) {
                        PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
                        String value = literalExpression.getValue() instanceof String ?
                                (String) literalExpression.getValue() : null;
                        if ((value != null && value.startsWith(X8L_PREFIX_STR + X8L_SEPARATOR_STR))) {
                            TextRange property = new TextRange(X8L_PREFIX_STR.length() + X8L_SEPARATOR_STR.length() + 1,
                                    value.length() + 1);
                            return new PsiReference[]{new X8lReference(element, property)};
                        }
                        return PsiReference.EMPTY_ARRAY;
                    }
                });
    }
}
