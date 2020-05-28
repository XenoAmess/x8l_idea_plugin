package com.xenoamess.x8l.psi;

import com.intellij.psi.tree.IElementType;
import com.xenoamess.x8l.idea_plugin.X8lLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * @author XenoAmess
 */
public class X8lElementType extends IElementType {

    public X8lElementType(@NotNull @NonNls String debugName) {
        super(debugName, X8lLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "X8l." + super.toString();
    }

}
