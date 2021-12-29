package ru.geekbrains.gb03_android_on_java_calculator.expression;

import java.math.BigDecimal;

public class EmptyExpression extends Expression {
    @Override
    public String convertToString() {
        return "";
    }

    @Override
    public BigDecimal calculate() throws InvalidExpression {
        throw new InvalidExpression("Calculate EmptyExpression");
    }
}
