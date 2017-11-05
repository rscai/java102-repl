package me.raycai.java102.repl.parse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import me.raycai.java102.repl.parse.Token.Type;
import me.raycai.java102.repl.util.dfa.AbstractDFA;

public class ArithmeticLexerDFA extends AbstractDFA<ExpressionState, CharacterInput> {

  private List<Token> tokens = new ArrayList<>();
  private List<String> buffer = new ArrayList<>();

  public ArithmeticLexerDFA() {
    startWith(ExpressionState.SPACE);

    when(ExpressionState.SPACE, matchEvent(SpaceInput.class, (CharacterInput input) -> {
      stay();
    }).orElse(NumericInput.class, (CharacterInput input) -> {
      buffer.add(input.character);
      transitTo(ExpressionState.NUMERIC);
    }).orElse(OperatorInput.class, (CharacterInput input) -> {
      buffer.add(input.character);
      transitTo(ExpressionState.OPERATOR);
    }));

    when(ExpressionState.NUMERIC, matchEvent(SpaceInput.class, (CharacterInput input) -> {
      tokens.add(new Token(Type.NUMERIC, buffer.stream().collect(Collectors.joining())));
      buffer.clear();
      transitTo(ExpressionState.SPACE);
    }).orElse(NumericInput.class, (CharacterInput input) -> {
      buffer.add(input.character);
      transitTo(ExpressionState.NUMERIC);
    }).orElse(OperatorInput.class, (CharacterInput input) -> {
      tokens.add(new Token(Type.NUMERIC, buffer.stream().collect(Collectors.joining())));
      buffer.clear();
      buffer.add(input.character);
      transitTo(ExpressionState.OPERATOR);
    }));

    when(ExpressionState.OPERATOR, matchEvent(SpaceInput.class, (CharacterInput input) -> {
      final String operator = buffer.stream().collect(Collectors.joining());
      switch (operator) {
        case "+":
          tokens.add(new Token(Type.ADDITION, operator));
          break;
        case "-":
          tokens.add(new Token(Type.SUBTRACTION, operator));
          break;
        case "*":
          tokens.add(new Token(Type.MULTIPLICATION, operator));
          break;
        case "/":
          tokens.add(new Token(Type.DIVISION, operator));
          break;
        default:

      }
      buffer.clear();
      transitTo(ExpressionState.SPACE);
    }).orElse(NumericInput.class, (CharacterInput input) -> {
      final String operator = buffer.stream().collect(Collectors.joining());
      switch (operator) {
        case "+":
          tokens.add(new Token(Type.ADDITION, operator));
          break;
        case "-":
          tokens.add(new Token(Type.SUBTRACTION, operator));
          break;
        case "*":
          tokens.add(new Token(Type.MULTIPLICATION, operator));
          break;
        case "/":
          tokens.add(new Token(Type.DIVISION, operator));
          break;
        default:

      }
      buffer.clear();
      buffer.add(input.character);
      transitTo(ExpressionState.NUMERIC);
    }).orElse(OperatorInput.class, (CharacterInput input) -> {
      final String operator = buffer.stream().collect(Collectors.joining());
      switch (operator) {
        case "+":
          tokens.add(new Token(Type.ADDITION, operator));
          break;
        case "-":
          tokens.add(new Token(Type.SUBTRACTION, operator));
          break;
        case "*":
          tokens.add(new Token(Type.MULTIPLICATION, operator));
          break;
        case "/":
          tokens.add(new Token(Type.DIVISION, operator));
          break;
        default:

      }
      buffer.clear();
      buffer.add(input.character);
      transitTo(ExpressionState.OPERATOR);
    }));

    initialize();
  }

  public List<Token> getTokens() {
    return tokens;
  }
}
