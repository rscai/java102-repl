package me.raycai.java102.repl.parse;

import me.raycai.java102.repl.ReplException;

public class ParseException extends ReplException {

  public ParseException(String message) {
    super(message);
  }

  public ParseException(String message, Throwable cause) {
    super(message, cause);
  }

}
