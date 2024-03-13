package edu.westga.cs3212.inventory_manager.test.admineditcredentialspageviewmodel;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.viewmodel.AdminEditCredentialsPageViewModel;

class TestConstructor {

	@Test
	void testConstructor() {
		AdminEditCredentialsPageViewModel adminEdit = new AdminEditCredentialsPageViewModel();
		
		assertNotNull(adminEdit.getFirstName());
		assertNotNull(adminEdit.getLastName());
		assertNotNull(adminEdit.getPassword());
		assertNotNull(adminEdit.getSelectedEmployeeType());
	}

}
