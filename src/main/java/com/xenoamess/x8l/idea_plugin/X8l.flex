package com.xenoamess.x8l.idea_plugin;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.xenoamess.x8l.idea_plugin.psi.X8lTypes;
import com.intellij.psi.TokenType;

%%

%class X8lLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

CRLF=\R
WHITE_SPACE=\s
NORMAL_CHARACTOR=[^ \s=<>%]
KEY_CHARACTER={NORMAL_CHARACTOR} | %.
VALUE_CHARACTER={NORMAL_CHARACTOR} | %.
SEPARATOR==
TEXT_CHARACTER=[^<>%] | %.

%state WAITING_VALUE

%%
<YYINITIAL> {
  {WHITE_SPACE}                { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
  {KEY_CHARACTER}+             { yybegin(YYINITIAL); return X8lTypes.KEY; }
  {SEPARATOR}                  { yybegin(WAITING_VALUE); return X8lTypes.SEPARATOR; }
  {TEXT_CHARACTER}+            { yybegin(YYINITIAL); return X8lTypes.TEXT_STRING; }
}
<WAITING_VALUE> {
  {VALUE_CHARACTER}+           { yybegin(YYINITIAL); return X8lTypes.VALUE; }
}

[^]                            { return TokenType.BAD_CHARACTER; }