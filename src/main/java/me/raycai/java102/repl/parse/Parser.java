package me.raycai.java102.repl.parse;

import java.util.List;
import me.raycai.java102.repl.model.Expression;

public abstract class Parser {

  protected Lexer lexer;

  public Parser(final Lexer lexer) {
    this.lexer = lexer;
  }

  public Expression parse(final String text) throws ParseException {
    final List<Token> tokens = lexer.tokenize(text);
    return tokensToExpression(tokens);
  }

  protected abstract Expression tokensToExpression(final List<Token> tokens) throws ParseException;

}
