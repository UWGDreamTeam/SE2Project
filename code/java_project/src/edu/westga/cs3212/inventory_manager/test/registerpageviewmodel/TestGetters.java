package edu.westga.cs3212.inventory_manager.test.registerpageviewmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.viewmodel.admin.RegisterPageViewModel;

class TestGetters {

	private RegisterPageViewModel viewModel;

    @BeforeEach
    public void setUp() {
        this.viewModel = new RegisterPageViewModel();
    }

    @Test
    public void testFirstNamePropertyGetter() {
        String expected = "John";
        this.viewModel.firstNameProperty().set(expected);
        assertEquals(expected, this.viewModel.firstNameProperty().get());
    }

    @Test
    public void testLastNamePropertyGetter() {
        String expected = "Doe";
        this.viewModel.lastNameProperty().set(expected);
        assertEquals(expected, this.viewModel.lastNameProperty().get());
    }

    @Test
    public void testPasswordPropertyGetter() {
        String expected = "password";
        this.viewModel.passwordProperty().set(expected);
        assertEquals(expected, this.viewModel.passwordProperty().get());
    }

    @Test
    public void testConfirmPasswordPropertyGetter() {
        String expected = "password";
        this.viewModel.confirmPasswordProperty().set(expected);
        assertEquals(expected, this.viewModel.confirmPasswordProperty().get());
    }

    @Test
    public void testEmployeeTypePropertyGetter() {
        String expected = "Type";
        this.viewModel.employeeTypeProperty().set(expected);
        assertEquals(expected, this.viewModel.employeeTypeProperty().get());
    }

}
