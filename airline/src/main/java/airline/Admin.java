package airline;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Admin extends User{
	
	
	public Admin() {
	super("admin", "admin");
	}
	
	public void createFlight(List <Flight> flights,int economySeatsAvailable, int firstClassSeatsAvailable, String from, String to, double distance, LocalTime time, LocalDate date, boolean highDemand, boolean competitors) {
		
		double economyprice = economyFare(distance, highDemand, competitors);
		double firstclassprice = firstclassFare(distance, highDemand, competitors);
		
		Flight flight = new Flight( economySeatsAvailable, firstClassSeatsAvailable, from, to,  economyprice ,firstclassprice , time,  date, highDemand, competitors);
		
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
	
	private void cancelFlight(List <Flight> flights, String ID) {
		
		int index = Flight.searchIndexFlightById(flights, ID);
		flights.remove(index);
	}
	
	private void delayFlight(List <Flight> flights, String ID, LocalDate date, LocalDate time) {
		
		int index = Flight.searchIndexFlightById(flights ,ID);
		flights.get(index).setDate(date);
		flights.get(index).setTime(time);
	}
}

