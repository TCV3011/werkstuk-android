package be.ehb.werkstukandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import be.ehb.werkstukandroid.database.Database;
import be.ehb.werkstukandroid.database.DatabaseRepository;
import be.ehb.werkstukandroid.entity.Note;

public class EditNote extends AppCompatActivity {
    private int id;
    private FloatingActionButton saveEditNoteButton, deleteEditNoteButton;
    private EditText noteEditTitle, noteEditDetails;
    private DatabaseRepository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.edit_note);
        setContentView(R.layout.activity_edit_note);

        repo = new DatabaseRepository(getApplication());
        // id ophalen dat megegeven is met de intent van de list
        Intent i = getIntent();
        id = i.getIntExtra("noteId", -1);

        saveEditNoteButton = findViewById(R.id.saveEditNoteButton);
        deleteEditNoteButton = findViewById(R.id.deleteEditNoteButton);
        noteEditTitle = findViewById(R.id.noteEditTitle);
        noteEditDetails = findViewById(R.id.noteEditDetails);

        Note current = repo.getNoteById(id);
        noteEditTitle.setText(current.title);
        noteEditDetails.setText(current.text);

        deleteEditNoteButton.setOnClickListener(view -> deleteNote());
        saveEditNoteButton.setOnClickListener(view -> updateNote());
    }

    private void updateNote() {
        Note current = repo.getNoteById(id);
        current.title = noteEditTitle.getText().toString();
        current.text = noteEditDetails.getText().toString();
        repo.updateNote(current);

        // https://stackoverflow.com/questions/8202006/android-have-toast-appear-on-button-click
        Toast toast = Toast.makeText(getApplicationContext(), R.string.note_updated, Toast.LENGTH_SHORT);
        toast.show();
        goToMain();
    }

    // https://www.geeksforgeeks.org/android-alert-dialog-box-and-how-to-create-it/
    private void deleteNote() {
        Note current = repo.getNoteById(id);
        // Create the object of
        // AlertDialog Builder class
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(EditNote.this);
        builder.setMessage(R.string.delete_message);
        builder.setTitle(R.string.delete_title);

        // Set Cancelable false
        // for when the user clicks on the outside
        // the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name
        // OnClickListener method is use of
        // DialogInterface interface.
        builder
                .setPositiveButton(
                        R.string.alter_yes,
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {
                                repo.deleteNote(current);
                                // https://stackoverflow.com/questions/8202006/android-have-toast-appear-on-button-click
                                Toast toast = Toast.makeText(getApplicationContext(), R.string.note_deleted, Toast.LENGTH_SHORT);
                                toast.show();
                                goToMain();
                            }
                        });

        // Set the Negative button with No name
        // OnClickListener method is use
        // of DialogInterface interface.
        builder
                .setNegativeButton(
                        R.string.alert_no,
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {

                                // If user click no
                                // then dialog box is canceled.
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    // method to go back to list view and refresh the list with the new note
    // https://www.youtube.com/watch?v=pa_lghjVQVA (41:05)
    private void goToMain() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
