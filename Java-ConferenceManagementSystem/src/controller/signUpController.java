package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.filesMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.UserAccount;
import model.UserAccountContainer;
import app.ConferenceManagementSystem;
public class signUpController {

    @FXML
    private Button createButton;

    @FXML
    private TextField signupUsername;

    @FXML
    private PasswordField signUpPass1;

    @FXML
    private PasswordField signUpPass2;

    @FXML
    private TextField email;

    @FXML
    void createAccount(ActionEvent event) throws Exception {
    	if (signupUsername.getText() != null &&
    		    signUpPass1.getText() != null && !signUpPass1.getText().isEmpty() &&
    		    signUpPass2.getText() != null && !signUpPass2.getText().isEmpty() &&
    		    signUpPass1.getText().equals(signUpPass2.getText())) {
    		if(signUpPass1.getText().length()!=6 && (signUpPass1.getText().length()<6)) {
                displayMessage("Password should consist of 6 digits or letters", AlertType.ERROR);}
    		else {
	    		if(this.isValidEmail(email.getText())) {
	    		    // Rest of the code
	    	    	System.out.println("succecefully entered to sig up");
	    	    	UserAccount ua=new UserAccount(signupUsername.getText(),signUpPass1.getText(),email.getText());
	                UserAccountContainer.userAccounts.add(ua);
	                filesMethods.writeUserAccounts(UserAccountContainer.userAccounts);
	    	    	displayMessage("Your account has been created. Please go login", AlertType.CONFIRMATION);

	    	    	((Window) createButton.getScene().getWindow()).hide();
	    	    	Pane root = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
	    			Stage stage = new Stage();stage.setScene(new Scene(root,600,400));
	    			stage.show();
	    		}else {
	                displayMessage("email sytax should be test@example.com", AlertType.ERROR);
	
	    	}
    		}}else {
            displayMessage("Please fill al the blanks & both passwords should be identical", AlertType.ERROR);

    	}
    	
    }
    
    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    public static boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

	public void displayMessage(String message, AlertType type) {
		Alert alert = new Alert(type);
		alert.setTitle("System Message");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();

	}
}
