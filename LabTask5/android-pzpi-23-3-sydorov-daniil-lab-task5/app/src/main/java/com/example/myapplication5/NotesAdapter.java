package com.example.myapplication5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class NotesAdapter extends ArrayAdapter<Note> {
    public NotesAdapter(Context context, ArrayList<Note> notes) {
        super(context, 0, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Note note = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.note_item, parent, false);
        }

        TextView noteText = convertView.findViewById(R.id.noteText);
        TextView noteDate = convertView.findViewById(R.id.noteDate);
        ImageView priorityIcon = convertView.findViewById(R.id.priorityIcon);

        noteText.setText(note.getText());
        noteText.setTextColor(note.getTextColor());

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());
        noteDate.setText(sdf.format(new Date(note.getTimestamp())));

        switch (note.getPriority()) {
            case 0:
                priorityIcon.setImageResource(R.drawable.priority_low);
                break;
            case 1:
                priorityIcon.setImageResource(R.drawable.priority_medium);
                break;
            case 2:
                priorityIcon.setImageResource(R.drawable.priority_high);
                break;
        }

        return convertView;
    }
}