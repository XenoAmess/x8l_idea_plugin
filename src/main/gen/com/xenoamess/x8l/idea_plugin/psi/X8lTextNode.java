// This is a generated file. Not intended for manual editing.
package com.xenoamess.x8l.idea_plugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteral;
import com.intellij.navigation.ItemPresentation;

public interface X8lTextNode extends PsiLiteral {

  @NotNull
  X8lTextNodeContent getTextNodeContent();

  String getValue();

  ItemPresentation getPresentation();

}
