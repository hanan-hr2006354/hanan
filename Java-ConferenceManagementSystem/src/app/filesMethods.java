package app;

import java.io.BufferedReader;
import java.io.*;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.*;
public class filesMethods {
	
	 private static final String USER_ACCOUNTS_FILE = "user_accounts.txt";
	 private static final String Conference_FILE = "Allconferences.txt";

	 public static void main(String[] args) throws Exception {
		    UserAccount user1 = new UserAccount("Ahmed Ali", "123456", "ahmed12@gmail.com");
	        UserAccount user2 = new UserAccount("Fatima Mohammed", "123456", "fatima@gmail.com");
	        UserAccount user3 = new UserAccount("Hanan Rashid", "123456", "hanan@gmail.com");
	        UserAccount user4 = new UserAccount("Maya Ali", "123456", "maya@gmail.com");
	        UserAccount user5 = new UserAccount("David Jackson", "123456", "david@gmail.com");
	        UserAccount user6 = new UserAccount("Sarah James,", "123456", "sarah@james.com");
	        UserAccount user7 = new UserAccount("Richard Owell", "123456", "richard@gmail.com");
	        UserAccount user8 = new UserAccount("Hasan Majid", "123456", "hasan@gmail.com");
	        UserAccount user9 = new UserAccount("Bella Rose", "123456", "bella@gmail.com");
	        UserAccount user10 = new UserAccount("Jackson Henry", "123456", "jackson@gmail.com");
	        // Add the user accounts to the container
	        UserAccountContainer.userAccounts.add(user1);
	        UserAccountContainer.userAccounts.add(user2);
	        UserAccountContainer.userAccounts.add(user3);
	        UserAccountContainer.userAccounts.add(user4);
	        UserAccountContainer.userAccounts.add(user5);
	        UserAccountContainer.userAccounts.add(user6);
	        UserAccountContainer.userAccounts.add(user7);
	        UserAccountContainer.userAccounts.add(user8);
	        UserAccountContainer.userAccounts.add(user9);
	        UserAccountContainer.userAccounts.add(user10);

	        // Write user accounts to file
	        filesMethods.writeUserAccounts(UserAccountContainer.userAccounts);
	        
	        
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        
	     // Conference 1
	        Reviewer r1 = new Reviewer("John Doe", "Developer");
	        Reviewer r2 = new Reviewer("Jane Smith", "Designer");
	        ReviewerContainer rc1 = new ReviewerContainer();
	        rc1.add(r1);
	        rc1.add(r2);
	        
	    	List<ConferenceVenue> venue1=new ArrayList<ConferenceVenue>();
	        ConferenceVenue cv11=new ConferenceVenue("Doha City","Street 123","8:00AM - 10:00AM");
	        ConferenceVenue cv12=new ConferenceVenue("Wakra","Street 111","10:00AM - 12:00PM");
	        venue1.add(cv11);venue1.add(cv12);


	        //Paper 1
	        Authors authorPaper11 = new Authors();
	        authorPaper11.addAuthor(UserAccountContainer.userAccounts.get(0));
	        authorPaper11.addAuthor(UserAccountContainer.userAccounts.get(1));
	        PaperContainer pc1 = new PaperContainer();
	        Paper p11 = new Paper("A Functional Programming Language with Versions", "While modern software development heavily uses versioned packages\n, programming languages rarely support the concept of versions\n in their semantics, which makes software updates more bulky and unsafe.\n This paper proposes a programming language that intrinsically supports versions.\n The main goals are to design core language features to support multiple versions\n in one program and establish a proper notion of type safety with those features.", 
	        		authorPaper11.getAuthors(),
	                "Type Safety in Versioned Programs,\nFunctional Programming Language,\n Software Updates and Safety");
	        
	        //Paper 2
	        Authors authorPaper12 = new Authors();
	        authorPaper12.addAuthor(UserAccountContainer.userAccounts.get(6));
	        authorPaper12.addAuthor(UserAccountContainer.userAccounts.get(7));
	        authorPaper12.addAuthor(UserAccountContainer.userAccounts.get(3));
	        Paper p12 = new Paper("A Theory of Composing Protocols\r\n"
	        		+ "", "In programming, protocols are everywhere.\n"+
	        				"Protocols describe the pattern of interaction\n "
	        				+"(or communication) between software systems, for\n "+
	        				"example, between a user-space program and the kernel\n"+
	        				"or between a local application and an online service.\n"+
	        				"Ensuring conformance to protocols avoids a significant\n"+
	        				"class of software errors. Subsequently, there has been\n"
	        				+"a lot of work on verifying code against formal protocol\n"+ 
	        				"specifications.", authorPaper12.getAuthors(),
	                "Composing Protocols\r\n"
	                + "Protocol Design\r\n"
	                + "Communication Protocols\r\n"
	                + "Theoretical Framework"
	              );
	        pc1.add(p11);
	        pc1.add(p12);
	        Conference c1 = new Conference("Sarah Owais","Programming", LocalDate.parse("2023-11-01", formatter),
	                LocalDate.parse("2023-12-05", formatter), rc1, pc1,venue1);
		    ConferenceContainer.addConference(c1);
		    
		    
		  //Conference 2
		    //All Reviewers for that conference
		    Reviewer r3 = new Reviewer("Alice Johnson", "Engineer" );
	        Reviewer r4 = new Reviewer("Bob Williams", "Scientist");
	        ReviewerContainer rc2 = new ReviewerContainer();
	        rc2.add(r3);
	        rc2.add(r4);
	        
	        //Add Venue
	    	List<ConferenceVenue> venue2=new ArrayList<ConferenceVenue>();
	        ConferenceVenue cv21=new ConferenceVenue("Dafna","Street 789","8:00AM - 10:00AM");
	        ConferenceVenue cv22=new ConferenceVenue("Shamal","Street 101","10:00AM - 12:00PM");
	        venue2.add(cv21);venue2.add(cv22);

	        //Paper1
	        Authors authorPaper21 = new Authors();
	        authorPaper21.addAuthor(UserAccountContainer.userAccounts.get(9));
	        authorPaper21.addAuthor(UserAccountContainer.userAccounts.get(8));
	        authorPaper21.addAuthor(UserAccountContainer.userAccounts.get(2));
	        authorPaper21.addAuthor(UserAccountContainer.userAccounts.get(9));
	        
	        PaperContainer pc2= new PaperContainer();
	        Paper p21 = new Paper("Web Development Framework : Future Trends"
	        		 , "Today advancement in technology is changing and challenging\n"
	        		 		+ " everyone’s life and making it easier for humans to fulfil\n"
	        		 		+ " their needs, and to expect a great evolution in expansion for\n"
	        		 		+ " web development. Now when we hear about online systems, we seem \n"
	        		 		+ "to be well known to this term, whereas not long time ago, we were \n"
	        		 		+ "quite hesitant towards it and considered it a taboo and unnecessary\n"
	        		 		+ " science revolution.", authorPaper21.getAuthors(),
	                "authoring languages\n"
	                + "Internet\n"
	                + "mobile computing\n"
	                + "online front-ends");
	        
	        //Paper 2
	        Authors authorPaper22 = new Authors();
	        authorPaper22.addAuthor(UserAccountContainer.userAccounts.get(7));
	        authorPaper22.addAuthor(UserAccountContainer.userAccounts.get(5));

	        Paper p22 = new Paper("Web design — Past, present and future"
	        		, "Nowadays there is a wide prevalence of various devices\n"
	        				+ " that can be connected online for different kind of activities, \n"
	        				+ "and among others, for browsing web pages. Web design is constantly\n"
	        				+ " changing and adjusting due to the appearance of different sizes\n"
	        				+ " and types of devices. Web design from the 1990s is greatly different\n"
	        				+ " from current web pages. The main goal of the design, is not the design\n"
	        				+ " itself, but to enable web content transfer on more readable and\n "
	        				+ "comprehensible way, no matter which device is used to present web \n"
	        				+ "content, desktop or hybrid computer, various mobile or wearable device. \n"
	        				, authorPaper22.getAuthors(),
	                "Web design\n"
	                + "Web pages\n"
	                + "Web content transfer");
	        
	        //Paper 3
	        Authors authorPaper23 = new Authors();
	        authorPaper23.addAuthor(UserAccountContainer.userAccounts.get(5));

	        Paper p23 = new Paper("Developing Software Online With Platform-as-a-Service Technology"
	        		, "Vendors are starting to offer platform as a service systems.\n"
	        				+ " PaaS systems are hosted, Web-based application-development \n"
	        				+ "platforms, providing end-to-end or, in some cases, partial \n"
	        				+ "environments for developing full programs online."
	        				, authorPaper23.getAuthors(),
	                "groupware\n"
	                + "Internet\n"
	                + "program debugging\n"
	                + "programming environments\n"
	                + "software maintenance");
	        

	        pc2.add(p21);
	        pc2.add(p22);
	        pc2.add(p23);

	        Conference c2 = new Conference("Ali Zeyad","Web Development", LocalDate.parse("2023-11-15", formatter),
	                LocalDate.parse("2023-11-30", formatter), rc2, pc2,venue2);
		    ConferenceContainer.addConference(c2);

		    
		    //Conference 3.
		    Reviewer r5 = new Reviewer("John Stewart", "Climate Scientist");
	        ReviewerContainer rc3 = new ReviewerContainer();
	        rc3.add(r5);
	        
	        //Location
	    	List<ConferenceVenue> venue3=new ArrayList<ConferenceVenue>();
	        ConferenceVenue cv31=new ConferenceVenue("Musaeed","Street 20","8:00AM - 10:00AM");
	        venue3.add(cv31);

	        //Paper1
	        Authors authorPaper31 = new Authors();
	        authorPaper31.addAuthor(UserAccountContainer.userAccounts.get(4));
	        authorPaper31.addAuthor(UserAccountContainer.userAccounts.get(3));
	        
	        PaperContainer pc3= new PaperContainer();
	        Paper p31 = new Paper("Agricultural Meteorological Disasters effecting Cimate Change"
	        		 , "Due to the complex climate types and various meteorological disasters in gansu province, \n"
	        		 		+ "with the background of climate change dominated by warming, the losses caused by\n"
	        		 		+ " meteorological disasters are increasing. The understanding of disaster risk \n"
	        		 		+ "characteristics and its response to climate change need to be solved urgently.", authorPaper31.getAuthors(),
	                "authoring languages\n"
	                + "Internet\n"
	                + "mobile computing\n"
	                + "online front-ends");
	        
	      
	        pc3.add(p31);

	        Conference c3 = new Conference("Ali Zeyad","International Conference on Climate Change", LocalDate.parse("2023-11-15", formatter),
	                LocalDate.parse("2023-11-21", formatter), rc3, pc3,venue3);
		    ConferenceContainer.addConference(c3);

		    
		    //Conference 4.
		    Reviewer r6 = new Reviewer("Amanda halt", "Statistics Learner");
		    Reviewer r7 = new Reviewer("Saeed hamad", "Deep Learning");
		    Reviewer r8 = new Reviewer("Alia Sheikh", "Computer Visioner");
		    Reviewer r9 = new Reviewer("Zeyad Saeed", "Reinforcement Learning");

	        ReviewerContainer rc4 = new ReviewerContainer();
	        rc4.add(r6);rc4.add(r7);
	        rc4.add(r8);
	        rc4.add(r9);
	        //Venue
	    	List<ConferenceVenue> venue4=new ArrayList<ConferenceVenue>();
	        ConferenceVenue cv41=new ConferenceVenue("New Salata","Street 123","1:00PM - 2:00PM");
	        ConferenceVenue cv42=new ConferenceVenue("Mamora","Street 220","2:00PM - 4:00PM");
	        ConferenceVenue cv43=new ConferenceVenue("Duhail","Street 180","5:00PM - 8:00PM");
	        ConferenceVenue cv44=new ConferenceVenue("Bida","Street 10","9:00PM - 10:00PM");
	        venue4.add(cv41);venue4.add(cv42);venue4.add(cv44);

	        //Paper1
	        Authors authorPaper41= new Authors();
	        authorPaper41.addAuthor(UserAccountContainer.userAccounts.get(4));
	        authorPaper41.addAuthor(UserAccountContainer.userAccounts.get(2));
	        
	        PaperContainer pc4= new PaperContainer();
	        Paper p41 = new Paper("A machine learning approach to predict the result of League of Legends"
	        		 , "Nowadays, the MOBA game is the game type with the most audiences and players \n"
	        		 		+ "around the world. Recently, the League of Legends has become an official\n"
	        		 		+ " sport as an e-sport among 37 events in the 2022 Asia Games held in Hangzhou. \n"
	        		 		+ "As the development in the e-sport, analytical skills are also involved in this field. ",authorPaper41.getAuthors(),
	        		 		"League of Legends\r\n"
	        		 				+ "machine learning\n"
	        		 				+ "Voting classifier\n"
	        		 				+ "MOBA");
	        
	      
	        pc4.add(p41);

	        Conference c4 = new Conference("Ali Zeyad","International Conference on Machine Learning", LocalDate.parse("2023-11-15", formatter),
	                LocalDate.parse("2023-12-20", formatter), rc4, pc4,venue4);
		    ConferenceContainer.addConference(c4);

		    
		    
		    //Conference 5.
		    Reviewer r10 = new Reviewer("Amanda halt", "Statistics Learner");
		    Reviewer r11 = new Reviewer("Zeyad Saeed", "Reinforcement Learning");

	        ReviewerContainer rc5 = new ReviewerContainer();
	        rc5.add(r10);rc5.add(r11);
	        
	        List<ConferenceVenue> venue5=new ArrayList<ConferenceVenue>();
	        ConferenceVenue cv51=new ConferenceVenue("New Salata","Street 123","1:00PM - 2:00PM");
	        ConferenceVenue cv52=new ConferenceVenue("Mamora","Street 220","2:00PM - 4:00PM");
	        venue5.add(cv51); venue5.add(cv52);
	        PaperContainer pc5= new PaperContainer();
	    
	        Conference c5 = new Conference("Mahmoud Ali","Molecular Biology and Genetics", LocalDate.parse("2023-12-15", formatter),
	                LocalDate.parse("2023-12-20", formatter), rc5, pc5,venue5);
		    ConferenceContainer.addConference(c5);

	        filesMethods.writeConference(ConferenceContainer.getUserConference());


//		    ConferenceContainer.displayAllconference();

	        
	    }
	    public static void writeUserAccounts(List<UserAccount> userAccounts) {
	        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_ACCOUNTS_FILE))) {
	            oos.writeObject(userAccounts);
	            System.out.println("User accounts written to file: " + USER_ACCOUNTS_FILE);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    // Method to read user accounts from a file
	    @SuppressWarnings("unchecked")
	    public static List<UserAccount> readUserAccounts() {
	        List<UserAccount> userAccounts = null;
	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_ACCOUNTS_FILE))) {
	            userAccounts = (List<UserAccount>) ois.readObject();
	            System.out.println("User accounts read from file: " + USER_ACCOUNTS_FILE);
	        } catch (IOException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return userAccounts;
	    }
	    
	    public static void writeConference(List<Conference> conference) {
	        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Conference_FILE))) {
	            oos.writeObject(conference);
	            System.out.println("User accounts written to file: "+Conference_FILE);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    // Method to read user accounts from a file
	    @SuppressWarnings("unchecked")
	    public static List<Conference> readConference() {
	        List<Conference> conferences = null;
	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Conference_FILE))) {
	        	conferences = (List<Conference>) ois.readObject();
	            System.out.println("User accounts read from file: "+Conference_FILE);
	        } catch (IOException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return conferences;
	    }

}
