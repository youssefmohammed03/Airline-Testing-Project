package GUIpackage;

import java.awt.Label;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
    private Button addButton;

    @FXML
    private TextField dateTextField;

    @FXML
    private TextField economyPriceTextField;

    @FXML
    private TextField firstClassPriceTextField;

    @FXML
    private TextField flightIdTextField;

    @FXML
    private TextField fromTextField;

    @FXML
    private TextField highDemandTextField;

    @FXML
    private TextField timeTextField;

    @FXML
    private TextField toTextField;
    
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
    void signup(ActionEvent event) throws IOException{
    	boolean isCreated = this.a.signup(username.getText(), passwordTextField.getText(), name.getText(), passportnumber.getText(), emailTextField.getText(), phoneNumberTextField.getText(), address.getText(), dateofbirth.getValue(), nationality.getText(), true);
    	if(isCreated) {
    		try {
 	           FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
 	           Parent root = loader.load();

 	           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

 	           stage.getScene().setRoot(root);
 	       } catch (IOException e) {
 	           e.printStackTrace();
 	       }
    	} else {
    		Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("User Already Exits");
			alert.setHeaderText("This User Already Exits.");
			if (alert.showAndWait().get() == ButtonType.OK){
			}
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
   void addFlightInfo(ActionEvent event) {

   }
   
   @FXML
   void EditFlghtInfo(ActionEvent event) {

   }
   
   @FXML
   void DeleteFlight(ActionEvent event) {

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
	   System.out.println("Enter the function");
	   System.out.println("All:");
	   for(Flight f:this.a.flights) {
		   System.out.println(f.toString());
	   }
	   this.a.p.setDesiredFlightst(this.a.searchFlight(Departure.getText(), Arrival.getText(), flightdatepicker.getValue()));
	   try {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("FlightsPage.fxml"));
           Parent root = loader.load();

           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

           stage.getScene().setRoot(root);
       } catch (IOException e) {
           e.printStackTrace();
       }
	   System.out.println("Desired:");
	   for(Flight f:this.a.p.getDesiredFlights()) {
		   System.out.println(f.toString());
	   }
	   System.out.println("Exit the function");
   }


}
