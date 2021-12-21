package ru.geekbrains.gb03_android_on_java_calculator;

import static ru.geekbrains.gb03_android_on_java_calculator.CalculatorButton.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.EnumSet;

public class MainActivity extends AppCompatActivity {

    private TextView inputTextView;

    // множество кнопок предназначенных для ввода числа
    private final EnumSet<CalculatorButton> numButtons = EnumSet.range(NUM_0, NUM_DOT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewByIdAndInitListeners();
    }

    private void findViewByIdAndInitListeners() {
        inputTextView = findViewById(R.id.input_text_view);

        for (CalculatorButton numButton : numButtons) {
            findViewById(numButton.getId()).setOnClickListener(v -> {
                CalculatorButton clickedButton = CalculatorButton.findById(v.getId());
                numButtonClick(clickedButton);
            });
        }
    }

    private void numButtonClick(CalculatorButton button) {
    }

}