package me.raycai.java102.repl.model;

public abstract class BiOperator {
    public final Expression leftOperand;
    public final Expression rightOperand;
    
    public BiOperator(final Expression leftOperand, final Expression rightOperand){
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }
}
