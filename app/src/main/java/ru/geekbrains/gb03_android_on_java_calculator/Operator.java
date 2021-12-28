package ru.geekbrains.gb03_android_on_java_calculator;

import java.math.BigDecimal;

public enum Operator {
    MULTIPLY("*") {
        @Override
        public BigDecimal calculate(BigDecimal a, BigDecimal b) {
            return a.multiply(b);
        }
    },
    DIVIDE("/") {
        @Override
        public BigDecimal calculate(BigDecimal a, BigDecimal b) {
            return a.divide(b);
        }
    },
    PLUS("+") {
        @Override
        public BigDecimal calculate(BigDecimal a, BigDecimal b) {
            return a.add(b);
        }
    },
    MINUS("-") {
        @Override
        public BigDecimal calculate(BigDecimal a, BigDecimal b) {
            return a.subtract(b);
        }
    };

    private final String name;

    Operator(String name) {
        this.name = name;
    }

    public String convertToString() {
        return name;
    }

    abstract public BigDecimal calculate(BigDecimal a, BigDecimal b);
}
