package model;

import java.io.Serializable;

public class ConferenceVenue implements Serializable{//for an object to be serialized (to be written to an object stream
	//unique identifier for the version of the class, and it helps with versioning when deserializing objects.
	private static final long serialVersionUID = 1L;
	private String venue;
	private String address;
	private String time;
	public String getVenue() {
		return venue;
	}
	
	public ConferenceVenue() {
		super();
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public ConferenceVenue(String venue, String address, String time) {
		super();
		this.venue = venue;
		this.address = address;
		this.time = time;
	}
	@Override
	public String toString() {
		return "ConferenceVenue [venue=" + venue + ", address=" + address + ", time=" + time + "]";
	}
	

}
