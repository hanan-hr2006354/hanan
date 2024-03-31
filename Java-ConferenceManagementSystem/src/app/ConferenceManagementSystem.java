package app;

import javafx.application.Application;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.UserAccount;
import model.ConferenceContainer;
import model.PaperContainer;
import model.Conference;
import model.UserAccountContainer;
import app.filesMethods;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ConferenceManagementSystem extends Application {
	 @Override 
	    public void start(Stage stage) throws Exception{
		 	List<UserAccount> userAccounts = filesMethods.readUserAccounts();
	        // Set user accounts in UserAccountContainer
	        if (userAccounts != null) {
	            UserAccountContainer.userAccounts.addAll(userAccounts);}
	        
	    	List<Conference> conference = filesMethods.readConference();
	        // Set user accounts in UserAccountContainer
	        if (conference != null) {
	        	ConferenceContainer.userConference.addAll(conference);
	        }

	        Pane root = FXMLLoader.load(getClass().getResource("/view/loginChoice.fxml"));
	        stage.setScene(new Scene(root, 600, 400));
	        stage.setTitle("Login Page");
	        stage.show();
	    }
	 
	 public static void main(String[] args) {
		    launch(args);
		




  
}}