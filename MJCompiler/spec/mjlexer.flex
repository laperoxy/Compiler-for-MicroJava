
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

"program"   { return new_symbol(sym.PROG, yytext());}
"break"		{ return new_symbol(sym.BREAK, yytext());}
"class"		{ return new_symbol(sym.CLASS, yytext());}
"enum"		{ return new_symbol(sym.ENUM, yytext());}
"else"		{ return new_symbol(sym.ELSE, yytext());}
"const"		{ return new_symbol(sym.CONST, yytext());}
"if"		{ return new_symbol(sym.IF, yytext());}
"do"		{ return new_symbol(sym.DO, yytext());}
"while"		{ return new_symbol(sym.WHILE, yytext());}
"new"		{ return new_symbol(sym.NEW, yytext());}
"print" 	{ return new_symbol(sym.PRINT, yytext()); }
"read"		{ return new_symbol(sym.READ, yytext());}
"return" 	{ return new_symbol(sym.RETURN, yytext()); }
"void" 		{ return new_symbol(sym.VOID, yytext()); }
"extends"	{ return new_symbol(sym.EXTENDS, yytext());}
"continue"	{ return new_symbol(sym.CONTINUE, yytext());}
"this"	    { return new_symbol(sym.THIS, yytext());}
"super"	    { return new_symbol(sym.SUPER, yytext());}
"goto"  	{ return new_symbol(sym.GOTO, yytext());}
"record"    { return new_symbol(sym.RECORD, yytext());}
"+" 		{ return new_symbol(sym.PLUS, yytext()); }
"-"			{ return new_symbol(sym.MINUS, yytext()); }
"=" 		{ return new_symbol(sym.EQUAL, yytext()); }
"++"		{ return new_symbol(sym.PLUSPLUS, yytext()); }
"--"		{ return new_symbol(sym.MINUSMINUS, yytext()); }
"||"		{ return new_symbol(sym.OR, yytext()); }
"&&"		{ return new_symbol(sym.AND, yytext()); }
"new"		{ return new_symbol(sym.NEW, yytext()); }
"."			{ return new_symbol(sym.DOT, yytext()); }
"=="		{ return new_symbol(sym.SAME, yytext()); }
"!="		{ return new_symbol(sym.NOTSAME, yytext()); }
">"			{ return new_symbol(sym.GREATER, yytext()); }
">="		{ return new_symbol(sym.GREATEREQ, yytext()); }
"<"			{ return new_symbol(sym.LESS, yytext()); }
"<="		{ return new_symbol(sym.LESSEQ, yytext()); }
"*"			{ return new_symbol(sym.ASTERIX, yytext()); }
"/"			{ return new_symbol(sym.SLASH, yytext()); }
"%"			{ return new_symbol(sym.PERCENT, yytext()); }
";" 		{ return new_symbol(sym.SEMI, yytext()); }
":" 		{ return new_symbol(sym.COLON, yytext()); }
"," 		{ return new_symbol(sym.COMMA, yytext()); }
"(" 		{ return new_symbol(sym.LPAREN, yytext()); }
")" 		{ return new_symbol(sym.RPAREN, yytext()); }
"{" 		{ return new_symbol(sym.LBRACE, yytext()); }
"}"			{ return new_symbol(sym.RBRACE, yytext()); }
"["			{ return new_symbol(sym.LANGLE, yytext()); }
"]"			{ return new_symbol(sym.RANGLE, yytext()); }
"^"			{ return new_symbol(sym.POW, yytext()); }



"//" {yybegin(COMMENT);}
<COMMENT> . {yybegin(COMMENT);}
<COMMENT> "\r\n" { yybegin(YYINITIAL); }

(true|false) { return new_symbol(sym.BOOL, yytext()); }
('([a-z]|[A-Z])') | ('\+') | ('-') { return new_symbol(sym.CHAR, yytext()); }
[0-9]+  { return new_symbol(sym.NUMBER, new Integer (yytext())); }
([a-z]|[A-Z])[a-z|A-Z|0-9|_]* 	{return new_symbol (sym.IDENT, yytext()); }

. { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1)); }










