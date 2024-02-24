package edu.westga.cs3212.inventory_manager.test.registerpageviewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.viewmodel.RegisterPageViewModel;

class TestRegisterEmployee {

	private RegisterPageViewModel viewModel;

	@BeforeEach
	public void setUp() {
		this.viewModel = new RegisterPageViewModel();
	}

	@Test
	public void testRegisterEmployeeWithPasswordMismatchThrowsException() {
		this.viewModel.passwordProperty().set("password");
		this.viewModel.confirmPasswordProperty().set("differentPassword");
		Exception exception = assertThrows(IllegalArgumentException.class, this.viewModel::registerEmployee);
		assertEquals("Error: Passwords do not match.", exception.getMessage());
	}

	@Test
	public void testRegisterEmployeeWithMatchingPasswords() {
		this.viewModel.firstNameProperty().set("John");
		this.viewModel.lastNameProperty().set("Doe");
		this.viewModel.passwordProperty().set("password");
		this.viewModel.confirmPasswordProperty().set("password");
		this.viewModel.employeeTypeProperty().set("Manager");
	}
	
	@Test
	public void testRegisterEmployeeWithMatchingPasswordsAndValidData() {
	    this.viewModel.firstNameProperty().set("John");
	    this.viewModel.lastNameProperty().set("Doe");
	    this.viewModel.passwordProperty().set("password");
	    this.viewModel.confirmPasswordProperty().set("password");
	    this.viewModel.employeeTypeProperty().set("Manager");
	    
	    assertDoesNotThrow(() -> this.viewModel.registerEmployee());
	}
	
	@Test
	public void testRegisterEmployeeWithInvalidPasswordThrowsException() {
	    this.viewModel.firstNameProperty().set("John");
	    this.viewModel.lastNameProperty().set("Doe");
	    this.viewModel.passwordProperty().set("");
	    this.viewModel.confirmPasswordProperty().set("");
	    this.viewModel.employeeTypeProperty().set("Manager");

	    Exception exception = assertThrows(IllegalArgumentException.class, this.viewModel::registerEmployee);
	    assertNotNull(exception);
	}

}
