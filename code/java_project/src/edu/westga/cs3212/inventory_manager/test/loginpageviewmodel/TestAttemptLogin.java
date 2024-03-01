package edu.westga.cs3212.inventory_manager.test.loginpageviewmodel;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.EmployeeType;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentialsManager;
import edu.westga.cs3212.inventory_manager.viewmodel.LoginViewModel;

public class TestAttemptLogin {

	@Test
	void testAttemptLoginWithValidCredentials() {
		LoginViewModel testLoginViewModel = new LoginViewModel();
		LocalEmployeeCredentialsManager manager = new LocalEmployeeCredentialsManager();
		manager.addEmployee("Jason", "Nunez", "Password", EmployeeType.MANAGER.toString());
		String employeeID = manager.getEmployees().get(0).getEmployeeID();
		String password = manager.getEmployees().get(0).getPassword();
		testLoginViewModel.employeeIDProperty().set(employeeID);
		testLoginViewModel.passwordProperty().set(password);
		assertTrue(testLoginViewModel.attemptLogin());
	}
	
	@Test
	void testAttemptLoginWithInvalidCredentials() {
		LoginViewModel testLoginViewModel = new LoginViewModel();
		LocalEmployeeCredentialsManager manager = new LocalEmployeeCredentialsManager();
		manager.addEmployee("Jason", "Nunez", "Password", EmployeeType.MANAGER.toString());
		String password = "InvalidPassword";
		testLoginViewModel.employeeIDProperty().set("");
		testLoginViewModel.passwordProperty().set(password);
		assertTrue(!testLoginViewModel.attemptLogin());
	}
}
