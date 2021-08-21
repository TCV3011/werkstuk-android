package be.ehb.werkstukandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

import be.ehb.werkstukandroid.adapter.CustomNoteAdapter;
import be.ehb.werkstukandroid.database.DatabaseRepository;
import be.ehb.werkstukandroid.entity.Note;

public class MainActivity extends AppCompatActivity {

    private ListView listOfNotes;
    private FloatingActionButton addButton;
    private DatabaseRepository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).setTitle("My notes");
        repo = new DatabaseRepository(getApplication());
        addButton = findViewById(R.id.addNoteButton);
        listOfNotes = findViewById(R.id.listOfNotes);
        addButton.setOnClickListener(view -> showAddNoteActivity());
        showNotesList();
    }

    public void showAddNoteActivity() {
        Intent i = new Intent(this, AddNote.class);
        startActivity(i);
    }

    public void showNotesList() {
        ArrayList<Note> notes = new ArrayList<>(repo.getAllNotes());

        final CustomNoteAdapter adapter = new CustomNoteAdapter(notes, this, repo);
        listOfNotes.setAdapter(adapter);
    }
}