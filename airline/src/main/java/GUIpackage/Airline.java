package GUIpackage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Airline {
	public static List<Flight> flights = new ArrayList<>();
	public static List<Passenger> passengers = new ArrayList<>();
	public static Passenger p;
	public static List<Admin> admins = new ArrayList<>();

	public Airline() {
		admins.add(new Admin("admin1", "admin1"));
		admins.add(new Admin("admin2", "admin2"));
		admins.add(new Admin("admin3", "admin3"));    
	}
	public boolean bookSeat(Flight flight, String seatType, Passenger passenger) {
		double final_price;
		String ticketid;
		for (Flight flighttocheck : flights) {
	        if (flighttocheck == flight) {
	        	if (flight.getEconomySeatsAvailable() > 0 || flight.getFirstClassSeatsAvailable() > 0) {
	                if (seatType.equalsIgnoreCase("Economy") && flight.getEconomySeatsAvailable() > 0) {
	                	ticketid = "e "+ Integer.toString(flight.getEconomySeatsAvailable());
	                    flight.setEconomySeatsAvailable(flight.getEconomySeatsAvailable() - 1);
	                    final_price = flight.getEconomyPrice();
	                } else if (seatType.equalsIgnoreCase("FirstClass") && flight.getFirstClassSeatsAvailable() > 0) {
	                	ticketid = "fc "+ Integer.toString(flight.getFirstClassSeatsAvailable());
	                	flight.setFirstClassSeatsAvailable(flight.getFirstClassSeatsAvailable() - 1);
	                    final_price = flight.getFirstClassPrice();
	                } else {
	                    System.out.println("No available seats in the selected class.");
	                    return false;
	                }
	                Ticket ticket = new Ticket(ticketid, seatType, final_price);
	                ticket.setFlight(flight);
	                ticket.setPassenger(passenger);
	                passenger.setTicket(ticket);
	                flight.addTicket(ticket);
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
	            } else if (ticket.getSeatType().equalsIgnoreCase("FirstClass")) {
	                flight.setFirstClassSeatsAvailable(flight.getFirstClassSeatsAvailable() + 1);
	            }
	            passenger.setTicket(null); // Remove ticket reference from passenger
	            flight.removeTicket(ticket.getTicketId());
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
	
	public void createFlight(int economySeatsAvailable, int firstClassSeatsAvailable, String from, String to, double distance, LocalTime time, LocalDate date, boolean highDemand, boolean competitors) {
		double economyprice = economyFare(distance, highDemand, competitors);
		double firstclassprice = firstclassFare(distance, highDemand, competitors);
		
		Flight flight = new Flight(flights, economySeatsAvailable, firstClassSeatsAvailable, from, to,  economyprice ,firstclassprice , time,  date, highDemand, competitors);
	}
	
	public double economyFare(double distance, boolean highDemand, boolean competitors) {
		
		double economyprice = distance * 0.2;
		
		if(highDemand) 
			economyprice = economyprice * 1.2;
		
		if(competitors)
			economyprice = economyprice * 0.8;
		
		return economyprice;
	}
	
	public double firstclassFare(double distance, boolean highDemand, boolean competitors) {
		
		double firstclassprice = distance * 0.4;
		
		if(highDemand) 
			firstclassprice = firstclassprice * 1.3;
		
		if(competitors)
			firstclassprice = firstclassprice * 0.75;
		
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

	public boolean signup(String username, String password, String name, String passportNumber, String email,String phoneNumber,String address,LocalDate dateOfBirth,String nationality, Boolean isAdult) {
		for(Passenger passenger : passengers) {
			if(passenger.getUsername().equals(username)) {
				System.out.println("Username already exists. Please try again.");
				return false;
			}
		}
		Passenger passenger = new Passenger(username, password, name, passportNumber, email, phoneNumber, address, dateOfBirth,nationality, isAdult);
		passengers.add(passenger);
		System.out.println("Signup successful.");
		return true;
	}

	public int login(String username, String password) {
		for (Passenger passenger : passengers) {
			if (passenger.getUsername().equals(username) && passenger.getPassword().equals(password)) {
				System.out.println("Passenger Login successful.");
				this.p = passenger;
				return 1;
			}
		}
		for (Admin admin : admins) {
			if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
				System.out.println("Admin Login successful.");
				return 0;
			}
		}
		System.out.println("Invalid username or password. Please try again.");
		return -1;
	}
}
