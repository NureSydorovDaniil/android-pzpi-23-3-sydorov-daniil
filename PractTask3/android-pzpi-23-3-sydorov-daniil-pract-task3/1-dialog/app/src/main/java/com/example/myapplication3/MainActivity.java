package com.example.myapplication3;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // AlertDialog
        Button showDialogButton = findViewById(R.id.showDialogButton);
        showDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Створення та показ AlertDialog
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Діалог") // Заголовок діалогу
                        .setMessage("Це приклад AlertDialog.") // Повідомлення в діалозі
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Дія при натисканні кнопки OK
                                Toast.makeText(MainActivity.this, "Натиснуто OK", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Дія при натисканні кнопки Cancel
                                Toast.makeText(MainActivity.this, "Натиснуто Cancel", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        })
                        .show(); // Показ діалогу
            }
        });

        Button showDatePickerButton = findViewById(R.id.showDatePickerButton);
        showDatePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Обробка вибраної дати
                            }
                        }, 2023, 8, 1); // Рік, місяць (0-based), день
                datePickerDialog.show();
            }
        });

        Button showCustomDialogButton = findViewById(R.id.showCustomDialogButton);
        showCustomDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Створення кастомного діалогу
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_dialog, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(dialogView)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                EditText input = dialogView.findViewById(R.id.customDialogInput);
                                String userInput = input.getText().toString();
                                Toast.makeText(MainActivity.this, "Введено: " + userInput, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                builder.create().show();
            }
        });
    }
}

