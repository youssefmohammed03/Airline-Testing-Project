/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Saleh Ahmed
 */
public class CustomerFlightController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private Button ConfirmBtn;

      @FXML
    private Button BackBtn;

    @FXML
    private DatePicker Mydatepicker;

    @FXML
    void BackOneScene(MouseEvent event) throws IOException {
    
    Parent previousSceneParent = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
    Scene previousScene = new Scene(previousSceneParent);
    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
    window.setScene(previousScene);
}

    @FXML
    void getDate(ActionEvent event) {

    }
    @FXML
    void GoToFlightsPage(MouseEvent event) {
        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FlightsPage.fxml"));
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
}
