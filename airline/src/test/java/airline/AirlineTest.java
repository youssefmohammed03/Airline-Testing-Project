package airline;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
    
    @AfterEach
    void tearDown() {
        testFlight = new Flight(airline.flights, 100, 50, "New York", "London", 200.0, 300.0, LocalTime.of(8, 0), LocalDate.of(2024, 4, 23), true, false);
    }
    
    @Nested
    class FlightBookingTests {
        
        @ParameterizedTest(name = "Test Book Seat: {0}")
        @ValueSource(strings = {"Economy", "First Class"})
        void testBookSeat(String input) {
            System.out.println("Running test with flight: " + input);
            assertTrue(airline.bookSeat(testFlight, input, testPassenger));
        }
        
        @RepeatedTest(5)
        void testBookSeatEconomyClass() {
            int initialAvailableSeats = testFlight.getEconomySeatsAvailable();
            System.out.println("Economy seats available = " + initialAvailableSeats);
            airline.bookSeat(testFlight, "Economy", testPassenger);
            assertEquals(initialAvailableSeats - 1, testFlight.getEconomySeatsAvailable());
        }
        
        @RepeatedTest(5)
        void testBookSeatFirstClass() {
            int initialAvailableSeats = testFlight.getFirstClassSeatsAvailable();
            airline.bookSeat(testFlight, "First Class", testPassenger);
            assertEquals(initialAvailableSeats - 1, testFlight.getFirstClassSeatsAvailable());
        }
        @Test
        void testBookSeatNoAvailableSeats() {
            // Fill up all seats
            for (int i = 0; i < 100; i++) {
                airline.bookSeat(testFlight, "Economy", new Passenger("Passenger " + i, "pass" + i, "Passenger Name", "P" + i, "email@example.com", "123456789", "Address", LocalDate.of(1980, 1, 1), "Nationality", true));
            }
            for (int i = 0; i < 50; i++) {
                airline.bookSeat(testFlight, "First Class", new Passenger("Passenger " + i, "pass" + i, "Passenger Name", "P" + i, "email@example.com", "123456789", "Address", LocalDate.of(1980, 1, 1), "Nationality", true));
            }
            assertFalse(airline.bookSeat(testFlight, "Economy", testPassenger));
            assertFalse(airline.bookSeat(testFlight, "First Class", testPassenger));
        }
    }
    
    
    @Nested
    class BookingCancelTest{
    	
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
}
