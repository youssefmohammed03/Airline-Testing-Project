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
		// Instruction 1
		double final_price;

		// Instruction 2
		String ticketid;

		// Instruction 3
		for (Flight flighttocheck : flights) {

		    // Instruction 4
		    if (flighttocheck == flight) {

		        // Instruction 5
		        if ((seatType.equalsIgnoreCase("Economy") && flight.getEconomySeatsAvailable() > 0) ||
		            (seatType.equalsIgnoreCase("First Class") && flight.getFirstClassSeatsAvailable() > 0)) {

		            // Instruction 6
		            if (seatType.equalsIgnoreCase("Economy")) {
		                ticketid = "e " + flight.getEconomySeatsAvailable();
		                // Instruction 7
		                flight.setEconomySeatsAvailable(flight.getEconomySeatsAvailable() - 1);
		                // Instruction 8
		                final_price = flight.getEconomyPrice();
		            } else {
		                // Instruction 9
		                ticketid = "fc " + flight.getFirstClassSeatsAvailable();
		                // Instruction 10
		                flight.setFirstClassSeatsAvailable(flight.getFirstClassSeatsAvailable() - 1);
		                // Instruction 11
		                final_price = flight.getFirstClassPrice();
		            }

		            // Instruction 12
		            Ticket ticket = new Ticket(ticketid, seatType, final_price);
		            // Instruction 13
		            ticket.setFlight(flight);
		            // Instruction 14
		            ticket.setPassenger(passenger);
		            // Instruction 15
		            passenger.setTicket(ticket);
		            // Instruction 16
		            flight.addTicket(ticket);
		            // Instruction 17
		            return true;	
		        } else {
		            // Instruction 18
		            System.out.println("Sorry, no seats available for this flight.");
		            // Instruction 19
		            return false;
		        }
		    }
		}

		// Instruction 20
		return false;
	}
	


	
	public boolean cancelBooking(Ticket ticket, Flight flight, Passenger passenger) {
	    // Instruction 1
	    if (ticket != null) {
	        // Instruction 2
	        if (ticket.getSeatType().equalsIgnoreCase("Economy")) {
	            // Instruction 3
	            flight.setEconomySeatsAvailable(flight.getEconomySeatsAvailable() + 1);
	          //Instruction 4  
	        } else if (ticket.getSeatType().equalsIgnoreCase("First Class")) {
	            // Instruction 5
	            flight.setFirstClassSeatsAvailable(flight.getFirstClassSeatsAvailable() + 1);
	        }
	        // Instruction 6
	        passenger.setTicket(null); // Remove ticket reference from passenger
	        // Instruction 7
	        flight.removeTicket(ticket.getTicketId());
	        // Instruction 8
	        ticket = null; // Delete the ticket object
	        // Instruction 9
	        return true;
	    } else {
	        // Instruction 10
	        System.out.println("No booking found for this passenger.");
	        // Instruction 11
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
