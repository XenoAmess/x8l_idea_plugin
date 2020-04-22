package com.xenoamess.x8l.psi;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.xenoamess.x8l.psi.X8lTypes;

%%

%{
  public X8lLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class X8lLexer
%implements com.intellij.lexer.FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

CRLF=\R
WHITE_SPACE=[\s\R]
//KEY_CHARACTER cannot contain '='
KEY_CHARACTER=[^ \s=<>%] | "%". | "%"\R
//but VALUE_CHARACTER can contain '='
VALUE_CHARACTER=[^ \s<>%] | "%". | "%"\R
SEPARATOR=[=]
TEXT_CHARACTER=[^<>%&] | "%". | "%"\R
COMMENT_CHARACTER=[^>%] | "%". | "%"\R
LEFT_BRACKET = "<"
RIGHT_BRACKET = ">"
TEXT_SEPARATOR = "&"


%state HEAD_AREA
%state CHILDREN_AREA
%state COMMENT_AREA
%state WAITING_VALUE


%%
<YYINITIAL>         {TEXT_CHARACTER}+                                          { yybegin(YYINITIAL); return X8lTypes.TEXT_NODE_CONTENT_STRING; }
<YYINITIAL>         {TEXT_SEPARATOR}                                          { yybegin(YYINITIAL); return X8lTypes.TEXT_SEPARATOR; }

<YYINITIAL>         {LEFT_BRACKET}                                               { yybegin(HEAD_AREA); return X8lTypes.LEFT_BRACKET; }
<YYINITIAL>         {LEFT_BRACKET}{WHITE_SPACE}*{LEFT_BRACKET}                   { yybegin(COMMENT_AREA); return X8lTypes.COMMENT_NODE_LEFT_BRACKET; }

<COMMENT_AREA>      {COMMENT_CHARACTER}+                                          { yybegin(COMMENT_AREA); return X8lTypes.COMMENT_NODE_CONTENT_STRING; }
<COMMENT_AREA>      {RIGHT_BRACKET}                                              { yybegin(YYINITIAL); return X8lTypes.COMMENT_NODE_RIGHT_BRACKET; }

<HEAD_AREA>         {WHITE_SPACE}+                                             { return X8lTypes.WHITE_SPACE_CONTENT_STRING; }
<WAITING_VALUE>     {WHITE_SPACE}+                                             { return X8lTypes.WHITE_SPACE_CONTENT_STRING; }

<HEAD_AREA>         {KEY_CHARACTER}+                                           { yybegin(HEAD_AREA); return X8lTypes.CONTENT_NODE_ATTRIBUTE_KEY_CONTENT_STRING; }
<HEAD_AREA>         {SEPARATOR}                                                { yybegin(WAITING_VALUE); return X8lTypes.SEPARATOR; }
<HEAD_AREA>         {RIGHT_BRACKET}                                              { yybegin(YYINITIAL); return X8lTypes.RIGHT_BRACKET; }
<YYINITIAL>         {RIGHT_BRACKET}                                              { yybegin(YYINITIAL); return X8lTypes.RIGHT_BRACKET; }

<WAITING_VALUE>     {VALUE_CHARACTER}+                                         { yybegin(HEAD_AREA); return X8lTypes.CONTENT_NODE_ATTRIBUTE_VALUE_CONTENT_STRING; }

[^]                                                                            { return TokenType.BAD_CHARACTER; }