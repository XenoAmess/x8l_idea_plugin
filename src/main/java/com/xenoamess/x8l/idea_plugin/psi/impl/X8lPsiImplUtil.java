package com.xenoamess.x8l.idea_plugin.psi.impl;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.xenoamess.x8l.idea_plugin.psi.X8lCommentNode;
import com.xenoamess.x8l.idea_plugin.psi.X8lContentNodeAttribute;
import com.xenoamess.x8l.idea_plugin.psi.X8lTextNode;

public class X8lPsiImplUtil {

    //-----X8lTextNodeUtils starts

    public static String getValue(X8lTextNode element) {
        return X8lTextNodeUtil.getValue(element);
    }

    public static ItemPresentation getPresentation(final X8lTextNode element) {
        return X8lTextNodeUtil.getPresentation(element);
    }

    //-----X8lTextNodeUtils ends

    //-----X8lCommentNodeUtil starts

    public static IElementType getTokenType(final X8lCommentNode x8lCommentNode) {
        return X8lCommentNodeUtil.getTokenType(x8lCommentNode);
    }

    //-----X8lCommentNodeUtil ends

    //-----X8lContentNodeAttributeUtil starts
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

    public static PsiElement getNameIdentifier(X8lContentNodeAttribute element) {
        return X8lContentNodeAttributeUtil.getNameIdentifier(element);
    }

    public static ItemPresentation getPresentation(final X8lContentNodeAttribute element) {
        return X8lContentNodeAttributeUtil.getPresentation(element);
    }

    //-----X8lCommentNodeUtil ends


//    public static String getKey(X8lProperty element) {
//        ASTNode keyNode = element.getNode().findChildByType(X8lTypes.KEY);
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
//        ASTNode keyNode = element.getNode().findChildByType(X8lTypes.KEY);
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
//        ASTNode keyNode = element.getNode().findChildByType(X8lTypes.KEY);
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