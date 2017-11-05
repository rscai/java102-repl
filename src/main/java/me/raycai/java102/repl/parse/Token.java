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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Token token = (Token) o;

    if (type != token.type) {
      return false;
    }
    return text != null ? text.equals(token.text) : token.text == null;
  }

  @Override
  public int hashCode() {
    int result = type != null ? type.hashCode() : 0;
    result = 31 * result + (text != null ? text.hashCode() : 0);
    return result;
  }
}
