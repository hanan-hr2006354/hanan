package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.PaperContainer;
import model.Paper;
import model.Conference;

import java.util.Arrays;

class PaperContainerTestCase2 {

    private PaperContainer paperContainer = new PaperContainer();
 
    @Test
    void testAddPaperContainer() {

    }

    @Test
    void testAllTotalPapers() {   
        assertEquals(null, paperContainer.maximum3Papers());

    }
}