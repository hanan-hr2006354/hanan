package model;

public class Reviewer {
	private String name;
	private String expertise;
	private String venue;
	private String address;


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
	
	
	
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Reviewer [name=" + name + ", expertise=" + expertise + ", venue=" + venue + ", address=" + address
				+ "]";
	}
	public Reviewer(String name, String expertise, String venue, String address) {
		super();
		this.name = name;
		this.expertise = expertise;
		this.venue = venue;
		this.address = address;
	}
	
	
	

}
