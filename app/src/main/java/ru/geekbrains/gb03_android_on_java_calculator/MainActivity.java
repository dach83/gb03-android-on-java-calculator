package ru.geekbrains.gb03_android_on_java_calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String CURRENT_EXPR_KEY = "current_expr_key";

    // идентификаторы кнопок предназначенных для ввода выражения,
    // у них общий обработчик
    private static final int[] inputExpressionButtonsId = {
            R.id.num0_button, R.id.num1_button, R.id.num2_button,
            R.id.num3_button, R.id.num4_button, R.id.num5_button,
            R.id.num6_button, R.id.num7_button, R.id.num8_button,
            R.id.num9_button, R.id.num00_button, R.id.dot_button,
            R.id.minus_button,
    };

    private Expr currentExpr;
    private TextView inputTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null && savedInstanceState.containsKey(CURRENT_EXPR_KEY)) {
            currentExpr = savedInstanceState.getParcelable(CURRENT_EXPR_KEY);
        } else {
            currentExpr = NumExpr.ZERO;
        }

        initView();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(CURRENT_EXPR_KEY, currentExpr);
    }

    private void initView() {
        inputTextView = findViewById(R.id.input_text_view);

        findViewById(R.id.clear_button).setOnClickListener(this::onClickClearButton);
        findViewById(R.id.del_button).setOnClickListener(this::onClickDelButton);
        findViewById(R.id.equal_button).setOnClickListener(this::onClickEqualButton);
        findViewById(R.id.show_second_activity_button).setOnClickListener(this::onClickShowSecondActivity);

        View.OnClickListener inputExpressionButtonListener = this::onClickInputExpressionButton;
        for (int id : inputExpressionButtonsId) {
            findViewById(id).setOnClickListener(inputExpressionButtonListener);
        }

        updateInputTextView();
    }

    private void onClickShowSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(SecondActivity.EXPR_EXTRA_KEY, currentExpr);
        startActivity(intent);
    }

    private void onClickClearButton(View view) {
        currentExpr = NumExpr.ZERO;
        updateInputTextView();
    }

    private void onClickDelButton(View view) {
        currentExpr = currentExpr.delete();
        updateInputTextView();
    }

    private void onClickEqualButton(View view) {
        currentExpr = currentExpr.simplify();
        updateInputTextView();
    }

    private void onClickInputExpressionButton(View view) {
        if (view instanceof Button) {
            Button button = (Button) view;
            currentExpr = currentExpr.concat(button.getText().toString());
            updateInputTextView();
        }
    }

    private void updateInputTextView() {
        inputTextView.setText(currentExpr.toString());
    }
}