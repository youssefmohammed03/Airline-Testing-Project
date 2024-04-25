package gui;

import java.awt.Label;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import GUIpackage.Airline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Controller{
	
	private Airline a;
	
	
    public Controller() {
        this.a = new Airline();
    }
    
	@FXML
    private Button BackBtnToLogin;

    @FXML
    private TextField address;

    @FXML
    private DatePicker dateofbirth;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField name;

    @FXML
    private TextField nationality;

    @FXML
    private TextField passportnumber;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private Button signupButton;

    @FXML
    private TextField username;

    @FXML
    private TextField emailPlaceholder;
    
    @FXML
    private TextField passwordPlaceholder;
    
    @FXML
    private TextField Arrival;

    @FXML
    private Button BackBtn;

    @FXML
    private Button ConfirmBtn;

    @FXML
    private TextField Departure;

    @FXML
    private DatePicker flightdatepicker;

    @FXML
    private Button cancelBtn;

    @FXML
    private Text flightLabel;

    @FXML
    private Text priceLabel;

    @FXML
    private Text seatTypeLabel;

    @FXML
    private Text ticketIdLabel;

    @FXML
    void CancelTicket(ActionEvent event) {

    }


    
    @FXML
    void goBackToLogin(ActionEvent event) throws IOException{
    	try {
	           FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
	           Parent root = loader.load();

	           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

	           stage.getScene().setRoot(root);
	       } catch (IOException e) {
	           e.printStackTrace();
	       }
    }
    
    @FXML
    void signup(ActionEvent event) {
        List<String> errors = validateSignupData();

        if (errors.isEmpty()) {
            boolean isCreated = a.signup(username.getText(), passwordTextField.getText(), name.getText(),
                    passportnumber.getText(), emailTextField.getText(), phoneNumberTextField.getText(),
                    address.getText(), dateofbirth.getValue(), nationality.getText(), true);
            if (isCreated) {
                // Navigate to login page
                navigateToLoginPage(event);
            }
        } else {
            // Display validation errors in an alert
            displayAlert("Validation Error", "Please correct the following errors:", String.join("\n", errors));
        }
    }
    
    private void navigateToLoginPage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   @FXML
   public void handleLogin(ActionEvent event) throws IOException {
	   int adminOrPassenger = this.a.login(emailPlaceholder.getText(), passwordPlaceholder.getText());
	   if(adminOrPassenger == 0) {
		   try {
	           FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminPanel.fxml"));
	           Parent root = loader.load();

	           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

	           stage.getScene().setRoot(root);
	       } catch (IOException e) {
	           e.printStackTrace();
	       }
		   
	   } else if(adminOrPassenger == 1) {
		   try {
	           FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerFlight.fxml"));
	           Parent root = loader.load();

	           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

	           stage.getScene().setRoot(root);
	       } catch (IOException e) {
	           e.printStackTrace();
	       } 
	   }else {
		    Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Invalid");
			alert.setHeaderText("Invalid username or password. Please try again.");
			if (alert.showAndWait().get() == ButtonType.OK){
			}
	   }
	   
   }
   @FXML
   public void handleSignup(ActionEvent event) {
       try {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
           Parent root = loader.load();

           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

           stage.getScene().setRoot(root);
       } catch (IOException e) {
           e.printStackTrace();
       }

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

   @FXML
   void GoToFlightsPage(ActionEvent event) {
	   Airline.p.setDesiredFlights(this.a.searchFlight(Departure.getText(), Arrival.getText(), flightdatepicker.getValue()));
	   if(Airline.p.getDesiredFlights().isEmpty()) {
		   Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Failed");
           alert.setHeaderText(null);
           alert.setContentText("There is no flights available for this search.");

           alert.showAndWait();
	   } else {
		   try {
			   FXMLLoader loader = new FXMLLoader(getClass().getResource("FlightsPage.fxml"));
			   Parent root = loader.load();
			   
			   Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			   
			   stage.getScene().setRoot(root);
		   } catch (IOException e) {
			   e.printStackTrace();
		   } 
	   }
   }
   
   
   private List<String> validateSignupData() {
    List<String> errors = new ArrayList<>();

    // Validate username
    String usernameString = username.getText();
    if (usernameString.isEmpty()) {
        errors.add("Username field is empty.");
    }

    // Validate password
    String passwordString = passwordTextField.getText();
    if (passwordString.isEmpty() || passwordString.length() < 6) {
        errors.add("Password must be at least 6 characters long.");
    }

    // Validate name
    String nameString = name.getText();
    if (nameString.isEmpty()) {
        errors.add("Name field is empty.");
    }

    // Validate passport number
    String passportNumberString = passportnumber.getText();
    if (passportNumberString.isEmpty() || passportNumberString.length() != 8) {
        errors.add("Passport number must be 8 characters long.");
    }

    // Validate email
    String emailString = emailTextField.getText();
    if (emailString.isEmpty() || !emailString.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
        errors.add("Invalid email format.");
    }

    // Validate phone number
    String phoneNumberString = phoneNumberTextField.getText();
    if (phoneNumberString.isEmpty() || !phoneNumberString.matches("\\d+")) {
        errors.add("Phone number must contain only numbers.");
    }

    // Validate address
    String addressString = address.getText();
    if (addressString.isEmpty()) {
        errors.add("Address field is empty.");
    }

    // Validate date of birth
    LocalDate dob = dateofbirth.getValue();
    if (dob == null) {
        errors.add("Date of birth is not selected.");
    }

    // Validate nationality
    String nationalityString = nationality.getText();
    if (nationalityString.isEmpty()) {
        errors.add("Nationality field is empty.");
    }

    return errors;
}

   
   private void displayAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }


}
