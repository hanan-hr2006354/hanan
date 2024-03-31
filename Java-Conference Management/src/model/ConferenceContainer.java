package model;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConferenceContainer {
	private static List<Conference> userConference = new ArrayList<Conference>();

	public static void addConference(Conference c) {
		if (c!=null) {
			userConference.add(c);

		}
	}

	public static List<Conference> getUserConference() {
		return userConference;
	}
	public static void displayAllconference() {
		for(Conference c:userConference)
			c.display();
	}

	
}
