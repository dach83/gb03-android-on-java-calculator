package ru.geekbrains.gb03_android_on_java_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static final String EXPR_EXTRA_KEY = "expr_extra_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        if (intent != null) {
            Expression expression = intent.getParcelableExtra(EXPR_EXTRA_KEY);
            TextView exprTextView = findViewById(R.id.expression_text_view);
            exprTextView.setText(expression.toString());
        }
    }
}