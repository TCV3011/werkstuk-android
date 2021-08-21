package be.ehb.werkstukandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import be.ehb.werkstukandroid.database.Database;
import be.ehb.werkstukandroid.database.DatabaseRepository;
import be.ehb.werkstukandroid.entity.Note;

public class AddNote extends AppCompatActivity {
    EditText noteTitle, noteDetails;
    DatabaseRepository repo;
    FloatingActionButton saveNoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("New note");
        setContentView(R.layout.activity_add_note);

        saveNoteButton = findViewById(R.id.saveNoteButton);
        saveNoteButton.setOnClickListener(view -> saveNote());
    }

    private void saveNote() {
        repo = new DatabaseRepository(this.getApplication());
        noteTitle = findViewById(R.id.noteTitle);
        noteDetails = findViewById(R.id.noteDetails);

        repo.insertNote(new Note(noteTitle.getText().toString(), noteDetails.getText().toString()));

        // https://stackoverflow.com/questions/8202006/android-have-toast-appear-on-button-click
        Toast toast = Toast.makeText(getApplicationContext(), "Note saved", Toast.LENGTH_SHORT);
        toast.show();
        goToMain();
    }

    // method to go back to list view and refresh the list with the new note
    // https://www.youtube.com/watch?v=pa_lghjVQVA (41:05)
    private void goToMain() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}