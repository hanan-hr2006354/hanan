package model;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;


public class Paper implements Serializable{//for an object to be serialized (to be written to an object stream
	//unique identifier for the version of the class, and it helps with versioning when deserializing objects.
	private static final long serialVersionUID = 1L;
    static Random r=new Random();

	private static int PaperNo = PaperContainer.AllTotalPapers(ConferenceContainer.getUserConference());
//	private static int RandomNo = r.nextInt(100);

    private int PNo;
    private String paperTitle;
    private String abstractPaper;
    private List<UserAccount> authors;
    private String keywords;

    public Paper(String paperTitle, String abstractPaper, List<UserAccount> authors, String keywords) {
        this.PNo = generateUniquePaperNo();
        this.paperTitle = paperTitle;
        this.abstractPaper = abstractPaper;
        this.authors = authors;
        this.keywords = keywords;
    }

    private int generateUniquePaperNo() {
        return ++PaperNo;
//        return RandomNo;
    }
	public Paper() {
		authors= new ArrayList<UserAccount>();
		
	}
	
	public static int getPaperNo() {
		return PaperNo;
	}

	public static void setPaperNo(int paperNo) {
		PaperNo = paperNo;
	}

	public int getPNo() {
		return PNo;
	}


	public void setPNo(int pNo) {
		PNo = pNo;
	}


	public String getPaperTitle() {
		return paperTitle;
	}

	public void setPaperTitle(String paperTitle) {
		this.paperTitle = paperTitle;
	}

	public String getAbstractPaper() {
		return abstractPaper;
	}

	public void setAbstractPaper(String abstactPaper) {
		this.abstractPaper = abstactPaper;
	}

	public List<UserAccount> getAuthors() {
		return authors;
	}

	public void setAuthors(List<UserAccount> authors) {
		this.authors = authors;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}


	@Override
	public String toString() {
		return "Paper [paperNo="+PaperNo+"paperTitle=" + paperTitle + ", abstactPaper=" + abstractPaper + ", authors=" + authors
				+ ", keywords=" + keywords + "]";
	}
	}
