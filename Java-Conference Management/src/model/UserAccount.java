package model;

import java.util.Date;
import java.util.List;

import app.filesMethods;

public class UserAccount {
	private String username="", password="",email=""; 
	private boolean isLogged=false;
	
	public List<Conference> authorSelection;
	
	
	
	public void setEmail(String email) {
		this.email = email;
	}

	public List<Conference> getAuthorSelection() {
		return authorSelection;
	}
	
	public void setAuthorSelection(List<Conference> authorSelection) {
		this.authorSelection = authorSelection;
	}
	
	public void addAuthorSelection(Conference c) {
		authorSelection.add(c);
	}

	public UserAccount(String username, String password, String email) {
		if (UserAccountContainer.find(username)==null) {//check if Account exists
			this.username = username;
			this.password = password;
			this.email=email;
			UserAccountContainer.addUser(this);//automatically added to container

		}
		else
			System.err.println("Error: Username already exists.");
	}

	public String getUsername() {
		return username;
	}
	public String getEmail() {
		return email;
	}
	
	public boolean isLogged() {
		return isLogged;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void logout() {
		final StackTraceElement[] elements = new Throwable().getStackTrace();
		if (elements[1].getClassName().equals("model.UserAccountContainer"))//only the
			this.isLogged = false;
	}

	public void login() {
		final StackTraceElement[] elements = new Throwable().getStackTrace();
		if (elements[1].getClassName().equals("model.UserAccountContainer"))//only the
			this.isLogged = true;
	}

	public void changePassword(String oldPassword, String newPassword) {
		if (this.password.equals(oldPassword))
			this.password = newPassword;
	}
	public boolean isValid(String username, String password) {
	   return this.username.equalsIgnoreCase(username) && this.password.equals(password);
	}

	@Override
	public String toString() {
		return "UserAccount [username=" + username + ", password=" + password + ", email=" + email + ", isLogged="
				+ isLogged + "]";
	}
	
}
