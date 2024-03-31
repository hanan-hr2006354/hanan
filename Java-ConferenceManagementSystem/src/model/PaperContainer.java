package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PaperContainer implements Serializable{//for an object to be serialized (to be written to an object stream
	//unique identifier for the version of the class, and it helps with versioning when deserializing objects.
	private static final long serialVersionUID = 1L;
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
	public static int AllTotalPapers(List<Conference> con) {
		int total=0;
		for(Conference c: con) {
			for(Paper p:c.getAllpapers().getPapers()) {
				total++;
			}
		}return total;
	}
	
	
	public void remove(Paper r) {
		if (r!=null)
			listPaper.remove(r);}
	public void display() {
		for(Paper p: listPaper) {
			System.out.println(p.toString());
		}
	}}
