package application.bookstore.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AuthorTest {

  /**
   * testing search method by creating a temporary Array for testing and comparing it with the search result from the actual method
   */
  @Test
  void testGetSearchResults() {
    ArrayList<Author> test = new ArrayList<>();
    Author newAuthor = new Author("Ismail","Kadare");
    test.add(newAuthor);
    ArrayList<Author> actualSearchResults = Author.getSearchResults("Ismail Kadare");
    assertEquals(test.toString(), actualSearchResults.toString());
    System.out.println(actualSearchResults);
  }

  /**
   * test save method
   */
  @Test
  void testSaveInFile() {
    Author test = new Author("testSave","LastNameTest");
    assertTrue(test.saveInFile());
    assertFalse((new Author("Jane", "")).saveInFile());
  }

  /**
   * test isvalid method to check if a Author is valid also included some cases when it is not
   */
  @Test
  void testIsValid() {
    assertTrue((new Author("Ismail", "Kadare")).isValid());
    assertFalse((new Author("", "Doe")).isValid());
    assertFalse((new Author("Jane", "")).isValid());
  }

  /**
   * test delete method
   */
  @Test
  void testDeleteFromFile() {
    assertTrue((new Author("Jane", "Doe")).deleteFromFile());
  }

  /**
   * Testing the getAuthors function if the size of actualAuthors is >0 it will
   * finish the test successfully if the method doesn't return a size >0 it means
   * that there are no users thus the test will fail.
   */
  @Test
  void testGetAuthors() {
    ArrayList<Author> actualAuthors = Author.getAuthors();
    assertTrue(!actualAuthors.isEmpty());
  }

  /**
   * testing getters and setters
   */
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
