//package com.xenoamess.x8l.idea_plugin;
//
//import com.intellij.formatting.*;
//import com.intellij.lang.ASTNode;
//import com.intellij.psi.TokenType;
//import com.intellij.psi.formatter.common.AbstractBlock;
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class X8lBlock extends AbstractBlock {
//    protected X8lBlock(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment) {
//        super(node, wrap, alignment);
//    }
//
//    @Override
//    protected List<Block> buildChildren() {
//        List<Block> blocks = new ArrayList<Block>();
//        ASTNode child = myNode.getFirstChildNode();
//        while (child != null) {
//            if (child.getElementType() != TokenType.WHITE_SPACE) {
//                Block block = new X8lBlock(child, Wrap.createWrap(WrapType.NONE, false), Alignment.createAlignment());
//                blocks.add(block);
//            }
//            child = child.getTreeNext();
//        }
//        return blocks;
//    }
//
//    @Override
//    public Indent getIndent() {
//        return Indent.getNoneIndent();
//    }
//
//    @Nullable
//    @Override
//    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
//        if (!(child1 instanceof X8lBlock)) {
//            return Spacing.createSpacing(0, 0, 0, false, 0);
//        }
//        if (!(child2 instanceof X8lBlock)) {
//            return Spacing.createSpacing(0, 0, 0, false, 0);
//        }
//        X8lBlock x8lBlock1 = (X8lBlock) child1;
//        X8lBlock x8lBlock2 = (X8lBlock) child2;
//        x8lBlock1.getMyNode().getElementType();
//        x8lBlock2.getMyNode().getElementType();
//        return Spacing.createSpacing(0, 0, 0, false, 0);
//    }
//
//    @Override
//    public boolean isLeaf() {
//        return myNode.getFirstChildNode() == null;
//    }
//
//    public ASTNode getMyNode() {
//        return myNode;
//    }
//}