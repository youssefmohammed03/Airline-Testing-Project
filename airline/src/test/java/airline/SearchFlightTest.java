package airline;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;

import GUIpackage.Flight;
import GUIpackage.Ticket;
import GUIpackage.Airline;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SearchFlightTest{
   public static Airline airline = new Airline();

    @BeforeAll
    public static void Setup(){
        airline.createFlight(250, 50, "Cairo", "Aswan", 900, LocalTime.of(14, 30), LocalDate.of(2024, 4, 20), true, false);
		airline.createFlight(180, 30, "London", "New York", 7000, LocalTime.of(18, 45), LocalDate.of(2023, 4, 22), true, false);
	    airline.createFlight(220, 45, "Paris", "Dubai", 6000, LocalTime.of(8, 15), LocalDate.of(2023, 4, 25), false, true);
	    airline.createFlight(190, 35, "New York", "Tokyo", 11000, LocalTime.of(22, 30), LocalDate.of(2023, 4, 28), true, true);
    }

    @AfterAll
    public static void tearDown(){
        for(Flight f : Airline.flights) {
        System.out.println(f.toString());
        }

        for(int i = 0; i < Airline.flights.size(); i++) {
        Airline.flights.set(i, null);
        }
    }
@Test 
@Order(1)
public void searchIndexFlightByIdTest(){
    int index = airline.searchIndexFlightById("9999");
    assertEquals(-1, index);
    assertEquals(0, airline.searchIndexFlightById("10001"));
    assertEquals(1, airline.searchIndexFlightById("10002"));
    assertFalse(airline.searchIndexFlightById("10003") == 3);
}

@Test
void testSeacrhFlights() {
	
    
    String from = "Cairo";
    String to = "Aswan";
    LocalDate date = LocalDate.of(2024, 4, 20);

    // Perform the search
    List<Flight> matchingFlights = airline.searchFlight(from, to, date);

    // Validate the results
    //assertEquals(1, matchingFlights.size());
    assertEquals(airline.flights.get(0), matchingFlights.get(0));
}
}
