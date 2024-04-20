/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUIpackage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.text.Text;



/**
 * FXML Controller class
 *
 * @author Saleh Ahmed
 */
public class FlightsPageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
      @FXML
    private Button BackBtn;

    @FXML
    private Button confirmationButton;

    @FXML
    private Text deatilsLabel;

    @FXML
    void BackOneScene(MouseEvent event) {

    }

    @FXML
    void GoToPassengerInformationForm(MouseEvent event) {

    }
    
}
