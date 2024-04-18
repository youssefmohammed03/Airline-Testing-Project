package airline;

import java.util.LocalDate;

public class Passenger extends User{
    private String name;
    private String passportNumber;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDate dateOfBirth;
    private String nationality;
    private Boolean isAdult;
    private Ticket ticket;

    public Passenger( String username, String Password, String name, String passportNumber, String email, String phoneNumber, String address, LocalDate dateOfBirth, String nationality, Boolean isAdult) {
        super(username, Password);
        this.name = name;
        this.passportNumber = passportNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.isAdult = isAdult;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(Boolean adult) {
        isAdult = adult;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    public void setTicket(Ticket ticket) {
    	this.ticket = ticket;
    }
    
    public Ticket getTicket() {
    	return ticket;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth+ '\'' +
                " Above 18 =" + isAdult + "}";
    }
}
