package model;

import java.util.ArrayList;
import java.util.List;

public class PaperContainer {
	private List<Paper> listPaper = new ArrayList<Paper>();//not static cause it differentiates


	public Boolean maximum3Papers() {
		if(listPaper.size()==3) {
			return true;
		}return false;
	}
	public List<Paper> getPapers() {
		return listPaper;
	}

	public void add(Paper r) {
		if(maximum3Papers()==false) {
		if (r!=null)
			listPaper.add(r);}
	}
	
	public void remove(Paper r) {
		if (r!=null)
			listPaper.remove(r);}
	public void display() {
		for(Paper p: listPaper) {
			System.out.println(p.toString());
		}
	}}
