package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class loginChoiceController {

    @FXML
    private Button organizer;

    @FXML
    private Button reviewer;

    @FXML
    private Button author;

    @FXML
    void handleAuthor(ActionEvent event) throws IOException {
    	((Window) author.getScene().getWindow()).hide();
        Pane root = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root,  600, 400));
        stage.show();
    }

    @FXML
    void handleOrgnizer(ActionEvent event) {

    }

    @FXML
    void handleReviewer(ActionEvent event) {

    }

}
