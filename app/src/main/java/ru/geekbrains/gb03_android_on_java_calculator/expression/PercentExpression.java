package ru.geekbrains.gb03_android_on_java_calculator.expression;

import java.math.BigDecimal;

public class PercentExpression extends Expression {

    private final Expression innerExpression;

    public PercentExpression(Expression innerExpression) {
        this.innerExpression = innerExpression;
    }

    @Override
    public String convertToString() {
        return innerExpression.convertToString() + "%";
    }

    @Override
    public BigDecimal calculate() throws InvalidExpression {
        try {
            return innerExpression.calculate().divide(BigDecimal.valueOf(100));
        } catch (Exception e) {
            throw new InvalidExpression(e.getMessage());
        }
    }
}
