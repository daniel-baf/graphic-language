echo "GENERATING LEXERS"
echo "------> deleting files on /Lexer/"
rm -rf ../Lexer/GraphLangLexer.java
echo "------> compiling .jflex files"
jflex Lexer.jflex
echo "------> moving new .jflex files to /Lexer/"
mv GraphLangLexer.java ../Lexer/

echo "GENERATING PARSERS AND SYM TABLE" 
echo "------> deleting files on /Parser/"
rm -rf ../Parser/GraphLangParser.java ../Parser/sym.java
echo "------> compiling .cup file"
cup -parser GraphLangParser -symbols sym Syntax.cup 
echo "------> moving new .java files to /Parser/"
mv GraphLangParser.java sym.java ../Parser/
