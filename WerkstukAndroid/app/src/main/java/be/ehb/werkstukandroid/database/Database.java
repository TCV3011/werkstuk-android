package be.ehb.werkstukandroid.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import be.ehb.werkstukandroid.dao.UserDao;
import be.ehb.werkstukandroid.entities.User;

/*
* resource used for this code:
* https://developer.android.com/codelabs/android-room-with-a-view#7
* https://stackoverflow.com/questions/52999851/why-is-recommended-not-to-use-allowmainthreadqueries-for-android-room
* https://stackoverflow.com/questions/49629656/please-provide-a-migration-in-the-builder-or-call-fallbacktodestructivemigration
* */

@androidx.room.Database(entities = {User.class}, version = 1)
public abstract class Database extends RoomDatabase {

    public abstract UserDao userDao();

    private static volatile Database database;

    static Database getDatabase(final Context context) {
        if (database == null) {
            synchronized (Database.class) {
                if (database == null) {
                    database = Room.databaseBuilder(context.getApplicationContext(), Database.class, "Database")
                            .allowMainThreadQueries().fallbackToDestructiveMigration().build();
                }
            }
        }
        return database;
    }
}