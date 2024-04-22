package GUIpackage;

import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;



public class FlightsPageController {
    
    @FXML
    private TableView<Flight> flightTableView;
    @FXML
    private TableColumn<Flight, Void> classColumn;
    
    @FXML
    private Button confirmationButton;
    
    @FXML
    private Text deatilsLabel;

    @FXML
    private TableView<Flight> FilghtsTable;

    @FXML
    private Button BackBtn;
     @FXML
    private Button InfoBtn;


    @FXML
    void BackOneScene(ActionEvent event) throws IOException {
    
    Parent previousSceneParent = FXMLLoader.load(getClass().getResource("CustomerFlight.fxml"));
    Scene previousScene = new Scene(previousSceneParent);
    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
    window.setScene(previousScene);
}


   @FXML
    void GoToPassengerInformationForm(ActionEvent event) throws IOException {
        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PassengerInformationForm.fxml"));
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the root of the scene to the content of the new FXML file
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception accordingly
        }

    }


    @FXML
    public void initialize() {
        // Set up columns
        TableColumn<Flight, String> flightIdColumn = new TableColumn<>("Flight ID");
        flightIdColumn.setCellValueFactory(new PropertyValueFactory<>("flightId"));

        TableColumn<Flight, String> fromColumn = new TableColumn<>("From");
        fromColumn.setCellValueFactory(new PropertyValueFactory<>("from"));

        TableColumn<Flight, String> toColumn = new TableColumn<>("To");
        toColumn.setCellValueFactory(new PropertyValueFactory<>("to"));

        TableColumn<Flight, LocalDate> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Flight, LocalTime> timeColumn = new TableColumn<>("Time");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        
        flightTableView.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        flightTableView.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

       
        // Add flights to the TableView
        flightTableView.getItems().addAll(Airline.p.getDesiredFlights());
        // Set the cell factory for the classColumn
        classColumn.setCellFactory(param -> new TableCell<Flight, Void>() {
            private final RadioButton btn1 = new RadioButton("First Class");
            private final RadioButton btn2 = new RadioButton("Economy");
            private final ToggleGroup group = new ToggleGroup();

            {
                btn1.setToggleGroup(group);
                btn2.setToggleGroup(group);
                setGraphic(new HBox(10, btn1, btn2));
            }

            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(new HBox(10, btn1, btn2));
                }
            }
        });
         flightTableView.setOnMouseClicked(event -> {
        // Check if it's a double-click
        if (event.getClickCount() == 2) {
            // Get the selected flight
            Flight selectedFlight = flightTableView.getSelectionModel().getSelectedItem();

            // Display the flight details in the deatilsLabel
            if (selectedFlight != null) {
                String flightDetails = String.format("From: %s  To: %s  Time: %s  Date: %s",
                        selectedFlight.getFrom(),
                        selectedFlight.getTo(),
                        selectedFlight.getTime(),
                        selectedFlight.getDate());
                deatilsLabel.setText(flightDetails);
            }
        }
    });
}
}
