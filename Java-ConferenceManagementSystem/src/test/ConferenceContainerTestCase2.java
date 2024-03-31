package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.ConferenceContainer;

class ConferenceContainerTestCase2 {

    @Test
    void testConferenceContainer() {
    }
    @Test
    void testAddConference() {
        // Clearing and testing with null conference
        ConferenceContainer.getUserConference().clear();
        ConferenceContainer.addConference(null);
        assertTrue(ConferenceContainer.getUserConference().isEmpty());
    }
    @Test
    void testContainerEmptyState() {
        // Clearing and testing the initial state
        ConferenceContainer.getUserConference().clear();
        assertTrue(ConferenceContainer.getUserConference().isEmpty());
    }
}
