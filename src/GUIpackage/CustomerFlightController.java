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
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


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
    void BackOneScene(MouseEvent event) {

    }

    @FXML
    void getDate(ActionEvent event) {

    }
    @FXML
    void GoToFlightsPage(MouseEvent event) {

    }
}
