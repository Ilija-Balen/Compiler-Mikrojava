package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{

	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}

%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }

"program"   { return new_symbol(sym.PROG, yytext()); }
"print" 	{ return new_symbol(sym.PRINT, yytext()); }
"return" 	{ return new_symbol(sym.RETURN, yytext()); }
"void" 		{ return new_symbol(sym.VOID, yytext()); }
"if"	{ return new_symbol(sym.IF, yytext()); }
"else"	{ return new_symbol(sym.ELSE, yytext()); }
"break"	{ return new_symbol(sym.BREAK, yytext()); }
"class"	{ return new_symbol(sym.CLASS, yytext()); }
"const"	{ return new_symbol(sym.CONST, yytext()); }
"new"	{ return new_symbol(sym.NEW, yytext()); }
"read"	{ return new_symbol(sym.READ, yytext()); }
"extends"	{ return new_symbol(sym.EXTENDS, yytext()); }
"continue"	{ return new_symbol(sym.CONTINUE, yytext()); }
"for"	{ return new_symbol(sym.FOR, yytext()); }
"static"	{ return new_symbol(sym.STATIC, yytext()); }
"namespace"	{ return new_symbol(sym.NAMEPACE, yytext()); }


"++"		{ return new_symbol(sym.INC, yytext()); }
"+"			{ return new_symbol(sym.PLUS, yytext()); }
"--"		{yybegin(YYINITIAL); return new_symbol(sym.DEC, yytext());}
"-"			{yybegin(YYINITIAL); return new_symbol(sym.MINUS, yytext());}			
"=>" 		{ return new_symbol(sym.LAMBDA, yytext()); }
"==" 		{ return new_symbol(sym.IFEQU, yytext()); }
"=" 		{ return new_symbol(sym.EQUAL, yytext()); }
";" 		{ return new_symbol(sym.SEMI, yytext()); }
"," 		{ return new_symbol(sym.COMMA, yytext()); }
"(" 		{ return new_symbol(sym.LPAREN, yytext()); }
")" 		{ return new_symbol(sym.RPAREN, yytext()); }
"{" 		{ return new_symbol(sym.LBRACE, yytext()); }
"}"			{ return new_symbol(sym.RBRACE, yytext()); }
"*" 		{ return new_symbol(sym.MUL, yytext()); }
"/" 		{ return new_symbol(sym.DIVISION, yytext()); }
"%" 		{ return new_symbol(sym.DIV, yytext()); }
"!=" 		{ return new_symbol(sym.NOTEQU, yytext()); }
">=" 		{ return new_symbol(sym.GTEQU, yytext()); }
">" 		{ return new_symbol(sym.GT, yytext()); }
"<=" 		{ return new_symbol(sym.LTEQU, yytext()); }
"<" 		{ return new_symbol(sym.LT, yytext()); }
"&&" 		{ return new_symbol(sym.AND, yytext()); }
"||" 		{ return new_symbol(sym.OR, yytext()); }
":" 		{ return new_symbol(sym.COLON, yytext()); }
"[" 		{ return new_symbol(sym.LSQBRACE, yytext()); }
"]" 		{ return new_symbol(sym.RSQBRACE, yytext()); }
"."			{ return new_symbol(sym.DOT, yytext()); }

<YYINITIAL> "//" 		     { yybegin(COMMENT); }
<COMMENT> "\r\n" { yybegin(YYINITIAL); }
<COMMENT> .      { yybegin(COMMENT); }



"true" | "false" 	{return new_symbol(sym.BOOL_IDENT, new Boolean(yytext())); }
'([a-z]|[A-Z])'		{return new_symbol(sym.CHAR_CONST, new Character(yytext().charAt(1)));}

[0-9]+  { return new_symbol(sym.NUMBER, new Integer (yytext())); }
([a-z]|[A-Z])[a-zA-Z0-9_]* 	{return new_symbol (sym.IDENT, yytext()); }


. { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1)); }






