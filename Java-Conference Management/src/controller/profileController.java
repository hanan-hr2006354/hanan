package controller;

import java.io.IOException;
import java.util.ArrayList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Conference;
import model.ConferenceContainer;
import model.Paper;
import model.UserAccount;
import model.UserAccountContainer;

public class profileController {

    @FXML
    private TextField username;

    @FXML
    private TextField email;
    @FXML
    private TableView<Paper> paperTable;
    @FXML
    private TableView<Conference> confTable;

    @FXML
    private TableColumn<Paper, Integer> paperNocol;

    @FXML
    private TableColumn<Paper, String> PaperTitleCOl;

    @FXML
    private TableColumn<Conference, Integer> cNocol;

    @FXML
    private TableColumn<Conference, String> CNamecol;
    @FXML
    private Button HomeButton;

    @FXML
    private Button ConferenceButton;

    @FXML
    private Button HelpButton;
    @FXML
    private Button edit;
    @FXML
    private Button displayConference;

    @FXML
    private Button displayPaper;

    @FXML
//    private UserAccount ua=UserAccountContainer.currentLogin;
      private UserAccount ua=UserAccountContainer.userAccounts.get(0);

    private List<Conference> allconferences=ConferenceContainer.getUserConference();
    
    private List<Paper> papers= new ArrayList<Paper>();
    private List<Conference> conf= new ArrayList<Conference>();
    
	public List<UserAccount> accounts = UserAccountContainer.userAccounts;//same as authors. it should be available


	@FXML
	void initialize() {
	    if (!UserAccountContainer.userAccounts.isEmpty() && confTable != null && paperTable != null) {
	        UserAccount ua = UserAccountContainer.userAccounts.get(0);
	        username.setText(ua.getUsername());
	        username.setEditable(false);
	        email.setText(ua.getEmail());
	        email.setEditable(false);
	        SearchPapers();PopulateTable();}
	    	edit.setOnAction(this::editEmail);
	    	paperTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	            if (newValue != null) {
	            	displayselectedPaper(newValue);
	            }
	        });
	    	confTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	            if (newValue != null) {
	            	displayselectedConference(newValue);
	            }
	        });
	}

	@FXML
	void editEmail(ActionEvent event) {
	    email.setEditable(true);
	}

	@FXML
	void saveEmail(ActionEvent event) {
	    String newEmail = email.getText();
	    ua.setEmail(newEmail);
	    email.setEditable(false);
	    email.setText(newEmail);
	}
	
	@FXML
	void PopulateTable() {
	 paperNocol.setCellValueFactory(new PropertyValueFactory<>("PNo"));
     PaperTitleCOl.setCellValueFactory(new PropertyValueFactory<>("paperTitle"));

     cNocol.setCellValueFactory(new PropertyValueFactory<>("cNo"));
     CNamecol.setCellValueFactory(new PropertyValueFactory<>("CName"));

     ObservableList<Paper> paperList = FXCollections.observableArrayList(papers);
     ObservableList<Conference> conferenceList = FXCollections.observableArrayList(conf);

     confTable.setItems(conferenceList);
     paperTable.setItems(paperList);
	}
	
    public void SearchPapers() {
    	for(Conference c: allconferences) {
    		for(Paper p:c.getAllpapers().getPapers()) {
    			for(UserAccount us:accounts) {
    				if(us.equals(ua)) {
    					papers.add(p);
    					conf.add(c);
    				}
    			}
    		}
    	}
    	
    }
    
    
    ///////////////////////////////////////////////////////////////
    @FXML
    void deleteHandle(ActionEvent event) {
        Paper selectedPaper = paperTable.getSelectionModel().getSelectedItem();
        Conference selectedConference = confTable.getSelectionModel().getSelectedItem();
        if (selectedPaper != null && selectedConference != null) {
        	int index=papers.indexOf(selectedPaper);//check if papers nad columns on same row
        	if(selectedConference.equals(conf.get(index))) {
            papers.remove(selectedPaper);
            conf.remove(selectedConference);
            updateTableViews();
        }else {
            displayMessage("Please choose the Paper you want to deleta as well as corresponding conference.", AlertType.ERROR);

        }
        	}
        else {
            displayMessage("Please choose the Paper you want to deleta as well as corresponding conference.", AlertType.ERROR);
        	
        }
    }
    private void updateTableViews() {
        ObservableList<Paper> paperList = FXCollections.observableArrayList(papers);
        ObservableList<Conference> conferenceList = FXCollections.observableArrayList(conf);
        confTable.setItems(conferenceList);
        paperTable.setItems(paperList);
        
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
		Stage stage = new Stage();stage.setScene(new Scene(root,300,300));
		stage.show();
	}
	@FXML
	void editHandle(ActionEvent event) {
	    // Your implementation here
	}

    
    public void displayselectedPaper(Paper p) {
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
    public void displayselectedConference(Conference c) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/oneConferenceView.fxml"));
            Parent root = loader.load();

            // Assuming PaperController is the controller for Paper.fxml
            OneConferenceViewController oconferenceController = loader.getController();
            oconferenceController.setConference(c);

            Stage stage = new Stage();
            stage.setScene(new Scene(root,  900, 500));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
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
	public void displayMessage(String message, AlertType type) {
		Alert alert = new Alert(type);
		alert.setTitle("System Message");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();

	}}

