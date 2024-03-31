package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.Reviewer;

class ReviewerTestCase1 {

    private Reviewer reviewer = new Reviewer("name", "expertise");

    @Test
    void testReviewerStrinString() {
        testGetName();
        testGetExpertise();
    }

    @Test
    void testGetName() {
        assertEquals("name", reviewer.getName());
    }

    @Test
    void testGetExpertise() {
        assertEquals("expertise", reviewer.getExpertise());
    }
}
