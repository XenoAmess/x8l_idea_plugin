package com.xenoamess.x8l.idea_plugin;

import com.intellij.psi.tree.IElementType;
import com.xenoamess.x8l.idea_plugin.psi.X8lTypes;
import com.intellij.psi.TokenType;

%%

%class X8lLexer
%implements com.intellij.lexer.FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

CRLF=\R
WHITE_SPACE=\s
KEY_CHARACTER=[^ \s=<>%] | "%". | "%"\R
VALUE_CHARACTER=[^ \s=<>%] | "%". | "%"\R
SEPARATOR=[=]
TEXT_CHARACTER=[^<>%] | "%". | "%"\R
LEFT_BRACKET = "<"
RIGHT_BRACKET = ">"


%state HEAD_AREA
%state CHILDREN_AREA
%state COMMENT_AREA
%state WAITING_VALUE


%%
<YYINITIAL>         {TEXT_CHARACTER}+                                          { yybegin(YYINITIAL); return X8lTypes.TEXT_STRING; }

<YYINITIAL>         {LEFT_BRACKET}                                               { yybegin(HEAD_AREA); return X8lTypes.LEFT_BRACKET; }
<YYINITIAL>         {LEFT_BRACKET}{WHITE_SPACE}*{LEFT_BRACKET}                   { yybegin(COMMENT_AREA); return X8lTypes.COMMENT_NODE_LEFT_BRACKET; }

<COMMENT_AREA>      {TEXT_CHARACTER}+                                          { yybegin(COMMENT_AREA); return X8lTypes.COMMENT_NODE_CONTENT; }
<COMMENT_AREA>      {RIGHT_BRACKET}                                              { yybegin(YYINITIAL); return X8lTypes.COMMENT_NODE_RIGHT_BRACKET; }

<HEAD_AREA>         {WHITE_SPACE}+                                             { return TokenType.WHITE_SPACE; }
<WAITING_VALUE>     {WHITE_SPACE}+                                             { return TokenType.WHITE_SPACE; }

<HEAD_AREA>         {KEY_CHARACTER}+                                           { yybegin(HEAD_AREA); return X8lTypes.KEY; }
<HEAD_AREA>         {SEPARATOR}                                                { yybegin(WAITING_VALUE); return X8lTypes.SEPARATOR; }
<HEAD_AREA>         {RIGHT_BRACKET}                                              { yybegin(YYINITIAL); return X8lTypes.RIGHT_BRACKET; }
<YYINITIAL>         {RIGHT_BRACKET}                                              { yybegin(YYINITIAL); return X8lTypes.RIGHT_BRACKET; }

<WAITING_VALUE>     {VALUE_CHARACTER}+                                         { yybegin(HEAD_AREA); return X8lTypes.VALUE; }

[^]                                                                            { return TokenType.BAD_CHARACTER; }