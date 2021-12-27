package ru.geekbrains.gb03_android_on_java_calculator;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class ExpressionBuilder {

    private static final Expression ZERO = new NumExpression("0");
    private static final Pattern doubleNumPattern = Pattern.compile("^(-?)(0|([1-9][0-9]*))*(\\.[0-9]*)?$");

    public static Expression zeroExpression() {
        return ZERO;
    }

    public static Expression buildFromString(String str) throws InvalidExpression {
        try {
            String noZeroStr = str.replaceFirst("^0+", "");
            if (noZeroStr.isEmpty()) {
                return ZERO;
            } else if (noZeroStr.equals(".")) {
                str = "0.";
            } else if (noZeroStr.equals("-.")) {
                str = "-0.";
            }

            if (doubleNumPattern.matcher(str).find()) {
                return new NumExpression(str);
            }

            throw new InvalidExpression(str);
        } catch (Exception e) {
            throw new InvalidExpression(e.getMessage());
        }
    }

    public static Expression concatWithString(Expression expression, String str) {
        try {
            if (expression == ZERO) {
                return buildFromString(str);
            }

            return buildFromString(expression.convertToString() + str);
        } catch (InvalidExpression invalidExpression) {
            return expression;
        }
    }

    public static Expression deleteLastChar(Expression expression) {
        try {
            if (expression == ZERO) {
                return ZERO;
            }

            String str = expression.convertToString();
            if (str.length() <= 1) {
                return ZERO;
            }

            return buildFromString(str.substring(0, str.length() - 1));
        } catch (InvalidExpression invalidExpression) {
            return expression;
        }
    }

    public static Expression calculateExpression(Expression expression) {
        try {
            if (expression instanceof NumExpression) {
                return expression;
            }

            // вычисляем результат составного выражения
            BigDecimal res = expression.calculate();
            if (res.equals(BigDecimal.ZERO)) {
                return ZERO;
            }

            // создаем новое выражение из вычисленного результата
            return buildFromString(res.toPlainString());
        } catch (InvalidExpression invalidExpression) {
            return expression;
        }
    }
}
