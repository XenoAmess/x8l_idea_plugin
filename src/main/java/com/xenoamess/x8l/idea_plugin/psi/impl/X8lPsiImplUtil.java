package com.xenoamess.x8l.idea_plugin.psi.impl;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.xenoamess.x8l.idea_plugin.psi.*;
import com.xenoamess.x8l.idea_plugin.psi.impl.utils.*;

public class X8lPsiImplUtil {

    //-----X8lTextNodeUtils starts
    public static IElementType getTokenType(final X8lTextNode element) {
        return X8lTextNodeUtil.getTokenType(element);
    }

    public static PsiElement getNameIdentifier(X8lTextNode element) {
        return X8lTextNodeUtil.getNameIdentifier(element);
    }

    public static String getValue(X8lTextNode element) {
        return X8lTextNodeUtil.getValue(element);
    }

    public static String getName(X8lTextNode element) {
        return X8lTextNodeUtil.getName(element);
    }

    public static PsiElement setName(X8lTextNode element, String newName) {
        return X8lTextNodeUtil.setName(element, newName);
    }

    public static ItemPresentation getPresentation(final X8lTextNode element) {
        return X8lTextNodeUtil.getPresentation(element);
    }

    //-----X8lTextNodeUtils ends

    //-----X8lCommentNodeUtil starts

    public static IElementType getTokenType(final X8lCommentNode element) {
        return X8lCommentNodeUtil.getTokenType(element);
    }

    public static PsiElement getNameIdentifier(X8lCommentNode element) {
        return X8lCommentNodeUtil.getNameIdentifier(element);
    }

    public static String getValue(X8lCommentNode x8lCommentNode) {
        return X8lCommentNodeUtil.getValue(x8lCommentNode);
    }

    public static String getName(X8lCommentNode element) {
        return X8lCommentNodeUtil.getName(element);
    }

    public static PsiElement setName(X8lCommentNode element, String newName) {
        return X8lCommentNodeUtil.setName(element, newName);
    }

    public static ItemPresentation getPresentation(final X8lCommentNode x8lCommentNode) {
        return X8lCommentNodeUtil.getPresentation(x8lCommentNode);
    }

    //-----X8lCommentNodeUtil ends

    //-----X8lCommentNodeUtilContent starts
    public static IElementType getTokenType(final X8lCommentNodeContent element) {
        return X8lCommentNodeContentUtil.getTokenType(element);
    }

    public static PsiElement getNameIdentifier(X8lCommentNodeContent element) {
        return X8lCommentNodeContentUtil.getNameIdentifier(element);
    }

    public static String getValue(X8lCommentNodeContent element) {
        return X8lCommentNodeContentUtil.getValue(element);
    }

    public static String getName(X8lCommentNodeContent element) {
        return X8lCommentNodeContentUtil.getName(element);
    }

    public static PsiElement setName(X8lCommentNodeContent element, String newName) {
        return X8lCommentNodeContentUtil.setName(element, newName);
    }

    public static ItemPresentation getPresentation(final X8lCommentNodeContent element) {
        return X8lCommentNodeContentUtil.getPresentation(element);
    }
    //-----X8lCommentNodeUtilContent ends

    //-----X8lContentNodeAttributeUtil starts
    public static IElementType getTokenType(final X8lContentNodeAttribute element) {
        return X8lContentNodeAttributeUtil.getTokenType(element);
    }

    public static PsiElement getNameIdentifier(X8lContentNodeAttribute element) {
        return X8lContentNodeAttributeUtil.getNameIdentifier(element);
    }

    public static String getKey(X8lContentNodeAttribute element) {
        return X8lContentNodeAttributeUtil.getKey(element);
    }

    public static String getValue(X8lContentNodeAttribute element) {
        return X8lContentNodeAttributeUtil.getValue(element);
    }

    public static String getName(X8lContentNodeAttribute element) {
        return X8lContentNodeAttributeUtil.getName(element);
    }

    public static PsiElement setName(X8lContentNodeAttribute element, String newName) {
        return X8lContentNodeAttributeUtil.setName(element, newName);
    }

    public static ItemPresentation getPresentation(final X8lContentNodeAttribute element) {
        return X8lContentNodeAttributeUtil.getPresentation(element);
    }

    //-----X8lContentNodeAttributeUtil ends

    //-----X8lContentNodeAttributeValueUtil starts

    public static IElementType getTokenType(final X8lContentNodeAttributeValue element) {
        return X8lContentNodeAttributeValueUtil.getTokenType(element);
    }

    public static PsiElement getNameIdentifier(X8lContentNodeAttributeValue element) {
        return X8lContentNodeAttributeValueUtil.getNameIdentifier(element);
    }

    public static String getValue(X8lContentNodeAttributeValue element) {
        return X8lContentNodeAttributeValueUtil.getValue(element);
    }

    public static String getName(X8lContentNodeAttributeValue element) {
        return X8lContentNodeAttributeValueUtil.getName(element);
    }

    public static PsiElement setName(X8lContentNodeAttributeValue element, String newName) {
        return X8lContentNodeAttributeValueUtil.setName(element, newName);
    }

    public static ItemPresentation getPresentation(final X8lContentNodeAttributeValue element) {
        return X8lContentNodeAttributeValueUtil.getPresentation(element);
    }

    //-----X8lContentNodeAttributeValueUtil ends

    //-----X8lContentNodeAttributeKeyUtil starts
    public static IElementType getTokenType(final X8lContentNodeAttributeKey element) {
        return X8lContentNodeAttributeKeyUtil.getTokenType(element);
    }

    public static PsiElement getNameIdentifier(X8lContentNodeAttributeKey element) {
        return X8lContentNodeAttributeKeyUtil.getNameIdentifier(element);
    }

    public static String getValue(X8lContentNodeAttributeKey element) {
        return X8lContentNodeAttributeKeyUtil.getValue(element);
    }

    public static String getName(X8lContentNodeAttributeKey element) {
        return X8lContentNodeAttributeKeyUtil.getName(element);
    }

    public static PsiElement setName(X8lContentNodeAttributeKey element, String newName) {
        return X8lContentNodeAttributeKeyUtil.setName(element, newName);
    }

    public static ItemPresentation getPresentation(final X8lContentNodeAttributeKey element) {
        return X8lContentNodeAttributeKeyUtil.getPresentation(element);
    }
    //-----X8lContentNodeAttributeKeyUtil ends

    //-----X8lTextNodeContentUtil starts
    public static IElementType getTokenType(final X8lTextNodeContent element) {
        return X8lTextNodeContentUtil.getTokenType(element);
    }

    public static PsiElement getNameIdentifier(X8lTextNodeContent element) {
        return X8lTextNodeContentUtil.getNameIdentifier(element);
    }

    public static String getValue(X8lTextNodeContent element) {
        return X8lTextNodeContentUtil.getValue(element);
    }

    public static String getName(X8lTextNodeContent element) {
        return X8lTextNodeContentUtil.getName(element);
    }

    public static PsiElement setName(X8lTextNodeContent element, String name) {
        return X8lTextNodeContentUtil.setName(element, name);
    }

    public static ItemPresentation getPresentation(final X8lTextNodeContent element) {
        return X8lTextNodeContentUtil.getPresentation(element);
    }
    //-----X8lTextNodeContentUtil ends

    //-----X8lContentNodeUtil starts
    public static IElementType getTokenType(final X8lContentNode element) {
        return X8lContentNodeUtil.getTokenType(element);
    }

    public static PsiElement getNameIdentifier(X8lContentNode element) {
        return X8lContentNodeUtil.getNameIdentifier(element);
    }

    public static String getValue(X8lContentNode element) {
        return X8lContentNodeUtil.getValue(element);
    }

    public static String getName(X8lContentNode element) {
        return X8lContentNodeUtil.getName(element);
    }

    public static PsiElement setName(X8lContentNode element, String newName) {
        return X8lContentNodeUtil.setName(element, newName);
    }

    public static ItemPresentation getPresentation(final X8lContentNode element) {
        return X8lContentNodeUtil.getPresentation(element);
    }
    //-----X8lContentNodeUtil ends

    //-----X8lContentNodeHeadAreaUtil starts
    public static IElementType getTokenType(final X8lContentNodeHeadArea element) {
        return X8lContentNodeHeadAreaUtil.getTokenType(element);
    }

    public static PsiElement getNameIdentifier(X8lContentNodeHeadArea element) {
        return X8lContentNodeHeadAreaUtil.getNameIdentifier(element);
    }

    public static String getValue(X8lContentNodeHeadArea element) {
        return X8lContentNodeHeadAreaUtil.getValue(element);
    }

    public static String getName(X8lContentNodeHeadArea element) {
        return X8lContentNodeHeadAreaUtil.getName(element);
    }

    public static PsiElement setName(X8lContentNodeHeadArea element, String newName) {
        return X8lContentNodeHeadAreaUtil.setName(element, newName);
    }

    public static ItemPresentation getPresentation(final X8lContentNodeHeadArea element) {
        return X8lContentNodeHeadAreaUtil.getPresentation(element);
    }
    //-----X8lContentNodeHeadAreaUtil ends

    //-----X8lContentNodeChildrenAreaUtil starts
    public static IElementType getTokenType(final X8lContentNodeChildrenArea element) {
        return X8lContentNodeChildrenAreaUtil.getTokenType(element);
    }

    public static PsiElement getNameIdentifier(X8lContentNodeChildrenArea element) {
        return X8lContentNodeChildrenAreaUtil.getNameIdentifier(element);
    }

    public static String getValue(X8lContentNodeChildrenArea element) {
        return X8lContentNodeChildrenAreaUtil.getValue(element);
    }

    public static String getName(X8lContentNodeChildrenArea element) {
        return X8lContentNodeChildrenAreaUtil.getName(element);
    }

    public static PsiElement setName(X8lContentNodeChildrenArea element, String name) {
        return X8lContentNodeChildrenAreaUtil.setName(element, name);
    }

    public static ItemPresentation getPresentation(final X8lContentNodeChildrenArea element) {
        return X8lContentNodeChildrenAreaUtil.getPresentation(element);
    }
    //-----X8lContentNodeChildrenAreaUtil ends

//    public static String getKey(X8lProperty element) {
//        ASTNode keyNode = element.getNode().findChildByType(X8lTypes.CONTENT_NODE_ATTRIBUTE_KEY);
//        if (keyNode != null) {
//            // IMPORTANT: Convert embedded escaped spaces to simple spaces
//            return keyNode.getText().replaceAll("\\\\ ", " ");
//        } else {
//            return null;
//        }
//    }
//
//    public static String getValue(X8lProperty element) {
//        ASTNode valueNode = element.getNode().findChildByType(X8lTypes.VALUE);
//        if (valueNode != null) {
//            return valueNode.getText();
//        } else {
//            return null;
//        }
//    }
//
//    public static String getName(X8lProperty element) {
//        return getKey(element);
//    }
//
//    public static PsiElement setName(X8lProperty element, String newName) {
//        ASTNode keyNode = element.getNode().findChildByType(X8lTypes.CONTENT_NODE_ATTRIBUTE_KEY);
//        if (keyNode != null) {
//
//            X8lProperty property = X8lElementFactory.createProperty(element.getProject(), newName);
//            ASTNode newKeyNode = property.getFirstChild().getNode();
//            element.getNode().replaceChild(keyNode, newKeyNode);
//        }
//        return element;
//    }
//
//    public static PsiElement getNameIdentifier(X8lProperty element) {
//        ASTNode keyNode = element.getNode().findChildByType(X8lTypes.CONTENT_NODE_ATTRIBUTE_KEY);
//        if (keyNode != null) {
//            return keyNode.getPsi();
//        } else {
//            return null;
//        }
//    }
//
//    public static ItemPresentation getPresentation(final X8lProperty element) {
//        return new ItemPresentation() {
//            @Nullable
//            @Override
//            public String getPresentableText() {
//                return element.getKey();
//            }
//
//            @Nullable
//            @Override
//            public String getLocationString() {
//                return element.getContainingFile().getName();
//            }
//
//            @Nullable
//            @Override
//            public Icon getIcon(boolean unused) {
//                return X8lDataCenter.X8L_LANGUAGE_ICON;
//            }
//        };
//    }
}