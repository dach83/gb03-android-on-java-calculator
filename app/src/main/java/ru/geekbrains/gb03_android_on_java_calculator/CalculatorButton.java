package ru.geekbrains.gb03_android_on_java_calculator;

public enum CalculatorButton {
    NUM_0(R.id.num0_button, "0"),
    NUM_1(R.id.num1_button, "1"),
    NUM_2(R.id.num2_button, "2"),
    NUM_3(R.id.num3_button, "3"),
    NUM_4(R.id.num4_button, "4"),
    NUM_5(R.id.num5_button, "5"),
    NUM_6(R.id.num6_button, "6"),
    NUM_7(R.id.num7_button, "7"),
    NUM_8(R.id.num8_button, "8"),
    NUM_9(R.id.num9_button, "9"),
    NUM_00(R.id.num00_button, "00"),
    DOT(R.id.dot_button, "."),
    MINUS(R.id.minus_button, "-"),
    EMPTY_ACTION(0, "");

    private final int id;
    private final String text;

    CalculatorButton(int id, String name) {
        this.id = id;
        this.text = name;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public static CalculatorButton findById(int id) {
        // кнопок не много, поэтому для поиска использую простой перебор
        for (CalculatorButton button : CalculatorButton.values()) {
            if (button.id == id)
                return button;
        }
        return EMPTY_ACTION;
    }
}

