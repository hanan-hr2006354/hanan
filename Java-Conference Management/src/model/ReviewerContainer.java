package model;

import java.util.ArrayList;
import java.util.List;

public class ReviewerContainer{
	private List<Reviewer> userReviewers = new ArrayList<Reviewer>();//not static cause it differentiates



	public List<Reviewer> getUserReviewers() {
		return userReviewers;
	}

	public void add(Reviewer r) {
		if (r!=null)
			userReviewers.add(r);
	}
	public void display() {
		for(Reviewer r: userReviewers) {
			System.out.println(r.toString());
		}
	}
	
}
