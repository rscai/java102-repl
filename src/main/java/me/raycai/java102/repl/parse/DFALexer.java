package me.raycai.java102.repl.parse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DFALexer implements Lexer {

  @Override
  public List<Token> tokenize(String text) {
    final ArithmeticLexerDFA dfa = new ArithmeticLexerDFA();
    final String normalizedText = text+" ";
    for (final char c : normalizedText.toCharArray()) {
      if (Arrays.asList(' ').contains(c)) {
        dfa.receive(new SpaceInput(String.valueOf(c)));
      } else if (Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9').contains(c)) {
        dfa.receive(new NumericInput(String.valueOf(c)));
      } else if (Arrays.asList('+', '-', '*', '/').contains(c)) {
        dfa.receive(new OperatorInput(String.valueOf(c)));
      }
    }

    return new ArrayList<>(dfa.getTokens());
  }
}
