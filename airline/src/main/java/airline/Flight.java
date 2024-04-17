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
    private LocalTime time;
    private LocalDate date;
    private String flightNumber;
    public List<Ticket> tickets = new ArrayList<>();
    
    public static List<Flight> flights = new ArrayList<>();
    
    public static List<Flight> flightSearch(String from, String to, LocalDate date) {
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

    public Flight(int economySeatsAvailable, int firstClassSeatsAvailable, String from, String to, double economyPrice, double firstClassPrice, LocalTime time, LocalDate date) {
        this.economySeatsAvailable = economySeatsAvailable;
        this.firstClassSeatsAvailable = firstClassSeatsAvailable;
        this.from = from;
        this.to = to;
        this.economyPrice = economyPrice;
        this.firstClassPrice = firstClassPrice;
        this.date = date;
        this.time = time;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    
    /*public static Ticket searchTicket(int ticketId) {
	    for (Ticket ticket : tickets) {
	        if (ticket.getTicketId() == ticketId) {
	            return ticket;
	        }
	    }
	    return null;
	}*/
    
    @Override
    public String toString() {
        return "Flight Number: '" + flightNumber + '\'' +
                ", From: '" + from + '\'' +
                ", To: '" + to + '\'' +
                ", Date: " + date +
                ", Time: " + time +
                ", Economy Seats Available: " + economySeatsAvailable +
                ", First Class Seats Available: " + firstClassSeatsAvailable +
                ", Economy Price: " + economyPrice +
                ", First Class Price: " + firstClassPrice;
    }

}

