package ru.geekbrains.gb03_android_on_java_calculator;

import static ru.geekbrains.gb03_android_on_java_calculator.CalculatorButton.*;
import static ru.geekbrains.gb03_android_on_java_calculator.NumExpr.ZERO_EXPR;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.EnumSet;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";

    private Expr expr = ZERO_EXPR;

    private TextView inputTextView;

    // множество кнопок предназначенных для ввода числа
    private final EnumSet<CalculatorButton> inputNumButtons = EnumSet.of(
            NUM_1, NUM_2, NUM_3, NUM_4, NUM_5, NUM_6, NUM_7,
            NUM_8, NUM_9, NUM_0, NUM_00, DOT, MINUS
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActivityView();
    }

    private void initActivityView() {
        inputTextView = findViewById(R.id.input_text_view);
        UpdateInputTextView();

        for (CalculatorButton numButton : inputNumButtons) {
            findViewById(numButton.getId()).setOnClickListener(view -> {
                CalculatorButton clickedButton = CalculatorButton.findById(view.getId());
                numButtonClick(clickedButton);
            });
        }

        findViewById(R.id.clear_button).setOnClickListener(view -> {
            expr = ZERO_EXPR;
            UpdateInputTextView();
        });

        findViewById(R.id.del_button).setOnClickListener(view -> {
            expr = expr.delete();
            UpdateInputTextView();
        });
    }

    private void numButtonClick(CalculatorButton button) {
        try {
            expr = expr.concat(button.getText());
            UpdateInputTextView();
        } catch (Exception e) {
            Log.d(TAG, "Illegal expression");
        }
    }

    private void UpdateInputTextView() {
        inputTextView.setText(expr.toString());
    }

}