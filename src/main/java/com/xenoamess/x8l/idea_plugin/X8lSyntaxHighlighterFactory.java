package com.xenoamess.x8l.idea_plugin;

import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

/**
 * @author XenoAmess
 */
public class X8lSyntaxHighlighterFactory extends SyntaxHighlighterFactory {
    private static final X8lSyntaxHighlighter SINGLETON = new X8lSyntaxHighlighter();

    @NotNull
    @Override
    public SyntaxHighlighter getSyntaxHighlighter(Project project, VirtualFile virtualFile) {
        return SINGLETON;
    }
}