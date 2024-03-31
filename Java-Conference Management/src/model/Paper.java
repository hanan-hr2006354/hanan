package model;

import java.util.List;

public class Paper {
	private String paperTitle;
	private String abstactPaper;
	private List<UserAccount> authors;
	private String keywords;
	
	
	public Paper(List<UserAccount> authors) {
		super();
		this.authors = authors;
	}

	public static int paperNo=0;
	private int PNo=0;
	//should list all authors so user can select who are in and however it selects it gets added to a list.
	public Paper(String paperTitle, String abstactPaper, List<UserAccount> authors, String keywords) {
		this.paperTitle = paperTitle;
		this.abstactPaper = abstactPaper;
		this.authors = authors;
		this.keywords = keywords;
		this.PNo=paperNo	++;}

	
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

	public String getAbstactPaper() {
		return abstactPaper;
	}

	public void setAbstactPaper(String abstactPaper) {
		this.abstactPaper = abstactPaper;
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

	public static int getPaperNo() {
		return paperNo;
	}

	public static void setPaperNo(int paperNo) {
		Paper.paperNo = paperNo;
	}

	@Override
	public String toString() {
		return "Paper [paperTitle=" + paperTitle + ", abstactPaper=" + abstactPaper + ", authors=" + authors
				+ ", keywords=" + keywords + "]";
	}
	}
