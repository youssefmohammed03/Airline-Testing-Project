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

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginSignupTest {
    public static Airline airline;
    
    @BeforeAll
    public static void createPassengers() {
        airline = new Airline();
        airline.signup( "Ahmed24", "AO@76", "Ahmed Hatem Amr", "P123456", "ahmed24@example.com", "1234567890", "123 Street, City, Country", LocalDate.of(1990, 1, 1), "Egyptian", true);
        airline.signup("JohnDoe", "JD@123", "John Doe", "P789012", "johndoe@example.com", "0987654321", "456 Avenue, City, Country", LocalDate.of(1985, 2, 2), "American", true);
        airline.signup("JaneDoe", "JaneD@456", "Jane Doe", "P345678", "janedoe@example.com", "1122334455", "789 Boulevard, City, Country", LocalDate.of(2012, 3, 3), "Canadian", false);
    }

    @AfterAll
    public static void destructPassengers() {
        for(Passenger p : airline.passengers) {
            System.out.println(p.toString());
        }

        for(int i = 0; i < airline.passengers.size(); i++) {
            airline.passengers.set(i, null);
        }
    }

    @Test 
    @Order(1)
    public void signuptest(){
    assertFalse(airline.signup( "Ahmed24", "AO@76", "Ahmed Hatem Amr", "P123456", "ahmed24@example.com", "1234567890", "123 Street, City, Country", LocalDate.of(1990, 1, 1), "Egyptian", true));
    assertTrue(airline.signup( "NewUser", "NU@123", "New User", "P654321", "newuser@example.com", "9876543210", "321 Lane, City, Country", LocalDate.of(2000, 4, 4), "British", false));
    }

    @Test 
    @Order(2)
    public void logintest(){
    	assertEquals( -1, airline.login( "Ahmed24", "xx@xx"));
       assertEquals( 1, airline.login( "JohnDoe", "JD@123")); 
        assertEquals( 0, airline.login( "admin1", "admin1"));
        assertEquals( 1, airline.login( "NewUser", "NU@123")); 
    }
}
