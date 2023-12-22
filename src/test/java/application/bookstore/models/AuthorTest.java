package application.bookstore.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AuthorTest {

    @Test
    void testGetSearchResults() {
        ArrayList<Author> actualSearchResults = Author.getSearchResults("Ismail Kadare");
        assertEquals(1, actualSearchResults.size());
    }


    @Test
    void testIsValid() {
        assertTrue((new Author("Jane", "Doe")).isValid());
        assertFalse((new Author("", "Doe")).isValid());
        assertFalse((new Author("Jane", "")).isValid());
    }

    @Test
    void testDeleteFromFile() {
        assertTrue((new Author("Jane", "Doe")).deleteFromFile());
    }


    @Test
    void testGettersAndSetters() {
        Author author = new Author("test", "last");
        String actualToStringResult = author.toString();
        String actualFirstName = author.getFirstName();
        assertEquals("last", author.getLastName());
        assertEquals("test last", actualToStringResult);
        assertEquals("test", actualFirstName);
    }

}
