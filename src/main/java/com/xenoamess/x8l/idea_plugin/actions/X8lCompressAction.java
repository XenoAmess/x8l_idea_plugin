package com.xenoamess.x8l.idea_plugin.actions;

import com.xenoamess.x8l.X8lTree;
import org.jetbrains.annotations.NotNull;

public class X8lCompressAction extends X8lFileAction {

    @Override
    public void doJob(@NotNull X8lTree x8lTree) {
        x8lTree.trimForce();
    }
}
