/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUIpackage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author Saleh Ahmed
 */
public class AdminPanelController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
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
    void addFlightInfo(ActionEvent event) {

    }
}