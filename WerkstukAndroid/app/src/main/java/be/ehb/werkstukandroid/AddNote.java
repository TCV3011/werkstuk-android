package be.ehb.werkstukandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import be.ehb.werkstukandroid.database.Database;
import be.ehb.werkstukandroid.database.DatabaseRepository;
import be.ehb.werkstukandroid.entity.Note;

public class AddNote extends AppCompatActivity {
    EditText noteTitle, noteDetails;
    DatabaseRepository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("New note");
        setContentView(R.layout.activity_add_note);
    }

    public void saveNote(View view) {
        repo = new DatabaseRepository(this.getApplication());
        noteTitle = findViewById(R.id.noteTitle);
        noteDetails = findViewById(R.id.noteDetails);

        repo.insertNote(new Note(noteTitle.getText().toString(), noteDetails.getText().toString()));
    }
}