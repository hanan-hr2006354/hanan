package model;

import java.io.Serializable;
import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Conference implements Serializable{//for an object to be serialized (to be written to an object stream
	//unique identifier for the version of the class, and it helps with versioning when deserializing objects.
	 //ensure that the serialized and deserialized objects have the same structure.
	private static final long serialVersionUID = 1L;
	private String organizer;
	private int cNo;
	private String CName;
	private LocalDate sDate;
	private LocalDate eDate;
	private ReviewerContainer reviewers;// with expertise
	private int noOfPapers=0;//for paper
	private PaperContainer allpapers;
	public static int conferenceNo=0;

	
	
	private List<ConferenceVenue> cvenues=new ArrayList<ConferenceVenue>();
	public Conference( String organizer,String cName, LocalDate sDate, LocalDate eDate, ReviewerContainer reviewers,PaperContainer allpapers,List<ConferenceVenue> cvenues) {
		super();
		cNo=++conferenceNo;
		CName = cName;
		this.sDate = sDate;
		this.eDate = eDate;
		this.reviewers = reviewers;
		this.allpapers=allpapers;
		this.noOfPapers=allpapers.getPapers().size();
		this.cvenues=cvenues;
		this.organizer=organizer;
	}

    public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	public List<ConferenceVenue> getCvenues() {
		return cvenues;
	}

	public void setCvenues(List<ConferenceVenue> cvenues) {
		this.cvenues = cvenues;
	}

	public int getCNo() {
        return cNo;
    }

    public LocalDate getSDate() {
        return sDate;
    }

    public LocalDate getEDate() {
        return eDate;
    }
	


	public PaperContainer getAllpapers() {
		return allpapers;
	}

	public void setAllpapers(PaperContainer allpapers) {
		this.allpapers = allpapers;
	}

	public int getcNo() {
		return cNo;
	}

	public void setcNo(int cNo) {
		this.cNo = cNo;
	}

	public String getCName() {
	    return CName;
	}


	public void setCName(String cName) {
		CName = cName;
	}

	public LocalDate getsDate() {
		return sDate;
	}

	public void setsDate(LocalDate sDate) {
		this.sDate = sDate;
	}

	public LocalDate geteDate() {
		return eDate;
	}

	public void seteDate(LocalDate eDate) {
		this.eDate = eDate;
	}

	public ReviewerContainer getReviewers() {
		return reviewers;
	}

	public void setReviewers(ReviewerContainer reviewers) {
		this.reviewers = reviewers;
	}
	public int getNoOfPapers() {
		return allpapers.getPapers().size();
	}

	public void setNoOfPapers(int noOfPapers) {
		this.noOfPapers = noOfPapers;
	}

	public static int getConferenceNo() {
		return conferenceNo;
	}

	public static void setConferenceNo(int conferenceNo) {
		Conference.conferenceNo = conferenceNo;
	}

	public void display() {
		System.out.println("Conference [cNo="+cNo+" , CName"+CName + ", sDate=" + sDate + ", eDate=" + eDate + "Orgnizer"+organizer+ "reviewers=");
		reviewers.display();
		allpapers.display();
	

	
	
	
	
	
	

}}
