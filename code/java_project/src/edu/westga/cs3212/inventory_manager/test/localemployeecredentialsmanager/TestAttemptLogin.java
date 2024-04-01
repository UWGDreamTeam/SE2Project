package edu.westga.cs3212.inventory_manager.test.localemployeecredentialsmanager;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentials;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentialsManager;

class TestAttemptLogin {

	@BeforeEach
	public void setUp() throws IOException {
		Files.deleteIfExists(Paths.get(Constants.EMPLOYEE_CREDENTIAL_FILE_LOCATION));
	}
	
	@AfterEach
	public void tearDown() throws IOException {
		LocalEmployeeCredentialsManager manager = new LocalEmployeeCredentialsManager();
	}
	
	@Test
	void testAttemptLoginWhenThereAreNoEmployees() {
		LocalEmployeeCredentialsManager testManager = new LocalEmployeeCredentialsManager();
		assertFalse(testManager.attemptLogin("invalidID", "password"));
	}
	
	@Test
	void testAttemptLoginWhenThereIsOneEmployee() {
        LocalEmployeeCredentialsManager testManager = new LocalEmployeeCredentialsManager();
		for (LocalEmployeeCredentials currentEmployee : testManager
				.getEmployees()) {
			testManager.removeEmployee(currentEmployee.getEmployeeID());
		}
        testManager.addEmployee("password", "ADMIN", "John", "MANAGER");
        int employeeSize = testManager.getEmployees().size();
        String employeeID = testManager.getEmployees().get(employeeSize - 1).getEmployeeID();
        assertTrue(testManager.attemptLogin(employeeID, "John"));
	}
	
	@Test
	void testAttemptLoginWhenThereAreMultipleEmployees() {
        LocalEmployeeCredentialsManager testManager = new LocalEmployeeCredentialsManager();
        testManager.addEmployee("password", "ADMIN", "John", "MANAGER");
        testManager.addEmployee("password", "USER", "Jane", "WORKER");
        String employeeID = testManager.getEmployees().get(0).getEmployeeID();
        String employeePassword = testManager.getEmployees().get(0).getPassword();
        assertTrue(testManager.attemptLogin(employeeID, employeePassword));
        
	}

}
