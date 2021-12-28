package ru.geekbrains.gb03_android_on_java_calculator.expression;

import java.math.BigDecimal;

public class FuncExpression extends Expression {

    private final Operator operator;
    private Expression leftExpression;
    private Expression rightExpression;

    public FuncExpression(Operator operator) {
        this.operator = operator;
        this.leftExpression = ExpressionBuilder.emptyExpression();
        this.rightExpression = ExpressionBuilder.emptyExpression();
    }

    @Override
    public String convertToString() {
        return leftExpression.convertToString() +
                operator.convertToString() +
                rightExpression.convertToString();
    }

    @Override
    public BigDecimal calculate() throws InvalidExpression {
        return operator.calculate(leftExpression.calculate(), rightExpression.calculate());
    }

    public void setLeftExpression(Expression leftExpression) {
        this.leftExpression = leftExpression;
    }

    public void setRightExpression(Expression rightExpression) {
        this.rightExpression = rightExpression;
    }
}
