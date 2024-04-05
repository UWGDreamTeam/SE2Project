package edu.westga.cs3212.inventory_manager.test.serveremployeecredentialsmanager;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.credentials.EmployeeType;
import edu.westga.cs3212.inventory_manager.model.server.credentials.EmployeeCredentialsManager;

class TestAttemptLogin {
	
	@BeforeEach
    void setUp() {
        EmployeeCredentialsManager.clearCredentials();
    }

	@Test
	void testValidLogin() {
		
		EmployeeCredentialsManager.addEmployee("testFirstName",
        		"testLastName",
        		"testPassword",
        		EmployeeType.MANAGER);
		
		assertTrue(EmployeeCredentialsManager.attemptLogin("tt0001", "testPassword"));
		
	}
	
	@Test
	void testInvalidPassword() {
		EmployeeCredentialsManager.addEmployee("testFirstName",
        		"testLastName",
        		"testPassword",
        		EmployeeType.MANAGER);
		
		assertThrows(IllegalArgumentException.class, () -> {
			EmployeeCredentialsManager.attemptLogin("tt0001", "testLastname");
		});
	}
	
	@Test
	void testInvaliID() {
		EmployeeCredentialsManager.addEmployee("testFirstName",
        		"testLastName",
        		"testPassword",
        		EmployeeType.MANAGER);
		
		assertThrows(IllegalArgumentException.class, () -> {
			EmployeeCredentialsManager.attemptLogin("Admin", "testLastname");
		});
	}

}
