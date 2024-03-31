package model;

import java.util.ArrayList;
import java.util.List;

public class Authors {
	private List<UserAccount> authors = new ArrayList<UserAccount>();//same as authors. it should be available
	public void addAuthor(UserAccount a) {
		if (a!=null) {
			authors.add(a);
		}
	}
	public List<UserAccount> getAuthors() {
		return authors;
	}

}
