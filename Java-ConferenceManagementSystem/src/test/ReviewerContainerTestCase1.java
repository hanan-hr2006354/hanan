package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Reviewer;
import model.ReviewerContainer;

class ReviewerContainerTestCase1 {

    private ReviewerContainer reviewerContainer = new ReviewerContainer();
    private Reviewer reviewer1 = new Reviewer("Reviewer1", "Expertise1");
    private Reviewer reviewer2 = new Reviewer("Reviewer2", "Expertise2");
    
    @Test
    void testReviewers() {
    	testGetAdd();
    }
    @Test
    void testGetAdd() {
        reviewerContainer.add(reviewer1);
        reviewerContainer.add(reviewer2);

        assertTrue(reviewerContainer.getUserReviewers().contains(reviewer1));
        assertTrue(reviewerContainer.getUserReviewers().contains(reviewer2));
        assertEquals(2, reviewerContainer.getUserReviewers().size());
    }
}
