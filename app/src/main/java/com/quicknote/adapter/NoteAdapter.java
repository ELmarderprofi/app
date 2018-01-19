package com.quicknote.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.quicknote.R;
import com.quicknote.entity.Note;

import java.util.List;

public class NoteAdapter extends BaseAdapter {

    private Context context;
    private List<Note> notes;

    public NoteAdapter(Context context) {
        this.context = context;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public int getCount() {
        if (notes == null) {
            return 0;
        }

        return notes.size();
    }

    @Override
    public Object getItem(int i) {
        if (notes == null) {
            return null;
        }

        return notes.get(i);
    }

    @Override
    public long getItemId(int i) {
        if (notes == null || notes.size() == 0) {
            return 0;
        }

        return notes.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.view_list_item, viewGroup, false);
        }

        Note note = (Note) getItem(i);
        if (note != null) {
            TextView title = view.findViewById(R.id.title);
            TextView message = view.findViewById(R.id.message);
            TextView date = view.findViewById(R.id.date);

            title.setText(note.getTitle());
            message.setText(note.getMessage());
            date.setText(DateFormat.getDateFormat(context).format(note.getTimestamp()));
        }

        return view;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
