package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Conference;
import model.ConferenceContainer;
import model.PaperContainer;
import model.ReviewerContainer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class ConferenceContainerTestCase1 {

    private Conference conference1 = new Conference("Organizer1", "Tech Conference 1", LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 3), new ReviewerContainer(), new PaperContainer(), new ArrayList<>());
    private Conference conference2 = new Conference("Organizer2", "Tech Conference 2", LocalDate.of(2023, 2, 1), LocalDate.of(2023, 2, 3), new ReviewerContainer(), new PaperContainer(), new ArrayList<>());

    @Test
    void testConferenceContainer() {
        testAddAndGetConferences();
    }

    @Test
    void testAddAndGetConferences() {
        // Clearing and adding conferences to the container
        ConferenceContainer.getUserConference().clear();
        ConferenceContainer.addConference(conference1);
        ConferenceContainer.addConference(conference2);

        // Assertions
        List<Conference> conferences = ConferenceContainer.getUserConference();
        assertTrue(conferences.contains(conference1), "ConferenceContainer should contain conference1");
        assertTrue(conferences.contains(conference2), "ConferenceContainer should contain conference2");
        assertEquals(2, conferences.size(), "ConferenceContainer should have 2 conferences");
    }
}
