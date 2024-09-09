import java.io.*;

enum TokenType { NUM, SOMA, SUB, MULT, DIV, APar, FPar, EOF }

class Token {
    String lexema;
    TokenType token;

    Token(String l, TokenType t) {
        lexema = l;
        token = t;
    }
}

class AnaliseLexica {

    BufferedReader arquivo;

    AnaliseLexica(String a) throws Exception {
        this.arquivo = new BufferedReader(new FileReader(a));
    }

    Token getNextToken() throws Exception {
        int eof = -1;
        char currchar;
        int currchar1;
        StringBuilder lexema = new StringBuilder();

        do {
            currchar1 = arquivo.read();
            currchar = (char) currchar1;
        } while (currchar == '\n' || currchar == ' ' || currchar == '\t' || currchar == '\r');

        if (currchar1 != eof && currchar1 != 10) {
            if (currchar >= '0' && currchar <= '9') {
                while (currchar >= '0' && currchar <= '9') {
                    lexema.append(currchar);
                    arquivo.mark(1);
                    currchar1 = arquivo.read();
                    currchar = (char) currchar1;
                }
                arquivo.reset();
                return new Token(lexema.toString(), TokenType.NUM);
            } else {
                switch (currchar) {
                    case '(':
                        return new Token(String.valueOf(currchar), TokenType.APar);
                    case ')':
                        return new Token(String.valueOf(currchar), TokenType.FPar);
                    case '+':
                        return new Token(String.valueOf(currchar), TokenType.SOMA);
                    case '-':
                        return new Token(String.valueOf(currchar), TokenType.SUB);
                    case '*':
                        return new Token(String.valueOf(currchar), TokenType.MULT);
                    case '/':
                        return new Token(String.valueOf(currchar), TokenType.DIV);
                    default:
                        throw new Exception("Caractere inválido: " + ((int) currchar));
                }
            }
        }

        arquivo.close();
        return new Token(String.valueOf(currchar), TokenType.EOF);
    }
}