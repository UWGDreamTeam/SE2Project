package edu.westga.cs3212.inventory_manager.test.localemployeecredentialsmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.EmployeeType;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentials;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentialsManager;

class TestAddEmployee {

	@BeforeEach
	public void setUp() throws IOException {
		Files.deleteIfExists(Paths.get(Constants.EMPLOYEE_CREDENTIAL_FILE_LOCATION));
	}
	
	@AfterEach
	public void tearDown() throws IOException {
		LocalEmployeeCredentialsManager manager = new LocalEmployeeCredentialsManager();
		manager.addAdminUser();
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
		LocalEmployeeCredentialsManagerForTest manager = new LocalEmployeeCredentialsManagerForTest();
		int employeeSize = manager.getEmployees().size();
		for (LocalEmployeeCredentials currentEmployee : manager
				.getEmployees()) {
			manager.removeEmployee(currentEmployee.getEmployeeID());
		}

		manager.addEmployee("John", "Doe", "Password", EmployeeType.MANAGER.toString());
		manager.addEmployee("Strong", "Type", "Type", EmployeeType.WORKER.toString());

		employeeSize = manager.getEmployees().size();
		assertEquals(2, employeeSize);

	}

	@Test
	public void testAddEmployeeWithInvalidEmployeeType() {
		LocalEmployeeCredentialsManagerForTest manager = new LocalEmployeeCredentialsManagerForTest();

		assertThrows(IllegalArgumentException.class, () -> {
			manager.addEmployee("John", "Doe", "Password", "YAYA");
		});
	}

	private class LocalEmployeeCredentialsManagerForTest extends LocalEmployeeCredentialsManager {
		private int callCount = 0;

		@Override
		public String generateUniqueEmployeeID() {
			if (this.callCount < 2) {
				this.callCount++;
				return "duplicateUUID";
			} else {
				return "uniqueUUID123";
			}
		}
	}
}
