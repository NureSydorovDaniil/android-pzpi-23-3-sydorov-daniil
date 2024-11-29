package com.example.myapplicationlaba4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText titleEditText, descriptionEditText;
    private Spinner importanceSpinner;
    private Button saveButton, selectImageButton;
    private ImageView noteImageView;
    private Uri imageUri;  // Зберігає вибрану картинку

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        // Ініціалізація UI-елементів
        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        importanceSpinner = findViewById(R.id.importanceSpinner);
        saveButton = findViewById(R.id.saveButton);
        selectImageButton = findViewById(R.id.selectImageButton);
        noteImageView = findViewById(R.id.noteImageView);

        // Обробка натискання кнопки вибору зображення
        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        // Обробка натискання кнопки збереження
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
    }

    // Метод для відкриття галереї
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            noteImageView.setImageURI(imageUri);  // Встановлюємо вибране зображення
        }
    }

    // Метод для збереження нотатки
    private void saveNote() {
        String title = titleEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();
        int importance = importanceSpinner.getSelectedItemPosition(); // Вибір важливості
        String dateTime = "2024-11-24 12:00";  // Тут можна підставити актуальну дату/час
        String imageUriString = (imageUri != null) ? imageUri.toString() : "";

        // Перевірка, чи всі поля заповнені
        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else {
            // Додавання нотатки в базу даних
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            long noteId = dbHelper.addNote(title, description, importance, dateTime, imageUriString);

            if (noteId != -1) {
                Toast.makeText(this, "Note added successfully", Toast.LENGTH_SHORT).show();
                finish();  // Закриваємо активність після збереження
            } else {
                Toast.makeText(this, "Error adding note", Toast.LENGTH_SHORT).show();
            }
        }
    }
}