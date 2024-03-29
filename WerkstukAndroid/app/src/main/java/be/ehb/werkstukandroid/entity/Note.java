package be.ehb.werkstukandroid.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notes")
public class Note {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "text")
    public String text;

    public Note() {

    }

    public Note(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public Note(int id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }
}
