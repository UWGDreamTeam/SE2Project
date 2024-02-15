package edu.westga.cs3212.inventory_manager.test.localemployeecredentialsmanager;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.local_impl.EmployeeType;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentialsManager;

public class TestAddEmployee {

	@BeforeEach
	public void setUp() throws Exception {
		Files.deleteIfExists(Paths.get(Constants.EMPLOYEE_CREDENTIAL_FILE_LOCATION));
	}
	
	@Test
	public void testAddEmployeeWithNullFirstName() {
        LocalEmployeeCredentialsManager manager = new LocalEmployeeCredentialsManager();
        assertThrows(IllegalArgumentException.class, () -> {
            manager.addEmployee(null, "Doe", "Password", EmployeeType.MANAGER.toString());
            });
	}
	
	@Test
	public void testAddEmployeeWithNullLastName() {
		LocalEmployeeCredentialsManager manager = new LocalEmployeeCredentialsManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.addEmployee("John", null, "Password", EmployeeType.MANAGER.toString());
		});
	}
	
	@Test
	public void testAddEmployeeWithNullPassword() {
		LocalEmployeeCredentialsManager manager = new LocalEmployeeCredentialsManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.addEmployee("John", "Doe", null, EmployeeType.MANAGER.toString());
		});
	}
	
	@Test
	public void testAddEmployeeWithNullEmployeeType() {
		LocalEmployeeCredentialsManager manager = new LocalEmployeeCredentialsManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.addEmployee("John", "Doe", "Password", null);
		});
	}
	
	@Test
	public void testAddEmployeeWithEmptyFirstName() {
		LocalEmployeeCredentialsManager manager = new LocalEmployeeCredentialsManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.addEmployee("", "Doe", "Password", EmployeeType.MANAGER.toString());
		});
	}
	
	@Test
	public void testAddEmployeeWithEmptyLastName() {
		LocalEmployeeCredentialsManager manager = new LocalEmployeeCredentialsManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.addEmployee("John", "", "Password", EmployeeType.MANAGER.toString());
		});
	}
	
	@Test
	public void testAddEmployeeWithEmptyPassword() {
		LocalEmployeeCredentialsManager manager = new LocalEmployeeCredentialsManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.addEmployee("John", "Doe", "", EmployeeType.MANAGER.toString());
		});
	}
	
	@Test
	public void testAddEmployeeWithEmptyEmployeeType() {
		LocalEmployeeCredentialsManager manager = new LocalEmployeeCredentialsManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.addEmployee("John", "Doe", "Password", "");
		});
	}
	
	@Test
	public void testAddEmployeeWithDuplicateEmployeeID() {
		LocalEmployeeCredentialsManager manager = new LocalEmployeeCredentialsManager();
		manager.addEmployee("John", "Doe", "Password", EmployeeType.MANAGER.toString());
		manager.addEmployee("Jane", "Doe", "Password", EmployeeType.WORKER.toString());
		
		assertThrows(IllegalArgumentException.class, () -> {
			manager.addEmployee("John", "Doe", "Password", EmployeeType.MANAGER.toString());
		});
		
	}
}
