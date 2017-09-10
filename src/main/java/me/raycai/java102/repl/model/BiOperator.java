package me.raycai.java102.repl.model;

public abstract class BiOperator {
    protected final Expression leftOperand;
    protected final Expression rightOperand;
    
    public BiOperator(final Expression leftOperand, final Expression rightOperand){
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }
}
