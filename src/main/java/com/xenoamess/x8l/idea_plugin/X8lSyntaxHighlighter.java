package com.xenoamess.x8l.idea_plugin;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.xenoamess.x8l.psi.X8lTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class X8lSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey SEPARATOR =
            createTextAttributesKey("X8L_SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey KEY =
            createTextAttributesKey("X8L_KEY", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey VALUE =
            createTextAttributesKey("X8L_VALUE", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey COMMENT =
            createTextAttributesKey("X8L_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey TEXT =
            createTextAttributesKey("X8L_TEXT", HighlighterColors.TEXT);

    public static final TextAttributesKey BAD_CHARACTER =
            createTextAttributesKey("X8L_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    public static final TextAttributesKey BRACKETS =
            createTextAttributesKey("X8L_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS);

//    public static final TextAttributesKey BRACKETS =
//            createTextAttributesKey("X8L_BRACKETS", XmlHighlighterColors.XML_PROLOGUE);

    public static final TextAttributesKey ATTRIBUTE =
            createTextAttributesKey("X8L_ATTRIBUTE", DefaultLanguageHighlighterColors.MARKUP_ATTRIBUTE);

    public static final TextAttributesKey NODE =
            createTextAttributesKey("X8L_NODE", DefaultLanguageHighlighterColors.MARKUP_ENTITY);

    public static final TextAttributesKey NODE_HEAD =
            createTextAttributesKey("X8L_NODE_HEAD", DefaultLanguageHighlighterColors.METADATA);

    public static final TextAttributesKey NODE_CHILDREN =
            createTextAttributesKey("X8L_NODE_CHILDREN", DefaultLanguageHighlighterColors.MARKUP_TAG);


    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] SEPARATOR_KEYS = new TextAttributesKey[]{SEPARATOR};
    private static final TextAttributesKey[] KEY_KEYS = new TextAttributesKey[]{KEY};
    private static final TextAttributesKey[] VALUE_KEYS = new TextAttributesKey[]{VALUE};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};

    private static final TextAttributesKey[] TEXT_KEYS = new TextAttributesKey[]{TEXT};

    private static final TextAttributesKey[] BRACKETS_KEYS = new TextAttributesKey[]{BRACKETS};

    private static final TextAttributesKey[] ATTRIBUTE_KEYS = new TextAttributesKey[]{ATTRIBUTE};

    private static final TextAttributesKey[] NODE_KEYS = new TextAttributesKey[]{NODE};

    private static final TextAttributesKey[] NODE_HEAD_KEYS = new TextAttributesKey[]{NODE_HEAD};

    private static final TextAttributesKey[] NODE_CHILDREN_KEYS = new TextAttributesKey[]{NODE_CHILDREN};

    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new X8lLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        return getTokenHighlightsStatic(tokenType);
    }

    public static TextAttributesKey[] getTokenHighlightsStatic(IElementType tokenType) {
        if (tokenType.equals(X8lTypes.SEPARATOR)) {
            return SEPARATOR_KEYS;
        } else if (tokenType.equals(X8lTypes.CONTENT_NODE_ATTRIBUTE_KEY) || tokenType.equals(X8lTypes.CONTENT_NODE_ATTRIBUTE_KEY_CONTENT_STRING)) {
            return KEY_KEYS;
        } else if (tokenType.equals(X8lTypes.CONTENT_NODE_ATTRIBUTE_VALUE) || tokenType.equals(X8lTypes.CONTENT_NODE_ATTRIBUTE_VALUE_CONTENT_STRING)) {
            return VALUE_KEYS;
        } else if (tokenType.equals(X8lTypes.CONTENT_NODE_ATTRIBUTE)) {
            return ATTRIBUTE_KEYS;
        } else if (tokenType.equals(X8lTypes.COMMENT_NODE) || tokenType.equals(X8lTypes.COMMENT_NODE_CONTENT) || tokenType.equals(X8lTypes.COMMENT_NODE_LEFT_BRACKET) || tokenType.equals(X8lTypes.COMMENT_NODE_CONTENT_STRING) || tokenType.equals(X8lTypes.COMMENT_NODE_RIGHT_BRACKET)) {
            return COMMENT_KEYS;
        } else if (tokenType.equals(X8lTypes.TEXT_NODE) || tokenType.equals(X8lTypes.TEXT_NODE_CONTENT) || tokenType.equals(X8lTypes.TEXT_NODE_CONTENT_STRING)) {
            return TEXT_KEYS;
        } else if (tokenType.equals(X8lTypes.LEFT_BRACKET) || tokenType.equals(X8lTypes.RIGHT_BRACKET)) {
            return BRACKETS_KEYS;
        } else if (tokenType.equals(X8lTypes.CONTENT_NODE)) {
            return NODE_KEYS;
        } else if (tokenType.equals(X8lTypes.CONTENT_NODE_HEAD_AREA)) {
            return NODE_HEAD_KEYS;
        } else if (tokenType.equals(X8lTypes.CONTENT_NODE_CHILDREN_AREA)) {
            return NODE_CHILDREN_KEYS;
        } else if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        } else {
            return EMPTY_KEYS;
        }
    }
}