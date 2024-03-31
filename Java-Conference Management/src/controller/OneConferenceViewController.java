package controller;

import java.io.IOException;

import java.time.LocalDate;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.*;

public class OneConferenceViewController {

    @FXML
    private TextField cNoTextField;

    @FXML
    private TextField cNameTextField;

    @FXML
    private TextField startDateTextField;

    @FXML
    private TextField endDateTextField;



    private Conference conference;

    public void setConference(Conference conference) {
        this.conference = conference;
   	    populateFields();
   	 initializeReviewerTableView(); 
 	initializePaperTableView();

    }
    
    @FXML
    void initialize() {
    	initializeReviewerTableView(); 
    	initializePaperTableView();
    	// Add a listener to the PaperTable selection
        PaperTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                displayPaperDetails(newValue);
            }
        });
    }

    private void populateFields() {
        if (cNoTextField != null && cNameTextField != null && startDateTextField != null &&
            endDateTextField != null ) {

            cNoTextField.setText(String.valueOf(conference.getCNo()));
            cNoTextField.setEditable(false);
            
            cNameTextField.setText(conference.getCName());
            cNameTextField.setEditable(false);

            startDateTextField.setText(conference.getSDate().toString());
            startDateTextField.setEditable(false);

            endDateTextField.setText(conference.getEDate().toString());
            endDateTextField.setEditable(false);

  
            // Assuming 'reviewers' is a List<Reviewer> in the Conference class
        } else {
            System.err.println("One or more TextField elements are not properly initialized.");
        }
    }
    

    @FXML
    private TableView<Reviewer> reviewerTable;

    @FXML
    private TableColumn<Reviewer, String> reviewerName;

    @FXML
    private TableColumn<Reviewer, String> reviewerExpertise;

    @FXML
    private TableColumn<Reviewer, String> reviewerVenue;

    @FXML
    private TableColumn<Reviewer, String> reviewerAddress;
    
    @FXML
    private void initializeReviewerTableView() {
        try {
            // Retrieve conference data
            if (conference != null) {
                System.out.println("Conference data is available.");

                reviewerName.setCellValueFactory(new PropertyValueFactory<>("name"));
                reviewerExpertise.setCellValueFactory(new PropertyValueFactory<>("expertise"));
                reviewerVenue.setCellValueFactory(new PropertyValueFactory<>("venue"));
                reviewerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

                List<Reviewer> allReviewers = conference.getReviewers().getUserReviewers();

                // Debug statement
                System.out.println("Number of Reviewers: " + allReviewers.size());

                ObservableList<Reviewer> reviewerList = FXCollections.observableArrayList(allReviewers);
                // Set the items for the TableView
                reviewerTable.setItems(reviewerList);

            } else {
                System.err.println("Conference object is null.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // You might want to log the exception or show an error message
        }
    }

    @FXML
    private void initializePaperTableView() {
        try {
            // Debug statements
            System.out.println("Initializing Paper TableView...");

            if (conference != null) {
                System.out.println("Conference data is available.");

                PaperNoCol.setCellValueFactory(new PropertyValueFactory<>("PNo"));
                PaperNameCol.setCellValueFactory(new PropertyValueFactory<>("paperTitle"));

                List<Paper> papers = conference.getAllpapers().getPapers();

                // Debug statement
                System.out.println("Number of Papers: " + papers.size());

                ObservableList<Paper> paperList = FXCollections.observableArrayList(papers);

                // Set the items for the TableView
                PaperTable.setItems(paperList);
            } else {
                System.err.println("Conference object is null.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // You might want to log the exception or show an error message
        }
    }


    @FXML
    private TableView<Paper> PaperTable;
    
    @FXML
    private TableColumn<Paper,Integer> PaperNoCol;

    @FXML
    private TableColumn<Paper, String> PaperNameCol;

    @FXML
    private Button submitPaperNo;

    @FXML
    private Button CancelButton;

    @FXML
    void handleCancel(ActionEvent event) {
    	((Window) CancelButton.getScene().getWindow()).hide();

    }

    @FXML
    void handleSubmit(ActionEvent event) throws IOException {
        if(this.conference.getAllpapers().maximum3Papers()==true) {
  	            displayMessage("You cannot submit any Paper Now. There has beem already 3 papers submitted.", AlertType.ERROR);
  	          if(this.conference.getEDate().compareTo(LocalDate.now())<0) {
  	        	displayMessage("The Dealine is already over. You cannot submit a paper now.", AlertType.ERROR);}
  	          if(this.conference.getSDate().compareTo(LocalDate.now())>0) {
  	        	displayMessage("The Start Date is not started yet. You cannot submit a paper now.", AlertType.ERROR);}
  	            return; }
        
        	  if(this.conference.getEDate().compareTo(LocalDate.now())<0) {
        	  displayMessage("The Dealine is already over. You cannot submit a paper now.", AlertType.ERROR);
	            return;}
        
        if(this.conference.getSDate().compareTo(LocalDate.now())>0) {
	        	displayMessage("The Start Date is not started yet. You cannot submit a paper now.", AlertType.ERROR);
	            return;}
        else {
        if(this.conference.getSDate().compareTo(LocalDate.now())<0) {
    	((Window) submitPaperNo.getScene().getWindow()).hide();
    	 FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/submitPaper.fxml"));
         Parent root = loader.load();
         // Assuming OneConferenceViewController is the controller for OneConferenceView.fxml
         submitPaperController submitPaperController = loader.getController();
         submitPaperController.setConference(conference);
         Stage stage = new Stage();
         stage.setScene(new Scene(root, 700, 500));
         stage.show();
        }
    }}

	public void displayMessage(String message, AlertType type) {
		Alert alert = new Alert(type);
		alert.setTitle("System Message");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();

	}
	
//Paper Details:
	@FXML
    private Text TitleText;

    @FXML
    private Text AuthorsText;

    @FXML
    private TextArea abstractText;

    @FXML
    private TextArea abstractText1;
    
    public void displayPaperDetails(Paper p) {
    	        try {
    	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Paper.fxml"));
    	            Parent root = loader.load();

    	            // Assuming PaperController is the controller for Paper.fxml
    	            PaperController paperController = loader.getController();
    	            paperController.setPaperDetails(p);

    	            Stage stage = new Stage();
    	            stage.setScene(new Scene(root, 600, 400));
    	            stage.show();
    	        } catch (IOException e) {
    	            e.printStackTrace(); // Handle the exception appropriately
    	        }
    	    }
    
}