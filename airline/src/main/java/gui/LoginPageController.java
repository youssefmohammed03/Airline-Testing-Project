/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Saleh Ahmed
 */
public class LoginPageController implements Initializable {
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private TextField emailPlaceholder;

    @FXML
    private TextField passwordPlaceholder;


    @FXML
    public void handleLogin(ActionEvent event) throws IOException {
        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerFlight.fxml"));
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
    public void handleSignup(ActionEvent event) {
        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
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


    

