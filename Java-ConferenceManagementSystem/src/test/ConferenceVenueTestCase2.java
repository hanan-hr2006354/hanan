package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.ConferenceVenue;

class ConferenceVenueTestCase2 {

    private ConferenceVenue venue = new ConferenceVenue();

    @Test
    void testConferenceVenue() {

    }
    @Test
    void testGetVenue() {
        assertEquals(null, venue.getVenue());
    }
    @Test
    void testGetAddress() {
    	assertEquals(null, venue.getAddress());
    }
    @Test
    void testGetTime() {
    	assertEquals(null, venue.getTime());
    }
}
