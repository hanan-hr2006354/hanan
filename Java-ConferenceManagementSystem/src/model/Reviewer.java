package model;

import java.io.Serializable;

public class Reviewer implements Serializable{//for an object to be serialized (to be written to an object stream
	//unique identifier for the version of the class, and it helps with versioning when deserializing objects.
    private static final long serialVersionUID = 1L;
	private String name;
	private String expertise;

	public Reviewer() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	@Override
	public String toString() {
		return "Reviewer [name=" + name + ", expertise=" + expertise 
				+ "]";
	}
	public Reviewer(String name, String expertise) {
		super();
		this.name = name;
		this.expertise = expertise;

	}
	
	
	

}
