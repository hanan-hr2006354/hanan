package controller;

import java.io.IOException;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.UserAccount;
import model.UserAccountContainer;

public class LoginController {//take from text fields and do stuff
	@FXML
	private PasswordField password;
	@FXML
	public TextField username;
	@FXML
	private Button loginButton;
    @FXML
    private Button signUpButton;
	@FXML
	void login(ActionEvent event) throws Exception {//shouldnt show sign up option at all
		UserAccount user = UserAccountContainer.login(username.getText(), UserAccount.encrypt(password.getText(),"Hello"));//can get if it logged in or not &returns
		if (user != null && user.isLogged()) {
			for(UserAccount ua: UserAccountContainer.userAccounts) {
				 System.out.println(ua.toString());
			 }
			UserAccountContainer.currentLogin=user;
			displayMessage("Welcome " + user.getUsername()   + ". You have successfully logged in.", AlertType.CONFIRMATION);
			username.clear();
			password.clear();
			((Window) loginButton.getScene().getWindow()).hide();
			 FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Menu.fxml"));
		        Parent root = loader.load();
		        Stage stage = new Stage();
		        stage.setScene(new Scene(root, 800, 400));
		        stage.show();
		} else {
			displayMessage("Unsuccessfull login. Try again.", AlertType.ERROR);
			((Window) loginButton.getScene().getWindow()).hide();
			Pane root = FXMLLoader.load(getClass().getResource("/view/LoginSignConf.fxml"));
			Stage stage = new Stage();stage.setScene(new Scene(root,600,400));
			stage.show();
		}
	}

    @FXML
    void signupConf(ActionEvent event) throws IOException {
    	((Window) loginButton.getScene().getWindow()).hide();
		Pane root = FXMLLoader.load(getClass().getResource("/view/signUp.fxml"));
		Stage stage = new Stage();stage.setScene(new Scene(root,600,400));
		stage.show();
    }
  

	

	public void displayMessage(String message, AlertType type) {
		Alert alert = new Alert(type);
		alert.setTitle("System Message");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
