package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.Paper;
import model.UserAccount;

import java.util.ArrayList;
import java.util.List;

class PaperTestCase2 {
    
    private List<UserAccount> authors = new ArrayList<>(); // Empty list of authors
    private Paper paper = new Paper();

    @Test
    void testPaper() {

    }

    @Test
    void testPaperConstructorWithAuthors() {
        assertNotNull(paper);//Paper object should be created even with empty authors
    }

    @Test
    void testGetPaperTitle() {
        assertEquals(null, paper.getPaperTitle());
    }

    @Test
    void testGetAbstractPaper() {
        assertEquals(null, paper.getAbstractPaper());
    }

    @Test
    void testGetAuthors() {
        assertTrue(paper.getAuthors().isEmpty());
    }

    @Test
    void testGetKeywords() {
        assertEquals(null, paper.getKeywords());
    }

    @Test
    void testGetPNo() {
        // Assuming PNo is properly set in the constructor
        assertTrue(paper.getPNo() >= 0, "Paper number should be non-negative");
    }
}
