package com.xenoamess.x8l.idea_plugin;

import com.intellij.lang.Language;

import static com.xenoamess.x8l.idea_plugin.X8lDataCenter.X8L_LANGUAGE_NAME;

/**
 * @author XenoAmess
 */
public class X8lLanguage extends Language {
    public static final X8lLanguage INSTANCE = new X8lLanguage();

    protected X8lLanguage() {
        super(X8L_LANGUAGE_NAME);
    }
}