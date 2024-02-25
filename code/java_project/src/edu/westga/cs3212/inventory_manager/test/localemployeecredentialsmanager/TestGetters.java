package edu.westga.cs3212.inventory_manager.test.localemployeecredentialsmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.EmployeeType;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentials;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentialsManager;

class TestGetters {

	@BeforeEach
	public void setUp() throws IOException {
		Files.deleteIfExists(Paths.get(Constants.EMPLOYEE_CREDENTIAL_FILE_LOCATION));
	}
	
	
	@Test
	void testGetEmployeeCredentialsWhenTheresValidEmployeeID() {
		LocalEmployeeCredentialsManager testManager = new LocalEmployeeCredentialsManager();
		testManager.addEmployee("password", "ADMIN", "John", EmployeeType.MANAGER.toString());
		String employeeID = testManager.getEmployees().get(0).getEmployeeID();
		assertEquals(employeeID, testManager.getEmployeeCredentials(employeeID).getEmployeeID());
	}
	
	@Test
	void testGetEmployeeCredentialsWhenTheresInvalidEmployeeID() {
		LocalEmployeeCredentialsManager testManager = new LocalEmployeeCredentialsManager();
		testManager.addEmployee("password", "ADMIN", "John", EmployeeType.MANAGER.toString());
		assertNull(testManager.getEmployeeCredentials("invalidID"));
	}
	
	@Test
	void testGetEmployeeCredentialsWhenTheresMultipleEmployees() {
		LocalEmployeeCredentialsManager testManager = new LocalEmployeeCredentialsManager();
		testManager.addEmployee("password", "ADMIN", "John", EmployeeType.MANAGER.toString());
		testManager.addEmployee("password", "USER", "Jane", EmployeeType.WORKER.toString());
		String employeeID = testManager.getEmployees().get(0).getEmployeeID();
		assertEquals(employeeID, testManager.getEmployeeCredentials(employeeID).getEmployeeID());
	}
	
	@Test
	void testGetEmployeeCredentialsWhenTheresNoEmployees() {
		LocalEmployeeCredentialsManager testManager = new LocalEmployeeCredentialsManager();
		assertNull(testManager.getEmployeeCredentials("invalidID"));
	}
	
	@Test
	void testGetEmployeePasswordWhereTheresNoEmployee() {
		LocalEmployeeCredentialsManager testManager = new LocalEmployeeCredentialsManager();
		assertNull(testManager.getEmployeePassword("invalidID"));
	}
	
	@Test
	void testGetEmployeePasswordWhereTheresValidEmployee() {
		LocalEmployeeCredentialsManager testManager = new LocalEmployeeCredentialsManager();
		testManager.addEmployee("password", "ADMIN", "John", EmployeeType.MANAGER.toString());
		String employeeID = testManager.getEmployees().get(0).getEmployeeID();
		assertEquals("John", testManager.getEmployeePassword(employeeID));
	}
	
	@Test
	void testGetEmployeePasswordWhereTheresMultipleEmployees() {
		LocalEmployeeCredentialsManager testManager = new LocalEmployeeCredentialsManager();
		testManager.addEmployee("password", "ADMIN", "John", EmployeeType.MANAGER.toString());
		testManager.addEmployee("password", "USER", "Jane", EmployeeType.WORKER.toString());
		LocalEmployeeCredentials employee = testManager.getEmployees().get(0);
		String employeeID = employee.getEmployeeID();
		String employeePassword = employee.getPassword();
		assertEquals(employeePassword, testManager.getEmployeePassword(employeeID));
	}
	
	@Test
	void testGetEmployeePasswordWhenEmployeeIDIsNull() {
		LocalEmployeeCredentialsManager testManager = new LocalEmployeeCredentialsManager();
		assertThrows(IllegalArgumentException.class, () -> testManager.getEmployeePassword(null));
	}
	
	@Test
	void testGetEmployeePasswordWhenEmployeeIDIsEmpty() {
		LocalEmployeeCredentialsManager testManager = new LocalEmployeeCredentialsManager();
		assertThrows(IllegalArgumentException.class, () -> testManager.getEmployeePassword(""));
	}

}
