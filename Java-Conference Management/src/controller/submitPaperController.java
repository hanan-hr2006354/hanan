package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Authors;
import model.Conference;
import model.ConferenceContainer;
import model.Paper;
import model.UserAccount;
import model.UserAccountContainer;
import javafx.scene.control.MenuItem;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class submitPaperController {
	  private Conference conference;

	    public void setConference(Conference conference) {
	        this.conference = conference;
	    }
	    @FXML
	    private Button submitbutton;

	    @FXML
	    private Button cancelButton;

	    @FXML
	    private TextField titleTextField;

	    @FXML
	    private MenuButton selectAnAuthor;

	   
	    @FXML
	    private TableView<UserAccount> AuthorTable;
	    @FXML
	    private TextField keywordTextField;

	    @FXML
	    private TextField AbstractTextField;
	    @FXML
	    private TableColumn<UserAccount, String> authorCol;

		private List<UserAccount> paperAuthors = new ArrayList<UserAccount>();//same as authors. it should be available
		private Paper paper=new Paper(new ArrayList<UserAccount>());
	    @FXML
	    void cancelButton(ActionEvent event) throws IOException {
	    	((Window)cancelButton.getScene().getWindow()).hide();
	    }

	    @FXML
	    private Button deleteButton;


	    @FXML
	    void handleDelete(ActionEvent event) {
	    	 UserAccount selectedAuthor = AuthorTable.getSelectionModel().getSelectedItem();
	    	    if (selectedAuthor != null) {
	    	        paper.getAuthors().remove(selectedAuthor);
	    	        populateAuthorsTable(); // Update the TableView after removing the author
	    	    }
	    }

	    @FXML
	    void submitButton(ActionEvent event) throws IOException {
	    	
	        // Check if conference is set
	        if (this.conference == null) {
	            displayMessage("Conference is not set.", AlertType.ERROR);
	            return;
	        }

	        // Check if at least one author is selected
	        if (paper.getAuthors().isEmpty()) {
	            displayMessage("Please select at least one author.", AlertType.ERROR);
	            return;
	        }

	        // Check if title, keywords, and abstract are not empty
	        if (titleTextField.getText().isEmpty() || keywordTextField.getText().isEmpty() || AbstractTextField.getText().isEmpty()) {
	            displayMessage("Please fill all blanks.", AlertType.ERROR);
	            return;
	        }
	        
	        // Set paper information
	        paper.setPaperTitle(titleTextField.getText());
	        paper.setKeywords(keywordTextField.getText());
	        paper.setAbstactPaper(AbstractTextField.getText());
	        if (paper != null && conference != null) {
	            conference.getAllpapers().add(paper);
	            conference.updateConference(titleTextField.getText(), keywordTextField.getText(), AbstractTextField.getText(), paper.getAuthors());
	        }
	        // Close the current stage
	        Stage stage = (Stage) submitbutton.getScene().getWindow();
	        stage.close();

	        // Add the paper to the conference
	        if (paper != null) {
	            conference.getAllpapers().add(paper);
	        }

	        // Open a new stage with OneConferenceViewController.fxml
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/OneConferenceView.fxml"));
	        Parent root = loader.load();
	        // Assuming OneConferenceViewController is the controller for OneConferenceView.fxml
	        OneConferenceViewController oneConferenceController = loader.getController();
	        oneConferenceController.setConference(conference);
	        Stage stage1 = new Stage();
	        stage1.setScene(new Scene(root, 900, 500));
	        stage1.show();
	    }


	    // ... other methods ...
	    @FXML
	    private void initialize() {
	    	this.setConference(conference);
	        populateAuthorsMenu();
	       
	       	    }


	    private void populateAuthorsMenu() {
	        for (UserAccount userAccount : UserAccountContainer.userAccounts) {
	            MenuItem menuItem = new MenuItem(userAccount.getUsername());
	            menuItem.setOnAction(event -> handleAuthorSelection(userAccount));
	            selectAnAuthor.getItems().add(menuItem);
	        }
	    }
	    private List<UserAccount> selectedUsernames = new ArrayList<>();

	    private void handleAuthorSelection(UserAccount selectedUser) {
	        System.out.println("Selected Author: " + selectedUser);
	        Boolean choosen=checkAuthorSelection(selectedUser);
	        if(choosen==false) {
	        paper.getAuthors().add(selectedUser);}
	        
	        populateAuthorsTable();
	        // Add your logic to handle the selected author
	    }
	    
	    public boolean checkAuthorSelection(UserAccount selectedUser) {
	    	for(UserAccount ua:paper.getAuthors()) {
	    		if(selectedUser.equals(ua))
	    			return true;
	    	}return false;
	    }
	    @FXML
	    private void populateAuthorsTable() {
	    	 if (AuthorTable != null && paper != null) {
		            authorCol.setCellValueFactory(new PropertyValueFactory<>("username"));
		            ObservableList<UserAccount> authorList = FXCollections.observableArrayList(paper.getAuthors());
		            AuthorTable.setItems(authorList);
		        }
	    }	    
		

		public void displayMessage(String message, AlertType type) {
			Alert alert = new Alert(type);
			alert.setTitle("System Message");
			alert.setHeaderText(null);
			alert.setContentText(message);
			alert.showAndWait();

		}
}
