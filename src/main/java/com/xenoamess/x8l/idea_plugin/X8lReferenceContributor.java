package com.xenoamess.x8l.idea_plugin;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralValue;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceContributor;
import com.intellij.psi.PsiReferenceProvider;
import com.intellij.psi.PsiReferenceRegistrar;
import com.intellij.util.ProcessingContext;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

/**
 * @author XenoAmess
 */
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
                if (value != null) {
                    return ifPrimitiveValue(value) ?
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

    public static boolean ifPrimitiveValue(@NotNull String value) {
        if (StringUtils.isNumeric(value)) {
            return true;
        }
        if (StringUtils.isNumeric(value)) {
            return true;
        }
        try {
            double d = Double.parseDouble(value);
            if (!Double.isNaN(d)) {
                return true;
            }
        } catch (Exception ignored) {
            //do nothing
        }
        return StringUtils.equalsAnyIgnoreCase(value, "true", "false");
    }
}
