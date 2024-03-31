package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Reviewer;
import model.ReviewerContainer;

class ReviewerContainerTestCase2 {

    private ReviewerContainer reviewerContainer = new ReviewerContainer();
   
    @Test
    void testReviewers() {
    }
    @Test
    void testGetAdd() {
        reviewerContainer.add(null);
        assertTrue(reviewerContainer.getUserReviewers().isEmpty());
    }
}
