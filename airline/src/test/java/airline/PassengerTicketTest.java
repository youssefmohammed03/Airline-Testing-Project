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


import GUIpackage.Passenger;
import GUIpackage.Airline;
import GUIpackage.Flight;
import GUIpackage.Ticket;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PassengerTicketTest {

    public static Airline airline;
    public static Ticket ticket;
    public static Passenger passenger;
    public static Flight flight;

    @BeforeAll
    public  static void setup(){
    airline = new Airline();
    airline.signup("Ahmed689l","ahmed123","Ahmed Hossam ElDin","P123456","ahmed789@example.com","1234567890","123 Street, City, Country",LocalDate.of(1990, 1, 1),"Egyptian",true);
    passenger = airline.passengers.get(0);
    airline.createFlight(250, 50, "Cairo", "Aswan", 900, LocalTime.of(14, 30), LocalDate.of(2024, 4, 20), true, false);
    flight = airline.flights.get(0);
    airline.bookSeat(flight, "Economy", passenger);
    ticket = passenger.getTicket();
    airline.createFlight(250, 50, "Cairo", "Aswan", 900, LocalTime.of(14, 30), LocalDate.of(2024, 4, 20), true, false);
    airline.createFlight(180, 30, "London", "New York", 7000, LocalTime.of(18, 45), LocalDate.of(2023, 4, 22), true, false);
	airline.createFlight(220, 45, "Paris", "Dubai", 6000, LocalTime.of(8, 15), LocalDate.of(2023, 4, 25), false, true);
	airline.createFlight(190, 35, "New York", "Tokyo", 11000, LocalTime.of(22, 30), LocalDate.of(2023, 4, 28), true, true);
   
    }

    @Test 
    @Order(1)
    public void TicketSetterGetterTest(){
    ticket.setTicketId("t123");
    assertEquals("t123", ticket.getTicketId());
    ticket.setSeatType("First Class");
    assertEquals("First Class", ticket.getSeatType());
    ticket.setPrice(1000);
    assertEquals(1000, ticket.getPrice());
    ticket.setFlight(flight);
    assertEquals(flight, ticket.getFlight());
    ticket.setPassenger(passenger);
    assertEquals(passenger, ticket.getPassenger());
    }
    @Test
    @Order(2)
    public void PassengerSetterGetterTest(){
        passenger.setUsername("Ahmed123");
        assertEquals("Ahmed123", passenger.getUsername());
        passenger.setPassword("ahmed123");
        assertEquals("ahmed123", passenger.getPassword());
        passenger.setName("Ahmed Hossam ElDin");
        assertEquals("Ahmed Hossam ElDin", passenger.getName());
        passenger.setPassportNumber("P123456");
        assertEquals("P123456", passenger.getPassportNumber());
        passenger.setEmail("ahmed123@example.com");
        assertEquals("ahmed123@example.com", passenger.getEmail());
        passenger.setPhoneNumber("1234567890");
        assertEquals("1234567890", passenger.getPhoneNumber());
        passenger.setAddress("123 Street, Cairo, Egypt");
        assertEquals("123 Street, Cairo, Egypt", passenger.getAddress());
        passenger.setDateOfBirth(LocalDate.of(1990, 1, 1));
        assertEquals(LocalDate.of(1990, 1, 1), passenger.getDateOfBirth());
        passenger.setNationality("Egyptian");
        assertEquals("Egyptian", passenger.getNationality());
        passenger.setAdult(true);
        assertEquals(true, passenger.isAdult());
    }
   
        


}
