package com.quicknote.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.quicknote.R;
import com.quicknote.constants.Constants;
import com.quicknote.database.DatabaseHelper;
import com.quicknote.entity.Note;

public class NoteDetailActivity extends AppCompatActivity {

    private TextView noteTitle;
    private TextView noteMessage;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        // Find views by id
        Toolbar toolbar = findViewById(R.id.toolbar);
        noteTitle = findViewById(R.id.note_title);
        noteMessage = findViewById(R.id.note_message);

        // Init toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Init objects
        long noteId = getIntent().getLongExtra(Constants.INTENT_EXTRA_ID, 0);
        note = DatabaseHelper.getInstance(this).getNote(noteId);
    }

    private void updateView() {
        if (note != null) {
            noteTitle.setText(note.getTitle());
            noteMessage.setText(note.getMessage());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_detail, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            finish();

            return true;
        } else if (itemId == R.id.edit) {
            Intent intent = new Intent(this, NoteEditActivity.class);
            startActivity(intent);

            return true;
        }

        return false;
    }
}
