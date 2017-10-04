package me.raycai.java102.repl.model;

import java.math.BigDecimal;

public class Subtraction extends BiOperator implements Expression {

  public Subtraction(final Expression leftOperand, final Expression rightOperand) {
    super(leftOperand, rightOperand);
  }

  @Override
  public BigDecimal evaluate() {
    return leftOperand.evaluate().subtract(rightOperand.evaluate());
  }
}
