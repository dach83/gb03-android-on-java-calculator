package ru.geekbrains.gb03_android_on_java_calculator;

import androidx.annotation.NonNull;

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
    public double calc() throws InvalidExpression {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new InvalidExpression(e.getMessage());
        }
    }
}
