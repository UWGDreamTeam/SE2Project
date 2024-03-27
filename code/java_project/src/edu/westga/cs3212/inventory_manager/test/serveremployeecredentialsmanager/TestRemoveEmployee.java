package edu.westga.cs3212.inventory_manager.test.serveremployeecredentialsmanager;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.EmployeeType;
import edu.westga.cs3212.inventory_manager.model.server_impl.EmployeeCredentialsManager;

class TestRemoveEmployee {

    @BeforeEach
    void setUp() {
    	EmployeeCredentialsManager.clearCredentials();

        EmployeeCredentialsManager.addEmployee("testFirstName",
        		"testLastName",
        		"testPassword",
        		EmployeeType.MANAGER);
    }
    
	@Test
	void testRemoveNullEmployeeID() {
		assertThrows(IllegalArgumentException.class, () -> {
			EmployeeCredentialsManager.removeEmployee(null);
		});
	}

	@Test
	void testRemoveEmployeeWithValidCredentials() {
		assertTrue(EmployeeCredentialsManager.removeEmployee("tt0001"), "Should remove user with specified ID");
	}
	
	@Test
	void testRemoveUserNotFound() {
		assertThrows(IllegalArgumentException.class, () -> {
			EmployeeCredentialsManager.removeEmployee("tt0000");
		});
	}

}
