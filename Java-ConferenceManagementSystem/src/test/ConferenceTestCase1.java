package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Conference;
import model.ConferenceVenue;
import model.PaperContainer;
import model.ReviewerContainer;

import java.time.LocalDate;
import java.util.ArrayList;

class ConferenceTestCase1 {

    private ReviewerContainer reviewers = new ReviewerContainer(); // Assuming this is correct
    private PaperContainer allpapers = new PaperContainer(); // Assuming this is correct
    private ArrayList<ConferenceVenue> cvenues = new ArrayList<>(); // Assuming ConferenceVenue is a class you have defined
    private Conference conference = new Conference("Organizer Name", "Tech Conference", LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 3), reviewers, allpapers, cvenues);

    @Test
    void testConferenceStringStringsDateeDate() {
        assertEquals("Tech Conference", conference.getCName());
        assertEquals(LocalDate.of(2023, 1, 1), conference.getSDate());
        assertEquals(LocalDate.of(2023, 1, 3), conference.getEDate());
    }

    @Test
    void testGetConferenceName() {
        conference.setCName("New Conference Name");
        assertEquals("New Conference Name", conference.getCName());
    }

    @Test
    void testGetStartDate() {
        LocalDate newStartDate = LocalDate.of(2023, 2, 1);
        conference.setsDate(newStartDate);
        assertEquals(newStartDate, conference.getSDate());
    }

    @Test
    void testGetEndDate() {
        LocalDate newEndDate = LocalDate.of(2023, 2, 3);
        conference.seteDate(newEndDate);
        assertEquals(newEndDate, conference.getEDate());
    }
}
