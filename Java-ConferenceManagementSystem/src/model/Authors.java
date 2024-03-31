package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Authors implements Serializable{//for an object to be serialized (to be written to an object stream
	//unique identifier for the version of the class, and it helps with versioning when deserializing objects.
    private static final long serialVersionUID = 1L;
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
