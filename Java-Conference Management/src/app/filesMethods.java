package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.UserAccount;

public class filesMethods {
    public static void main(String[] args) throws Exception {
        initializeUserAccounts();
    }

    private static void initializeUserAccounts() {
        UserAccount user1 = new UserAccount("Ahmed Ali", "123456", "ahmed12@gmail.com");
        UserAccount user2 = new UserAccount("Fatima Mohammed", "123456", "fatima@gmail.com");
        UserAccount user3 = new UserAccount("Hanan Rashid", "123456", "hanan@gmail.com");
        
  
        }
	  public static void writeToFile(UserAccount user) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter("user_accounts.txt", true))) {
	            writer.write("Name: " + user.getUsername() + ", Password: " + user.getPassword() + ", Email: " + user.getEmail());
	            writer.newLine();
	        } catch (IOException e) {
	            e.printStackTrace(); // Handle this exception according to your application's requirements
	        }
	    }

	  public static List<UserAccount> readFromFile(String fileName) {
	        List<UserAccount> userList = new ArrayList<>();
	        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                // Split the line into parts and create a UserAccount object
	                String[] parts = line.split(", ");
	                String name = parts[0].split(": ")[1];
	                String password = parts[1].split(": ")[1];
	                String email = parts[2].split(": ")[1];

	                UserAccount user = new UserAccount(name, password, email);
	                userList.add(user);
	            }
	        } catch (IOException e) {
	            e.printStackTrace(); // Handle this exception according to your application's requirements
	        }

	        return userList;
	    }
}
