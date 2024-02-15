package edu.westga.cs3212.inventory_manager.test.localemployeecredentialsmanager;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.EmployeeType;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentialsManager;

class TestConstructor {

	private final Path credentialsFilePath = Paths.get("employeeCredentials.json");
	
	@BeforeEach
	public void setUp() throws Exception {
		Files.deleteIfExists(this.credentialsFilePath);
	}

	
	@Test
    public void testConstructorInitializesMap() {
        LocalEmployeeCredentialsManager manager = new LocalEmployeeCredentialsManager();
        assertNotNull(manager.getEmployees());
    }
	
	@Test
	public void testConstructorInitializesMapWithNoEmployees() {
		LocalEmployeeCredentialsManager manager = new LocalEmployeeCredentialsManager();
		assertEquals(0, manager.getEmployees().size());
	}
	
	@Test
	public void testConstructorToLoadWithOneEmployeeCredentials() {
		LocalEmployeeCredentialsManager manager = new LocalEmployeeCredentialsManager();
		manager.addEmployee("password", "ADMIN", "John", EmployeeType.MANAGER.toString());
		
		LocalEmployeeCredentialsManager newManager = new LocalEmployeeCredentialsManager();
		assertEquals(1, newManager.getEmployees().size());
	}
	
	@Test
	public void testConstructorToLoadWithMultipleEmployeeCredentials() {
		LocalEmployeeCredentialsManager manager = new LocalEmployeeCredentialsManager();
		manager.addEmployee("password", "ADMIN", "John", EmployeeType.MANAGER.toString());
		manager.addEmployee("password", "USER", "Jane", EmployeeType.WORKER.toString());

		LocalEmployeeCredentialsManager newManager = new LocalEmployeeCredentialsManager();
		assertEquals(2, newManager.getEmployees().size());
	}

}
