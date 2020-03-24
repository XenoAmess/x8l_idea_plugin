package com.xenoamess.x8l.idea_plugin;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;


public class X8lReferenceContributor extends PsiReferenceContributor {
    public static final PsiReferenceProvider PSI_REFERENCE_PROVIDER = new PsiReferenceProvider() {
        @NotNull
        @Override
        public PsiReference[] getReferencesByElement(@NotNull PsiElement element,
                                                     @NotNull ProcessingContext context) {
            if (element instanceof PsiLiteralValue) {
                PsiLiteralValue literalExpression = (PsiLiteralValue) element;
                String value = literalExpression.getValue() instanceof String ?
                        literalExpression.getValue().toString() : null;
                if (value != null
                ) {
                    boolean isPrimitive = false;
                    try {
                        Double.parseDouble(value);
                        isPrimitive = true;
                    } catch (Exception e) {
                    }
                    if (!isPrimitive) {
                        try {
                            Boolean.parseBoolean(value);
                            isPrimitive = true;
                        } catch (Exception e) {
                        }
                    }

                    return isPrimitive ?
                            PsiReference.EMPTY_ARRAY :
                            new PsiReference[]{
                                    new X8lReference(element, new TextRange(1, 1 + value.length()))
                            };
                }
            }
            return PsiReference.EMPTY_ARRAY;
        }
    };

    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(
                PlatformPatterns.psiElement(),
                PSI_REFERENCE_PROVIDER
        );
    }
}
