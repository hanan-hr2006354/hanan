package app;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.Date;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Authors;
import model.Conference;
import model.ConferenceContainer;
import model.Paper;
import model.PaperContainer;
import model.Reviewer;
import model.ReviewerContainer;
import model.UserAccount;
import model.UserAccountContainer;

public class LoginApp extends Application {
    @Override
    
    public void start(Stage stage) throws Exception{
        Pane root = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle("Login Dialog");
        stage.show();
    }

    public static void main(String[] args) {
	    	new UserAccount("Ahmed Ali", "123456","ahmed12@gmail.com");
	    	new UserAccount("Fatima Mohammed", "123456","fatima@gmail.com");
	    	new UserAccount("Hanan Rashid", "123456","fatima@gmail.com");
		//Initial Available Conferences:
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        Reviewer r1=new Reviewer("Hanan Rashid","developer","doha","New Salata, Street 1134");
			Reviewer r2=new Reviewer("Maha Ali","coder","doha","New Salata, Street 1134");
			ReviewerContainer rc1=new ReviewerContainer();
			rc1.add(r1); rc1.add(r2); //association with conference?
			
			ReviewerContainer rc2=new ReviewerContainer();
			Reviewer r3=new Reviewer("AA","developer","doha","New Salata, Street 1134");
			Reviewer r4=new Reviewer("MM","coder","doha","New Salata, Street 1134");
			rc2.add(r3); rc2.add(r4);
			
			ReviewerContainer rc3=new ReviewerContainer();
			Reviewer r5=new Reviewer("Jawaher","student","doha","New Salata, Street 1134");
			Reviewer r6=new Reviewer("Ahmed","principle","doha","New Salata, Street 1134");
			rc3.add(r5); rc3.add(r6);
			
			Authors authorPaper1= new Authors();
			authorPaper1.addAuthor(UserAccountContainer.userAccounts.get(0));
			authorPaper1.addAuthor(UserAccountContainer.userAccounts.get(1));

			PaperContainer pc1=new PaperContainer();
			Paper p1=new Paper("robots","hello",authorPaper1.getAuthors(),"hello");
			Paper p2=new Paper("robots","hello",authorPaper1.getAuthors(),"hello");

			pc1.add(p1);			pc1.add(p2);

			Conference c1= new Conference("Programming language",LocalDate.parse("2023-11-01",formatter),LocalDate.parse("2023-11-11",formatter),rc1,"Musherib",pc1);
			Conference c2= new Conference("Web Development",LocalDate.parse("2023-11-13",formatter),LocalDate.parse("2023-11-29",formatter),rc2,"Shiraton Building",pc1);
			Conference c3= new Conference("Mobile Application",LocalDate.parse("2023-11-22",formatter),LocalDate.parse("2023-11-12",formatter),rc3,"Shiraton Building",pc1);
		    ConferenceContainer.addConference(c1);
		    ConferenceContainer.addConference(c2);
		    ConferenceContainer.addConference(c3);
		    ConferenceContainer.displayAllconference();
			launch(args);

    }
}
