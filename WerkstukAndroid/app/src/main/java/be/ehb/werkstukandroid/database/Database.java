package be.ehb.werkstukandroid.database;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import be.ehb.werkstukandroid.dao.NoteDao;
import be.ehb.werkstukandroid.entity.Note;

// https://developer.android.com/codelabs/android-room-with-a-view#7
// https://stackoverflow.com/questions/52999851/why-is-recommended-not-to-use-allowmainthreadqueries-for-android-room
// https://stackoverflow.com/questions/49629656/please-provide-a-migration-in-the-builder-or-call-fallbacktodestructivemigration

@androidx.room.Database(entities = {Note.class}, version = 1) // version = aantal veranderingen in dao of entity
public abstract class Database extends RoomDatabase {

    public abstract NoteDao noteDao();

    // 1 instantie nodig van de room database voor de app -> singleton
    private static volatile Database singleton;

    // Singleton maken
    static Database getDatabase(final Context context) {
        if (singleton == null) {
            synchronized (Database.class) {
                if (singleton == null) {
                    singleton = Room.databaseBuilder(context.getApplicationContext(), Database.class, "Database")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration() // Fix migration problems & database access op main thread.
                            .build();
                }
            }
        }
        return singleton;
    }
}
