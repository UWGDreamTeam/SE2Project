package edu.westga.cs3212.inventory_manager.test.serveremployeecredentialsmanager;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.EmployeeType;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentials;
import edu.westga.cs3212.inventory_manager.model.server_impl.EmployeeCredentialsManager;

class TestAddEmployee {
	private LocalEmployeeCredentials employee;
	
    @BeforeEach
    void setUp() {
        this.employee = new LocalEmployeeCredentials(
        		"testId",
        		"testFirstName",
        		"testLastName",
        		"testPassword",
        		EmployeeType.MANAGER);
    }
    
	@Test
	void testAddNullEmployee() {
		assertThrows(IllegalArgumentException.class, () -> {
			EmployeeCredentialsManager.addEmployee(null);
		});
	}

	@Test
	void testAddEmployeeWithValidCredentials() {
		EmployeeCredentialsManager.clearCredentials();
		assertEquals("testId", EmployeeCredentialsManager.addEmployee(this.employee), "Ids do not match");
	}
	
	@Test
	void testAddDuplicatedEmployee() {
		EmployeeCredentialsManager.addEmployee(this.employee);
	}

}
