/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUIpackage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Saleh Ahmed
 */
public class TicketInfoController implements Initializable {
    
	Airline a = new Airline();
	
    Ticket ticket = Airline.p.getTicket();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         // Display ticket information
        if (ticket != null) {
            ticketIdLabel.setText(ticket.getTicketId());
            seatTypeLabel.setText(ticket.getSeatType());
            priceLabel.setText(Double.toString(ticket.getPrice()));
            
            // If flight information is available, display it
            Flight flight = ticket.getFlight();
            if (flight != null) {
                flightLabel.setText(flight.getFlightId());
            } else {
                flightLabel.setText("Flight information not available");
            }
        } else {
            ticketIdLabel.setText("Ticket information not available");
        }
       
        // TODO
    }    
    @FXML
    private Text FlightText;

    @FXML
    private Text Pricetext;

    @FXML
    private Text Ticketidtext;

    @FXML
    private Button cancelBtn;

    @FXML
    private Text flightLabel;

    @FXML
    private Text priceLabel;

    @FXML
    private Text seatTypeLabel;

    @FXML
    private Text seatTypeText;

    @FXML
    private Text ticketIdLabel;

    @FXML
    void CancelTicket(ActionEvent event) {
    // Implement cancel ticket functionality
    if (ticket != null) {
        // Remove the Ticket object
        a.cancelBooking(ticket, ticket.getFlight(), Airline.p);

        // Clear the text of the labels
        ticketIdLabel.setText("");
        seatTypeLabel.setText("");
        priceLabel.setText("");
        flightLabel.setText("");

        // Return to the previous page
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FlightsPage.fxml"));
            Parent root = loader.load();

            // Access the stage from the button's scene
            Stage stage = (Stage) cancelBtn.getScene().getWindow();

            // Set the new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
        // Display an alert if ticket is not available to cancel
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No Ticket to Cancel");
        alert.setHeaderText(null);
        alert.setContentText("There is no ticket available to cancel.");
        alert.showAndWait();
    }
}


}
