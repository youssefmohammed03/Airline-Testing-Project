package GUIpackage;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PassengerInformationFormController {

    @FXML
    private Button BackBtn;

    @FXML
    private TextField addressTextField;

    @FXML
    private CheckBox adultCheckBox;

    @FXML
    private Button confirmPassengerInfo;

    @FXML
    private DatePicker dobDatePicker;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField nationalityTextField;

    @FXML
    private TextField passportNumberTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    void BackOneScene(MouseEvent event) throws IOException {
    
    Parent previousSceneParent = FXMLLoader.load(getClass().getResource("FlightsPage.fxml"));
    Scene previousScene = new Scene(previousSceneParent);
    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
    window.setScene(previousScene);
}

    @FXML
void CheckInfoAndDisplaySuccessOrError(ActionEvent event) {
    // Your existing code...

    // Display a success message
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(null);
    alert.setContentText("Ticket confirmed!");

    alert.showAndWait();
}


}
