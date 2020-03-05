// This is a generated file. Not intended for manual editing.
package com.xenoamess.x8l.idea_plugin.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.xenoamess.x8l.idea_plugin.psi.X8lTypes.*;
import static com.xenoamess.x8l.idea_plugin.parser.X8lParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class X8lParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return x8lFile(b, l + 1);
  }

  /* ********************************************************** */
  // "<<" TEXT_STRING ? ">"
  public static boolean COMMENT_NODE(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "COMMENT_NODE")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COMMENT_NODE, "<comment node>");
    r = consumeToken(b, "<<");
    r = r && COMMENT_NODE_1(b, l + 1);
    r = r && consumeToken(b, ">");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // TEXT_STRING ?
  private static boolean COMMENT_NODE_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "COMMENT_NODE_1")) return false;
    consumeToken(b, TEXT_STRING);
    return true;
  }

  /* ********************************************************** */
  // LEFT_BRACE CONTENT_NODE_HEAD_AREA RIGHT_BRACE CONTENT_NODE_CHILDREN_AREA RIGHT_BRACE
  public static boolean CONTENT_NODE(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CONTENT_NODE")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONTENT_NODE, "<content node>");
    r = LEFT_BRACE(b, l + 1);
    r = r && CONTENT_NODE_HEAD_AREA(b, l + 1);
    r = r && RIGHT_BRACE(b, l + 1);
    r = r && CONTENT_NODE_CHILDREN_AREA(b, l + 1);
    r = r && RIGHT_BRACE(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (KEY WHITE_SPACE* SEPARATOR WHITE_SPACE* VALUE) | KEY
  public static boolean CONTENT_NODE_ATTRIBUTE(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CONTENT_NODE_ATTRIBUTE")) return false;
    if (!nextTokenIs(b, KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = CONTENT_NODE_ATTRIBUTE_0(b, l + 1);
    if (!r) r = consumeToken(b, KEY);
    exit_section_(b, m, CONTENT_NODE_ATTRIBUTE, r);
    return r;
  }

  // KEY WHITE_SPACE* SEPARATOR WHITE_SPACE* VALUE
  private static boolean CONTENT_NODE_ATTRIBUTE_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CONTENT_NODE_ATTRIBUTE_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEY);
    r = r && CONTENT_NODE_ATTRIBUTE_0_1(b, l + 1);
    r = r && consumeToken(b, SEPARATOR);
    r = r && CONTENT_NODE_ATTRIBUTE_0_3(b, l + 1);
    r = r && consumeToken(b, VALUE);
    exit_section_(b, m, null, r);
    return r;
  }

  // WHITE_SPACE*
  private static boolean CONTENT_NODE_ATTRIBUTE_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CONTENT_NODE_ATTRIBUTE_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, WHITE_SPACE)) break;
      if (!empty_element_parsed_guard_(b, "CONTENT_NODE_ATTRIBUTE_0_1", c)) break;
    }
    return true;
  }

  // WHITE_SPACE*
  private static boolean CONTENT_NODE_ATTRIBUTE_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CONTENT_NODE_ATTRIBUTE_0_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, WHITE_SPACE)) break;
      if (!empty_element_parsed_guard_(b, "CONTENT_NODE_ATTRIBUTE_0_3", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // (TEXT_NODE (COMMENT_NODE|CONTENT_NODE))* TEXT_NODE
  public static boolean CONTENT_NODE_CHILDREN_AREA(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CONTENT_NODE_CHILDREN_AREA")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONTENT_NODE_CHILDREN_AREA, "<content node children area>");
    r = CONTENT_NODE_CHILDREN_AREA_0(b, l + 1);
    r = r && TEXT_NODE(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (TEXT_NODE (COMMENT_NODE|CONTENT_NODE))*
  private static boolean CONTENT_NODE_CHILDREN_AREA_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CONTENT_NODE_CHILDREN_AREA_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!CONTENT_NODE_CHILDREN_AREA_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "CONTENT_NODE_CHILDREN_AREA_0", c)) break;
    }
    return true;
  }

  // TEXT_NODE (COMMENT_NODE|CONTENT_NODE)
  private static boolean CONTENT_NODE_CHILDREN_AREA_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CONTENT_NODE_CHILDREN_AREA_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = TEXT_NODE(b, l + 1);
    r = r && CONTENT_NODE_CHILDREN_AREA_0_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // COMMENT_NODE|CONTENT_NODE
  private static boolean CONTENT_NODE_CHILDREN_AREA_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CONTENT_NODE_CHILDREN_AREA_0_0_1")) return false;
    boolean r;
    r = COMMENT_NODE(b, l + 1);
    if (!r) r = CONTENT_NODE(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // WHITE_SPACE* (CONTENT_NODE_ATTRIBUTE WHITE_SPACE+)* ((CONTENT_NODE_ATTRIBUTE WHITE_SPACE*) ? )
  public static boolean CONTENT_NODE_HEAD_AREA(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CONTENT_NODE_HEAD_AREA")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONTENT_NODE_HEAD_AREA, "<content node head area>");
    r = CONTENT_NODE_HEAD_AREA_0(b, l + 1);
    r = r && CONTENT_NODE_HEAD_AREA_1(b, l + 1);
    r = r && CONTENT_NODE_HEAD_AREA_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // WHITE_SPACE*
  private static boolean CONTENT_NODE_HEAD_AREA_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CONTENT_NODE_HEAD_AREA_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, WHITE_SPACE)) break;
      if (!empty_element_parsed_guard_(b, "CONTENT_NODE_HEAD_AREA_0", c)) break;
    }
    return true;
  }

  // (CONTENT_NODE_ATTRIBUTE WHITE_SPACE+)*
  private static boolean CONTENT_NODE_HEAD_AREA_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CONTENT_NODE_HEAD_AREA_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!CONTENT_NODE_HEAD_AREA_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "CONTENT_NODE_HEAD_AREA_1", c)) break;
    }
    return true;
  }

  // CONTENT_NODE_ATTRIBUTE WHITE_SPACE+
  private static boolean CONTENT_NODE_HEAD_AREA_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CONTENT_NODE_HEAD_AREA_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = CONTENT_NODE_ATTRIBUTE(b, l + 1);
    r = r && CONTENT_NODE_HEAD_AREA_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // WHITE_SPACE+
  private static boolean CONTENT_NODE_HEAD_AREA_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CONTENT_NODE_HEAD_AREA_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, WHITE_SPACE);
    while (r) {
      int c = current_position_(b);
      if (!consumeToken(b, WHITE_SPACE)) break;
      if (!empty_element_parsed_guard_(b, "CONTENT_NODE_HEAD_AREA_1_0_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // (CONTENT_NODE_ATTRIBUTE WHITE_SPACE*) ?
  private static boolean CONTENT_NODE_HEAD_AREA_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CONTENT_NODE_HEAD_AREA_2")) return false;
    CONTENT_NODE_HEAD_AREA_2_0(b, l + 1);
    return true;
  }

  // CONTENT_NODE_ATTRIBUTE WHITE_SPACE*
  private static boolean CONTENT_NODE_HEAD_AREA_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CONTENT_NODE_HEAD_AREA_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = CONTENT_NODE_ATTRIBUTE(b, l + 1);
    r = r && CONTENT_NODE_HEAD_AREA_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // WHITE_SPACE*
  private static boolean CONTENT_NODE_HEAD_AREA_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CONTENT_NODE_HEAD_AREA_2_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, WHITE_SPACE)) break;
      if (!empty_element_parsed_guard_(b, "CONTENT_NODE_HEAD_AREA_2_0_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // "<"
  public static boolean LEFT_BRACE(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LEFT_BRACE")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LEFT_BRACE, "<left brace>");
    r = consumeToken(b, "<");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ">"
  public static boolean RIGHT_BRACE(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RIGHT_BRACE")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RIGHT_BRACE, "<right brace>");
    r = consumeToken(b, ">");
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // TEXT_STRING ?
  public static boolean TEXT_NODE(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TEXT_NODE")) return false;
    Marker m = enter_section_(b, l, _NONE_, TEXT_NODE, "<text node>");
    consumeToken(b, TEXT_STRING);
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // CONTENT_NODE_CHILDREN_AREA
  static boolean x8lFile(PsiBuilder b, int l) {
    return CONTENT_NODE_CHILDREN_AREA(b, l + 1);
  }

}
