package airline;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import GUIpackage.Airline;
import GUIpackage.Flight;
import GUIpackage.Passenger;
import GUIpackage.Ticket;

class AirlineTest {
	
    private Airline airline;
    private Flight testFlight;
    private Passenger testPassenger;
    	
    @BeforeEach
    void setUp() {
        airline = new Airline();
        testFlight = new Flight(airline.flights, 100, 50, "New York", "London", 200.0, 300.0, LocalTime.of(8, 0), LocalDate.of(2024, 4, 23), true, false);
        testPassenger = new Passenger("testUser", "testPassword", "Test Passenger", "ABC123", "test@example.com", "123456789", "123 Test St", LocalDate.of(1990, 1, 1), "Nationality", true);
    }

    @Test
    void testBookSeatEconomy() {
    	//System.out.println(testFlight.)
        assertTrue(airline.bookSeat(testFlight, "Economy", testPassenger));
        assertEquals(99, testFlight.getEconomySeatsAvailable());
    }

    @Test
    void testBookSeatFirstClass() {
        assertTrue(airline.bookSeat(testFlight, "FirstClass", testPassenger));
        assertEquals(49, testFlight.getFirstClassSeatsAvailable());
    }

    @Test
    void testBookSeatNoAvailableSeats() {
        // Fill up all seats
        for (int i = 0; i < 100; i++) {
            airline.bookSeat(testFlight, "Economy", new Passenger("Passenger " + i, "pass" + i, "Passenger Name", "P" + i, "email@example.com", "123456789", "Address", LocalDate.of(1980, 1, 1), "Nationality", true));
        }
        assertFalse(airline.bookSeat(testFlight, "Economy", testPassenger));
        assertFalse(airline.bookSeat(testFlight, "First Class", testPassenger));
    }

    @Test
    void testCancelBooking() {
        assertTrue(airline.bookSeat(testFlight, "Economy", testPassenger));
        assertEquals(99, testFlight.getEconomySeatsAvailable());

        Ticket ticket = testPassenger.getTicket();
        assertTrue(airline.cancelBooking(ticket, testFlight, testPassenger));
        assertEquals(100, testFlight.getEconomySeatsAvailable());
        assertNull(testPassenger.getTicket());
    }

    @Test
    void testCancelBookingNoBooking() {
        assertFalse(airline.cancelBooking(null, testFlight, testPassenger));
    }
}
