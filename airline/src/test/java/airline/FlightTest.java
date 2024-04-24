package airline;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import GUIpackage.Flight;
import GUIpackage.Ticket;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FlightTest {
	
	public static List<Flight> flights = new ArrayList<>();
	public static List<Ticket> tickets = new ArrayList<>();
	
	@BeforeAll
	public static void createFlights() {
		Flight flight1 = new Flight(flights, 250, 50, "cairo", "London",  248 ,450 , LocalTime.of(14, 30), LocalDate.of(2023, 4, 20), true, false);
		Flight flight2 = new Flight(flights, 300, 25, "cairo", "London",  248 ,450 , LocalTime.of(10, 0), LocalDate.of(2023, 4, 20), false, true);
		Flight flight3 = new Flight(flights, 400, 100, "London", "New York",  248 ,450 , LocalTime.of(18, 45), LocalDate.of(2023, 4, 22), true, false);
		Flight flight4 = new Flight(flights, 100, 35, "Paris", "Dubai",  248 ,450 , LocalTime.of(8, 15), LocalDate.of(2023, 4, 25), false, true);
		Flight flight5 = new Flight(flights, 200, 40, "New York", "Tokyo",  248 ,450 , LocalTime.of(22, 30), LocalDate.of(2023, 4, 28), true, true);
		Ticket t1 = new Ticket("fc123", "First Class", 5000);
		Ticket t2 = new Ticket("e40", "Economy", 2000);
		Ticket t3 = new Ticket("fc23", "First Class", 3000);
		Ticket t4 = new Ticket("e367", "Economy", 2400);
		Ticket t5 = new Ticket("fc57", "First Class", 7450);
		tickets.addAll(Arrays.asList(t1, t2, t3, t4, t5));
	}
	
	@AfterAll
	public static void destructFlights() {
		for(int i = 0; i < flights.size(); i++) {
	        flights.set(i, null);
	    }
	}
	
	@Test
	void testCreateFlight() {
		assertNotNull(flights.get(0));
		assertNotNull(flights.get(4));
	}
	
	@Test
	void testGenerateFlightId() {
		assertEquals(flights.get(0).getFlightId(), "10001");
		assertEquals(flights.get(4).getFlightId(), "10005");
		flights.get(4).setFlightId("99999");
		Flight flight6 = new Flight(flights, 200, 40, "New York", "Tokyo",  248 ,450 , LocalTime.of(21, 30), LocalDate.of(2023, 4, 28), true, true);
		assertEquals(flights.get(5).getFlightId(), "99999");
		assertEquals(flights.get(4).getFlightId(), "10005");
	}
	
	@Test
	void testTicketsSettersGetters() {
		List <Ticket> t = new ArrayList<>();
		t.add(new Ticket("fc25", "First Class", 700));
		t.add(new Ticket("e80", "Economy", 400));
		flights.get(2).setTickets(t);
		assertSame(t, flights.get(2).getTickets());
	}
	
	@Nested
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class TicketManipulation{
		@Order(1)
		@ParameterizedTest
		@ValueSource(ints = {0, 1, 2, 3, 4})
		void testAddTicket(int number) {
			flights.get(0).addTicket(tickets.get(number));
			flights.get(1).addTicket(tickets.get(number));

			assertSame(tickets.get(number), flights.get(0).getTickets().get(number));
			assertSame(tickets.get(number), flights.get(1).getTickets().get(number));
		}
		
		@Test
		@Order(2)
		void testSearchTicket() {
			assertSame(flights.get(0).searchTicket("fc123"), flights.get(0).getTickets().get(0));
			assertNotSame(flights.get(0).searchTicket("fc123"), flights.get(0).getTickets().get(1));
		}
		
		@Order(3)
		@ParameterizedTest
		@CsvSource({"fc123, 4", "e40, 3", "fc23, 2", "e367, 1", "fc57, 0"})
		void testRemoveTicket(String ticketId, int number) {
			flights.get(0).removeTicket(ticketId);
			assertTrue(flights.get(0).getTickets().size() == number);
		}
	}

}
