package com.quicknote.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.quicknote.R;
import com.quicknote.preferences.Preferences;
import com.quicknote.util.StringUtil;

public class NoteDetailActivity extends AppCompatActivity {

    private TextView noteTitle;
    private TextView noteMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        // Find views by id
        noteTitle = findViewById(R.id.note_title);
        noteMessage = findViewById(R.id.note_message);

        // Init toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void updateView() {
        String title = Preferences.getNoteTitle(this);

        if (StringUtil.isNullOrEmpty(title)) {
            noteTitle.setText(R.string.note_empty);
        } else {
            noteTitle.setText(title);
        }
        noteMessage.setText(Preferences.getNoteMessage(this));
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
