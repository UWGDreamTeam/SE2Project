package edu.westga.cs3212.inventory_manager.test.loginpageviewmodel;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.viewmodel.LoginViewModel;

public class TestConstructor {

	@Test
	void testConstructor() {
		LoginViewModel testLoginViewModel = new LoginViewModel();
		assertNotNull(testLoginViewModel);
		assertNotNull(testLoginViewModel.employeeIDProperty());
		assertNotNull(testLoginViewModel.employeeIDProperty());
	}
}
