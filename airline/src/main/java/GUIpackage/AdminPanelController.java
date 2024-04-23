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
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
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
    	String dateString = dateTextField.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        String timeString = timeTextField.getText();
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");

    	a.createFlight(Integer.parseInt(economySeatsAvailable.getText()), Integer.parseInt(firstClassSeats.getText()), fromTextField.getText(), toTextField.getText(), Integer.parseInt(distance.getText()), LocalTime.parse(timeString, formatterTime), LocalDate.parse(dateString, formatter), highDemand.isSelected(), competitors.isSelected());
    	initialize();
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
    
    
    
}
