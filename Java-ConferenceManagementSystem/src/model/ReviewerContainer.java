package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReviewerContainer implements Serializable{//for an object to be serialized (to be written to an object stream
	//unique identifier for the version of the class, and it helps with versioning when deserializing objects.
	private static final long serialVersionUID = 1L;
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
