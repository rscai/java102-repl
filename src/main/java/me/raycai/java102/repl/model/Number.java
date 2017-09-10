package me.raycai.java102.repl.model;

public class Number implements Expression {
    private final float value;
    
    public Number(final float value){
        this.value = value;
    }

    @Override
    public float evaluate() {
        return value;
    }
}
