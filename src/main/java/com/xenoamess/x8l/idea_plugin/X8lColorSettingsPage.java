package com.xenoamess.x8l.idea_plugin;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

import static com.xenoamess.x8l.idea_plugin.X8lDataCenter.X8L_LANGUAGE_ICON;
import static com.xenoamess.x8l.idea_plugin.X8lDataCenter.X8L_LANGUAGE_NAME;

public class X8lColorSettingsPage implements ColorSettingsPage {
    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Key", X8lSyntaxHighlighter.KEY),
            new AttributesDescriptor("Separator", X8lSyntaxHighlighter.SEPARATOR),
            new AttributesDescriptor("Value", X8lSyntaxHighlighter.VALUE),
            new AttributesDescriptor("Bad Value", X8lSyntaxHighlighter.BAD_CHARACTER)
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return X8L_LANGUAGE_ICON;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new X8lSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return X8lDataCenter.DEMO_X8L_TEXT;
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return X8L_LANGUAGE_NAME;
    }
}