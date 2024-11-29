package com.example.myapplication4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<String> userList; // Список користувачів

    // Конструктор
    public UserAdapter(List<String> userList) {
        this.userList = userList;
    }

    // Створення ViewHolder (елемент списку)
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new UserViewHolder(view);
    }

    // Прив'язка даних до ViewHolder
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.textView.setText(userList.get(position));
    }

    // Кількість елементів у списку
    @Override
    public int getItemCount() {
        return userList.size();
    }

    // Оновлення списку користувачів
    public void updateData(List<String> newUserList) {
        this.userList = newUserList;
        notifyDataSetChanged(); // Оновити RecyclerView
    }

    // ViewHolder - визначення елемента списку
    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}