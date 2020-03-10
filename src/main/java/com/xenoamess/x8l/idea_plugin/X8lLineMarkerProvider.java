package com.xenoamess.x8l.idea_plugin;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.SmartList;
import com.xenoamess.x8l.psi.X8lTypes;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

import static com.xenoamess.x8l.idea_plugin.X8lUtil.findMostRemotePsiElementsIncludingTranscode;
import static com.xenoamess.x8l.idea_plugin.X8lUtil.getStringFromElement;

public class X8lLineMarkerProvider extends RelatedItemLineMarkerProvider {

    protected static final IElementType[] I_ELEMENT_TYPES = new IElementType[]{
            X8lTypes.CONTENT_NODE_ATTRIBUTE_KEY_CONTENT_STRING,
            X8lTypes.CONTENT_NODE_ATTRIBUTE_VALUE_CONTENT_STRING,
            X8lTypes.TEXT_NODE_CONTENT_STRING,
            X8lTypes.COMMENT_NODE_CONTENT_STRING,
            X8lTypes.COMMENT_NODE,
    };

    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element,
                                            @NotNull Collection<? super RelatedItemLineMarkerInfo> result) {
        String string = getStringFromElement(element);

        if (ifIllegalString(element, string)) {
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
            // Add the property to a collection of line marker info
            NavigationGutterIconBuilder<PsiElement> builder =
                    NavigationGutterIconBuilder.create(X8lDataCenter.X8L_LANGUAGE_ICON)
                            .setTargets(elements)
                            .setTooltipText("Navigate to X8l language elements");
            result.add(builder.createLineMarkerInfo(element));
        }
    }

    protected static boolean ifIllegalString(PsiElement element, String string) {
        return X8lAnnotator.ifIllegalString(element, string);
    }
}