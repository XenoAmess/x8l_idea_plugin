// This is a generated file. Not intended for manual editing.
package com.xenoamess.x8l.idea_plugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface X8lContentNode extends PsiElement {

  @NotNull
  X8lContentNodeChildrenArea getContentNodeChildrenArea();

  @NotNull
  X8lContentNodeHeadArea getContentNodeHeadArea();

  @NotNull
  X8lLeftBrace getLeftBrace();

  @NotNull
  List<X8lRightBrace> getRightBraceList();

}
