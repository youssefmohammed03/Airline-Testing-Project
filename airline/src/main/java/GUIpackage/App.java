package GUIpackage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class App {

	public static void main(String[] args) {
		Airline a = new Airline();
		a.createFlight(250, 50, "Cairo", "London", 10000, LocalTime.of(14, 30), LocalDate.of(2023, 4, 20), true, false);
		a.createFlight(200, 40, "Cairo", "London", 9000, LocalTime.of(10, 0), LocalDate.of(2023, 4, 20), false, true);
        a.createFlight(180, 30, "London", "New York", 7000, LocalTime.of(18, 45), LocalDate.of(2023, 4, 22), true, false);
        a.createFlight(220, 45, "Paris", "Dubai", 6000, LocalTime.of(8, 15), LocalDate.of(2023, 4, 25), false, true);
        a.createFlight(190, 35, "New York", "Tokyo", 11000, LocalTime.of(22, 30), LocalDate.of(2023, 4, 28), true, true);
        a.createFlight(210, 40, "Dubai", "Singapore", 5000, LocalTime.of(14, 0), LocalDate.of(2023, 4, 30), false, false);
        List <Flight> flightsAvaialble = a.searchFlight("cairo", "London", LocalDate.of(2023, 4, 20));
        for (Flight f : flightsAvaialble) {
            System.out.println(f.toString());
        }
	}

}
