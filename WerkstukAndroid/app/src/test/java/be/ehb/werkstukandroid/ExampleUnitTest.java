package be.ehb.werkstukandroid;

import org.junit.Test;

import static org.junit.Assert.*;

import be.ehb.werkstukandroid.entity.Note;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void createNote() {
        int id;
        String title, text;

        id = 1;
        title = "Test note title";
        text = "Test note text";

        Note note = new Note(id, title, text);

        assertEquals("Test note title", note.title);
    }
}