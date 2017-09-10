package me.raycai.java102.repl.model;

public class Division extends BiOperator implements Expression {
    public Division(final Expression leftOperand, final Expression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public float evaluate() {
        return leftOperand.evaluate() / rightOperand.evaluate();
    }
}
