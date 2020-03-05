// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.xenoamess.x8l.idea_plugin.psi;

import com.xenoamess.x8l.X8lTree;

public interface X8lRootElement extends X8lNamedElement {

    public X8lTree getPrimitiveX8lTree();
}