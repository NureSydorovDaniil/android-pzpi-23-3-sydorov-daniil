package com.example.myapplication32;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private String[] mData; // Массив данных для текста
    private int[] mImageIds; // Массив для изображений

    // Конструктор адаптера, который принимает данные
    public MyAdapter(String[] data, int[] imageIds) {
        mData = data;
        mImageIds = imageIds;
    }

    // Внутренний класс ViewHolder, который хранит ссылки на элементы интерфейса
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.textView);
            imageView = v.findViewById(R.id.imageView);

            // Добавлен обработчик нажатия на элемент
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Показываем Toast при нажатии
                    Toast.makeText(v.getContext(), "Clicked on item: " + textView.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Создаем представление для элемента списка
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Привязываем текст и изображение к элементам
        holder.textView.setText(mData[position]);
        holder.imageView.setImageResource(mImageIds[position]);
    }

    @Override
    public int getItemCount() {
        return mData.length; // Возвращаем количество элементов в списке
    }
}