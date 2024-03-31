package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.ConferenceVenue;

class ConferenceVenueTestCase1 {

    private ConferenceVenue venue = new ConferenceVenue("Venue Name", "123 Address", "10:00 AM");

    @Test
    void testConferenceVenue() {
        testGetVenue();
        testGetAddress();
        testGetTime();
    }
    @Test
    void testGetVenue() {
        assertEquals("Venue Name", venue.getVenue());
    }
    @Test
    void testGetAddress() {
        assertEquals("123 Address", venue.getAddress());
    }
    @Test
    void testGetTime() {
        assertEquals("10:00 AM", venue.getTime());
    }
}
