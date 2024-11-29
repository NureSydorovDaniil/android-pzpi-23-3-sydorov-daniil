package com.example.myapplicationlaba4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private Context context;
    private ArrayList<Note> notesList;
    private OnNoteClickListener onNoteClickListener;

    public NotesAdapter(Context context, ArrayList<Note> notesList, OnNoteClickListener onNoteClickListener) {
        this.context = context;
        this.notesList = notesList;
        this.onNoteClickListener = onNoteClickListener;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Note note = notesList.get(position);
        holder.title.setText(note.getTitle());
        holder.description.setText(note.getDescription());
        holder.dateTime.setText(note.getDateTime());
        holder.image.setImageURI(note.getImageUri());
        holder.importance.setImageResource(getImportanceIcon(note.getImportance()));

        holder.itemView.setOnClickListener(v -> onNoteClickListener.onNoteClick(note));
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    private int getImportanceIcon(int importance) {
        switch (importance) {
            case 1:
                return R.drawable.low_importance;
            case 2:
                return R.drawable.medium_importance;
            case 3:
                return R.drawable.high_importance;
            default:
                return R.drawable.low_importance;
        }
    }

    public interface OnNoteClickListener {
        void onNoteClick(Note note);
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView title, description, dateTime;
        ImageView image, importance;

        public NoteViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            dateTime = itemView.findViewById(R.id.dateTime);
            image = itemView.findViewById(R.id.image);
            importance = itemView.findViewById(R.id.importance);
        }
    }
}