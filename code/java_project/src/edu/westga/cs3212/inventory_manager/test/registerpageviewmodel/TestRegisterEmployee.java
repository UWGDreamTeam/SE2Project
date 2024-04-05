package edu.westga.cs3212.inventory_manager.test.registerpageviewmodel;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.viewmodel.admin.RegisterViewModel;

class TestRegisterEmployee {

	private RegisterViewModel viewModel;

	@BeforeEach
	void setUp() {
		this.viewModel = new RegisterViewModel();
	}

	@Test
	void testRegisterEmployeeWithPasswordMismatchThrowsException() {
		this.viewModel.passwordProperty().set("password");
		this.viewModel.confirmPasswordProperty().set("differentPassword");
		Exception exception = assertThrows(IllegalArgumentException.class, this.viewModel::registerEmployee);
		assertEquals("Error: Passwords do not match.", exception.getMessage());
	}

	@Test
	void testRegisterEmployeeWithMatchingPasswords() {
		this.viewModel.firstNameProperty().set("John");
		this.viewModel.lastNameProperty().set("Doe");
		this.viewModel.passwordProperty().set("password");
		this.viewModel.confirmPasswordProperty().set("password");
		this.viewModel.employeeTypeProperty().set("Manager");
	}
	
	@Test
	void testRegisterEmployeeWithMatchingPasswordsAndValidData() {
	    this.viewModel.firstNameProperty().set("John");
	    this.viewModel.lastNameProperty().set("Doe");
	    this.viewModel.passwordProperty().set("password");
	    this.viewModel.confirmPasswordProperty().set("password");
	    this.viewModel.employeeTypeProperty().set("Manager");
	    
	    assertDoesNotThrow(() -> this.viewModel.registerEmployee());
	}
	
	@Test
	void testRegisterEmployeeWithInvalidPasswordThrowsException() {
	    this.viewModel.firstNameProperty().set("John");
	    this.viewModel.lastNameProperty().set("Doe");
	    this.viewModel.passwordProperty().set("");
	    this.viewModel.confirmPasswordProperty().set("");
	    this.viewModel.employeeTypeProperty().set("Manager");

	    Exception exception = assertThrows(IllegalArgumentException.class, this.viewModel::registerEmployee);
	    assertNotNull(exception);
	}

}
