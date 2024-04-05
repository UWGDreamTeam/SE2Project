package edu.westga.cs3212.inventory_manager.test.loginpageviewmodel;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.credentials.EmployeeType;
import edu.westga.cs3212.inventory_manager.model.server.credentials.EmployeeCredentialsManager;
import edu.westga.cs3212.inventory_manager.viewmodel.login.LoginViewModel;

public class TestAttemptLogin {
	
	@BeforeEach
	void setup() {
		EmployeeCredentialsManager.clearCredentials();
	}

	@Test
	void testAttemptLoginWithValidCredentials() {
		LoginViewModel testLoginViewModel = new LoginViewModel();
		
		EmployeeCredentialsManager.addEmployee("Jason", "Nunez", "Password", EmployeeType.MANAGER);
		
		String employeeID = "jn0001";
		String password = "Password";
		
		testLoginViewModel.employeeIDProperty().set(employeeID);
		testLoginViewModel.passwordProperty().set(password);
		
		assertTrue(testLoginViewModel.attemptLogin());
	}
	
	@Test
	void testAttemptLoginWithInvalidPassword() {
		LoginViewModel testLoginViewModel = new LoginViewModel();
		
		EmployeeCredentialsManager.addEmployee("Jason", "Nunez", "Password", EmployeeType.MANAGER);
		String password = "InvalidPassword";
		
		testLoginViewModel.employeeIDProperty().set("jn0001");
		testLoginViewModel.passwordProperty().set(password);
		
		assertFalse(testLoginViewModel.attemptLogin());
	}
	
	@Test
	void testAttemptLoginWithInvalidID() {
		LoginViewModel testLoginViewModel = new LoginViewModel();
		
		EmployeeCredentialsManager.addEmployee("Jason", "Nunez", "Password", EmployeeType.MANAGER);
		
		testLoginViewModel.employeeIDProperty().set("jj0000");
		testLoginViewModel.passwordProperty().set("Password");
		
		assertFalse(testLoginViewModel.attemptLogin());
	}
}
