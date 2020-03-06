package com.xenoamess.x8l.idea_plugin;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import com.xenoamess.x8l.idea_plugin.psi.X8lTypes;
import org.jetbrains.annotations.NotNull;

public class X8lCompletionContributor extends CompletionContributor {
    public X8lCompletionContributor() {
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(X8lTypes.KEY).withLanguage(X8lLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create("key"));
                    }
                }
        );
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(X8lTypes.VALUE).withLanguage(X8lLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create("value"));
                    }
                }
        );
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(X8lTypes.SEPARATOR).withLanguage(X8lLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create("="));
                    }
                }
        );
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(X8lTypes.LEFT_BRACKET).withLanguage(X8lLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create("<"));
                    }
                }
        );
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(X8lTypes.RIGHT_BRACKET).withLanguage(X8lLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create(">"));
                    }
                }
        );
    }
}
