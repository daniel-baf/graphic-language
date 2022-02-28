/***************************************/
/********** USER CODE SECTION **********/
/***************************************/

package jefemayoneso.compi1prac1.Lexer;
import java_cup.runtime.*;
import jefemayoneso.compi1prac1.Parser.sym;
import java.util.ArrayList;
import jefemayoneso.compi1prac1.Utilities.CommonError;

/***************************************/
/**********   CONFIGURATION   **********/
/***************************************/
%%
%class GraphLangLexer
%type java_cup.runtime.Symbol
%cup
%full
%line
%column
%public

// USER CODE

%{
    private ArrayList<char[]> mathSymTknsPos = new ArrayList<>();
    private ArrayList<CommonError> errors = new ArrayList<>();

    private void saveInfMathTkn() {
        char L = ' ';
        char R = ' ';
        char C = ' ';
        try {
            C = yycharat(0);
        } catch(Exception e) {
            System.out.println("ERROR getting char at Lexer class");
        }
        try {
            L = yycharat(-1);

        } catch(Exception e) {
            System.out.println("ERROR getting char at Lexer class");
        }
        try {
            R = yycharat(1);
        } catch(Exception e) {
            System.out.println("ERROR getting char at Lexer class");
        }
        mathSymTknsPos.add(new char[]{L, C, R});
    }
    public ArrayList<char[]> getMathSymTknsPos() {
        return mathSymTknsPos;
    }
    public ArrayList<CommonError> getErrors() {
        return errors;
    }

    private void addError() {
      this.errors.add(new CommonError(yyline, yycolumn, "Lexico","Caracter no reconocido",yytext()));
    }

%}

/** DEFINE LANGUAGE **/
Letter=[a-zA-Z]+ // letters
Ints=[0-9]+ // digits integer
Decimal=[0-9]+"."[0-9]+ // digits decimal
whitespace=[\s\t\r\n]+ // whitespace

%%

/*SECTION 3: lexical rules*/

/*******************************************************/
/******************* special symbols *******************/
/*******************************************************/

/* COMMA */
(",")               {return new Symbol(sym.COMMA, yyline + 1, yycolumn + 1,  yytext());}
/* COLON */
(":")               {return new Symbol(sym.COLON, yyline + 1, yycolumn + 1,  yytext());}
/* SEMI COLON */
(";")               {return new Symbol(sym.SEMI_COLON, yyline + 1, yycolumn + 1,  yytext());}
/* OPEN BRACES */
("{")               {return new Symbol(sym.OPEN_BRACES, yyline + 1, yycolumn + 1,  yytext());}
/* CLOSE BRACES */
("}")               {return new Symbol(sym.CLOSE_BRACES, yyline + 1, yycolumn + 1,  yytext());}
/* OPEN BRACKETS */
("[")               {return new Symbol(sym.OPEN_BRACKETS, yyline + 1, yycolumn + 1,  yytext());}
/* CLOSE BRACKETS */
("]")               {return new Symbol(sym.CLOSE_BRACKETS, yyline + 1, yycolumn + 1,  yytext());}

/**********************************************/
/******************* IGNORE *******************/
/**********************************************/

/* WHITESPACES */
{whitespace}        {/*Ignore*/}
/* COMMENT */
("#"(.)*)           {/*Ignore*/}


/************************************************************/
/******************* arythmetical symbols *******************/
/************************************************************/

/* ADITION */
("+")               {saveInfMathTkn();  return new Symbol(sym.ADDITION, yyline + 1, yycolumn + 1);}
/* SUBSTRACTION */
("-")               {saveInfMathTkn();  return new Symbol(sym.SUBSTRACTION, yyline + 1, yycolumn + 1);}
/* MULTIPLICATION */
("*")               {saveInfMathTkn();  return new Symbol(sym.MULTIPLICATION, yyline + 1, yycolumn + 1);}
/* DIVISION */
("/")               {saveInfMathTkn();  return new Symbol(sym.DIVISION, yyline + 1, yycolumn + 1);}
/* PARENTHESIS */
("(")               {saveInfMathTkn(); return new Symbol(sym.OPEN_PARENTHESIS, yyline + 1, yycolumn + 1);}
(")")               {saveInfMathTkn(); return new Symbol(sym.CLOSE_PARENTHESIS, yyline + 1, yycolumn + 1);}

/******************************************************/
/******************* reserved words *******************/
/******************************************************/

/* START OF DEFINITION */
((D|d)ef)           {return new Symbol( sym.DEF, yyline + 1, yycolumn + 1, yytext());}
/* GRAPHIC TYPE */
(Barras)            {return new Symbol( sym.GRAPH_BAR, yyline + 1, yycolumn + 1);}
(Pie)               {return new Symbol( sym.GRAPH_PIE, yyline + 1, yycolumn + 1);}
/* DEFINITION OF ATTRIBUTES */
(titulo)            {return new Symbol( sym.TITLE_ATTR, yyline + 1, yycolumn + 1);}
(unir)              {return new Symbol( sym.MERGE_ATTR, yyline + 1, yycolumn + 1);}

/* BAR GRAPHICS ATTRIBUTES */
(ejex)              {return new Symbol( sym.X_AXIS_ATTR, yyline + 1, yycolumn + 1);}
(ejey)              {return new Symbol( sym.Y_AXIS_ATTR, yyline + 1, yycolumn + 1);}

/* PIE GRAJPHICS ATTRIBUTES */
(tipo)              {return new Symbol( sym.TYPE_ATTR, yyline + 1, yycolumn + 1);}
(etiquetas)         {return new Symbol( sym.LABEL_ATTR, yyline + 1, yycolumn + 1);}
(valores)           {return new Symbol( sym.VALUES_ATTR, yyline + 1, yycolumn + 1);}
(total)             {return new Symbol( sym.TOTAL_ATTR, yyline + 1, yycolumn + 1);}
(extra)             {return new Symbol( sym.EXTRA_ATTR, yyline + 1, yycolumn + 1);}
/* TYPE FOR RESULTS */
(Cantidad)          {return new Symbol( sym.TYPE_CUANT_ATTR, yyline + 1, yycolumn + 1);}
(Porcentaje)        {return new Symbol( sym.TYPE_PERC_ATTR, yyline + 1, yycolumn + 1);}
(Ejecutar)          {return new Symbol( sym.EXECUTE_ACT, yyline + 1, yycolumn + 1);}

/* DEFINE EXTRAS */
/* name set attribute */
(\"([a-zA-Z]|(\s)|[0-9])+\")         {return new Symbol( sym.VAL_ON_COMILLAS, yyline + 1, yycolumn + 1, yytext());}
/* Numbers */
{Ints}              {return new Symbol(sym.NUM_INT, yyline + 1, yycolumn + 1, new Integer(yytext()));}
{Decimal}           {return new Symbol(sym.NUM_DEC, yyline + 1, yycolumn + 1, Double.valueOf(yytext()));}
/*TEXT*/
({Letter}({Letter}|{Ints})*)           {return new Symbol(sym.ID, yyline + 1, yycolumn + 1, yytext());}

/*********************************************/
/******************* other *******************/
/*********************************************/

[^]                 {addError(); return new Symbol(sym.ERROR, yyline + 1, yycolumn + 1, yytext());}