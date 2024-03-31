package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Authors;
import model.UserAccount;

class AuthorTestCase1 {

    private Authors authors; // This is the Authors class from the model package
    private UserAccount author1;
    private UserAccount author2;

    @BeforeEach
    void setUp() throws Exception {
        authors = new Authors(); // Initialize the Authors class
        author1 = new UserAccount("Ahmed", "ahmed@gmail.com", "123456");
        author2 = new UserAccount("Mona","mona@example.com","123456");

        // Add authors to the Authors class
        authors.addAuthor(author1);
        authors.addAuthor(author2);
    }

    @Test
    void testAuthorsFunctionality() {
        testGetAuthors();
    }
    @Test
    void testGetAuthors() {
        assertEquals(2, authors.getAuthors().size());
        assertTrue(authors.getAuthors().contains(author1));
        assertTrue(authors.getAuthors().contains(author2));
    }
}
