package edu.westga.cs3212.inventory_manager.test.serveremployeecredentialsmanager;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.EmployeeType;
import edu.westga.cs3212.inventory_manager.model.server_impl.EmployeeCredentialsManager;

class TestAddEmployee {
	private String firstName;
	private String lastName;
	private String password;
	private EmployeeType role;
	
    @BeforeEach
    void setUp() {
        this.firstName = "John";
        this.lastName = "Doe";
        this.password = "password";
        this.role = EmployeeType.MANAGER;
    }
    
	@Test
	void testAddNullFirstName() {
		assertThrows(IllegalArgumentException.class, () -> {
			EmployeeCredentialsManager.addEmployee(null, this.lastName, this.password, this.role);
		});
	}

	@Test
	void testAddEmployeeWithValidCredentials() {
		EmployeeCredentialsManager.clearCredentials();
		assertEquals("jd0001", EmployeeCredentialsManager.addEmployee(this.firstName, this.lastName, this.password, this.role), "Ids do not match");
	}
	
	@Test
	void testAddSecondEmployeeWithSameInitials() {
		EmployeeCredentialsManager.clearCredentials();
		
		EmployeeCredentialsManager.addEmployee(this.firstName, this.lastName, this.password, this.role);
		assertEquals("jd0002", EmployeeCredentialsManager.addEmployee(this.firstName, this.lastName, this.password, this.role), "Ids do not match");
	}
	
	@Test
	void testAddThirdEmployeeWithSameInitials() {
		EmployeeCredentialsManager.clearCredentials();
		
		EmployeeCredentialsManager.addEmployee(this.firstName, this.lastName, this.password, this.role);
		EmployeeCredentialsManager.addEmployee(this.firstName, this.lastName, this.password, this.role);
		assertEquals("jd0003", EmployeeCredentialsManager.addEmployee("Joao", "Donato", this.password, this.role), "Ids do not match");
	}
}
