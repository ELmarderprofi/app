package com.quicknote.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.quicknote.R;
import com.quicknote.database.DatabaseHelper;
import com.quicknote.entity.Note;
import com.quicknote.preferences.Preferences;

public class NoteEditActivity extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener {

    private EditText title;
    private EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        // Find views by id
        Toolbar toolbar = findViewById(R.id.toolbar);
        Button button = findViewById(R.id.button);
        title = findViewById(R.id.title);
        message = findViewById(R.id.message);

        // Init toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Set listener
        button.setOnClickListener(this);
        title.setText(Preferences.getNoteTitle(this));
        message.setText(Preferences.getNoteMessage(this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            finish();

            return true;
        } else if (itemId == R.id.delete) {
            new AlertDialog.Builder(this)
                    .setMessage(R.string.dialog_delete_note_message)
                    .setNegativeButton(R.string.button_no_text, null)
                    .setPositiveButton(R.string.button_yes_text, NoteEditActivity.this)
                    .show();
        }

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_edit, menu);

        return true;
    }

    @Override
    public void onClick(View view) {
        String titleString = title.getText().toString();
        String messageString = message.getText().toString();

        Preferences.setNoteTitle(this, titleString);
        Preferences.setNoteMessage(this, messageString);

        Note note = new Note();
        note.setTitle(titleString);
        note.setMessage(messageString);
        note.setTimestamp(System.currentTimeMillis());
        note.setStatus(Note.STATUS_TODO);

        DatabaseHelper.getInstance(this).insertNote(note);

        Toast.makeText(this, getString(R.string.message_note_saved), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        Preferences.setNoteTitle(this, null);
        Preferences.setNoteMessage(this, null);
        Toast.makeText(this, getString(R.string.message_note_deleted), Toast.LENGTH_SHORT).show();
        finish();
    }
}
