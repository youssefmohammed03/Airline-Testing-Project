package airline;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import GUIpackage.Flight;
import GUIpackage.Airline;;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AdminTest {

	Airline airline = new Airline();
	
	public AdminTest() {
		airline.createFlight(250, 50, "Cairo", "Aswan", 900, LocalTime.of(14, 30), LocalDate.of(2024, 4, 20), true, false);
		airline.createFlight(180, 30, "London", "New York", 7000, LocalTime.of(18, 45), LocalDate.of(2023, 4, 22), true, false);
	    airline.createFlight(220, 45, "Paris", "Dubai", 6000, LocalTime.of(8, 15), LocalDate.of(2023, 4, 25), false, true);
	    airline.createFlight(190, 35, "New York", "Tokyo", 11000, LocalTime.of(22, 30), LocalDate.of(2023, 4, 28), true, true);
	    
	}
	
	@Test
	@Order(1)
	void createFlightByAdmin() {
        
		for (int i = 0 ;  i< airline.flights.size() ; i++) {
		System.out.println(airline.flights.get(i).toString());
		assertNotNull(airline.flights.get(i));
		}
		
		assertEquals(airline.flights.get(2).getEconomySeatsAvailable(), 220);
		assertEquals(airline.flights.get(1).getFrom(), "London");
		assertEquals(airline.flights.get(3).getTo(), "Tokyo");
	}
	
	@Test
	void economyFareCalculation() {
		double fare1 = airline.economyFare(800, true, true);
		assertEquals(153.6 , fare1, 0.0001);
		
		double fare2 = airline.economyFare(1506, false, true);
		assertEquals(240.96 , fare2, 0.0001);
		
		double fare3 = airline.economyFare(919,  false, false);
		assertEquals(183.8, fare3, 0.0001);
		
		double fare4 = airline.economyFare(1506, true, false);
		assertEquals(361.44, fare4, 0.0001);
	}
	
	@Test
	void firstClassFareCalculation() {
		double fare1 = airline.firstclassFare(800, true, true);
		assertEquals(312 , fare1, 0.0001);
		
		double fare2 = airline.firstclassFare(1506, false, true);
		assertEquals(451.8 , fare2, 0.0001);
		
		double fare3 = airline.firstclassFare(919,  false, false);
		assertEquals(367.6, fare3, 0.0001);
		
		double fare4 = airline.firstclassFare(1506, true, false);
		assertEquals(783.12, fare4, 0.0001);
	}
	
	@Test
	@Order(2)
	void delayFlightTest() {
		String ID = airline.flights.get(2).getFlightId();
		airline.delayFlight(ID, LocalDate.of(2023, 4, 22) , LocalTime.of(22, 30));
		assertEquals( LocalTime.of(22, 30) , airline.flights.get(2).getTime());
		assertEquals( LocalDate.of(2023, 4, 22), airline.flights.get(2).getDate());
	}
	
	@Test
	@Order(3)
	void cancelFlightTest() {
		String ID = airline.flights.get(2).getFlightId();
		Flight flight = airline.flights.get(2);
		airline.searchFlightById(ID);
		assertNotNull(flight);
		System.out.println(flight.toString());
		airline.cancelFlight(ID);
		assertFalse(airline.flights.contains(flight));
		
		if( airline.flights.get(2) == null) System.out.println("Flight cancelled");
	}
}
