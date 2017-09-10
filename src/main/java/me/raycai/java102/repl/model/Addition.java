package me.raycai.java102.repl.model;

public class Addition extends BiOperator implements Expression{
    public Addition(final Expression leftOperand, final Expression rightOperand){
        super(leftOperand,rightOperand);
    }

    @Override
    public float evaluate() {
        return leftOperand.evaluate()+rightOperand.evaluate();
    }
}
