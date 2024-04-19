package airline;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Airline {
	public List<Flight> flights = new ArrayList<>();
	
	public Airline() {
		
	}
	
	
	public boolean bookSeat(Flight flight, String seatType, Passenger passenger) {
		for (Flight flighttocheck : flights) {
	        if (flighttocheck == flight) {
	        	if (flight.getEconomySeatsAvailable() > 0 || flight.getFirstClassSeatsAvailable() > 0) {
	                if (seatType.equalsIgnoreCase("Economy") && flight.getEconomySeatsAvailable() > 0) {
	                    flight.setEconomySeatsAvailable(flight.getEconomySeatsAvailable() - 1);
	                } else if (seatType.equalsIgnoreCase("First Class") && flight.getFirstClassSeatsAvailable() > 0) {
	                    flight.setFirstClassSeatsAvailable(flight.getFirstClassSeatsAvailable() - 1);
	                } else {
	                    System.out.println("No available seats in the selected class.");
	                    return false;
	                }
	                Ticket ticket = new Ticket(flight, passenger, seatType);
	                passenger.setTicket(ticket);
	                return true;
	            } else {
	                System.out.println("Sorry, no seats available for this flight.");
	                return false;
	            }
	        }
	    }
		return false;
    }
	
	 public boolean cancelBooking(Ticket ticket, Flight flight, Passenger passenger) {
	        if (ticket != null) {
	            if (ticket.getSeatType().equalsIgnoreCase("Economy")) {
	                flight.setEconomySeatsAvailable(flight.getEconomySeatsAvailable() + 1);
	            } else if (ticket.getSeatType().equalsIgnoreCase("First Class")) {
	                flight.setFirstClassSeatsAvailable(flight.getFirstClassSeatsAvailable() + 1);
	            }
	            passenger.setTicket(null); // Remove ticket reference from passenger
	            ticket = null; // Delete the ticket object
	            return true;
	        } else {
	            System.out.println("No booking found for this passenger.");
	            return false;
	        }
	    }
	
	
	public List<Flight> searchFlight(String from, String to, LocalDate date) {
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
	
	public Flight searchFlightById(String flightId) {
        for (Flight flight : flights) {
            if (flight.getFlightId().equals(flightId)) {
                return flight;
            }
        }
        return null;
    }
	
	public int searchIndexFlightById(String Id) {
	    for (int i = 0; i < flights.size(); i++) {
	        if (flights.get(i).getFlightId().equals(Id)) {
	            return i;
	        }
	    }
	    return -1;
	}
	
	public void createFlight(List <Flight> flights,int economySeatsAvailable, int firstClassSeatsAvailable, String from, String to, double distance, LocalTime time, LocalDate date, boolean highDemand, boolean competitors) {
		double economyprice = economyFare(distance, highDemand, competitors);
		double firstclassprice = firstclassFare(distance, highDemand, competitors);
		
		Flight flight = new Flight(flights, economySeatsAvailable, firstClassSeatsAvailable, from, to,  economyprice ,firstclassprice , time,  date, highDemand, competitors);
	}
	
	private double economyFare(double distance, boolean highDemand, boolean competitors) {
		
		double economyprice = distance * 0.2;
		
		if(highDemand) 
			economyprice = economyprice * 1.2;
		
		if(competitors)
			economyprice = economyprice * 0.8;
		
		return economyprice;
	}
	
	private double firstclassFare(double distance, boolean highDemand, boolean competitors) {
		
		double firstclassprice = distance * 0.4;
		
		if(highDemand) 
			firstclassprice = firstclassprice * 1.2;
		
		if(competitors)
			firstclassprice = firstclassprice * 0.8;
		
		return firstclassprice;
	}
	
	public boolean cancelFlight(String ID) {
		int index = searchIndexFlightById(ID);
		if (index == -1) return false;
		flights.remove(index);
		return true;
	}
	
	public boolean delayFlight(String ID, LocalDate date, LocalTime time) {
		int index = searchIndexFlightById(ID);
		if (index == -1) return false;
		flights.get(index).setDate(date);
		flights.get(index).setTime(time);
		return true;
	}
	
}
