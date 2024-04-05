package edu.westga.cs3212.inventory_manager.test.localemployeecredentials;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.credentials.EmployeeType;
import edu.westga.cs3212.inventory_manager.model.credentials.LocalEmployeeCredentials;

class TestConstructor {

	private final String employeeID = "123456";
	private final String firstName = "John";
	private final String lastName = "Doe";
	private final String password = "password123";
	private final EmployeeType employeeType = EmployeeType.WORKER;

	@Test
	void testConstructorShouldCreateObjectWithValidParameters() {
		LocalEmployeeCredentials credentials = new LocalEmployeeCredentials(this.employeeID, this.firstName,
				this.lastName, this.password, this.employeeType);
		assertNotNull(credentials);
		assertEquals(this.employeeID, credentials.getEmployeeID());
		assertEquals(this.firstName, credentials.getFirstName());
		assertEquals(this.lastName, credentials.getLastName());
		assertEquals("password123", credentials.getPassword());
		assertEquals(this.employeeType, credentials.getEmployeeType());
	}

	@Test
	void testSetFirstNameShouldUpdateFirstName() {
		LocalEmployeeCredentials credentials = new LocalEmployeeCredentials(this.employeeID, this.firstName,
				this.lastName, this.password, this.employeeType);
		credentials.setFirstName("Jane");
		assertEquals("Jane", credentials.getFirstName());
	}

	@Test
	void testSetLastNameShouldUpdateLastName() {
		LocalEmployeeCredentials credentials = new LocalEmployeeCredentials(this.employeeID, this.firstName,
				this.lastName, this.password, this.employeeType);
		credentials.setLastName("Smith");
		assertEquals("Smith", credentials.getLastName());
	}

	@Test
	void testSetPasswordShouldUpdatePassword() {
		LocalEmployeeCredentials credentials = new LocalEmployeeCredentials(this.employeeID, this.firstName,
				this.lastName, this.password, this.employeeType);
		credentials.setPassword("newpassword456");
		assertEquals("newpassword456", credentials.getPassword());
	}

	@Test
	void testSetEmployeeTypeShouldUpdateEmployeeType() {
		LocalEmployeeCredentials credentials = new LocalEmployeeCredentials(this.employeeID, this.firstName,
				this.lastName, this.password, this.employeeType);
		credentials.setEmployeeType(EmployeeType.MANAGER);
		assertEquals(EmployeeType.MANAGER, credentials.getEmployeeType());
	}

	@Test
	void testConstructorShouldThrowExceptionIfEmployeeIDIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new LocalEmployeeCredentials(null, this.firstName, this.lastName, this.password, this.employeeType);
		});
	}

	@Test
	void testConstructorShouldThrowExceptionIfEmployeeIDIsEmpty() {
		assertThrows(IllegalArgumentException.class, () -> {
			new LocalEmployeeCredentials("", this.firstName, this.lastName, this.password, this.employeeType);
		});
	}

	@Test
	void testConstructorShouldThrowExceptionIfFirstNameIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new LocalEmployeeCredentials(this.employeeID, null, this.lastName, this.password, this.employeeType);
		});
	}

	@Test
	void testConstructorShouldThrowExceptionIfLastNameIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new LocalEmployeeCredentials(this.employeeID, this.firstName, null, this.password, this.employeeType);
		});
	}

	@Test
	void testConstructorShouldThrowExceptionIfPasswordIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new LocalEmployeeCredentials(this.employeeID, this.firstName, this.lastName, null, this.employeeType);
		});
	}

	@Test
	void testConstructorShouldThrowExceptionIfEmployeeTypeIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new LocalEmployeeCredentials(this.employeeID, this.firstName, this.lastName, this.password, null);
		});
	}

	@Test
	void testConstructorShouldThrowExceptionIfFirstNameIsEmpty() {
		assertThrows(IllegalArgumentException.class, () -> {
			new LocalEmployeeCredentials(this.employeeID, "", this.lastName, this.password, this.employeeType);
		});
	}

	@Test
	void testConstructorShouldThrowExceptionIfLastNameIsEmpty() {
		assertThrows(IllegalArgumentException.class, () -> {
			new LocalEmployeeCredentials(this.employeeID, this.firstName, "", this.password, this.employeeType);
		});
	}

	@Test
	void testConstructorShouldThrowExceptionIfPasswordIsEmpty() {
		assertThrows(IllegalArgumentException.class, () -> {
			new LocalEmployeeCredentials(this.employeeID, this.firstName, this.lastName, "", this.employeeType);
		});
	}

	@Test
	void testConstructorShouldThrowExceptionIfFirstNameIsWhitespace() {
		assertThrows(IllegalArgumentException.class, () -> {
			new LocalEmployeeCredentials(this.employeeID, "   ", this.lastName, this.password, this.employeeType);
		});
	}

	@Test
	void testConstructorShouldThrowExceptionIfLastNameIsWhitespace() {
		assertThrows(IllegalArgumentException.class, () -> {
			new LocalEmployeeCredentials(this.employeeID, this.firstName, "   ", this.password, this.employeeType);
		});
	}

	@Test
	void testConstructorShouldThrowExceptionIfPasswordIsWhitespace() {
		assertThrows(IllegalArgumentException.class, () -> {
			new LocalEmployeeCredentials(this.employeeID, this.firstName, this.lastName, "   ", this.employeeType);
		});
	}

}
