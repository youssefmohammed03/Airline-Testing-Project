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
    private String flightId;
    private boolean highDemand;
    private boolean competitors;
    private List<Ticket> tickets = new ArrayList<>();
    
    private static String generateFlightNumber(List<Flight> flights, Flight flight) {
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

    public Flight(List<Flight> flights, int economySeatsAvailable, int firstClassSeatsAvailable, String from, String to, double economyPrice, double firstClassPrice, LocalTime time, LocalDate date, boolean highDemand, boolean competitors) {
        this.economySeatsAvailable = economySeatsAvailable;
        this.firstClassSeatsAvailable = firstClassSeatsAvailable;
        this.from = from;
        this.to = to;
        this.economyPrice = economyPrice;
        this.firstClassPrice = firstClassPrice;
        this.date = date;
        this.time = time;
        this.flightId = generateFlightNumber(flights, this);
        this.competitors = competitors;
        this.highDemand = highDemand;
    }
    
    public Ticket searchTicket(int ticketId) {
	    for (Ticket ticket : tickets) {
	        if (ticket.getTicketId() == ticketId) {
	            return ticket;
	        }
	    }
	    return null;
	}
    
    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public boolean removeTicket(int ticketId) {
        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i).getTicketId() == ticketId) {
                tickets.remove(i);
                return true;
            }
        }
        return false;
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

    public String getFlightId() {
        return flightId;
    }
    
    public void setHighDemand(boolean highDemand) {
    	this.highDemand = highDemand;
    }
    
    public boolean getHighDemand() {
    	return highDemand;
    }
    
    public void setCompetitors(boolean competitors) {
    	this.competitors = competitors;
    }
    
    public boolean getCompetitors() {
    	return competitors;
    }
    
    public void setTickets(List <Ticket> tickets) {
    	this.tickets = tickets;
    }
    
    public List <Ticket> getTickets() {
    	return tickets;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Flight Number: '").append(flightId).append('\'')
          .append("\nFrom: '").append(from).append('\'')
          .append("\nTo: '").append(to).append('\'')
          .append("\nDate: ").append(date)
          .append("\nTime: ").append(time)
          .append("\nEconomy Seats Available: ").append(economySeatsAvailable)
          .append("\nFirst Class Seats Available: ").append(firstClassSeatsAvailable)
          .append("\nEconomy Price: $").append(economyPrice)
          .append("\nFirst Class Price: $").append(firstClassPrice)
          .append("\nHigh Demand: ").append(highDemand)
          .append("\nCompetitors: ").append(competitors)
          .append("\nTickets:\n");

        for (Ticket ticket : tickets) {
            sb.append(ticket.toString()).append("\n");
        }

        return sb.toString();
    }


    
}

