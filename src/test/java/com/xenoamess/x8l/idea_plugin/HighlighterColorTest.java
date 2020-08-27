package com.xenoamess.x8l.idea_plugin;

import com.intellij.psi.tree.IElementType;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import static com.xenoamess.x8l.idea_plugin.X8lSyntaxHighlighter.getTokenHighlightsStatic;

public class HighlighterColorTest {
    protected static final IElementType[] I_ELEMENT_TYPES = X8lAnnotator.I_ELEMENT_TYPES;

    @Test
    public void test() {
        for (IElementType type : I_ELEMENT_TYPES) {
            Assertions.assertTrue(getTokenHighlightsStatic(type).length != 0, type.toString() + " have empty");
        }
    }
}
