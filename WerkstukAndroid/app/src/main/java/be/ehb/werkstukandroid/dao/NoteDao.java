package be.ehb.werkstukandroid.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import be.ehb.werkstukandroid.entity.Note;

// https://developer.android.com/training/data-storage/room

@Dao
public interface NoteDao {
    @Query("SELECT * FROM Notes")
    List<Note> getAllNotes();

    @Query("SELECT * FROM Notes WHERE id == :id")
    Note getNoteById(int id);

    @Insert
    long insertNote(Note note);

    @Update
    void updateNote(Note note);

    @Delete
    void deleteNote(Note note);
}
