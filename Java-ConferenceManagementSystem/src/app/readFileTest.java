package app;

import java.util.List;

import model.Conference;
import model.ConferenceContainer;
import model.Paper;
import model.UserAccount;
import model.UserAccountContainer;

public class readFileTest {
	 public static void main(String[] args) {
			List<UserAccount> userAccounts = filesMethods.readUserAccounts();
	        if (userAccounts != null) {
	            UserAccountContainer.userAccounts.addAll(userAccounts);}
	        
	        for(UserAccount us:UserAccountContainer.userAccounts) {
//	        	System.out.println(us.toString());}
	        }
	    	List<Conference> conference = filesMethods.readConference();
	        if (conference != null) {
	        	ConferenceContainer.userConference.addAll(conference);
	        }
	        for(Conference c:ConferenceContainer.userConference) {
	        	for(Paper p:c.getAllpapers().getPapers()) {
	        		System.out.println(p.getPNo());
	        	}
	        }
	        }}
