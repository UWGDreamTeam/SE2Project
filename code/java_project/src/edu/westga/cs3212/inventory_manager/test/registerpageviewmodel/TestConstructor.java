package edu.westga.cs3212.inventory_manager.test.registerpageviewmodel;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.viewmodel.admin.RegisterViewModel;

class TestConstructor {

	@Test
    public void testConstructorInitializesProperties() {
        RegisterViewModel viewModel = new RegisterViewModel();
        assertNotNull(viewModel.firstNameProperty());
        assertNotNull(viewModel.lastNameProperty());
        assertNotNull(viewModel.passwordProperty());
        assertNotNull(viewModel.confirmPasswordProperty());
        assertNotNull(viewModel.employeeTypeProperty());
    }

}
