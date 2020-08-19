package com.xenoamess.x8l.idea_plugin;

import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralValue;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.xenoamess.x8l.X8lTree;
import com.xenoamess.x8l.psi.X8lTypes;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author XenoAmess
 */
public class X8lFindUsagesProvider implements FindUsagesProvider {
    @Nullable
    @Override
    public WordsScanner getWordsScanner() {
        List<IElementType> identifierTokens = new ArrayList<>();
        identifierTokens.add(X8lTypes.CONTENT_NODE_ATTRIBUTE_KEY);

        List<IElementType> commentTokens = new ArrayList<>();
        commentTokens.add(X8lTypes.COMMENT_NODE);
        commentTokens.add(X8lTypes.COMMENT_NODE_CONTENT);

        List<IElementType> literalTokens = new ArrayList<>();
        literalTokens.add(X8lTypes.CONTENT_NODE_ATTRIBUTE_KEY);
        literalTokens.add(X8lTypes.TEXT_NODE_CONTENT);
        literalTokens.add(X8lTypes.CONTENT_NODE_ATTRIBUTE_VALUE);

        try {
            //java:
            //noinspection rawtypes
            Class javaClass = Class.forName("com.intellij.psi.JavaTokenType");
            //
            identifierTokens.add(
                    (IElementType) javaClass.getField("IDENTIFIER").get(null)
            );
            //
            commentTokens.add(
                    (IElementType) javaClass.getField("C_STYLE_COMMENT").get(null)
            );
            commentTokens.add(
                    (IElementType) javaClass.getField("END_OF_LINE_COMMENT").get(null)
            );
            //
            literalTokens.add(
                    (IElementType) javaClass.getField("STRING_LITERAL").get(null)
            );
            literalTokens.add(
                    (IElementType) javaClass.getField("TEXT_BLOCK_LITERAL").get(null)
            );
        } catch (Exception ignored) {
            //do nothing
        }
        return new DefaultWordsScanner(new X8lLexerAdapter(),
                TokenSet.create(
                        identifierTokens.toArray(new IElementType[0])
                ),
                TokenSet.create(
                        commentTokens.toArray(new IElementType[0])
                ),
                TokenSet.create(
                        literalTokens.toArray(new IElementType[0])
                )
        );
    }

    @Override
    public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
        return psiElement instanceof PsiLiteralValue;
    }

    @Nullable
    @Override
    public String getHelpId(@NotNull PsiElement psiElement) {
        return psiElement.getContainingFile().getName() + " : " + psiElement.getText();
    }

    @NotNull
    @Override
    public String getType(@NotNull PsiElement element) {
        return element.getNode().getElementType().toString();
    }

    @NotNull
    @Override
    public String getDescriptiveName(@NotNull PsiElement element) {
        return X8lTree.untranscode(X8lUtil.getStringFromElement(element));
    }

    @NotNull
    @Override
    public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
        return X8lUtil.getStringFromElement(element);
    }
}