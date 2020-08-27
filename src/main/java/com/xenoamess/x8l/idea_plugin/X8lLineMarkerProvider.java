package com.xenoamess.x8l.idea_plugin;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.SmartList;
import java.util.Collection;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import static com.xenoamess.x8l.idea_plugin.X8lReference.ALLOWED_CLASS_SET;
import static com.xenoamess.x8l.idea_plugin.X8lReferenceContributor.ifPrimitiveValue;
import static com.xenoamess.x8l.idea_plugin.X8lUtil.findMostRemotePsiElementsIncludingTranscode;
import static com.xenoamess.x8l.idea_plugin.X8lUtil.getStringFromElement;

/**
 * @author XenoAmess
 */
public class X8lLineMarkerProvider extends RelatedItemLineMarkerProvider {

    protected static final IElementType[] I_ELEMENT_TYPES = X8lAnnotator.I_ELEMENT_TYPES;

    @Override
    protected void collectNavigationMarkers(
            @NotNull PsiElement element,
            @NotNull Collection<? super RelatedItemLineMarkerInfo<?>> result
    ) {
        boolean allowed = false;
        for (Class au : ALLOWED_CLASS_SET) {
            if (au.isInstance(element)) {
                allowed = true;
                break;
            }
        }
        if (!allowed) {
            return;
        }

        String string = getStringFromElement(element);

        if (ifIllegalString(element, string)) {
            return;
        }

        if (ifPrimitiveValue(string)) {
            return;
        }

        // Get the X8l language property usage
        Project project = element.getProject();

        List<PsiElement> elements = new SmartList<>();

        for (IElementType iElementType : I_ELEMENT_TYPES) {
            elements.addAll(
                    findMostRemotePsiElementsIncludingTranscode(project, string, iElementType)
            );
        }

        if (!elements.isEmpty()) {
            List<PsiElement> newElements = new SmartList<>();

            for (PsiElement psiElement : elements) {
                if (psiElement != element) {
                    newElements.add(psiElement);
                }
            }

            // Add the property to a collection of line marker info
            NavigationGutterIconBuilder<PsiElement> builder =
                    NavigationGutterIconBuilder.create(X8lDataCenter.X8L_LANGUAGE_ICON)
                            .setTargets(newElements)
                            .setTooltipText("Navigate to X8l language elements");
            result.add(builder.createLineMarkerInfo(element));
        }
    }

    protected static boolean ifIllegalString(PsiElement element, String string) {
        return X8lAnnotator.ifIllegalString(element, string);
    }
}