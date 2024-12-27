package com.example.myapplication5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "NotesDB";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_NOTES = "notes";
    private static final String KEY_ID = "id";
    private static final String KEY_TEXT = "text";
    private static final String KEY_TIMESTAMP = "timestamp";
    private static final String KEY_PRIORITY = "priority";
    private static final String KEY_TEXT_COLOR = "text_color";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NOTES + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_TEXT + " TEXT,"
                + KEY_TIMESTAMP + " INTEGER,"
                + KEY_PRIORITY + " INTEGER,"
                + KEY_TEXT_COLOR + " INTEGER DEFAULT " + Color.BLACK
                + ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 3) {
            db.execSQL("ALTER TABLE " + TABLE_NOTES + " ADD COLUMN "
                    + KEY_TEXT_COLOR + " INTEGER DEFAULT " + Color.BLACK);
        }
    }

    public void addNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TEXT, note.getText());
        values.put(KEY_TIMESTAMP, note.getTimestamp());
        values.put(KEY_PRIORITY, note.getPriority());
        values.put(KEY_TEXT_COLOR, note.getTextColor());
        db.insert(TABLE_NOTES, null, values);
        db.close();
    }

    public void updateNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TEXT, note.getText());
        values.put(KEY_PRIORITY, note.getPriority());
        values.put(KEY_TEXT_COLOR, note.getTextColor());
        db.update(TABLE_NOTES, values, KEY_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }

    public List<Note> getAllNotes() {
        List<Note> notesList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NOTES + " ORDER BY " + KEY_TIMESTAMP + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Note note = new Note(
                        cursor.getLong(cursor.getColumnIndexOrThrow(KEY_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(KEY_TEXT)),
                        cursor.getLong(cursor.getColumnIndexOrThrow(KEY_TIMESTAMP)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_PRIORITY)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(KEY_TEXT_COLOR))
                );
                notesList.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notesList;
    }
}