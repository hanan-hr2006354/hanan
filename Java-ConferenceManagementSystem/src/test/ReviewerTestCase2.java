package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.Reviewer;

class ReviewerTestCase2 {

    private Reviewer reviewer = new Reviewer();

    @Test
    void testReviewerStrinString() {
       
    }

    @Test
    void testGetName() {
        assertEquals(null, reviewer.getName());
    }

    @Test
    void testGetExpertise() {
        assertEquals(null, reviewer.getExpertise());
    }
}
