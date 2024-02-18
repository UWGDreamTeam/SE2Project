package edu.westga.cs3212.inventory_manager.test.localemployeecredentialsmanager;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentialsManager;

class TestUpdatePassword {

	@BeforeEach
	public void setUp() throws Exception {
		Files.deleteIfExists(Paths.get(Constants.EMPLOYEE_CREDENTIAL_FILE_LOCATION));
	}
	
	@Test
	void testUpdatePasswordWhenThereAreNoEmployees() {
		LocalEmployeeCredentialsManager testManager = new LocalEmployeeCredentialsManager();
		assertFalse(testManager.updateEmployeePassword("invalidID", "newPassword"));
	}
	
	@Test
    void testUpdatePasswordWhenThereIsOneEmployee() {
        LocalEmployeeCredentialsManager testManager = new LocalEmployeeCredentialsManager();
        testManager.addEmployee("password", "ADMIN", "John", "MANAGER");
        String employeeID = testManager.getEmployees().get(0).getEmployeeID();
        assertTrue(testManager.updateEmployeePassword(employeeID, "newPassword"));
        assertEquals("newPassword", testManager.getEmployeePassword(employeeID));
	}
	
	@Test
	void testUpdatePasswordWhenThereAreMultipleEmployees() {
		LocalEmployeeCredentialsManager testManager = new LocalEmployeeCredentialsManager();
		testManager.addEmployee("password", "ADMIN", "John", "MANAGER");
		testManager.addEmployee("password", "USER", "Jane", "WORKER");
		String employeeID = testManager.getEmployees().get(0).getEmployeeID();
		assertTrue(testManager.updateEmployeePassword(employeeID, "newPassword"));
		assertEquals("newPassword", testManager.getEmployeePassword(employeeID));
	}

}
