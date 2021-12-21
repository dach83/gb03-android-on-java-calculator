package ru.geekbrains.gb03_android_on_java_calculator;

import static ru.geekbrains.gb03_android_on_java_calculator.NumExpr.ZERO_EXPR;

import java.util.regex.Pattern;

public abstract class Expr {

    private final static Pattern doubleNumPattern = Pattern.compile("^(-?)(0|([1-9][0-9]*))*(\\.[0-9]*)?$");

    static Expr build(String str) {
        if (doubleNumPattern.matcher(str).find())
            return new NumExpr(str.replaceFirst("^0", ""));

        throw new IllegalArgumentException();
    }

    public Expr concat(String str) {
        if (this == ZERO_EXPR)
            return build(str);

        return build(this.toString() + str);
    }

    public Expr delete() {
        if (this == ZERO_EXPR)
            return this;

        String str = this.toString();
        if (str.length() > 1) {
            return Expr.build(str.substring(0, str.length() - 1));
        } else {
            return ZERO_EXPR;
        }
    }

}
