package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.UserAccount;
import model.UserAccountContainer;

class UserAccountContainerTestCase2 {

    private UserAccount userAccount = new UserAccount();

    @Test void  UserAccountContainer() {
    }
    
    @Test
    void testGetFindExistentUser() {
        assertEquals("", userAccount.getUsername());
    }
    @Test
    void testGetLogin() throws Exception {
    	 assertEquals("", userAccount.getPassword());
    }
	@Test
    void testGetLogout() {
		 assertEquals("", userAccount.getEmail());
	}
}