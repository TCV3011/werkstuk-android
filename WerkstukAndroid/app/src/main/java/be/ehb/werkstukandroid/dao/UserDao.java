package be.ehb.werkstukandroid.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import be.ehb.werkstukandroid.entities.User;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User")
    List<User> getAllUsers();
}
