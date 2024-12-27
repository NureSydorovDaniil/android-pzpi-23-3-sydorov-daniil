package com.example.myapplication5;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import yuku.ambilwarna.AmbilWarnaDialog;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Note> notesList;
    private NotesAdapter notesAdapter;
    private EditText noteInput;
    private Spinner prioritySpinner;
    private ListView notesListView;
    private DatabaseHelper dbHelper;
    private int selectedTextColor = Color.BLACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        notesList = new ArrayList<>();
        noteInput = findViewById(R.id.noteInput);
        prioritySpinner = findViewById(R.id.prioritySpinner);
        notesListView = findViewById(R.id.notesListView);

        Button colorPickerButton = findViewById(R.id.colorPickerButton);
        colorPickerButton.setOnClickListener(v -> showColorPicker());

        loadNotes();
        notesAdapter = new NotesAdapter(this, notesList);
        notesListView.setAdapter(notesAdapter);

        notesListView.setOnItemLongClickListener((parent, view, position, id) -> {
            showEditDialog(notesList.get(position));
            return true;
        });
    }

    private void showColorPicker() {
        AmbilWarnaDialog dialog = new AmbilWarnaDialog(this, selectedTextColor,
                new AmbilWarnaDialog.OnAmbilWarnaListener() {
                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int color) {
                        selectedTextColor = color;
                    }

                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {
                    }
                });
        dialog.show();
    }

    private void showEditDialog(final Note note) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_edit_note);

        EditText editText = dialog.findViewById(R.id.editNoteText);
        Spinner editPrioritySpinner = dialog.findViewById(R.id.editPrioritySpinner);
        Button colorButton = dialog.findViewById(R.id.editColorButton);
        Button saveButton = dialog.findViewById(R.id.saveButton);
        Button cancelButton = dialog.findViewById(R.id.cancelButton);

        editText.setText(note.getText());
        editPrioritySpinner.setSelection(note.getPriority());
        selectedTextColor = note.getTextColor();

        colorButton.setOnClickListener(v -> {
            AmbilWarnaDialog colorDialog = new AmbilWarnaDialog(this, selectedTextColor,
                    new AmbilWarnaDialog.OnAmbilWarnaListener() {
                        @Override
                        public void onOk(AmbilWarnaDialog dialog, int color) {
                            selectedTextColor = color;
                        }

                        @Override
                        public void onCancel(AmbilWarnaDialog dialog) {
                        }
                    });
            colorDialog.show();
        });

        saveButton.setOnClickListener(v -> {
            note.setText(editText.getText().toString());
            note.setPriority(editPrioritySpinner.getSelectedItemPosition());
            note.setTextColor(selectedTextColor);
            dbHelper.updateNote(note);
            notesAdapter.notifyDataSetChanged();
            dialog.dismiss();
        });

        cancelButton.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    public void addNote(View view) {
        String noteText = noteInput.getText().toString();
        if (!noteText.isEmpty()) {
            int priority = prioritySpinner.getSelectedItemPosition();
            Note note = new Note(noteText, priority, selectedTextColor);
            dbHelper.addNote(note);
            notesList.add(0, note);
            notesAdapter.notifyDataSetChanged();
            noteInput.setText("");
            prioritySpinner.setSelection(0);
            selectedTextColor = Color.BLACK;
        }
    }

    private void loadNotes() {
        notesList.clear();
        notesList.addAll(dbHelper.getAllNotes());
    }
}