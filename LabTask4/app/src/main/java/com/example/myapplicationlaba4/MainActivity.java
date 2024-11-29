package com.example.myapplicationlaba4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements NotesAdapter.OnNoteClickListener {

    private static final int PICK_IMAGE_REQUEST = 1;
    private RecyclerView recyclerView;
    private NotesAdapter adapter;
    private ArrayList<Note> notesList = new ArrayList<>();
    private DatabaseHelper databaseHelper;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        databaseHelper = new DatabaseHelper(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NotesAdapter(this, notesList, this);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.addNoteButton).setOnClickListener(v -> openAddNoteActivity());
    }

    @Override
    protected void onStart() {
        super.onStart();
        notesList.clear();
        notesList.addAll(databaseHelper.getAllNotes());
        adapter.notifyDataSetChanged();
    }

    private void openAddNoteActivity() {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }

    @Override
    public void onNoteClick(Note note) {
        // Handle note click (e.g., show details or edit)
        Toast.makeText(this, "Note clicked: " + note.getTitle(), Toast.LENGTH_SHORT).show();
    }

    public void openGallery(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            // Use imageUri to set the image in AddNoteActivity
        }
    }
}