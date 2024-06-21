package com.craftinginterpreters.lox;

/**
 * Some terminology notes:
 * - Lexeme: Blobs of characters that have meaning. var test = 'meow' -> "var", "test", "meow" are lexemes.
 * - Token: Classifications of lexemes. Certain lexemes have a 'kind' of meaning. Like "var" (a keyword type, that we specifically call out as, well, "var")
 */
class Token {
    final TokenType type;
    final String lexeme;
    final Object literal;
    final int line;

    Token(TokenType type, String lexeme, Object literal, int line) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
    }

    public String toString() {
        return type + " " + lexeme + " " + literal;
    }
}