package com.quicknote.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.quicknote.R;
import com.quicknote.adapter.NoteAdapter;
import com.quicknote.constants.Constants;
import com.quicknote.database.DatabaseHelper;
import com.quicknote.entity.Note;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView listView;
    private NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find views by id
        Toolbar toolbar = findViewById(R.id.toolbar);
        listView = findViewById(R.id.list);

        // Init toolbar
        setSupportActionBar(toolbar);

        // Init objects
        adapter = new NoteAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateView();
    }

    private void updateView() {
        List<Note> notes = DatabaseHelper.getInstance(this).getAllNotes();
        adapter.setNotes(notes);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.add) {
            Intent intent = new Intent(this,
                    NoteDetailActivity.class);
            startActivity(intent);

            return true;
        }

        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent(this,
                NoteDetailActivity.class);
        intent.putExtra(Constants.INTENT_EXTRA_ID, id);
        startActivity(intent);
    }
}
