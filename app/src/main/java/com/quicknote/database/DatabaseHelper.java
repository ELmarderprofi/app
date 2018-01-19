package com.quicknote.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.quicknote.entity.Note;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database table column names
    private static final String DATABASE_NAME = "quicknote";
    private static final String DATABASE_TABLE_NAME = "notes";
    private static final int DATABASE_VERSION = 1;

    // Database properties
    private static final String KEY_ID = "id";
    private static final String KEY_TIMESTAMP = "timestamp";
    private static final String KEY_STATUS = "status";
    private static final String KEY_TITLE = "title";
    private static final String KEY_MESSAGE = "message";

    private static final String[] CURSOR_ARRAY = new String[] {
            KEY_ID, KEY_TIMESTAMP, KEY_STATUS, KEY_TITLE, KEY_MESSAGE
    };

    private static final String CREATE_TABLE = "CREATE TABLE " + DATABASE_TABLE_NAME + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_TIMESTAMP + " INT,"
            + KEY_STATUS + " INT,"
            + KEY_TITLE + " TEXT,"
            + KEY_MESSAGE + " TEXT"
            + ")";

    private static DatabaseHelper instance = null;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static  DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }

        return instance;
    }

    // Insert note into database
    public long insertNote(Note note) {
        long id;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_TIMESTAMP, note.getTimestamp());
        values.put(KEY_STATUS, note.getStatus());
        values.put(KEY_TITLE, note.getTitle());
        values.put(KEY_MESSAGE, note.getMessage());

        id = db.insert(DATABASE_TABLE_NAME, null, values);

        return id;
    }

    // Get all notes from database
    public List<Note> getAllNotes() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Note> notes = new ArrayList<>();

        try (Cursor cursor = db.query(DATABASE_TABLE_NAME, CURSOR_ARRAY, null,
                null, null, null, KEY_TIMESTAMP + " DESC", null)) {
            while (cursor.moveToNext()) {
                notes.add(cursorToNote(cursor));
            }
        }

        return notes;
    }

    // Get single note from database
    public Note getNote(long id) {
        SQLiteDatabase db = getReadableDatabase();
        Note note;

        Cursor cursor = db.query(DATABASE_TABLE_NAME, CURSOR_ARRAY, KEY_ID + "=?",
                new String[] { String.valueOf(id) } , null, null, null, null);

        cursor.moveToFirst();
        note = cursorToNote(cursor);
        cursor.close();

        return note;
    }

    // Create new note object from cursor
    private Note cursorToNote(Cursor cursor) {
        Note note = null;

        if (cursor != null) {
            note = new Note();
            note.setId(cursor.getLong(cursor.getColumnIndex(KEY_ID)));
            note.setTimestamp(cursor.getLong(cursor.getColumnIndex(KEY_TIMESTAMP)));
            note.setStatus(cursor.getInt(cursor.getColumnIndex(KEY_STATUS)));
            note.setTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
            note.setMessage(cursor.getString(cursor.getColumnIndex(KEY_MESSAGE)));
        }

        return note;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
