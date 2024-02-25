package edu.westga.cs3212.inventory_manager.test.registerpageviewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.viewmodel.RegisterPageViewModel;

class TestConstructor {

	private RegisterPageViewModel viewModel;
	
	@Test
    public void testConstructorInitializesProperties() {
        RegisterPageViewModel viewModel = new RegisterPageViewModel();
        assertNotNull(viewModel.firstNameProperty());
        assertNotNull(viewModel.lastNameProperty());
        assertNotNull(viewModel.passwordProperty());
        assertNotNull(viewModel.confirmPasswordProperty());
        assertNotNull(viewModel.employeeTypeProperty());
    }

}
