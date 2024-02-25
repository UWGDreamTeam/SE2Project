package edu.westga.cs3212.inventory_manager.test.localemployeecredentialsmanager;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentialsManager;

class TestRemoveEmployee {

	@BeforeEach
	public void setUp() throws IOException {
		Files.deleteIfExists(Paths.get(Constants.EMPLOYEE_CREDENTIAL_FILE_LOCATION));
	}
	
	@Test
	void testRemoveEmployeeWhenThereAreNoEmployees() {
		LocalEmployeeCredentialsManager testManager = new LocalEmployeeCredentialsManager();
		assertFalse(testManager.removeEmployee("invalidID"));
	}
	
	@Test
	void testRemoveEmployeeWhenThereIsOneEmployee() {
        LocalEmployeeCredentialsManager testManager = new LocalEmployeeCredentialsManager();
        testManager.addEmployee("password", "ADMIN", "John", "MANAGER");
        String employeeID = testManager.getEmployees().get(0).getEmployeeID();
        assertTrue(testManager.removeEmployee(employeeID));
	}
	
	@Test
	void testRemoveEmployeeWhenThereAreMultipleEmployees() {
		LocalEmployeeCredentialsManager testManager = new LocalEmployeeCredentialsManager();
		testManager.addEmployee("password", "ADMIN", "John", "MANAGER");
		testManager.addEmployee("password", "USER", "Jane", "WORKER");
		String employeeID = testManager.getEmployees().get(0).getEmployeeID();
		assertTrue(testManager.removeEmployee(employeeID));
	}

}
