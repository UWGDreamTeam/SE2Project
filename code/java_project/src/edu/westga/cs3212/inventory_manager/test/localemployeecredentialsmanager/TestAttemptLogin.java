package edu.westga.cs3212.inventory_manager.test.localemployeecredentialsmanager;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentialsManager;

class TestAttemptLogin {

	@BeforeEach
	public void setUp() throws Exception {
		Files.deleteIfExists(Paths.get(Constants.EMPLOYEE_CREDENTIAL_FILE_LOCATION));
	}
	
	@Test
	void testAttemptLoginWhenThereAreNoEmployees() {
		LocalEmployeeCredentialsManager testManager = new LocalEmployeeCredentialsManager();
		assertFalse(testManager.attemptLogin("invalidID", "password"));
	}
	
	@Test
	void testAttemptLoginWhenThereIsOneEmployee() {
        LocalEmployeeCredentialsManager testManager = new LocalEmployeeCredentialsManager();
        testManager.addEmployee("password", "ADMIN", "John", "MANAGER");
        String employeeID = testManager.getEmployees().get(0).getEmployeeID();
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
