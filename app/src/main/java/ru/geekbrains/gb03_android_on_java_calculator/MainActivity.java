package ru.geekbrains.gb03_android_on_java_calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ru.geekbrains.gb03_android_on_java_calculator.expression.Expression;
import ru.geekbrains.gb03_android_on_java_calculator.expression.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    private static final String CURRENT_EXPR_KEY = "current_expr_key";

    // идентификаторы кнопок предназначенных для ввода выражения,
    // у них общий обработчик
    private static final int[] inputExpressionButtonsId = {
            R.id.zero_button, R.id.one_button, R.id.two_button,
            R.id.three_button, R.id.four_button, R.id.five_button,
            R.id.six_button, R.id.seven_button, R.id.eight_button,
            R.id.nine_button, R.id.double_zero_button, R.id.dot_button,
            R.id.multiply_button, R.id.div_button,
            R.id.plus_button, R.id.minus_button,
            R.id.percent_button,
    };

    private Expression currentExpression;
    private TextView expressionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null && savedInstanceState.containsKey(CURRENT_EXPR_KEY)) {
            currentExpression = savedInstanceState.getParcelable(CURRENT_EXPR_KEY);
        } else {
            currentExpression = ExpressionBuilder.zeroExpression();
        }

        initView();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(CURRENT_EXPR_KEY, currentExpression);
    }

    private void initView() {
        findViewById(R.id.clear_button).setOnClickListener(this::onClickClearButton);
        findViewById(R.id.del_button).setOnClickListener(this::onClickDelButton);
        findViewById(R.id.equal_button).setOnClickListener(this::onClickEqualButton);

        Button showSecondActivityButton = findViewById(R.id.show_result_button);
        if (showSecondActivityButton != null) {
            showSecondActivityButton.setOnClickListener(this::onClickShowResultButton);
        }

        View.OnClickListener inputExpressionButtonListener = this::onClickInputExpressionButton;
        for (int id : inputExpressionButtonsId) {
            findViewById(id).setOnClickListener(inputExpressionButtonListener);
        }

        expressionTextView = findViewById(R.id.expression_text_view);
        updateExpressionTextView();
    }

    private void onClickShowResultButton(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        Expression resultExpression = ExpressionBuilder.calculateExpression(currentExpression);
        intent.putExtra(SecondActivity.EXPR_EXTRA_KEY, resultExpression);
        startActivity(intent);
    }

    private void onClickClearButton(View view) {
        currentExpression = ExpressionBuilder.zeroExpression();
        updateExpressionTextView();
    }

    private void onClickDelButton(View view) {
        currentExpression = ExpressionBuilder.deleteLastChar(currentExpression);
        updateExpressionTextView();
    }

    private void onClickEqualButton(View view) {
        currentExpression = ExpressionBuilder.calculateExpression(currentExpression);
        updateExpressionTextView();
    }

    private void onClickInputExpressionButton(View view) {
        if (view instanceof Button) {
            Button button = (Button) view;
            currentExpression = ExpressionBuilder.concatWithString(currentExpression, button.getText().toString());
            updateExpressionTextView();
        }
    }

    private void updateExpressionTextView() {
        expressionTextView.setText(currentExpression.convertToString());
    }
}