package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.Authors;
import model.UserAccount;

class AuthorTestCase2 {

    private Authors authors = new Authors();

    @Test
    void testAuthorsFunctionality() {

    }
    @Test
    void testGetAuthors() {
        assertNotNull(authors.getAuthors(), "Authors list should not be null");
        assertTrue(authors.getAuthors().isEmpty(), "Authors list should be empty initially");
    }
}
