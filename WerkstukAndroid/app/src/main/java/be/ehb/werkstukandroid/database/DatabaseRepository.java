package be.ehb.werkstukandroid.database;

import be.ehb.werkstukandroid.dao.UserDao;
import be.ehb.werkstukandroid.entities.User;

import android.app.Application;

import java.util.List;

public class DatabaseRepository {
    private UserDao userDao;

    public DatabaseRepository(Application application) {
        Database db = Database.getDatabase(application);

        userDao = db.userDao();
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
