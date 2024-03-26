package edu.westga.cs3212.inventory_manager.test.serveremployeecredentialsmanager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.EmployeeType;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentials;
import edu.westga.cs3212.inventory_manager.model.server_impl.EmployeeCredentialsManager;

class TestGetEmployee {

	@Test
	void testGetNullEmployeeID() {
		assertThrows(IllegalArgumentException.class, () -> {
			EmployeeCredentialsManager.getEmployee(null);
		});
	}
	
	@Test
	void testGetEmptyEmployeeID() {
		assertThrows(IllegalArgumentException.class, () -> {
			EmployeeCredentialsManager.getEmployee("");
		});
	}
	
	@Test
	void testGetIdNotFound() {
		EmployeeCredentialsManager.clearCredentials();
		
		LocalEmployeeCredentials employee = new LocalEmployeeCredentials(
        		"testId",
        		"testFirstName",
        		"testLastName",
        		"testPassword",
        		EmployeeType.MANAGER);
		
		EmployeeCredentialsManager.addEmployee(employee);
		
		assertThrows(IllegalArgumentException.class, () -> {
			EmployeeCredentialsManager.getEmployee("anotherId");
		});
	}
	
	@Test
	void getValidEmployee() {
		//ARRANGE
		EmployeeCredentialsManager.clearCredentials();
		
		LocalEmployeeCredentials employee = new LocalEmployeeCredentials(
        		"testId",
        		"testFirstName",
        		"testLastName",
        		"testPassword",
        		EmployeeType.MANAGER);
		EmployeeCredentialsManager.addEmployee(employee);
		
		//ACT
		LocalEmployeeCredentials actualEmployee = EmployeeCredentialsManager.getEmployee("testId");
		
		//ASSERT
		assertAll("Verify Employee Properties",
				  () -> assertEquals("testId", actualEmployee.getEmployeeID()),
				  () -> assertEquals("testFirstName", actualEmployee.getFirstName()),
				  () -> assertEquals("testLastName", actualEmployee.getLastName()),
				  () -> assertEquals("testPassword", actualEmployee.getPassword()),
				  () -> assertEquals(EmployeeType.MANAGER, actualEmployee.getEmployeeType()));
	}

}
