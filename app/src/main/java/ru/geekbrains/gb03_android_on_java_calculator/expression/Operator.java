package ru.geekbrains.gb03_android_on_java_calculator.expression;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.math.BigDecimal;
import java.util.function.BinaryOperator;

public enum Operator {
    MULTIPLY("*"),
    DIVIDE("/"),
    PLUS("+"),
    MINUS("-"),
    UNDEFINED("?");

    private final String name;

    Operator(String name) {
        this.name = name;
    }

    public String convertToString() {
        return name;
    }

    public BigDecimal calculate(BigDecimal a, BigDecimal b) throws InvalidExpression {
        try {
            switch (this) {
                case MULTIPLY:
                    return a.multiply(b);
                case DIVIDE:
                    return a.divide(b);
                case PLUS:
                    return a.add(b);
                case MINUS:
                    return a.subtract(b);
                default:
                    throw new InvalidExpression("Undefined operator");
            }
        } catch (Exception e) {
            throw new InvalidExpression(e.getMessage());
        }
    }

    public static Operator findByName(String name) {
        for (Operator operator : Operator.values()) {
            if (operator.name.equals(name)) {
                return operator;
            }
        }
        return UNDEFINED;
    }
}
