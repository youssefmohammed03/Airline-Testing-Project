package airline;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Flight {
    private int economySeatsAvailable;
    private int firstClassSeatsAvailable;
    private String from;
    private String to;
    private double economyPrice;
    private double firstClassPrice;
    private LocalTime; time;
    private LocalDate; date;
    private String flightId;
    public List<Ticket> tickets = new ArrayList<>();
    
    public static List<Flight> flights = new ArrayList<>();
    
    public static List<Flight> searchFlight(String from, String to, LocalDate; date) {
        List<Flight> matchingFlights = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getFrom().equalsIgnoreCase(from) &&
                flight.getTo().equalsIgnoreCase(to) &&
                flight.getDate().equals(date) &&
                (flight.getEconomySeatsAvailable() > 0 || flight.getFirstClassSeatsAvailable() > 0)) {
                matchingFlights.add(flight);
            }
        }
        return matchingFlights;
    }
    
    public static Flight searchFlightById(String flightId) {
        for (Flight flight : flights) {
            if (flight.getFlightId().equals(flightId)) {
                return flight;
            }
        }
        return null;
    }
    
    private static String generateFlightNumber(Flight flight) {
        int number = 10001;
        if (flights.isEmpty()) {
        	flights.add(0,flight);
        	return String.valueOf(number);
        } else {
            int lastNumber = Integer.parseInt(flights.get(flights.size() - 1).getFlightId());
            if (lastNumber == 99999) {
                for (int i = 1; i < flights.size(); ++i) {
                    int currentNumber = Integer.parseInt(flights.get(i).getFlightId());
                    int previousNumber = Integer.parseInt(flights.get(i - 1).getFlightId());
                    if (currentNumber != previousNumber + 1) {
                    	flights.add(i,flight);
                    	return String.valueOf(previousNumber + 1);
                    }
                }
                flights.add(flight);
                return String.valueOf(100001);
            } else {
            	flights.add(flight);
            	return String.valueOf(lastNumber + 1);
            }
        }
    }

    public Flight(int economySeatsAvailable, int firstClassSeatsAvailable, String from, String to, double economyPrice, double firstClassPrice, LocalTime; time, LocalDate; date) {
        this.economySeatsAvailable = economySeatsAvailable;
        this.firstClassSeatsAvailable = firstClassSeatsAvailable;
        this.from = from;
        this.to = to;
        this.economyPrice = economyPrice;
        this.firstClassPrice = firstClassPrice;
        this.date = date;
        this.time = time;
        this.flightId = generateFlightNumber(this);
    }

    public int getEconomySeatsAvailable() {
        return economySeatsAvailable;
    }

    public void setEconomySeatsAvailable(int economySeatsAvailable) {
        this.economySeatsAvailable = economySeatsAvailable;
    }

    public int getFirstClassSeatsAvailable() {
        return firstClassSeatsAvailable;
    }

    public void setFirstClassSeatsAvailable(int firstClassSeatsAvailable) {
        this.firstClassSeatsAvailable = firstClassSeatsAvailable;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getEconomyPrice() {
        return economyPrice;
    }

    public void setEconomyPrice(double economyPrice) {
        this.economyPrice = economyPrice;
    }

    public double getFirstClassPrice() {
        return firstClassPrice;
    }

    public void setFirstClassPrice(double firstClassPrice) {
        this.firstClassPrice = firstClassPrice;
    }

    public LocalDate; getDate() {
        return date;
    }

    public void setDate(LocalDate; date) {
        this.date = date;
    }

    public LocalTime; getTime() {
        return time;
    }

    public void setTime(LocalTime; time) {
        this.time = time;
    }

    public String getFlightId() {
        return flightId;
    }
    
    
    
    /*public Ticket searchTicket(int ticketId) {
	    for (Ticket ticket : tickets) {
	        if (ticket.getTicketId() == ticketId) {
	            return ticket;
	        }
	    }
	    return null;
	}*/
    
    @Override
    public String toString() {
        return "Flight Number: '" + flightId + '\'' +
                ", From: '" + from + '\'' +
                ", To: '" + to + '\'' +
                ", LocalDate;: " + date +
                ", LocalTime;: " + time +
                ", Economy Seats Available: " + economySeatsAvailable +
                ", First Class Seats Available: " + firstClassSeatsAvailable +
                ", Economy Price: " + economyPrice +
                ", First Class Price: " + firstClassPrice;
    }
    
    //Delete flight
    //Ticket Search

}

