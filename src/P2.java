import java.io.IOException;
import java.io.PrintStream;
import java.lang.System;
import java.util.*;
import java.io.*;

import java_cup.runtime.*;  // defines Symbol

/**
 * This program is to be used to test the Scanner.
 * This version is set up to test all tokens, but more code is needed to test 
 * other aspects of the scanner (e.g., input that causes errors, character 
 * numbers, values associated with tokens)
 */
public class P2 {
    public static void main(String[] args) throws IOException {
                                           // exception may be thrown by yylex
        // test all tokens
        testAllTokens();

        testAllTokensLines();

        // ADD CALLS TO OTHER TEST METHODS HERE
        testEOF();

        testBadStringLiteral();

        testBadIntLiteral();

        testIllegalCharacter();
    }

    /**
     * testAllTokens
     *
     * Open and read from file allTokens.txt
     * For each token read, write the corresponding string to allTokens.out
     * If the input file contains all tokens, one per line, we can verify
     * correctness of the scanner by comparing the input and output files
     * (e.g., using a 'diff' command).
     */
    private static void testAllTokens() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("allTokens.in");
            outFile = new PrintWriter(new FileWriter("allTokens.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("allTokens.out cannot be opened.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
            case sym.BOOL:
                outFile.println("bool"); 
                break;
			case sym.INT:
                outFile.println("int");
                break;
            case sym.VOID:
                outFile.println("void");
                break;
            case sym.TRUE:
                outFile.println("true"); 
                break;
            case sym.FALSE:
                outFile.println("false"); 
                break;
            case sym.STRUCT:
                outFile.println("struct"); 
                break;
            case sym.CIN:
                outFile.println("cin"); 
                break;
            case sym.COUT:
                outFile.println("cout");
                break;				
            case sym.IF:
                outFile.println("if");
                break;
            case sym.ELSE:
                outFile.println("else");
                break;
            case sym.WHILE:
                outFile.println("while");
                break;
            case sym.RETURN:
                outFile.println("return");
                break;
            case sym.ID:
                outFile.println(((IdTokenVal)token.value).idVal);
                break;
            case sym.INTLITERAL:  
                outFile.println(((IntLitTokenVal)token.value).intVal);
                break;
            case sym.STRINGLITERAL: 
                outFile.println(((StrLitTokenVal)token.value).strVal);
                break;    
            case sym.LCURLY:
                outFile.println("{");
                break;
            case sym.RCURLY:
                outFile.println("}");
                break;
            case sym.LPAREN:
                outFile.println("(");
                break;
            case sym.RPAREN:
                outFile.println(")");
                break;
            case sym.SEMICOLON:
                outFile.println(";");
                break;
            case sym.COMMA:
                outFile.println(",");
                break;
            case sym.DOT:
                outFile.println(".");
                break;
            case sym.WRITE:
                outFile.println("<<");
                break;
            case sym.READ:
                outFile.println(">>");
                break;				
            case sym.PLUSPLUS:
                outFile.println("++");
                break;
            case sym.MINUSMINUS:
                outFile.println("--");
                break;	
            case sym.PLUS:
                outFile.println("+");
                break;
            case sym.MINUS:
                outFile.println("-");
                break;
            case sym.TIMES:
                outFile.println("*");
                break;
            case sym.DIVIDE:
                outFile.println("/");
                break;
            case sym.NOT:
                outFile.println("!");
                break;
            case sym.AND:
                outFile.println("&&");
                break;
            case sym.OR:
                outFile.println("||");
                break;
            case sym.EQUALS:
                outFile.println("==");
                break;
            case sym.NOTEQUALS:
                outFile.println("!=");
                break;
            case sym.LESS:
                outFile.println("<");
                break;
            case sym.GREATER:
                outFile.println(">");
                break;
            case sym.LESSEQ:
                outFile.println("<=");
                break;
            case sym.GREATEREQ:
                outFile.println(">=");
                break;
			case sym.ASSIGN:
                outFile.println("=");
                break;
			default:
				outFile.println("UNKNOWN TOKEN");
            } // end switch

            token = scanner.next_token();
        } // end while
        outFile.close();
    }

    private static void testAllTokensLines() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("main.yes");
            outFile = new PrintWriter(new FileWriter("main.no"));
        } catch (FileNotFoundException ex) {
            System.err.println("File main.yes not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("main.yes cannot be opened.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        int charnum = 1;
        int linenum = 1;
        while (token.sym != sym.EOF) {
            int tokenLine = ((TokenVal) token.value).linenum;
            int tokenChar = ((TokenVal) token.value).charnum;
            while (linenum < tokenLine) {
                outFile.println();
                charnum = 1;
                linenum++;
            }
            while (charnum < tokenChar) {
                outFile.print(" ");
                charnum++;
            }
            switch (token.sym) {
                case sym.BOOL:
                    outFile.print("bool");
                    charnum += 4;
                    break;
                case sym.INT:
                    outFile.print("int");
                    charnum += 3;
                    break;
                case sym.VOID:
                    outFile.print("void");
                    charnum += 4;
                    break;
                case sym.TRUE:
                    outFile.print("true");
                    charnum += 4;
                    break;
                case sym.FALSE:
                    outFile.print("false");
                    charnum += 5;
                    break;
                case sym.STRUCT:
                    outFile.print("struct");
                    charnum += 6;
                    break;
                case sym.CIN:
                    outFile.print("cin");
                    charnum += 3;
                    break;
                case sym.COUT:
                    outFile.print("cout");
                    charnum += 4;
                    break;
                case sym.IF:
                    outFile.print("if");
                    charnum += 2;
                    break;
                case sym.ELSE:
                    outFile.print("else");
                    charnum += 4;
                    break;
                case sym.WHILE:
                    outFile.print("while");
                    charnum += 5;
                    break;
                case sym.RETURN:
                    outFile.print("return");
                    charnum += 6;
                    break;
                case sym.ID:
                    outFile.print(((IdTokenVal) token.value).idVal);
                    charnum += ((IdTokenVal) token.value).idVal.length();
                    break;
                case sym.INTLITERAL:
                    outFile.print(((IntLitTokenVal) token.value).intVal);
                    charnum += (""+((IntLitTokenVal) token.value).intVal).length();
                    break;
                case sym.STRINGLITERAL:
                    outFile.print(((StrLitTokenVal) token.value).strVal);
                    charnum += ((StrLitTokenVal) token.value).strVal.length();
                    break;
                case sym.LCURLY:
                    outFile.print("{");
                    charnum++;
                    break;
                case sym.RCURLY:
                    outFile.print("}");
                    charnum++;
                    break;
                case sym.LPAREN:
                    outFile.print("(");
                    charnum++;
                    break;
                case sym.RPAREN:
                    outFile.print(")");
                    charnum++;
                    break;
                case sym.SEMICOLON:
                    outFile.print(";");
                    charnum++;
                    break;
                case sym.COMMA:
                    outFile.print(",");
                    charnum++;
                    break;
                case sym.DOT:
                    outFile.print(".");
                    charnum++;
                    break;
                case sym.WRITE:
                    outFile.print("<<");
                    charnum++;
                    charnum++;
                    break;
                case sym.READ:
                    outFile.print(">>");
                    charnum++;
                    charnum++;
                    break;
                case sym.PLUSPLUS:
                    outFile.print("++");
                    charnum++;
                    charnum++;
                    break;
                case sym.MINUSMINUS:
                    outFile.print("--");
                    charnum++;
                    charnum++;
                    break;
                case sym.PLUS:
                    outFile.print("+");
                    charnum++;
                    break;
                case sym.MINUS:
                    outFile.print("-");
                    charnum++;
                    break;
                case sym.TIMES:
                    outFile.print("*");
                    charnum++;
                    break;
                case sym.DIVIDE:
                    outFile.print("/");
                    charnum++;
                    break;
                case sym.NOT:
                    outFile.print("!");
                    charnum++;
                    break;
                case sym.AND:
                    outFile.print("&&");
                    charnum++;
                    charnum++;
                    break;
                case sym.OR:
                    outFile.print("||");
                    charnum++;
                    charnum++;
                    break;
                case sym.EQUALS:
                    outFile.print("==");
                    charnum++;
                    charnum++;
                    break;
                case sym.NOTEQUALS:
                    outFile.print("!=");
                    charnum++;
                    charnum++;
                    break;
                case sym.LESS:
                    outFile.print("<");
                    charnum++;
                    break;
                case sym.GREATER:
                    outFile.print(">");
                    charnum++;
                    break;
                case sym.LESSEQ:
                    outFile.print("<=");
                    charnum++;
                    charnum++;
                    break;
                case sym.GREATEREQ:
                    outFile.print(">=");
                    charnum++;
                    charnum++;
                    break;
                case sym.ASSIGN:
                    outFile.print("=");
                    charnum++;
                    break;
                default:
                    outFile.print("UNKNOWN TOKEN");
            } // end switch
            //outFile.println(" "+tokenLine+":"+tokenChar);
            token = scanner.next_token();
        } // end while
        outFile.close();
    }


    private static void testEOF() throws IOException{
        // open input and output files
        FileReader inFile = null;
        ByteArrayOutputStream outPrint = new ByteArrayOutputStream();
        PrintStream err = System.err;
        System.setErr(new PrintStream(outPrint));
        try {
            inFile = new FileReader("eof.txt");
        } catch (FileNotFoundException ex) {
            System.err.println("File eof.txt not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        System.setErr(err);
        if ((token.sym != sym.EOF)) throw new java.lang.AssertionError(token.sym);
        String error_message = "1:1 ***ERROR*** unterminated string literal ignored\n";
        if (!outPrint.toString().replace("\r", "").equals(error_message))
            throw new java.lang.AssertionError(String.format("%s\n != %s\n", outPrint.toString(), error_message));
    }

    private static void testBadStringLiteral() throws IOException{
        // open input and output files
        FileReader inFile = null;
        ByteArrayOutputStream outPrint = new ByteArrayOutputStream();
        PrintStream err = System.err;
        System.setErr(new PrintStream(outPrint));

        try {
            inFile = new FileReader("badstring.txt");
        } catch (FileNotFoundException ex) {
            System.err.println("File badstring.txt not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        System.setErr(err);
        String error_message = "1:1 ***ERROR*** unterminated string literal with bad escaped character ignored\n";
        if ((!outPrint.toString().replace("\r", "").equals(error_message)))
            throw new java.lang.AssertionError(String.format("%s\ndoes not equal\n%s", outPrint.toString(), error_message));
        if ((token.sym != sym.ID)) throw new java.lang.AssertionError(token.sym);

        if(token.value instanceof IdTokenVal){
            IdTokenVal t = (IdTokenVal) token.value;
            if(!t.idVal.equals("abc")){
                throw new java.lang.AssertionError(t.idVal);
            }
        }else{
            throw new java.lang.AssertionError();
        }
    }

    private static void testBadIntLiteral() throws IOException{
        // open input and output files
        FileReader inFile = null;
        ByteArrayOutputStream outPrint = new ByteArrayOutputStream();
        PrintStream err = System.err;
        System.setErr(new PrintStream(outPrint));

        try {
            inFile = new FileReader("badint.txt");
        } catch (FileNotFoundException ex) {
            System.err.println("File badint.txt not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        System.setErr(err);
        String error_message = "1:1 ***ERROR*** integer literal too large; using max value\n";


        if(token.sym != sym.INTLITERAL) {
            System.out.println("Errors: "+outPrint);
            System.out.println(token.sym);
            throw new java.lang.AssertionError(token.sym);
        }

        if(!outPrint.toString().replace("\r", "").equals(error_message)) {
            error_message = error_message.replace("\n", "\\n");
            throw new java.lang.AssertionError(String.format("%s\n!=%s\n",
                    outPrint.toString().replace("\n", "\\n"),
                    error_message));
        }


        if(token.value instanceof IntLitTokenVal){
            IntLitTokenVal intToken = (IntLitTokenVal) token.value;
            if(intToken.intVal != Integer.MAX_VALUE){
                throw new java.lang.AssertionError(intToken.intVal);
            }
        }else{
            throw new java.lang.AssertionError();
        }
    }

    private static void testIllegalCharacter() throws IOException{
        // open input and output files
        FileReader inFile = null;
        ByteArrayOutputStream outPrint = new ByteArrayOutputStream();
        PrintStream err = System.err;
        System.setErr(new PrintStream(outPrint));

        try {
            inFile = new FileReader("illegal_character.txt");
        } catch (FileNotFoundException ex) {
            System.err.println("File illegal_character.txt not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();

        System.setErr(err);
        String error_message = "1:1 ***ERROR*** illegal character ignored: $\n";

        if(!outPrint.toString().replace("\r", "").equals(error_message))
            throw new java.lang.AssertionError(String.format("%s\ndoes not equal\n%s", outPrint.toString(), error_message));

        if(token.sym != sym.EOF)
            throw new java.lang.AssertionError();

    }
}
