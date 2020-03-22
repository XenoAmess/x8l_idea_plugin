package com.xenoamess.x8l.idea_plugin;

import com.intellij.lexer.FlexAdapter;
import com.xenoamess.x8l.psi.X8lLexer;

import java.io.Reader;

public class X8lLexerAdapter extends FlexAdapter {
    public X8lLexerAdapter() {
        super(new X8lLexer(null));
    }
}
