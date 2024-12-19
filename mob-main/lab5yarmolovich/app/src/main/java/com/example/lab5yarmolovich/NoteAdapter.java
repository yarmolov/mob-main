package com.example.lab5yarmolovich;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class NoteAdapter extends ArrayAdapter<com.example.lab5yarmolovich.Note> {
    public NoteAdapter(Context context, List<com.example.lab5yarmolovich.Note> notes) {
        super(context, 0, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        com.example.lab5yarmolovich.Note note = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.note_item, parent, false);
        }
        TextView textViewId = convertView.findViewById(R.id.textViewId);
        TextView textViewDescription = convertView.findViewById(R.id.textViewDescription);

        textViewId.setText(String.valueOf(note.getId()));
        textViewDescription.setText(note.getDescription());

        return convertView;
    }
}