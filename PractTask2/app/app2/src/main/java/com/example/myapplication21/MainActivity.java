package com.example.myapplication21;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);


        View.OnClickListener listener = view -> {
            Button button = (Button) view;
            String buttonText = button.getText().toString();
            String currentText = resultTextView.getText().toString();

            if (buttonText.equals("C")) {

                resultTextView.setText("0");
            } else if (buttonText.equals("=")) {

            } else {

                if (currentText.equals("0")) {
                    resultTextView.setText(buttonText);
                } else {
                    resultTextView.setText(currentText + buttonText);
                }
            }
        };


        int[] buttonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
                R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9,
                R.id.buttonPlus, R.id.buttonMinus, R.id.buttonMultiply, R.id.buttonDivide,
                R.id.buttonClear, R.id.buttonEquals
        };

        for (int id : buttonIds) {
            findViewById(id).setOnClickListener(listener);
        }
    }
}
