package ru.geekbrains.gb03_android_on_java_calculator.expression;

import java.math.BigDecimal;

public class NumExpression extends Expression {

    private final String value;

    public NumExpression(String value) {
        this.value = value;
    }

    @Override
    public String convertToString() {
        return value;
    }

    @Override
    public BigDecimal calculate() throws InvalidExpression {
        try {
            return new BigDecimal(value);
        } catch (Exception e) {
            throw new InvalidExpression(e.getMessage());
        }
    }
}
