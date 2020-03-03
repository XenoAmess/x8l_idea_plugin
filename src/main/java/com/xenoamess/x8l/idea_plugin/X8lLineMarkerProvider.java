// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.xenoamess.x8l.idea_plugin;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import com.intellij.psi.impl.source.tree.java.PsiJavaTokenImpl;
import com.xenoamess.x8l.idea_plugin.psi.X8lProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public class X8lLineMarkerProvider extends RelatedItemLineMarkerProvider {
    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element,
                                            @NotNull Collection<? super RelatedItemLineMarkerInfo> result) {
        // This must be an element with a literal expression as a parent
        if (!(element instanceof PsiJavaTokenImpl) || !(element.getParent() instanceof PsiLiteralExpression)) return;

        // The literal expression must start with the X8l language literal expression
        PsiLiteralExpression literalExpression = (PsiLiteralExpression) element.getParent();
        String value = literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;
        if ((value == null) || !value.startsWith(X8lAnnotator.X8L_PREFIX_STR + X8lAnnotator.X8L_SEPARATOR_STR))
            return;

        // Get the X8l language property usage
        Project project = element.getProject();
        String possibleProperties = value.substring(X8lAnnotator.X8L_PREFIX_STR.length() + X8lAnnotator.X8L_SEPARATOR_STR.length());
        final List<X8lProperty> properties = X8lUtil.findProperties(project, possibleProperties);
        if (properties.size() > 0) {
            // Add the property to a collection of line marker info
            NavigationGutterIconBuilder<PsiElement> builder =
                    NavigationGutterIconBuilder.create(X8lDataCenter.X8L_LANGUAGE_ICON)
                            .setTargets(properties)
                            .setTooltipText("Navigate to X8l language property");
            result.add(builder.createLineMarkerInfo(element));
        }
    }

}