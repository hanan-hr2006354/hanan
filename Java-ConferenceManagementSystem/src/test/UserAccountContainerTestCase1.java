package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.UserAccount;
import model.UserAccountContainer;

class UserAccountContainerTestCase1 {

    private UserAccount userAccount = new UserAccount("newUser", "password123", "newuser@example.com");

    @Test void  UserAccountContainer() throws Exception {
    	testGetFindExistentUser();
    	testGetLogin();
    	testGetLogout();
    }
    
    @Test
    void testGetFindExistentUser() {
        assertEquals("newUser", userAccount.getUsername());
    }
    @Test
    void testGetLogin() throws Exception {
    	 assertEquals("password123", userAccount.getPassword());
    }
	@Test
    void testGetLogout() {
		 assertEquals("newuser@example.com", userAccount.getEmail());
	}
}
