package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.UserAccount;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ConferenceManagementSystem extends Application {

	 @Override 
	    public void start(Stage stage) throws Exception{
	        Pane root = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
	        stage.setScene(new Scene(root, 600, 400));
	        stage.setTitle("Login Dialog");
	        stage.show();
	    }
	 
	 public static void main(String[] args) throws Exception {
	       launch(args);
	    }



  
}
