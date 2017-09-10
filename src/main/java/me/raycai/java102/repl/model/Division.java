package me.raycai.java102.repl.model;

import java.math.BigDecimal;

public class Division extends BiOperator implements Expression {
    public Division(final Expression leftOperand, final Expression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public BigDecimal evaluate() {
        return leftOperand.evaluate().divide(rightOperand.evaluate());
    }
}
