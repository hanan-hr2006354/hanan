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

    @FXML
    private TextField organizerTextField;
    public List<Conference> userConferences = ConferenceContainer.getUserConference();

    private Conference conference;
    public void setConference(Conference conference) {
        this.conference = conference;
   	    populateFields();
   	    initializeReviewerTableView(); 
 	    initializePaperTableView();
    	initializeVenueTableView();
}
    
    @FXML//get updated conference form submitPaperCOntroller
    public void setAfterSumissionConference(Conference conference) {
        this.conference = conference;
        populateFields();
        initializeReviewerTableView();
        initializePaperTableView();
    	initializeVenueTableView();

        // Add a listener to the PaperTable selection
        PaperTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                displayPaperDetails(newValue); } }); }

    //View Paper table, paper and listen to the selection of Paper
    @FXML
    void initialize() {
    	initializeReviewerTableView(); 
    	initializePaperTableView();
    	initializeVenueTableView();
    	// Add a listener to the PaperTable selection
        PaperTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                displayPaperDetails(newValue);}}); }

    //Fill Conference Details
    private void populateFields() {
        if (cNoTextField != null && cNameTextField != null && startDateTextField != null &&
            endDateTextField != null && organizerTextField!=null) {
        	
            cNoTextField.setText(String.valueOf(conference.getCNo()));
            cNoTextField.setEditable(false);
            
            cNameTextField.setText(conference.getCName());
            cNameTextField.setEditable(false);

            startDateTextField.setText(conference.getSDate().toString());
            startDateTextField.setEditable(false);

            endDateTextField.setText(conference.getEDate().toString());
            endDateTextField.setEditable(false);
            
            organizerTextField.setText(conference.getOrganizer());
            organizerTextField.setEditable(false);


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
  
    //Fill Reviewer Table
    @FXML
    private void initializeReviewerTableView() {
        try {
            // Retrieve conference data
            if (conference != null) {
                System.out.println("Conference data is available.");

                reviewerName.setCellValueFactory(new PropertyValueFactory<>("name"));
                reviewerExpertise.setCellValueFactory(new PropertyValueFactory<>("expertise"));

                List<Reviewer> allReviewers = conference.getReviewers().getUserReviewers();
                // Debug statement
                System.out.println("Number of Reviewers: " + allReviewers.size());
                ObservableList<Reviewer> reviewerList = FXCollections.observableArrayList(allReviewers);
                reviewerTable.setItems(reviewerList);
            } else {
                System.err.println("Conference object is null.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // You might want to log the exception or show an error message
        }}
    
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
    //Fill the Paper Table
    @FXML
    private void initializePaperTableView() {
        try {
            // Debug statements
            System.out.println("ITableView");
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
    private TableView<ConferenceVenue> VenueTable;

    @FXML
    private TableColumn<ConferenceVenue, String> venueCol;

    @FXML
    private TableColumn<ConferenceVenue, String> addressCol;

    @FXML
    private TableColumn<ConferenceVenue, String> timeCol;
    @FXML
    private void initializeVenueTableView() {
        try {
            // Debug statements
            System.out.println("VenueTableView");
            if (conference != null) {
                System.out.println("Conference data is available.");
                venueCol.setCellValueFactory(new PropertyValueFactory<>("venue"));
                addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
                timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
                List<ConferenceVenue> venues = conference.getCvenues();
                // Debug statement
                System.out.println("Number of Venues: " + venues.size());
                ObservableList<ConferenceVenue> conferenceVenueList = FXCollections.observableArrayList(venues);
                // Set the items for the TableView
                VenueTable.setItems(conferenceVenueList);
            } else {
                System.err.println("Conference object is null.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // You might want to log the exception or show an error message
        }
    }
    
    
    
    

    @FXML//cancel while sending the updated conference to ConferenceController
    void handleCancel(ActionEvent event) throws IOException {
    	((Window) submitPaperNo.getScene().getWindow()).hide();
    	 FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Conference.fxml"));
         Parent root = loader.load();
         // Assuming OneConferenceViewController is the controller for OneConferenceView.fxml
         ConferenceController ConferenceController = loader.getController();
         ConferenceController.setConference(conference,ConferenceController.ConfRowNumber-1);
         Stage stage = new Stage();
         stage.setScene(new Scene(root, 900, 500));
         stage.show();
  }

    //Submit paper
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
	
    
    public void displayPaperDetails(Paper p) {
    	        try {
    	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Paper.fxml"));
    	            Parent root = loader.load();
    	            // Assuming PaperController is the controller for Paper.fxml
    	            PaperController paperController = loader.getController();
    	            paperController.setPaperDetails(p);
    	            Stage stage = new Stage();
    	            stage.setScene(new Scene(root, 700, 500));
    	            stage.show();
    	        } catch (IOException e) {
    	            e.printStackTrace(); // Handle the exception appropriately
    	        }
    	    }
    
}