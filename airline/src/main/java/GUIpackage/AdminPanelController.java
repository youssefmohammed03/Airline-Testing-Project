/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUIpackage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TableCell;
import javafx.scene.control.ToggleGroup;


/**
 * FXML Controller class
 *
 * @author Saleh Ahmed
 */
public class AdminPanelController { 
    
    public Airline a = new Airline();
    
    Flight selectedFlight;
    
    @FXML
    private Button BackBtn;
    
    @FXML
    private Button DeleteBtn;

    @FXML
    private Button EditBtn;

    @FXML
    private TableView<Flight> TableView;

    @FXML
    private Button addButton;

    @FXML
    private CheckBox competitors;

    @FXML
    private TableColumn<Flight, String> dateColumn;

    @FXML
    private TextField dateTextField;

    @FXML
    private TextField distance;

    @FXML
    private TextField economySeatsAvailable;

    @FXML
    private TableColumn<Flight, String> economypriceColumn;

    @FXML
    private TextField firstClassSeats;

    @FXML
    private TableColumn<Flight, String> firstclasspriceColumn;

    @FXML
    private TableColumn<Flight, String> flightidColumn;

    @FXML
    private TableColumn<Flight, String> fromColumn;

    @FXML
    private TextField fromTextField;

    @FXML
    private CheckBox highDemand;

    @FXML
    private TextField timeTextField;
        
    @FXML
    private TableColumn<Flight, String> timecolumn;

    @FXML
    private TableColumn<Flight, String> toColumn;

    @FXML
    private TextField toTextField;
    
    @FXML
    private TextField editdate;

    @FXML
    private TextField edittime;

    @FXML
    void DeleteFlight(ActionEvent event) {
    	if(selectedFlight == null) {
	   		Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Failed");
            alert.setHeaderText(null);
            alert.setContentText("No selected flight.");

            alert.showAndWait();
            return;
	   	}
    	a.cancelFlight(this.selectedFlight.getFlightId());
    	initialize();
    }

    @FXML
    void EditFlghtInfo(ActionEvent event) {
    	String dateString = editdate.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        String timeString = edittime.getText();
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
        
        if(selectedFlight == null) {
	   		Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Failed");
            alert.setHeaderText(null);
            alert.setContentText("No selected flight.");

            alert.showAndWait();
            return;
	   	}
        a.delayFlight(this.selectedFlight.getFlightId(), LocalDate.parse(dateString, formatter), LocalTime.parse(timeString, formatterTime));
        initialize();

    }

    @FXML
void addFlightInfo(ActionEvent event) {
    List<String> errors = validateData();

    if (errors.isEmpty()) {
        String dateString = dateTextField.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        String timeString = timeTextField.getText();
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");

        // Create the flight only if data is valid
        a.createFlight(Integer.parseInt(economySeatsAvailable.getText()), Integer.parseInt(firstClassSeats.getText()), fromTextField.getText(), toTextField.getText(), Integer.parseInt(distance.getText()), LocalTime.parse(timeString, formatterTime), LocalDate.parse(dateString, formatter), highDemand.isSelected(), competitors.isSelected());
        initialize(); // Refresh table or UI after adding the flight
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText("Please correct the following errors:");
        alert.setContentText(String.join("\n", errors));

        alert.showAndWait();
    }
}


    
    public void initialize() {
    	
    	TableColumn<Flight, String> flightidColumn = new TableColumn<>("Flight ID");
    	flightidColumn.setCellValueFactory(new PropertyValueFactory<>("flightId"));

        TableColumn<Flight, String> fromColumn = new TableColumn<>("From");
        fromColumn.setCellValueFactory(new PropertyValueFactory<>("from"));

        TableColumn<Flight, String> toColumn = new TableColumn<>("To");
        toColumn.setCellValueFactory(new PropertyValueFactory<>("to"));

        TableColumn<Flight, LocalDate> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Flight, LocalTime> timecolumn = new TableColumn<>("Time");
        timecolumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        
        TableColumn<Flight, LocalDate> firstclasspriceColumn = new TableColumn<>("firstClassPrice");
        firstclasspriceColumn.setCellValueFactory(new PropertyValueFactory<>("firstClassPrice"));

        TableColumn<Flight, LocalTime> economypriceColumn = new TableColumn<>("economyPrice");
        economypriceColumn.setCellValueFactory(new PropertyValueFactory<>("economyPrice"));
        
        TableView.getItems().clear();
        TableView.getItems().addAll(Airline.flights);
        
        TableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                this.selectedFlight = TableView.getSelectionModel().getSelectedItem();
            }
        });
    }

    @FXML
    void BackOneScene(ActionEvent event) {
 	   try {
 	           FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
 	           Parent root = loader.load();

 	           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

 	           stage.getScene().setRoot(root);
 	       } catch (IOException e) {
 	           e.printStackTrace();
 	       }
    }
    
   public List<String> validateData() {
    List<String> errors = new ArrayList<>();

    // Validate date
    String dateString = dateTextField.getText();
    if (dateString.isEmpty()) {
        errors.add("Date field is empty.");
    } else {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            errors.add("Invalid date format. Date should be in the format dd/MM/yyyy.");
        }
    }

    // Validate time
    String timeString = timeTextField.getText();
    if (timeString.isEmpty()) {
        errors.add("Time field is empty.");
    } else {
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
        try {
            LocalTime.parse(timeString, formatterTime);
        } catch (DateTimeParseException e) {
            errors.add("Invalid time format. Time should be in the format HH:mm.");
        }
    }

    // Validate from and to fields
    String fromString = fromTextField.getText();
    String toString = toTextField.getText();
    if (fromString.isEmpty()) {
        errors.add("From field is empty.");
    }
    if (toString.isEmpty()) {
        errors.add("To field is empty.");
    }
    if (fromString.equals(toString)) {
        errors.add("From and To fields cannot be the same.");
    }

    // Validate distance
    String distanceString = distance.getText();
    if (distanceString.isEmpty()) {
        errors.add("Distance field is empty.");
    } else {
        try {
            Float.parseFloat(distanceString);
        } catch (NumberFormatException e) {
            errors.add("Invalid distance. Distance should be a valid number.");
        }
    }

    // Validate economy seats
    String economySeatsString = economySeatsAvailable.getText();
    if (economySeatsString.isEmpty()) {
        errors.add("Economy Seats field is empty.");
    } else {
        try {
            Integer.parseInt(economySeatsString);
        } catch (NumberFormatException e) {
            errors.add("Invalid economy seats. Economy seats should be a valid integer.");
        }
    }

    // Validate first class seats
    String firstClassSeatsString = firstClassSeats.getText();
    if (firstClassSeatsString.isEmpty()) {
        errors.add("First Class Seats field is empty.");
    } else {
        try {
            Integer.parseInt(firstClassSeatsString);
        } catch (NumberFormatException e) {
            errors.add("Invalid first class seats. First class seats should be a valid integer.");
        }
    }
    return errors;
}


}
    
       
    
    
    
    

