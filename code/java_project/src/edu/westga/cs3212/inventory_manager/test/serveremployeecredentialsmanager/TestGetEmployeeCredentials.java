package edu.westga.cs3212.inventory_manager.test.serveremployeecredentialsmanager;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.EmployeeType;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentials;
import edu.westga.cs3212.inventory_manager.model.server_impl.EmployeeCredentialsManager;

class TestGetEmployeeCredentials {

	@Test
	void testGetNullEmployeeID() {
		assertThrows(IllegalArgumentException.class, () -> {
			EmployeeCredentialsManager.getEmployeeCredentials(null);
		});
	}
	
	@Test
	void testGetEmptyEmployeeID() {
		assertThrows(IllegalArgumentException.class, () -> {
			EmployeeCredentialsManager.getEmployeeCredentials("");
		});
	}
	
	@Test
	void testGetIdNotFound() {
		EmployeeCredentialsManager.clearCredentials();
		
		EmployeeCredentialsManager.addEmployee("testFirstName",
        		"testLastName",
        		"testPassword",
        		EmployeeType.MANAGER);
		
		assertThrows(IllegalArgumentException.class, () -> {
			EmployeeCredentialsManager.getEmployeeCredentials("anotherId");
		});
	}
	
	@Test
	void getValidEmployee() {
		//ARRANGE
		EmployeeCredentialsManager.clearCredentials();
		
		EmployeeCredentialsManager.addEmployee("testFirstName",
        		"testLastName",
        		"testPassword",
        		EmployeeType.MANAGER);
		
		//ACT
		LocalEmployeeCredentials actualEmployee = EmployeeCredentialsManager.getEmployeeCredentials("tt0001");
		
		//ASSERT
		assertAll("Verify Employee Properties",
				  () -> assertEquals("tt0001", actualEmployee.getEmployeeID()),
				  () -> assertEquals("testFirstName", actualEmployee.getFirstName()),
				  () -> assertEquals("testLastName", actualEmployee.getLastName()),
				  () -> assertEquals("testPassword", actualEmployee.getPassword()),
				  () -> assertEquals(EmployeeType.MANAGER, actualEmployee.getEmployeeType()));
	}

}
