package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.UserAccount;

class UserAccountTestCase1 {

    private UserAccount userAccount = new UserAccount("username", "password", "email@example.com");

    @Test
    void testUserAccountProperties() throws Exception {
    	testGetUsername();
    	testGetPassword();
    	testGetEmail();
    }
    
    @Test
    void testGetUsername() {
    	  assertEquals("username", userAccount.getUsername());
    }
    
    @Test 
    void testGetPassword() throws Exception{
    	 assertEquals("password", userAccount.getPassword());
    }
    
    @Test 
    void testGetEmail() {
    	 assertEquals("email@example.com", userAccount.getEmail());
    }
}
