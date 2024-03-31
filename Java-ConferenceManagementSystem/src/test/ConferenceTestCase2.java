package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Conference;
import model.ConferenceVenue;
import model.PaperContainer;
import model.ReviewerContainer;

import java.time.LocalDate;
import java.util.ArrayList;

class ConferenceTestCase2 {

    private ReviewerContainer reviewers = new ReviewerContainer();
    private PaperContainer allpapers = new PaperContainer();
    private ArrayList<ConferenceVenue> cvenues = new ArrayList<>();
    private Conference conference = new Conference(null, null, null, null, reviewers, allpapers, cvenues);

    @Test
    void testConferenceNullDetails() {

    }

    @Test
    void testSetConferenceNameWithNull() {
        conference.setCName(null);
        assertNull(conference.getCName(), "Conference name should be set to null");
    }

    @Test
    void testSetStartDateWithNull() {
        conference.setsDate(null);
        assertNull(conference.getSDate(), "Start date should be set to null");
    }

    @Test
    void testSetEndDateWithNull() {
        conference.seteDate(null);
        assertNull(conference.getEDate(), "End date should be set to null");
    }
}
