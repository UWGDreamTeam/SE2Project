package edu.westga.cs3212.inventory_manager.test;



import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentialsManager;

class TestLocalEmployeeCredentialsManager {

	private LocalEmployeeCredentialsManager manager;

    @BeforeEach
    void setUp() {
        this.manager = new LocalEmployeeCredentialsManager();
    }

    @Test
    void testAddEmployeeWithValidData() {
        assertTrue(this.manager.addEmployee("employeeId", "password", "manager"));
    }

    @Test
    void testAddEmployeeWithInvalidData() {
        assertFalse(this.manager.addEmployee(null, "password", "manager"));
    }

    @Test
    void testGetEmployeeCredentialsWithExistingID() {
        this.manager.addEmployee("employeeID", "password", "manager");
        assertNotNull(this.manager.getEmployeeCredentials("employeeID"));
    }

    @Test
    void testGetEmployeeCredentialsWithNonExistingID() {
        assertNull(this.manager.getEmployeeCredentials("nonExistingID"));
    }

    @Test
    void testRemoveEmployeeWithExistingID() {
        this.manager.addEmployee("employeeID", "password", "manager");
        assertTrue(this.manager.removeEmployee("employeeID"));
    }

    @Test
    void testRemoveEmployeeWithNonExistingID() {
        assertFalse(this.manager.removeEmployee("nonExistingID"));
    }

    @Test
    void testUpdateEmployeePasswordWithValidData() {
        this.manager.addEmployee("employeeID", "password", "manager");
        assertTrue(this.manager.updateEmployeePassword("employeeID", "newPassword"));
    }

    @Test
    void testUpdateEmployeePasswordWithInvalidData() {
        assertFalse(this.manager.updateEmployeePassword("nonExistingID", "newPassword"));
    }

    @Test
    void testAttemptLoginWithCorrectCredentials() {
        this.manager.addEmployee("employeeID", "password", "manager");
        assertTrue(this.manager.attemptLogin("employeeID", "password"));
    }

    @Test
    void testAttemptLoginWithIncorrectCredentials() {
        this.manager.addEmployee("employeeID", "password", "manager");
        assertFalse(this.manager.attemptLogin("employeeID", "wrongPassword"));
    }

}
