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
import android.widget.TextView;
import android.widget.Toast;

import com.quicknote.R;
import com.quicknote.preferences.Preferences;
import com.quicknote.util.StringUtil;

public class NoteEditActivity extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener {

    private EditText title;
    private EditText message;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        title = findViewById(R.id.title);
        message = findViewById(R.id.message);
        button = findViewById(R.id.button);

        button.setOnClickListener(this);
        title.setText(Preferences.getNoteTitle(this));
        message.setText(Preferences.getNoteMessage(this));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            finish();

            return true;
        } else if (itemId == R.id.delete) {
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to delete this note?")
                    .setNegativeButton("No", null)
                    .setPositiveButton("Yes", NoteEditActivity.this)
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
        Preferences.setNoteTitle(this, title.getText().toString());
        Preferences.setNoteMessage(this, message.getText().toString());
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
