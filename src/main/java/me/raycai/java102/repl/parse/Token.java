package me.raycai.java102.repl.parse;

public class Token {

  public enum Type {
    NUMERIC,
    ADDITION,
    SUBTRACTION,
    MULTIPLICATION,
    DIVISION
  }

  public final Type type;
  public final String text;

  public Token(final Type type, final String text) {
    this.type = type;
    this.text = text;
  }

}
