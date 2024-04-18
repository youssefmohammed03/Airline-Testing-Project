package airline;

import java.util.List;

public class Ticket {
    private int ticketId;
    private Flight flight;
    private Passenger passenger;
    private String seatType;
    private double price;


    public Ticket(Flight flight, Passenger passenger, String seatType) {
        this.flight = flight;
        this.passenger = passenger;
        this.seatType = seatType;
    }

    public int getTicketId() {
        return ticketId;
    }

    public Flight getFlight() {
        return flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public String getSeatType() {
        return seatType;
    }

    public double getPrice() {
        return price;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
    
    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
    
    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public void setPrice(double price) {
        this.price = price;
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
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", flight=" + flight +
                ", passenger=" + passenger +
                ", seatType='" + seatType + '\'' +
                ", price=" + price +
                '}';
    }
}