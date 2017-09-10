package me.raycai.java102.repl.model;

import java.math.BigDecimal;

public class Addition extends BiOperator implements Expression {
    public Addition(final Expression leftOperand, final Expression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public BigDecimal evaluate() {
        return leftOperand.evaluate().add(rightOperand.evaluate());
    }
}
