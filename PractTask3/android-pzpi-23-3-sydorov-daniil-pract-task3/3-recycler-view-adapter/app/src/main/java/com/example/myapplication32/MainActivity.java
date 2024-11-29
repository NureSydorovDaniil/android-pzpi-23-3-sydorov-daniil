package com.example.myapplication32;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Данные для RecyclerView (тексты)
        String[] myDataset = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
        // Массив идентификаторов изображений (встроенные звезды)
        int[] myImageIds = {
                android.R.drawable.star_big_on,  // Встроенная иконка звезды
                android.R.drawable.star_big_on,  // Встроенная иконка звезды
                android.R.drawable.star_big_on,  // Встроенная иконка звезды
                android.R.drawable.star_big_on,  // Встроенная иконка звезды
                android.R.drawable.star_big_on   // Встроенная иконка звезды
        };

        // Инициализация RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));  // Устанавливаем вертикальный layout manager

        // Создание адаптера и подключение его к RecyclerView
        MyAdapter adapter = new MyAdapter(myDataset, myImageIds);
        recyclerView.setAdapter(adapter);  // Подключаем адаптер к RecyclerView
    }
}