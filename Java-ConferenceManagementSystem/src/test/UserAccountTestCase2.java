package test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import model.UserAccount;

class UserAccountTestCase2 {


    private UserAccount userAccount = new UserAccount();

    @Test
    void testUserAccountProperties() {

    }
    
    @Test
    void testGetUsername() {
    	  assertEquals("", userAccount.getUsername());
    }
    
    @Test 
    void testGetPassword() throws Exception{
    	 assertEquals("", userAccount.getPassword());
    }
    
    @Test 
    void testGetEmail() {
    	 assertEquals("", userAccount.getEmail());
    }
}
