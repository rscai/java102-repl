package me.raycai.java102.repl.model;

import java.math.BigDecimal;

public class Multiplication extends BiOperator implements Expression {

  public Multiplication(final Expression leftOperand, final Expression rightOperand) {
    super(leftOperand, rightOperand);
  }

  @Override
  public BigDecimal evaluate() {
    return leftOperand.evaluate().multiply(rightOperand.evaluate());
  }
}
