package be.ehb.werkstukandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import be.ehb.werkstukandroid.R;
import be.ehb.werkstukandroid.database.DatabaseRepository;
import be.ehb.werkstukandroid.entity.Note;

// https://www.journaldev.com/10416/android-listview-with-custom-adapter-example-tutorial
public class CustomNoteAdapter extends ArrayAdapter<Note> {
    private DatabaseRepository repo;
    Context context;
    private ArrayList<Note> notes;

    private static class ViewHolder {
        TextView noteTitle;
        GridLayout noteItem;
    }

    public CustomNoteAdapter(ArrayList<Note> data, Context context, DatabaseRepository repo) {
        super(context, R.layout.note_list_item, data);
        this.notes = data;
        this.context = context;
        this.repo = repo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Note note = getItem(position);
        final View result;
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.note_list_item, parent, false);

            viewHolder.noteTitle = (TextView) convertView.findViewById(R.id.noteListTitle);
            viewHolder.noteItem = (GridLayout) convertView.findViewById(R.id.noteListItem);

            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        String noteTitle = "";
        if (note.title.length() >= 30) {
            noteTitle = note.title.substring(0, 30) + "...";
        } else {
            noteTitle = note.title;
        }
        viewHolder.noteTitle.setText(noteTitle);

        // update note
        // viewHolder.noteItem.setOnClickListener();

        return convertView;
    }


}
