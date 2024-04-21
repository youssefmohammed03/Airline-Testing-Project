package airline;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import GUIpackage.Flight;
import GUIpackage.Ticket;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FlightTest {
	
	public static List<Flight> flights = new ArrayList<>();
	
	@BeforeAll
	public static void createFlights() {
		Flight flight1 = new Flight(flights, 250, 50, "cairo", "London",  248 ,450 , LocalTime.of(14, 30), LocalDate.of(2023, 4, 20), true, false);
		Flight flight2 = new Flight(flights, 300, 25, "cairo", "London",  248 ,450 , LocalTime.of(10, 0), LocalDate.of(2023, 4, 20), false, true);
		Flight flight3 = new Flight(flights, 400, 100, "London", "New York",  248 ,450 , LocalTime.of(18, 45), LocalDate.of(2023, 4, 22), true, false);
		Flight flight4 = new Flight(flights, 100, 35, "Paris", "Dubai",  248 ,450 , LocalTime.of(8, 15), LocalDate.of(2023, 4, 25), false, true);
		Flight flight5 = new Flight(flights, 200, 40, "New York", "Tokyo",  248 ,450 , LocalTime.of(22, 30), LocalDate.of(2023, 4, 28), true, true);
	}
	
	@AfterAll
	public static void destructFlights() {
		for(Flight f : flights) {
			System.out.println(f.toString());
		}
		
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
		flights.get(1).setTickets(t);
		assertSame(t, flights.get(1).getTickets());
	}
	
	@Test
	@Order(1)
	void testAddTicket() {
		Ticket t1 = new Ticket("fc123", "First Class", 540);
		Ticket t2 = new Ticket("e40", "Economy", 350);
		flights.get(0).addTicket(t1);
		flights.get(0).addTicket(t2);
		assertSame(t1, flights.get(0).getTickets().get(0));
		assertSame(t2, flights.get(0).getTickets().get(1));
	}
	
	@Test
	@Order(2)
	void testSearchTicket() {
		assertSame(flights.get(0).searchTicket("fc123"), flights.get(0).getTickets().get(0));
		assertNotSame(flights.get(0).searchTicket("fc123"), flights.get(0).getTickets().get(1));
	}
	
	@Test
	@Order(3)
	void testRemoveTicket() {
		flights.get(0).removeTicket("e40");
		assertTrue(flights.get(0).getTickets().size() == 1);
	}

}
