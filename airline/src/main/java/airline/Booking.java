package airline;

public class Booking {
    private Flight flight;
    private Passenger passenger;
    private Ticket ticket;

    public Booking(Flight flight, Passenger passenger) {
        this.flight = flight;
        this.passenger = passenger;
    }

    public boolean bookSeat(String seatType) {
        if (flight.getEconomySeatsAvailable() > 0 || flight.getFirstClassSeatsAvailable() > 0) {
            if (seatType.equalsIgnoreCase("Economy") && flight.getEconomySeatsAvailable() > 0) {
                flight.setEconomySeatsAvailable(flight.getEconomySeatsAvailable() - 1);
            } else if (seatType.equalsIgnoreCase("First Class") && flight.getFirstClassSeatsAvailable() > 0) {
                flight.setFirstClassSeatsAvailable(flight.getFirstClassSeatsAvailable() - 1);
            } else {
                System.out.println("No available seats in the selected class.");
                return false;
            }
            ticket = new Ticket(flight, passenger, seatType);
            passenger.setTicket(ticket);
            return true;
        } else {
            System.out.println("Sorry, no seats available for this flight.");
            return false;
        }
    }

    public boolean cancelBooking() {
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

}
