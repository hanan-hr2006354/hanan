package controller;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Conference;
import model.ConferenceContainer;
import model.Reviewer;
import model.UserAccountContainer;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import app.filesMethods;
import javafx.scene.control.cell.PropertyValueFactory;
public class ConferenceController {

    @FXML
    private TableView<Conference> conferenceTable;

    @FXML
    private TableColumn<Conference, Integer> ConfNoCol;

    @FXML
    private TableColumn<Conference, String> nameCol;

    @FXML
    private TableColumn<Conference, LocalDate> startDateCol;

    @FXML
    private TableColumn<Conference, LocalDate> EndDateCol;

    @FXML
    private TableColumn<Conference, String> venueCol;

    @FXML
    private TableColumn<Conference, Integer> submitedPaper;

    public List<Conference> userConferences = ConferenceContainer.getUserConference();

    @FXML
    private Button BackButton;
    

    public static int ConfRowNumber;
    @FXML
    void initialize() throws IOException {
        // Retrieve conference data
        // Assuming your Conference class has appropriate getters for the properties
        ConfNoCol.setCellValueFactory(new PropertyValueFactory<>("cNo"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("cName"));
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("sDate"));
        EndDateCol.setCellValueFactory(new PropertyValueFactory<>("eDate"));
        submitedPaper.setCellValueFactory(new PropertyValueFactory<>("noOfPapers"));

        ObservableList<Conference> conferenceList = FXCollections.observableArrayList(userConferences);

        // Set the items for the TableView
        conferenceTable.setItems(conferenceList);


        conferenceTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                try {
                    openOneConferenceView(newSelection);
                    ConfRowNumber=newSelection.getcNo();
                } catch (IOException e) {
                    e.printStackTrace();
                    // Handle the exception as needed (show an error message, log, etc.)
                }
            }
        });
    }
      private void openOneConferenceView(Conference selectedConference) throws IOException {
    	  if (ConferenceButton != null && ConferenceButton.getScene() != null) {
    	 ((Window) ConferenceButton.getScene().getWindow()).hide();
    	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/OneConferenceView.fxml"));
    	    Parent root = loader.load();
    	    // Assuming OneConferenceViewController is the controller for OneConferenceView.fxml
    	    OneConferenceViewController oneConferenceController = loader.getController();
    	    oneConferenceController.setConference(selectedConference);
    	    Stage stage = new Stage();
    	    stage.setScene(new Scene(root, 900, 500));
    	    stage.show();}
    }

    @FXML
    public void handleDragDetected(MouseEvent event) {
        // Handle the drag detected event
        System.out.println("Drag detected on conferenceTable");
        // Add your custom drag and drop logic here
    }

    @FXML
    private Button HomeButton;

    @FXML
    private Button ConferenceButton;

    @FXML
    private Button HelpButton;

    @FXML
    private MenuButton profileButton;

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
    void handleLogout(ActionEvent event) throws IOException {
        ((Window) profileButton.getScene().getWindow()).hide();
        Pane root = FXMLLoader.load(getClass().getResource("/view/loginChoice.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root,  600, 400));
        stage.show();
        UserAccountContainer.currentLogin=null;

    }


    @FXML
    void homeHandle(ActionEvent event) {
        try {
            ((Window) HomeButton.getScene().getWindow()).hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Menu.fxml"));
            Parent root = loader.load();
            System.out.println("FXML file loaded successfully.");
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading FXML file: " + e.getMessage());
        }
    }

	public void setConference(Conference conference, int index) throws IOException {
		userConferences.remove(index);
		userConferences.add(index, conference);
		userConferences.get(index).display();
		System.out.println("Number of Papers"+userConferences.get(index).getNoOfPapers());
		System.out.println("Number of Papers"+userConferences.get(index).getAllpapers().getPapers().size());

		conferenceTable.getItems().clear();

	    // Add the updated data to the TableView
	    ObservableList<Conference> conferenceList = FXCollections.observableArrayList(userConferences);
	    conferenceTable.setItems(conferenceList);
	    
        filesMethods.writeConference(userConferences);



	}
}
