package be.ehb.werkstukandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.InputStream;
import java.util.ArrayList;

import be.ehb.werkstukandroid.EditNote;
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
        ImageView noteImage;
    }

    // https://medium.com/@crossphd/android-image-loading-from-a-string-url-6c8290b82c5e
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap bmp = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bmp;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
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
            viewHolder.noteImage = (ImageView) convertView.findViewById(R.id.noteImage);

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
        new DownloadImageTask(viewHolder.noteImage).execute("https://www.pngkey.com/png/detail/20-201537_notes-clipart-posted-note-post-its-icon-png.png");
        // update note
        viewHolder.noteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, EditNote.class);
                i.putExtra("noteId", note.id);
                context.startActivity(i);
            }
        });

        return convertView;
    }


}
