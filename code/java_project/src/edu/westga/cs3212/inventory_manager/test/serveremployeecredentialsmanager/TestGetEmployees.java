package edu.westga.cs3212.inventory_manager.test.serveremployeecredentialsmanager;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.credentials.EmployeeType;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentials;
import edu.westga.cs3212.inventory_manager.model.server.credentials.EmployeeCredentialsManager;

class TestGetEmployees {
	
	@BeforeEach
    void setUp() {
        EmployeeCredentialsManager.clearCredentials();
    }
	
	@Test
	void testGetEmployeesEmptyList() {
		assertThrows(IllegalArgumentException.class, () -> {
			EmployeeCredentialsManager.getEmployees();
		});
	}

	@Test
	void testGetEmployeesWithOneEmployee() {
		//ARRANGE
		EmployeeCredentialsManager.addEmployee("testFirstName",
        		"testLastName",
        		"testPassword",
        		EmployeeType.MANAGER);
		
		//ACT
		List<LocalEmployeeCredentials> employeesList = EmployeeCredentialsManager.getEmployees(); 
		LocalEmployeeCredentials addedEmployee = employeesList.get(0);
		
		//ASSERT
		assertEquals(1, employeesList.size());
		assertAll("Verify Employee Properties",
				  () -> assertEquals("tt0001", addedEmployee.getEmployeeID()),
				  () -> assertEquals("testFirstName", addedEmployee.getFirstName()),
				  () -> assertEquals("testLastName", addedEmployee.getLastName()),
				  () -> assertEquals("testPassword", addedEmployee.getPassword()),
				  () -> assertEquals(EmployeeType.MANAGER, addedEmployee.getEmployeeType()));
	}
	
	@Test
	void testGetEmployeesWithMultiple() {
		//ARRANGE
		for (int quantity = 1; quantity < 4; quantity++) {
			
			EmployeeType role = quantity % 2 == 0 ?  EmployeeType.MANAGER : EmployeeType.WORKER;
			
			EmployeeCredentialsManager.addEmployee("testFirstName" + quantity,
	        		"testLastName" + quantity,
	        		"testPassword" + quantity,
	        		role);
		}
		
		//ACT
		List<LocalEmployeeCredentials> employeesList = EmployeeCredentialsManager.getEmployees(); 
		LocalEmployeeCredentials employee1 = employeesList.get(0);
		LocalEmployeeCredentials employee2 = employeesList.get(1);
		LocalEmployeeCredentials employee3 = employeesList.get(2);
		
		//ASSERT
		assertEquals(3, employeesList.size());
		
		assertAll("Employee 1",
				  () -> assertEquals("tt0001", employee1.getEmployeeID()),
				  () -> assertEquals("testFirstName1", employee1.getFirstName()),
				  () -> assertEquals("testLastName1", employee1.getLastName()),
				  () -> assertEquals("testPassword1", employee1.getPassword()),
				  () -> assertEquals(EmployeeType.WORKER, employee1.getEmployeeType()));
		
		assertAll("Employee 2",
				  () -> assertEquals("tt0002", employee2.getEmployeeID()),
				  () -> assertEquals("testFirstName2", employee2.getFirstName()),
				  () -> assertEquals("testLastName2", employee2.getLastName()),
				  () -> assertEquals("testPassword2", employee2.getPassword()),
				  () -> assertEquals(EmployeeType.MANAGER, employee2.getEmployeeType()));
		
		assertAll("Employee 3",
				  () -> assertEquals("tt0003", employee3.getEmployeeID()),
				  () -> assertEquals("testFirstName3", employee3.getFirstName()),
				  () -> assertEquals("testLastName3", employee3.getLastName()),
				  () -> assertEquals("testPassword3", employee3.getPassword()),
				  () -> assertEquals(EmployeeType.WORKER, employee3.getEmployeeType()));
	}

}
