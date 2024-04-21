package airline;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;

import GUIpackage.Flight;

class FlightTest {
	
	public static List<Flight> flights = new ArrayList<>();
	
	@BeforeAll
	public static void createAirline() {
		Flight flight1 = new Flight(flights, 250, 50, "cairo", "London",  248 ,450 , LocalTime.of(14, 30), LocalDate.of(2023, 4, 20), true, false);
		Flight flight2 = new Flight(flights, 300, 25, "cairo", "London",  248 ,450 , LocalTime.of(10, 0), LocalDate.of(2023, 4, 20), false, true);
		Flight flight3 = new Flight(flights, 400, 100, "London", "New York",  248 ,450 , LocalTime.of(18, 45), LocalDate.of(2023, 4, 22), true, false);
		Flight flight4 = new Flight(flights, 100, 35, "Paris", "Dubai",  248 ,450 , LocalTime.of(8, 15), LocalDate.of(2023, 4, 25), false, true);
		Flight flight5 = new Flight(flights, 200, 40, "New York", "Tokyo",  248 ,450 , LocalTime.of(22, 30), LocalDate.of(2023, 4, 28), true, true);
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

}
