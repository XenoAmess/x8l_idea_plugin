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
  // COMMENT_NODE_LEFT_BRACKET COMMENT_NODE_CONTENT COMMENT_NODE_RIGHT_BRACKET
  public static boolean COMMENT_NODE(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "COMMENT_NODE")) return false;
    if (!nextTokenIs(b, COMMENT_NODE_LEFT_BRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMENT_NODE_LEFT_BRACKET);
    r = r && COMMENT_NODE_CONTENT(b, l + 1);
    r = r && consumeToken(b, COMMENT_NODE_RIGHT_BRACKET);
    exit_section_(b, m, COMMENT_NODE, r);
    return r;
  }

  /* ********************************************************** */
  // COMMENT_NODE_CONTENT_STRING?
  public static boolean COMMENT_NODE_CONTENT(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "COMMENT_NODE_CONTENT")) return false;
    Marker m = enter_section_(b, l, _NONE_, COMMENT_NODE_CONTENT, "<comment node content>");
    consumeToken(b, COMMENT_NODE_CONTENT_STRING);
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // LEFT_BRACKET CONTENT_NODE_HEAD_AREA RIGHT_BRACKET CONTENT_NODE_CHILDREN_AREA RIGHT_BRACKET
  public static boolean CONTENT_NODE(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CONTENT_NODE")) return false;
    if (!nextTokenIs(b, LEFT_BRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LEFT_BRACKET);
    r = r && CONTENT_NODE_HEAD_AREA(b, l + 1);
    r = r && consumeToken(b, RIGHT_BRACKET);
    r = r && CONTENT_NODE_CHILDREN_AREA(b, l + 1);
    r = r && consumeToken(b, RIGHT_BRACKET);
    exit_section_(b, m, CONTENT_NODE, r);
    return r;
  }

  /* ********************************************************** */
  // (WHITE_SPACE CONTENT_NODE_ATTRIBUTE_KEY WHITE_SPACE SEPARATOR WHITE_SPACE CONTENT_NODE_ATTRIBUTE_VALUE WHITE_SPACE) | WHITE_SPACE CONTENT_NODE_ATTRIBUTE_KEY WHITE_SPACE
  public static boolean CONTENT_NODE_ATTRIBUTE(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CONTENT_NODE_ATTRIBUTE")) return false;
    if (!nextTokenIs(b, "<content node attribute>", CONTENT_NODE_ATTRIBUTE_KEY_CONTENT_STRING, WHITE_SPACE_CONTENT_STRING)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONTENT_NODE_ATTRIBUTE, "<content node attribute>");
    r = CONTENT_NODE_ATTRIBUTE_0(b, l + 1);
    if (!r) r = CONTENT_NODE_ATTRIBUTE_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // WHITE_SPACE CONTENT_NODE_ATTRIBUTE_KEY WHITE_SPACE SEPARATOR WHITE_SPACE CONTENT_NODE_ATTRIBUTE_VALUE WHITE_SPACE
  private static boolean CONTENT_NODE_ATTRIBUTE_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CONTENT_NODE_ATTRIBUTE_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = WHITE_SPACE(b, l + 1);
    r = r && CONTENT_NODE_ATTRIBUTE_KEY(b, l + 1);
    r = r && WHITE_SPACE(b, l + 1);
    r = r && consumeToken(b, SEPARATOR);
    r = r && WHITE_SPACE(b, l + 1);
    r = r && CONTENT_NODE_ATTRIBUTE_VALUE(b, l + 1);
    r = r && WHITE_SPACE(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // WHITE_SPACE CONTENT_NODE_ATTRIBUTE_KEY WHITE_SPACE
  private static boolean CONTENT_NODE_ATTRIBUTE_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CONTENT_NODE_ATTRIBUTE_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = WHITE_SPACE(b, l + 1);
    r = r && CONTENT_NODE_ATTRIBUTE_KEY(b, l + 1);
    r = r && WHITE_SPACE(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // CONTENT_NODE_ATTRIBUTE_KEY_CONTENT_STRING
  public static boolean CONTENT_NODE_ATTRIBUTE_KEY(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CONTENT_NODE_ATTRIBUTE_KEY")) return false;
    if (!nextTokenIs(b, CONTENT_NODE_ATTRIBUTE_KEY_CONTENT_STRING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CONTENT_NODE_ATTRIBUTE_KEY_CONTENT_STRING);
    exit_section_(b, m, CONTENT_NODE_ATTRIBUTE_KEY, r);
    return r;
  }

  /* ********************************************************** */
  // CONTENT_NODE_ATTRIBUTE_VALUE_CONTENT_STRING
  public static boolean CONTENT_NODE_ATTRIBUTE_VALUE(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CONTENT_NODE_ATTRIBUTE_VALUE")) return false;
    if (!nextTokenIs(b, CONTENT_NODE_ATTRIBUTE_VALUE_CONTENT_STRING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CONTENT_NODE_ATTRIBUTE_VALUE_CONTENT_STRING);
    exit_section_(b, m, CONTENT_NODE_ATTRIBUTE_VALUE, r);
    return r;
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
  // WHITE_SPACE CONTENT_NODE_ATTRIBUTE* WHITE_SPACE
  public static boolean CONTENT_NODE_HEAD_AREA(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CONTENT_NODE_HEAD_AREA")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONTENT_NODE_HEAD_AREA, "<content node head area>");
    r = WHITE_SPACE(b, l + 1);
    r = r && CONTENT_NODE_HEAD_AREA_1(b, l + 1);
    r = r && WHITE_SPACE(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // CONTENT_NODE_ATTRIBUTE*
  private static boolean CONTENT_NODE_HEAD_AREA_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CONTENT_NODE_HEAD_AREA_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!CONTENT_NODE_ATTRIBUTE(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "CONTENT_NODE_HEAD_AREA_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // CONTENT_NODE_CHILDREN_AREA
  public static boolean ROOT_NODE_CHILDREN_AREA(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ROOT_NODE_CHILDREN_AREA")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ROOT_NODE_CHILDREN_AREA, "<root node children area>");
    r = CONTENT_NODE_CHILDREN_AREA(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // TEXT_NODE_CONTENT
  public static boolean TEXT_NODE(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TEXT_NODE")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TEXT_NODE, "<text node>");
    r = TEXT_NODE_CONTENT(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // TEXT_NODE_CONTENT_STRING?
  public static boolean TEXT_NODE_CONTENT(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TEXT_NODE_CONTENT")) return false;
    Marker m = enter_section_(b, l, _NONE_, TEXT_NODE_CONTENT, "<text node content>");
    consumeToken(b, TEXT_NODE_CONTENT_STRING);
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // WHITE_SPACE_CONTENT_STRING?
  public static boolean WHITE_SPACE(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WHITE_SPACE")) return false;
    Marker m = enter_section_(b, l, _NONE_, WHITE_SPACE, "<white space>");
    consumeToken(b, WHITE_SPACE_CONTENT_STRING);
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // ROOT_NODE_CHILDREN_AREA
  static boolean x8lFile(PsiBuilder b, int l) {
    return ROOT_NODE_CHILDREN_AREA(b, l + 1);
  }

}
