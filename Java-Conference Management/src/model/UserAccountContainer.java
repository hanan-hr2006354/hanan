
package model;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import app.filesMethods;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserAccountContainer {
	public static List<UserAccount> userAccounts=new ArrayList<>();;//same as authors. it should be available
	public static UserAccount currentLogin;

    public static void userAccountsFromFile(String fileName) {
    	List<UserAccount> accountsFromFile = filesMethods.readFromFile("C:\\Admin\\eclipse-workspace\\user_accounts.txt");
		userAccounts.addAll(accountsFromFile);
    }

  
	public static void addUser(UserAccount user) {
		if (user!=null)
			userAccounts.add(user);
            filesMethods.writeToFile(user);

	}
	
	public static UserAccount find(String username) {
		for (UserAccount user : userAccounts) 
			if(user.getUsername().equals(username)) {
				currentLogin=user;
				return user;}
		
		return null;
	}
	
	public static UserAccount login(String username, String password) {
		UserAccount temp = find(username);
		if (temp!=null && temp.isValid(username, password))
		{
			temp.login();
			return temp;
		}
		return null;
	}
	public static void logout(UserAccount user) {
		if(user.isLogged())
			user.logout();
	}
	
	
}

