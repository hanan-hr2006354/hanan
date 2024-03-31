package model;

import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Conference {
	private int cNo;
	private String CName;
	private LocalDate sDate;
	private LocalDate eDate;
	private ReviewerContainer reviewers;// with expertise
	private String venue;
	private int noOfPapers=0;//for paper
	private PaperContainer allpapers;
	
	public static int conferenceNo=0;
	
	
	private String paperTitle;
	private String abstactPaper;
	private List<UserAccount> authors;
	private String keywords;
	private int PNo=0;

    public int getCNo() {
        return cNo;
    }

    public LocalDate getSDate() {
        return sDate;
    }

    public LocalDate getEDate() {
        return eDate;
    }
	
	public Conference( String cName, LocalDate sDate, LocalDate eDate, ReviewerContainer reviewers, String venue,PaperContainer allpapers ) {
		super();
		cNo=++conferenceNo;
		CName = cName;
		this.sDate = sDate;
		this.eDate = eDate;
		this.reviewers = reviewers;
		this.venue = venue;
		this.allpapers=allpapers;
	}

	public void updateConference(String title, String keywords, String abstractPaper, List<UserAccount> authors) {
		this.paperTitle = paperTitle;
		this.abstactPaper = abstactPaper;
		this.authors = authors;
		this.keywords = keywords;
		PNo=Paper.paperNo++;
		this.noOfPapers=allpapers.getPapers().size();
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

	public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

	public int getNoOfPapers() {
		return noOfPapers;
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
		System.out.println("Conference [cNo="+cNo+" , CName"+CName + ", sDate=" + sDate + ", eDate=" + eDate + ", reviewers=");
		reviewers.display();
		System.out.print( "venue=" + venue + "]");}
	

	
	
	
	
	
	

}
