package edu.westga.cs3212.inventory_manager.test.localemployeecredentialsmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.credentials.EmployeeType;
import edu.westga.cs3212.inventory_manager.model.credentials.LocalEmployeeCredentials;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentialsManager;

class TestConstructor {
	
	private LocalEmployeeCredentialsManager manager;
	
	@BeforeEach
	public void setUp() throws IOException {
		Files.deleteIfExists(Paths.get(Constants.EMPLOYEE_CREDENTIAL_FILE_LOCATION));
		this.manager = new LocalEmployeeCredentialsManager();
	}
	
	@Test
    public void testConstructorInitializesMap() {
        assertNotNull(manager.getEmployees());
    }
	
	@Test
	public void testConstructorInitializesMapWithNoEmployees() {
		for (LocalEmployeeCredentials currentEmployee : manager
				.getEmployees()) {
			manager.removeEmployee(currentEmployee.getEmployeeID());
		}
		assertEquals(0, manager.getEmployees().size());
	}
	
	@Test
	public void testConstructorToLoadWithOneEmployeeCredentials() {
		for (LocalEmployeeCredentials currentEmployee : manager
				.getEmployees()) {
			manager.removeEmployee(currentEmployee.getEmployeeID());
		}
		manager.addEmployee("password", "ADMIN", "John", EmployeeType.MANAGER.toString());
		
		LocalEmployeeCredentialsManager newManager = new LocalEmployeeCredentialsManager();
		assertEquals(2, newManager.getEmployees().size());
	}
	
	@Test
	public void testConstructorToLoadWithMultipleEmployeeCredentials() {
		for (LocalEmployeeCredentials currentEmployee : manager.getEmployees()) {
			manager.removeEmployee(currentEmployee.getEmployeeID());
		}
		manager.addEmployee("password", "ADMIN", "John", EmployeeType.MANAGER.toString());
		manager.addEmployee("password", "USER", "Jane", EmployeeType.WORKER.toString());

		LocalEmployeeCredentialsManager newManager = new LocalEmployeeCredentialsManager();
		assertEquals(3, newManager.getEmployees().size());
	}

}