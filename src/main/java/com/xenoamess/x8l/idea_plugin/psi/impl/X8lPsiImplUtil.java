package com.xenoamess.x8l.idea_plugin.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.xenoamess.x8l.X8lTree;
import com.xenoamess.x8l.idea_plugin.X8lDataCenter;
import com.xenoamess.x8l.idea_plugin.psi.*;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

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

    //-----X8lCommentNodeUtil starts
    public static String getKey(X8lContentNodeAttribute element) {
        ASTNode keyNode = element.getNode().findChildByType(X8lTypes.KEY);
        if (keyNode != null) {
            return X8lTree.untranscode(keyNode.getText());
        } else {
            return null;
        }
    }

    public static String getValue(X8lContentNodeAttribute element) {
        ASTNode valueNode = element.getNode().findChildByType(X8lTypes.VALUE);
        if (valueNode != null) {
            return valueNode.getText();
        } else {
            return null;
        }
    }

    public static String getName(X8lContentNodeAttribute element) {
        return getKey(element);
    }

    public static PsiElement setName(X8lContentNodeAttribute element, String newName) {
        ASTNode keyNode = element.getNode().findChildByType(X8lTypes.KEY);
        if (keyNode != null) {

            X8lContentNodeAttribute x8lContentNodeAttribute = X8lElementFactory.createX8lContentNodeAttribute(element.getProject(), newName);
            ASTNode newKeyNode = x8lContentNodeAttribute.getFirstChild().getNode();
            //todo fix it!
            element.getNode().replaceChild(keyNode, newKeyNode);
        }
        return element;
    }

    public static PsiElement getNameIdentifier(X8lContentNodeAttribute element) {
        ASTNode keyNode = element.getNode().findChildByType(X8lTypes.KEY);
        if (keyNode != null) {
            return keyNode.getPsi();
        } else {
            return null;
        }
    }

    public static ItemPresentation getPresentation(final X8lContentNodeAttribute element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return element.getKey();
            }

            @Nullable
            @Override
            public String getLocationString() {
                return element.getContainingFile().getName();
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return X8lDataCenter.X8L_LANGUAGE_ICON;
            }
        };
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