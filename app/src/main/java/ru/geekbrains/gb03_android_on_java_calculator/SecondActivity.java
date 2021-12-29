package ru.geekbrains.gb03_android_on_java_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import ru.geekbrains.gb03_android_on_java_calculator.expression.Expression;

public class SecondActivity extends AppCompatActivity {

    public static final String EXPR_EXTRA_KEY = "expr_extra_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView resultTextView = findViewById(R.id.result_text_view);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXPR_EXTRA_KEY)) {
            Expression expression = intent.getParcelableExtra(EXPR_EXTRA_KEY);
            resultTextView.setText(expression.toString());
        } else if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            String text = intent.getStringExtra(Intent.EXTRA_TEXT);
            resultTextView.setText(text);
        }
    }
}