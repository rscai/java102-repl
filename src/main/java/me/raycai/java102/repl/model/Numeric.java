package me.raycai.java102.repl.model;

import java.math.BigDecimal;

public class Numeric implements Expression {
    private final BigDecimal value;

    public Numeric(final BigDecimal value) {
        this.value = value;
    }

    @Override
    public BigDecimal evaluate() {
        return value;
    }
}
