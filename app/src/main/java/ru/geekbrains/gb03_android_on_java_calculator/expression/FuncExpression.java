package ru.geekbrains.gb03_android_on_java_calculator.expression;

import java.math.BigDecimal;

public class FuncExpression extends Expression {

    private final Operator operator;
    private Expression leftExpression;
    private Expression rightExpression;

    public FuncExpression(Operator operator) {
        this.operator = operator;
    }

    @Override
    public String convertToString() {
        return new StringBuilder()
                .append((leftExpression != null) ? leftExpression.convertToString() : "")
                .append(operator.convertToString())
                .append((rightExpression != null) ? rightExpression.convertToString() : "")
                .toString();
    }

    @Override
    public BigDecimal calculate() throws InvalidExpression {
        if (leftExpression == null || rightExpression == null) {
            throw new InvalidExpression("В выражении отсутствует один из операндов");
        }

        return operator.calculate(leftExpression.calculate(), rightExpression.calculate());
    }

    public void setLeftExpression(Expression leftExpression) {
        this.leftExpression = leftExpression;
    }

    public void setRightExpression(Expression rightExpression) {
        this.rightExpression = rightExpression;
    }
}
