package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.PaperContainer;
import model.Paper;

class PaperContainerTestCase1 {

    private PaperContainer paperContainer = new PaperContainer();
    private Paper paper1 = new Paper(); // Assuming a default constructor for Paper
    private Paper paper2 = new Paper(); // Assuming a default constructor for Paper
    private Paper paper3 = new Paper(); // Assuming a default constructor for Paper

    @Test
    void testAddPaperContainer() {
    	testGetMaximum3Papers();

    }

    @Test
    void testGetMaximum3Papers() {
        paperContainer.add(paper1);
        paperContainer.add(paper2);
        paperContainer.add(paper3);
        assertTrue(paperContainer.maximum3Papers());

        Paper paper4 = new Paper(); // Additional paper
        paperContainer.add(paper4);
        assertEquals(3, paperContainer.getPapers().size());
    }
}
