package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;  // Correct import for MenuItem
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MenuController {

    @FXML
    private Button HomeButton;

    @FXML
    private Button ConferenceButton;

    @FXML
    private Button HelpButton;

    @FXML
    private MenuButton profileButton;

    @FXML
    private MenuItem logout;

    @FXML
    private MenuItem profilechoice;

    @FXML
    private MenuItem logoutchoice;
    @FXML
    void logoutOption(ActionEvent event) {
        // Handle logout action
    }

    @FXML
    void handleConference(ActionEvent event) throws IOException {
        ((Window) ConferenceButton.getScene().getWindow()).hide();
        Pane root = FXMLLoader.load(getClass().getResource("/view/Conference.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 800, 550));
        stage.show();
    }

    @FXML
    void handleHelp(ActionEvent event) throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("/view/help.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 300, 300));
        stage.show();
    }

    @FXML
    void handleProfile(ActionEvent event) throws IOException {
        ((Window) profileButton.getScene().getWindow()).hide();
        Pane root = FXMLLoader.load(getClass().getResource("/view/profile.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 700, 550));
        stage.show();
    }

    @FXML
    private void initialize() {
        // You can perform initialization here
        System.out.println("MenuController initialized.");
    }
    
    @FXML
     void handlelogout(ActionEvent event) throws IOException {
    	 ((Window) profileButton.getScene().getWindow()).hide();
         Pane root = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
         Stage stage = new Stage();
         stage.setScene(new Scene(root,  600, 400));
         stage.show();
    }


    @FXML
    void handleHome(MouseEvent event) {
        System.out.println("handleHome method called.");

        try {
            // Load the main menu FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Menu.fxml"));
            Parent root = loader.load();

            System.out.println("FXML file loaded successfully.");

            // Create a new scene with the loaded FXML file
            Scene scene = new Scene(root);

            // Get the current stage from the ActionEvent
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(scene);
            stage.show();

            System.out.println("Scene set successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed (show an error message, log, etc.)
            System.err.println("Error loading FXML file: " + e.getMessage());
        }
    }
    @FXML
    private void handleLogout(ActionEvent event) {
        // implementation
    }
}
