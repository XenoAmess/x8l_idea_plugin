package com.xenoamess.x8l.idea_plugin;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author XenoAmess
 */
public class X8lFileType extends LanguageFileType {
    public static final X8lFileType INSTANCE = new X8lFileType();

    private X8lFileType() {
        super(X8lLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "X8l File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "X8l language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return X8lDataCenter.X8L_LANGUAGE_EXTENSION;
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return X8lDataCenter.X8L_LANGUAGE_ICON;
    }
}
