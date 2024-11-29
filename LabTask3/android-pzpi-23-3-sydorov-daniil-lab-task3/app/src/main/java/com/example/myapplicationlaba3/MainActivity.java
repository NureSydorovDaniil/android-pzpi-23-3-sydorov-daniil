package com.example.myapplicationlaba3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private String currentInput = "";
    private double firstOperand = 0;
    private double secondOperand = 0;
    private String currentOperator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);

        // Кнопки чисел
        findViewById(R.id.button0).setOnClickListener(view -> appendNumber(0));
        findViewById(R.id.button1).setOnClickListener(view -> appendNumber(1));
        findViewById(R.id.button2).setOnClickListener(view -> appendNumber(2));
        findViewById(R.id.button3).setOnClickListener(view -> appendNumber(3));
        findViewById(R.id.button4).setOnClickListener(view -> appendNumber(4));
        findViewById(R.id.button5).setOnClickListener(view -> appendNumber(5));
        findViewById(R.id.button6).setOnClickListener(view -> appendNumber(6));
        findViewById(R.id.button7).setOnClickListener(view -> appendNumber(7));
        findViewById(R.id.button8).setOnClickListener(view -> appendNumber(8));
        findViewById(R.id.button9).setOnClickListener(view -> appendNumber(9));

        // Оператори
        findViewById(R.id.buttonAdd).setOnClickListener(view -> setOperator("+"));
        findViewById(R.id.buttonSubtract).setOnClickListener(view -> setOperator("-"));
        findViewById(R.id.buttonMultiply).setOnClickListener(view -> setOperator("*"));
        findViewById(R.id.buttonDivide).setOnClickListener(view -> setOperator("/"));

        // Кнопка очищення
        findViewById(R.id.buttonClear).setOnClickListener(view -> clear());

        // Кнопка обчислення
        findViewById(R.id.buttonEquals).setOnClickListener(view -> calculate());
    }

    private void appendNumber(int number) {
        currentInput += number;
        resultTextView.setText(currentInput);
    }

    private void setOperator(String operator) {
        firstOperand = Double.parseDouble(currentInput);
        currentInput = "";
        currentOperator = operator;
    }

    private void calculate() {
        secondOperand = Double.parseDouble(currentInput);
        double result = 0;

        switch (currentOperator) {
            case "+":
                result = firstOperand + secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
            case "*":
                result = firstOperand * secondOperand;
                break;
            case "/":
                if (secondOperand != 0) {
                    result = firstOperand / secondOperand;
                } else {
                    resultTextView.setText("Error");
                    return;
                }
                break;
        }

        resultTextView.setText(String.valueOf(result));
        currentInput = String.valueOf(result);
    }

    private void clear() {
        currentInput = "";
        resultTextView.setText("0");
    }
}