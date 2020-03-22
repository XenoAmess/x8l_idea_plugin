package com.xenoamess.x8l.idea_plugin;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.xenoamess.x8l.psi.X8lCommentNode;
import com.xenoamess.x8l.psi.X8lContentNodeAttribute;
import com.xenoamess.x8l.psi.X8lContentNodeChildrenArea;
import com.xenoamess.x8l.psi.X8lContentNodeHeadArea;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class X8lFoldingBuilder extends FoldingBuilderEx implements DumbAware {
    @NotNull
    @Override
    public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {
        // Initialize the list of folding regions
        List<FoldingDescriptor> descriptors = new ArrayList<>();
        // Get a collection of the literal expressions in the document below root
        Collection<PsiElement> psiElements = X8lUtil.findAllPsiElements(root);
        // Evaluate the collection
        for (final PsiElement psiElement : psiElements) {
            TextRange textRange = null;
            if (psiElement instanceof X8lCommentNode) {
                X8lCommentNode element = (X8lCommentNode) psiElement;
                textRange = element.getCommentNodeContent().getTextRange();
//            } else if (psiElement instanceof X8lTextNode) {
//                X8lTextNode element = (X8lTextNode) psiElement;
//                textRange = element.getTextRange();
//            } else if (psiElement instanceof X8lContentNode) {
//                textRange = new TextRange(psiElement.getTextRange().getStartOffset() + 1, psiElement.getTextRange()
//                .getEndOffset() - 1);
            } else if (psiElement instanceof X8lContentNodeHeadArea) {
                textRange = new TextRange(psiElement.getTextRange().getStartOffset(),
                        psiElement.getTextRange().getEndOffset());
            } else if (psiElement instanceof X8lContentNodeChildrenArea) {
                textRange = new TextRange(psiElement.getTextRange().getStartOffset(),
                        psiElement.getTextRange().getEndOffset());
            }

            if (textRange != null && textRange.getLength() > 0) {
                descriptors.add(
                        new FoldingDescriptor(
                                psiElement.getNode(),
                                textRange
                        )
                );
            }

            // Add a folding descriptor for the literal expression at this node.

        }
        return descriptors.toArray(new FoldingDescriptor[0]);
    }

    /**
     * Gets the X8l Language 'value' string corresponding to the 'key'
     *
     * @param node Node corresponding to PsiLiteralExpression containing a string in the format
     *             X8L_PREFIX_STR + X8L_SEPARATOR_STR + Key, where Key is
     *             defined by the X8l language file.
     */
    @Nullable
    @Override
    public String getPlaceholderText(@NotNull ASTNode node) {
        final int maxPlaceHolderLength = 25;

        PsiElement psiElement = node.getPsi();
        String res = "...";
        if (psiElement instanceof X8lCommentNode) {
            X8lCommentNode element = (X8lCommentNode) psiElement;
            res = StringUtils.substring(element.getCommentNodeContent().getText().trim(), 0, maxPlaceHolderLength);
//        } else if (psiElement instanceof X8lTextNode) {
//            X8lTextNode element = (X8lTextNode) psiElement;
//            res = StringUtils.substring(element.getText().trim(), 0, 5);
//        } else if (psiElement instanceof X8lContentNode) {
//            X8lContentNode element = (X8lContentNode) psiElement;
//            List<X8lContentNodeAttribute> x8lContentNodeAttributeList = element.getContentNodeHeadArea()
//            .getContentNodeAttributeList();
//            String keyF = x8lContentNodeAttributeList.isEmpty() ? "" : x8lContentNodeAttributeList.get(0).getKey();
//            res = keyF + ">";
        } else if (psiElement instanceof X8lContentNodeHeadArea) {
            X8lContentNodeHeadArea element = (X8lContentNodeHeadArea) psiElement;
            List<X8lContentNodeAttribute> x8lContentNodeAttributeList = element.getContentNodeAttributeList();
            String keyF = StringUtils.substring(x8lContentNodeAttributeList.isEmpty() ? "" :
                            x8lContentNodeAttributeList.get(0).getContentNodeAttributeKey().getText().trim(), 0,
                    maxPlaceHolderLength);
            res = keyF;
        } else if (psiElement instanceof X8lContentNodeChildrenArea) {
            X8lContentNodeChildrenArea element = (X8lContentNodeChildrenArea) psiElement;
            res = element.getText().trim();
            res = StringUtils.substring(res, 0, maxPlaceHolderLength);
            if (res.startsWith("<") && !res.endsWith(">")) {
                res = StringUtils.substring(res, 0, maxPlaceHolderLength - 1);
                res += ">";
            }
        }
        return res;
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode node) {
        return false;
    }
}