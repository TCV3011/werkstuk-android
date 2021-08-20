package be.ehb.werkstukandroid.database;

import android.app.Application;

import java.util.List;

import be.ehb.werkstukandroid.dao.NoteDao;
import be.ehb.werkstukandroid.entity.Note;

public class DatabaseRepository {
    private NoteDao noteDao;

    public DatabaseRepository(Application application) {
        Database db = Database.getDatabase(application);
        noteDao = db.noteDao();
    }

    // CRUD Note
    public List<Note> getAllNotes() {
        return noteDao.getAllNotes();
    }

    public Note getNoteById(int id) {
        return noteDao.getNoteById(id);
    }

    public long insertNote(Note note) {
        return noteDao.insertNote(note);
    }

    public void updateNote(Note note) {
        noteDao.updateNote(note);
    }

    public void deleteNote(Note note) {
        noteDao.deleteNote(note);
    }
}
