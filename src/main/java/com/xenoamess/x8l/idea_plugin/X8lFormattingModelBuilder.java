//
//
//package com.xenoamess.x8l.idea_plugin;
//
//import com.intellij.formatting.*;
//import com.intellij.lang.ASTNode;
//import com.intellij.openapi.util.TextRange;
//import com.intellij.psi.PsiElement;
//import com.intellij.psi.PsiFile;
//import com.intellij.psi.codeStyle.CodeStyleSettings;
//import com.intellij.psi.tree.TokenSet;
//import com.xenoamess.x8l.idea_plugin.psi.X8lTypes;
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//
//public class X8lFormattingModelBuilder implements FormattingModelBuilder {
//    @NotNull
//    @Override
//    public FormattingModel createModel(PsiElement element, CodeStyleSettings settings) {
//        return FormattingModelProvider
//                .createFormattingModelForPsiFile(element.getContainingFile(),
//                        new X8lBlock(
//                                element.getNode(),
//                                Wrap.createWrap(WrapType.NONE, false),
//                                Alignment.createAlignment()),
//                        settings);
//    }
//
//
//    private static final TokenSet KEY = TokenSet.create(
//            X8lTypes.CONTENT_NODE_ATTRIBUTE_KEY
//            , X8lTypes.CONTENT_NODE_ATTRIBUTE_KEY_CONTENT_STRING
//    );
//    private static final TokenSet VALUE = TokenSet.create(
//            X8lTypes.CONTENT_NODE_ATTRIBUTE_VALUE
//            , X8lTypes.CONTENT_NODE_ATTRIBUTE_VALUE_CONTENT_STRING
//    );
//
//    private static SpacingBuilder createSpaceBuilder(CodeStyleSettings settings) {
//        return new SpacingBuilder(settings, X8lLanguage.INSTANCE)
////                .between(
////                        VALUE,
////                        KEY
////                )
////                .spaces(1)
//                .between(
//                        X8lTypes.LEFT_BRACKET,
//                        KEY
//                )
//                .none()
//                .between(
//                        X8lTypes.LEFT_BRACKET,
//                        X8lTypes.RIGHT_BRACKET
//                )
//                .none()
//                .between(
//                        KEY,
//                        X8lTypes.RIGHT_BRACKET
//                )
//                .none()
//                .between(
//                        VALUE,
//                        X8lTypes.RIGHT_BRACKET
//                )
//                .none()
//                .between(
//                        KEY,
//                        KEY
//                )
//                .none()
//                .before(VALUE)
//                .none()
//                .after(KEY)
//                .none()
//                .before(X8lTypes.RIGHT_BRACKET)
//                .none()
//                .after(X8lTypes.LEFT_BRACKET)
//                .none()
//                ;
//    }
//
//    @Nullable
//    @Override
//    public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset) {
//        return null;
//    }
//}
