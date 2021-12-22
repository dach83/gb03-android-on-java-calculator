package ru.geekbrains.gb03_android_on_java_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "@@@";

    // идентификаторы кнопок предназначенных для ввода выражения
    // у них общий обработчик
    private final int[] inputExprButtonsId = {
            R.id.num0_button, R.id.num1_button, R.id.num2_button,
            R.id.num3_button, R.id.num4_button, R.id.num5_button,
            R.id.num6_button, R.id.num7_button, R.id.num8_button,
            R.id.num9_button, R.id.num00_button, R.id.dot_button,
            R.id.minus_button,
    };

    private Expr currentExpr = NumExpr.ZERO;
    private TextView inputTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActivityView();
    }

    private void initActivityView() {
        inputTextView = findViewById(R.id.input_text_view);

        findViewById(R.id.clear_button).setOnClickListener(this::clearButtonOnClick);
        findViewById(R.id.del_button).setOnClickListener(this::delButtonOnClick);
        findViewById(R.id.equal_button).setOnClickListener(this::equalButtonOnClick);

        View.OnClickListener listener = this::inputExprButtonOnClick;
        for (int id : inputExprButtonsId)
            findViewById(id).setOnClickListener(listener);

        updateInputTextView();
    }

    private void clearButtonOnClick(View view) {
        currentExpr = NumExpr.ZERO;
        updateInputTextView();
    }

    private void delButtonOnClick(View view) {
        currentExpr = currentExpr.delete();
        updateInputTextView();
    }

    private void equalButtonOnClick(View view) {
        currentExpr = currentExpr.simplify();
        updateInputTextView();
    }

    private void inputExprButtonOnClick(View view) {
        Button button = (Button) view;
        currentExpr = currentExpr.concat(button.getText().toString());
        updateInputTextView();
    }

    private void updateInputTextView() {
        inputTextView.setText(currentExpr.toString());
        //Log.d(TAG, " == " + currentExpr.calc());
    }
}