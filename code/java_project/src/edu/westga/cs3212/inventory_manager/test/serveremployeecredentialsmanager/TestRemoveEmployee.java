package edu.westga.cs3212.inventory_manager.test.serveremployeecredentialsmanager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.EmployeeType;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentials;
import edu.westga.cs3212.inventory_manager.model.server_impl.EmployeeCredentialsManager;

class TestRemoveEmployee {

	private LocalEmployeeCredentials employee;
	
    @BeforeEach
    void setUp() {
    	EmployeeCredentialsManager.clearCredentials();
    	
        this.employee = new LocalEmployeeCredentials(
        		"testId",
        		"testFirstName",
        		"testLastName",
        		"testPassword",
        		EmployeeType.MANAGER);
        
        EmployeeCredentialsManager.addEmployee(this.employee);
    }
    
	@Test
	void testRemoveNullEmployeeID() {
		assertThrows(IllegalArgumentException.class, () -> {
			EmployeeCredentialsManager.removeEmployee(null);
		});
	}

	@Test
	void testRemoveEmployeeWithValidCredentials() {
		assertTrue(EmployeeCredentialsManager.removeEmployee("testId"), "Should remove user with specified ID");
	}
	
	@Test
	void testRemoveUserNotFound() {
		assertThrows(IllegalArgumentException.class, () -> {
			EmployeeCredentialsManager.removeEmployee("anotherID");
		});
	}

}
