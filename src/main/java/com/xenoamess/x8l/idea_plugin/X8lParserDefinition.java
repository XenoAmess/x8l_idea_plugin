package com.xenoamess.x8l.idea_plugin;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.xenoamess.x8l.idea_plugin.parser.X8lParser;
import com.xenoamess.x8l.idea_plugin.psi.X8lFile;
import com.xenoamess.x8l.idea_plugin.psi.X8lTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class X8lParserDefinition implements ParserDefinition {
    public static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
    public static final TokenSet COMMENTS = TokenSet.create(X8lTypes.COMMENT_NODE);

    public static final IFileElementType FILE = new IFileElementType(X8lLanguage.INSTANCE);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new X8lLexerAdapter();
    }

    @NotNull
    @Override
    public TokenSet getWhitespaceTokens() {
        return WHITE_SPACES;
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    @Override
    public PsiParser createParser(final Project project) {
        return new X8lParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @Override
    public PsiFile createFile(FileViewProvider viewProvider) {
        return new X8lFile(viewProvider);
    }

    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return this.spaceExistenceTypeBetweenTokens(left, right);
    }

    public SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
//        if (checkeElementTypeAllowSpaceBesides(left.getElementType()) && checkeElementTypeAllowSpaceBesides(right.getElementType())) {
//            return SpaceRequirements.MAY;
//        } else {
//            return SpaceRequirements.MUST_NOT;
//        }
        return SpaceRequirements.MUST_NOT;
    }

    public static boolean checkeElementTypeAllowSpaceBesides(@Nullable IElementType type) {
        if (X8lTypes.TEXT_NODE.equals(type)) {
            return false;
        }
        if (X8lTypes.TEXT_NODE_CONTENT.equals(type)) {
            return false;
        }
        if (X8lTypes.TEXT_NODE_CONTENT_STRING.equals(type)) {
            return false;
        }
        if (X8lTypes.COMMENT_NODE_CONTENT.equals(type)) {
            return false;
        }
        if (X8lTypes.COMMENT_NODE_CONTENT_STRING.equals(type)) {
            return false;
        }
        if (X8lTypes.CONTENT_NODE_HEAD_AREA.equals(type)) {
            return false;
        }
        if (X8lTypes.CONTENT_NODE_CHILDREN_AREA.equals(type)) {
            return false;
        }
        return true;
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return X8lTypes.Factory.createElement(node);
    }
}