package ru.geekbrains.gb03_android_on_java_calculator;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

public class NumExpr extends Expr {

    public static final Expr ZERO = new NumExpr("0");

    private final String value;

    public NumExpr(String value) {
        this.value = value;
    }

    @NonNull
    @Override
    public String toString() {
        return value;
    }

    @Override
    public BigDecimal calc() throws InvalidExpression {
        try {
            return new BigDecimal(value);
        } catch (Exception e) {
            throw new InvalidExpression(e.getMessage());
        }
    }
}
