###
# This Makefile can be used to make a scanner for the CFlat language
# (Yylex.class) and to make a program that tests the scanner (P2.class).
#
# The default makes both the scanner and the test program.
#
# make clean removes all generated files.
#
# Note: P2.java will not compile unless Yylex.class exists.
#
###

# define the java compiler to be used and the flags
JC = javac
FLAGS = -g -cp $(CP)
CP = /usr/share/java/cup.jar:~/Kiki/:.

P2.class: P2.java Yylex.class sym.class
	$(JC) $(FLAGS) P2.java

Yylex.class: CFlat.jlex.java ErrMsg.class sym.class
	$(JC) $(FLAGS) CFlat.jlex.java

CFlat.jlex.java: CFlat.jlex sym.class
	java -cp $(CP) JLex.Main CFlat.jlex

sym.class: sym.java
	$(JC) $(FLAGS) sym.java

ErrMsg.class: ErrMsg.java
	$(JC) $(FLAGS) ErrMsg.java

	
###
# testing - add more here to run your tester and compare its results
# to expected results
###
test: P2.class
	java -cp $(CP) P2 
	sed -r -i.no_comment '/^#|^\/\// d' allTokens.in
	diff allTokens.in.no_comment allTokens.out
	diff main.yes.correct main.no

###
# clean up
###

.PHONY: clean
clean:
	rm -f *~ *.class CFlat.jlex.java

cleantest:
	rm -f allTokens.out
	rm -f *.no_comment
	rm -f main.no
