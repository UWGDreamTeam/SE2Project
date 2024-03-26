package edu.westga.cs3212.inventory_manager.test.serveremployeecredentialsmanager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.EmployeeType;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentials;
import edu.westga.cs3212.inventory_manager.model.server_impl.EmployeeCredentialsManager;

class TestAttemptLogin {

	@Test
	void testValidLogin() {
		
		LocalEmployeeCredentials employee = new LocalEmployeeCredentials("admin", "testFirstName", "testLastName", "testPassword", EmployeeType.MANAGER);
		EmployeeCredentialsManager.addEmployee(employee);
		
		assertTrue(EmployeeCredentialsManager.attemptLogin(employee.getEmployeeID(), employee.getPassword()));
		
	}
	
	@Test
	void testInvalidPassword() {
		EmployeeCredentialsManager.clearCredentials();
		
		LocalEmployeeCredentials employee = new LocalEmployeeCredentials("admin", "testFirstName", "testLastName", "testPassword", EmployeeType.MANAGER);
		EmployeeCredentialsManager.addEmployee(employee);
		
		assertThrows(IllegalArgumentException.class, () -> {
			EmployeeCredentialsManager.attemptLogin(employee.getEmployeeID(), "testLastname");
		});
	}
	
	@Test
	void testInvaliID() {
		EmployeeCredentialsManager.clearCredentials();
		
		LocalEmployeeCredentials employee = new LocalEmployeeCredentials("admin", "testFirstName", "testLastName", "testPassword", EmployeeType.MANAGER);
		EmployeeCredentialsManager.addEmployee(employee);
		
		assertThrows(IllegalArgumentException.class, () -> {
			EmployeeCredentialsManager.attemptLogin("Admin", employee.getPassword());
		});
	}

}
