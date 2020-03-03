// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.xenoamess.x8l.idea_plugin;

import com.intellij.psi.codeStyle.*;

public class X8lCodeStyleSettings extends CustomCodeStyleSettings {
    public X8lCodeStyleSettings(CodeStyleSettings settings) {
        super("X8lCodeStyleSettings", settings);
    }
}