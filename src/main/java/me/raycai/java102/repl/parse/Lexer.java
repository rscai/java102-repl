package me.raycai.java102.repl.parse;

import java.util.List;

public interface Lexer {

  List<Token> tokenize(final String text);

}
