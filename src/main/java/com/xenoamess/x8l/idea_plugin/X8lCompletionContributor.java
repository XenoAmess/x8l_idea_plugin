package com.xenoamess.x8l.idea_plugin;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import com.xenoamess.x8l.psi.X8lTypes;
import org.jetbrains.annotations.NotNull;

public class X8lCompletionContributor extends CompletionContributor {
    public X8lCompletionContributor() {
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(X8lTypes.CONTENT_NODE_ATTRIBUTE_KEY).withLanguage(X8lLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create("key"));
                    }
                }
        );
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(X8lTypes.CONTENT_NODE_ATTRIBUTE_VALUE).withLanguage(X8lLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create("value"));
                    }
                }
        );
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(X8lTypes.SEPARATOR).withLanguage(X8lLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create("="));
                    }
                }
        );
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(X8lTypes.LEFT_BRACKET).withLanguage(X8lLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create("<"));
                    }
                }
        );
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(X8lTypes.RIGHT_BRACKET).withLanguage(X8lLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create(">"));
                    }
                }
        );
    }
}
