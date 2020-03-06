// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.xenoamess.x8l.idea_plugin;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import com.intellij.psi.PsiLiteralValue;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

import static com.xenoamess.x8l.dealers.JsonDealer.ARRAY_ID_ATTRIBUTE;
import static com.xenoamess.x8l.idea_plugin.X8lUtil.findPsiElementsIncludingTranscode;

public class X8lLineMarkerProvider extends RelatedItemLineMarkerProvider {
    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element,
                                            @NotNull Collection<? super RelatedItemLineMarkerInfo> result) {
        String string = null;
        if (element instanceof PsiLiteralValue) {
            Object valueObject = ((PsiLiteralValue) element).getValue();
            if (valueObject instanceof String) {
                string = (String) valueObject;
            }
        }

        if (string == null) {
            string = element.getText();
        }

        if (StringUtils.isBlank(string)) {
            return;
        }

        if (!(element instanceof PsiLiteralExpression) && ARRAY_ID_ATTRIBUTE.equals(string)) {
            return;
        }

        // Get the X8l language property usage
        Project project = element.getProject();

        List<PsiElement> elements = findPsiElementsIncludingTranscode(project, string, null);
        
        if (!elements.isEmpty()) {
            // Add the property to a collection of line marker info
            NavigationGutterIconBuilder<PsiElement> builder =
                    NavigationGutterIconBuilder.create(X8lDataCenter.X8L_LANGUAGE_ICON)
                            .setTargets(elements)
                            .setTooltipText("Navigate to X8l language elements");
            result.add(builder.createLineMarkerInfo(element));
        }
    }
}