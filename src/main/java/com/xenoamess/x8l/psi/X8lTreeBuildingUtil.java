package com.xenoamess.x8l.psi;

import com.intellij.psi.PsiElement;
import com.xenoamess.x8l.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class X8lTreeBuildingUtil {
    @NotNull
    public static X8lTree buildX8lTreeFromX8lFile(@Nullable X8lFile x8lFile) {
        X8lTree x8lTree = new X8lTree();
        if (x8lFile == null) {
            return x8lTree;
        }

        X8lRootNodeChildrenArea rootNodeChildrenArea = x8lFile.findChildByClass(X8lRootNodeChildrenArea.class);
        if (rootNodeChildrenArea == null) {
            return x8lTree;
        }
        RootNode root = x8lTree.getRoot();
        X8lContentNodeChildrenArea contentNodeChildrenArea = rootNodeChildrenArea.getContentNodeChildrenArea();
        buildContentNodeChildrenArea(root, contentNodeChildrenArea);

        return x8lTree;
    }

    public static void buildContentNodeHeadArea(@NotNull ContentNode nowNode, @NotNull X8lContentNodeHeadArea x8lContentNodeHeadArea) {
//        boolean noSpaceEnd = true;
        for (PsiElement psiElement : x8lContentNodeHeadArea.getContentNodeAttributeList()) {
            if (psiElement instanceof X8lContentNodeAttribute) {
                X8lContentNodeAttribute x8lContentNodeAttribute = (X8lContentNodeAttribute) psiElement;
                String keyString = x8lContentNodeAttribute.getContentNodeAttributeKey().getValue();
                X8lContentNodeAttributeValue x8lContentNodeAttributeValue = x8lContentNodeAttribute.getContentNodeAttributeValue();
                if (x8lContentNodeAttributeValue != null) {
                    nowNode.addAttribute(keyString, x8lContentNodeAttributeValue.getValue());
                } else {
                    nowNode.addAttribute(keyString);
                }
//                noSpaceEnd = true;
            } else if (psiElement instanceof X8lWhiteSpace) {
                X8lWhiteSpace x8lWhiteSpace = (X8lWhiteSpace) psiElement;
                if (!nowNode.getAttributeSegments().isEmpty()) {
                    nowNode.getAttributeSegments().set(nowNode.getAttributeSegments().size() - 1, x8lWhiteSpace.getText());
                }
//                noSpaceEnd = false;
            }
        }
//        if (noSpaceEnd) {
//            if (!nowNode.getAttributeSegments().isEmpty()) {
//                nowNode.getAttributeSegments().set(nowNode.getAttributeSegments().size() - 1, "");
//            }
//        }
    }

    public static void buildContentNodeChildrenArea(@NotNull ContentNode nowNode, @NotNull X8lContentNodeChildrenArea x8lContentNodeChildrenArea) {
        for (PsiElement psiElement : x8lContentNodeChildrenArea.getChildren()) {
            if (psiElement instanceof X8lTextNode) {
                buildTextNode(nowNode, (X8lTextNode) psiElement);
            } else if (psiElement instanceof X8lCommentNode) {
                buildCommentNode(nowNode, (X8lCommentNode) psiElement);
            } else if (psiElement instanceof X8lContentNode) {
                buildContentNode(nowNode, (X8lContentNode) psiElement);
            }
        }
    }

    @NotNull
    public static TextNode buildTextNode(@NotNull ContentNode parentNode, @NotNull X8lTextNode x8lTextNode) {
        return new TextNode(parentNode, x8lTextNode.getValue());
    }

    @NotNull
    public static CommentNode buildCommentNode(@NotNull ContentNode parentNode, @NotNull X8lCommentNode x8lCommentNode) {
        return new CommentNode(parentNode, x8lCommentNode.getValue());
    }

    @NotNull
    public static ContentNode buildContentNode(@NotNull ContentNode parentNode, @NotNull X8lContentNode x8lContentNode) {
        ContentNode nowNode = new ContentNode(parentNode);

        buildContentNodeHeadArea(nowNode, x8lContentNode.getContentNodeHeadArea());

        buildContentNodeChildrenArea(nowNode, x8lContentNode.getContentNodeChildrenArea());

        return nowNode;
    }
}
