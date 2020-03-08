//learned a lot from com.intellij.lang.xml.XmlCommenter

package com.xenoamess.x8l.idea_plugin;

import com.intellij.codeInsight.generation.CommenterDataHolder;
import com.intellij.codeInsight.generation.EscapingCommenter;
import com.intellij.codeInsight.generation.SelfManagingCommenter;
import com.intellij.lang.CustomUncommenter;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.RangeMarker;
import com.intellij.openapi.util.Couple;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.xenoamess.x8l.idea_plugin.psi.X8lCommentNode;
import com.xenoamess.x8l.idea_plugin.psi.X8lTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.xenoamess.x8l.idea_plugin.X8lUtil.createX8lFileFromString;


/**
 * @author max
 */
//EscapingCommenter,
public class X8lCommenter implements EscapingCommenter, CustomUncommenter, SelfManagingCommenter {

    @Override
    public String getLineCommentPrefix() {
        //shall never
        return null;
    }

    @NotNull
    @Override
    public String getBlockCommentPrefix() {
        return "<<";
    }

    @NotNull
    @Override
    public String getBlockCommentSuffix() {
        return ">";
    }

    @Override
    public String getCommentedBlockCommentPrefix() {
        return "<<";
    }

    @Override
    public String getCommentedBlockCommentSuffix() {
        return ">";
    }

    @Override
    public void escape(Document document, RangeMarker range) {
        int startOffset = range.getStartOffset();
        int endOffset = range.getEndOffset();
        //        String content = document.getText(new TextRange(range.getStartOffset() + this.getBlockCommentPrefix().length(), range.getEndOffset() - this.getBlockCommentSuffix().length()));
        //        String contentReplacement = X8lTree.transcodeComment(X8lTree.untranscode(content));
        //        document.replaceString(range.getStartOffset() + this.getBlockCommentPrefix().length(), range.getEndOffset() - this.getBlockCommentSuffix().length(), contentReplacement);
        String content = document.getText(new TextRange(startOffset + this.getBlockCommentPrefix().length(), endOffset - this.getBlockCommentSuffix().length()));
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < content.length(); i++) {
            char chr = content.charAt(i);
            if (chr == '%' || chr == '>') {
                stringBuilder.append('%');
            }
            stringBuilder.append(chr);
        }
        String contentReplacement = stringBuilder.toString();
        document.replaceString(startOffset + this.getBlockCommentPrefix().length(), endOffset - this.getBlockCommentSuffix().length(), contentReplacement);
    }


    @Override
    public void unescape(Document document, RangeMarker range) {
//        String content = document.getText(new TextRange(range.getStartOffset(), range.getEndOffset()));
//        String contentReplacement = X8lTree.transcode(X8lTree.untranscode(content));
//        document.replaceString(range.getStartOffset(), range.getEndOffset(), contentReplacement);
        String content = document.getText(new TextRange(range.getStartOffset(), range.getEndOffset()));
        StringBuilder stringBuilder = new StringBuilder();
        boolean lastCharIsModulus = false;
        for (int i = 0; i < content.length(); i++) {
            char chr = content.charAt(i);
            if (lastCharIsModulus) {
                stringBuilder.append(chr);
                lastCharIsModulus = false;
            } else if (chr == '%') {
                lastCharIsModulus = true;
            } else {
                stringBuilder.append(chr);
            }
        }
        String contentReplacement = stringBuilder.toString();
        document.replaceString(range.getStartOffset(), range.getEndOffset(), contentReplacement);
    }

    @Nullable
    @Override
    public TextRange findMaximumCommentedRange(@NotNull CharSequence text) {
//        Project dummyProject = DummyProject.getInstance();
//        X8lFile dummyFile = createFile(dummyProject, text.toString());

        PsiElement psiElement = createX8lFileFromString(text);
        List<PsiElement> elements = X8lUtil.findMostNearChildrenOfType(psiElement, null, X8lTypes.COMMENT_NODE, 1);
        if (elements.isEmpty()) return null;
        return elements.get(0).getNode().getTextRange();
    }

    @NotNull
    @Override
    public Collection<? extends Couple<TextRange>> getCommentRangesToDelete(@NotNull CharSequence text) {
//        Project dummyProject = DummyProject.getInstance();
//        X8lFile dummyFile = createFile(dummyProject, text.toString());

        PsiElement psiElement = createX8lFileFromString(text);
        List<PsiElement> elements = X8lUtil.findMostNearChildrenOfType(psiElement, null, X8lTypes.COMMENT_NODE, X8lUtil.X8L_GET_CHILD_ALL);
        List<Couple<TextRange>> result = new ArrayList<>();
        for (PsiElement element : elements) {
            assert (element instanceof X8lCommentNode);
            TextRange textRangeLeft = element.getNode().findChildByType(X8lTypes.COMMENT_NODE_LEFT_BRACKET).getTextRange();
            TextRange textRangeRight = element.getNode().findChildByType(X8lTypes.COMMENT_NODE_RIGHT_BRACKET).getTextRange();
            result.add(new Couple<>(textRangeLeft, textRangeRight));
        }
        return result;
    }

    @Nullable
    @Override
    public CommenterDataHolder createLineCommentingState(int startLine, int endLine, @NotNull Document document, @NotNull PsiFile file) {
        return SelfManagingCommenter.EMPTY_STATE;
    }

    @Nullable
    @Override
    public CommenterDataHolder createBlockCommentingState(int selectionStart, int selectionEnd, @NotNull Document document, @NotNull PsiFile file) {
        return SelfManagingCommenter.EMPTY_STATE;
    }

    @Override
    public void commentLine(int line, int offset, @NotNull Document document, @NotNull CommenterDataHolder data) {
        insertBlockComment(document.getLineStartOffset(line), document.getLineEndOffset(line), document, data);
    }

    @Override
    public void uncommentLine(int line, int offset, @NotNull Document document, @NotNull CommenterDataHolder data) {
        uncommentBlockComment(document.getLineStartOffset(line), document.getLineEndOffset(line), document, data);
    }

    @Override
    public boolean isLineCommented(int line, int offset, @NotNull Document document, @NotNull CommenterDataHolder data) {
//        String lineString = document.getText(new TextRange(offset, document.getLineEndOffset(line)));
//        int flag = 0;
//        for (int i = 0; i < lineString.length(); i++) {
//            switch (flag) {
//                case 0:
//                    if (Character.isSpaceChar(lineString.charAt(i))) {
//                        continue;
//                    }
//                    if (lineString.charAt(i) == '<') {
//                        flag = 1;
//                        continue;
//                    }
//                    return false;
//                case 1:
//                    if (Character.isSpaceChar(lineString.charAt(i))) {
//                        continue;
//                    }
//                    if (lineString.charAt(i) == '<') {
//                        return true;
//                    }
//                    return false;
//            }
//        }
//        return false;
        return getBlockCommentRange(document.getLineStartOffset(line), document.getLineEndOffset(line), document, data) != null;
    }

    @Nullable
    @Override
    public String getCommentPrefix(int line, @NotNull Document document, @NotNull CommenterDataHolder data) {
        return this.getBlockCommentPrefix();
    }

    @Nullable
    @Override
    public TextRange getBlockCommentRange(int selectionStart, int selectionEnd, @NotNull Document document, @NotNull CommenterDataHolder data) {
        TextRange selectedTextRange = new TextRange(selectionStart, selectionEnd);
        PsiElement psiElement = createX8lFileFromString(document.getText());
        List<PsiElement> elements = X8lUtil.findMostNearChildrenOfType(psiElement, null, X8lTypes.COMMENT_NODE, X8lUtil.X8L_GET_CHILD_ALL);
        for (PsiElement element : elements) {
            assert (element instanceof X8lCommentNode);
            TextRange textRange = element.getTextRange();
            if (selectedTextRange.intersects(textRange)) {
                return textRange;
            }
        }
        return null;
    }

    @Nullable
    @Override
    public String getBlockCommentPrefix(int selectionStart, @NotNull Document document, @NotNull CommenterDataHolder data) {
        return getBlockCommentPrefix();
    }

    @Nullable
    @Override
    public String getBlockCommentSuffix(int selectionEnd, @NotNull Document document, @NotNull CommenterDataHolder data) {
        return getBlockCommentSuffix();
    }

    @Override
    public void uncommentBlockComment(int startOffset, int endOffset, Document document, CommenterDataHolder data) {

        while (true) {
            PsiElement psiElement = createX8lFileFromString(document.getText());
            List<PsiElement> elements = X8lUtil.findMostNearChildrenOfType(psiElement, null, X8lTypes.COMMENT_NODE, X8lUtil.X8L_GET_CHILD_ALL);
            boolean flag = false;

            for (PsiElement element : elements) {
                assert (element instanceof X8lCommentNode);
                TextRange selectedTextRange = new TextRange(startOffset, endOffset);
                TextRange textRange = element.getTextRange();
                if (selectedTextRange.intersects(textRange)) {
                    String content = element.getNode().findChildByType(X8lTypes.COMMENT_NODE_CONTENT).getText();
                    StringBuilder stringBuilder = new StringBuilder();
                    boolean lastCharIsModulus = false;
                    for (int i = 0; i < content.length(); i++) {
                        char chr = content.charAt(i);
                        if (lastCharIsModulus) {
                            stringBuilder.append(chr);
                            lastCharIsModulus = false;
                        } else if (chr == '%') {
                            lastCharIsModulus = true;
                        } else {
                            stringBuilder.append(chr);
                        }
                    }

                    String contentReplacement = stringBuilder.toString();
                    document.replaceString(textRange.getStartOffset(), textRange.getEndOffset(), contentReplacement);
                    if (endOffset > textRange.getStartOffset()) {
                        endOffset += contentReplacement.length() - (textRange.getEndOffset() - textRange.getStartOffset());
                    }

                    int newTextRangeStartOffset = textRange.getStartOffset();
                    int newTextRangeEndOffset = textRange.getStartOffset() + contentReplacement.length();

                    startOffset = Math.min(startOffset, newTextRangeStartOffset);
                    endOffset = Math.max(endOffset, newTextRangeEndOffset);

                    flag = true;
                    break;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    @NotNull
    @Override
    public TextRange insertBlockComment(int startOffset, int endOffset, Document document, CommenterDataHolder data) {
        //        String content = document.getText(new TextRange(range.getStartOffset() + this.getBlockCommentPrefix().length(), range.getEndOffset() - this.getBlockCommentSuffix().length()));
        //        String contentReplacement = X8lTree.transcodeComment(X8lTree.untranscode(content));
        //        document.replaceString(range.getStartOffset() + this.getBlockCommentPrefix().length(), range.getEndOffset() - this.getBlockCommentSuffix().length(), contentReplacement);
        String content = document.getText(new TextRange(startOffset, endOffset));
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < content.length(); i++) {
            char chr = content.charAt(i);
            if (chr == '%' || chr == '>') {
                stringBuilder.append('%');
            }
            stringBuilder.append(chr);
        }
        String contentReplacement = getBlockCommentPrefix() + stringBuilder.toString() + getBlockCommentSuffix();
        document.replaceString(startOffset, endOffset, contentReplacement);
        return new TextRange(startOffset, startOffset + contentReplacement.length());
    }
}