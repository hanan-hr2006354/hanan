package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.Paper;
import model.UserAccount;

import java.util.Arrays;
import java.util.List;

class PaperTestCase1 {
	
    private List<UserAccount> authors = Arrays.asList(
	            new UserAccount("Author1", "author1@example.com", "ID1"),
	            new UserAccount("Author2", "author2@example.com", "ID2")
	        );

		private Paper paper = new Paper("Paper Title", "Abstract", authors, "Keywords");


    @Test
    void testPaper() {
        testGetPaperTitle();
        testGetAbstractPaper();
        testGetAuthors();
        testGetKeywords();
        testGetPNo();
    }
    @Test
    void testPaperConstructorWithAuthors() {
        assertNotNull(paper, "Paper object should be created");
    }

    @Test
    void testGetPaperTitle() {
        assertEquals("Paper Title", paper.getPaperTitle());
    }

    @Test
    void testGetAbstractPaper() {
        assertEquals("Abstract", paper.getAbstractPaper());
    }

    @Test
    void testGetAuthors() {
        assertEquals(authors, paper.getAuthors());
    }

    @Test
    void testGetKeywords() {
        assertEquals("Keywords", paper.getKeywords());
    }

    @Test
    void testGetPNo() {
        // Assuming PNo is properly set in the constructor
        assertTrue(paper.getPNo() >= 0);
    }
}
