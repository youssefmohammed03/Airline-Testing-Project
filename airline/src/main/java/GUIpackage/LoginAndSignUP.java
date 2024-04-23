/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package GUIpackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
/**
 *
 * @author Saleh Ahmed
 */
public class LoginAndSignUP extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Airline Reservation System");
        primaryStage.setScene(scene);
        primaryStage.show();
        
         
        primaryStage.getIcons().add(new Image("pngegg (1).png"));
       primaryStage.setOnCloseRequest(event -> {
				event.consume();
				logout(primaryStage);	
			});
    }
    
    public void logout(Stage primaryStage){	
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You're about to close program!");
		
		if (alert.showAndWait().get() == ButtonType.OK){
			System.out.println("You successfully logged out");
			primaryStage.close();
		} 
	}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
