package model;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import app.filesMethods;

public class UserAccount implements Serializable { //for an object to be serialized (to be written to an object stream
	//unique identifier for the version of the class, and it helps with versioning when deserializing objects.
	private static final long serialVersionUID = 1L;

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
			try {
				this.password = encrypt(password,"Hello");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.email=email;
		}
		else
			System.err.println("Error: Username already exists.");
	}

	public UserAccount() {
		// TODO Auto-generated constructor stub
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

	public String getPassword() throws Exception {
		return decrypt(password,"Hello");
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


public static String encrypt(String strClearText,String strKey) throws Exception{
	String strData="";
	
	try {
		SecretKeySpec skeyspec=new SecretKeySpec(strKey.getBytes(),"Blowfish");
		Cipher cipher=Cipher.getInstance("Blowfish");
		cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
		byte[] encrypted=cipher.doFinal(strClearText.getBytes());
		strData=new String(encrypted);
		
	} catch (Exception e) {
		e.printStackTrace();
		throw new Exception(e);
	}
	return strData;
  }
	public static String decrypt(String strEncrypted,String strKey) throws Exception{
		String strData="";
		try {
			SecretKeySpec skeyspec=new SecretKeySpec(strKey.getBytes(),"Blowfish");
			Cipher cipher=Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE, skeyspec);
			byte[] decrypted=cipher.doFinal(strEncrypted.getBytes());
			strData=new String(decrypted);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return strData;
	}
	@Override
	public String toString() {
		return "UserAccount [username=" + username + ", password=" + password + ", email=" + email + ", isLogged="
				+ isLogged + "]";
	}
	
}
