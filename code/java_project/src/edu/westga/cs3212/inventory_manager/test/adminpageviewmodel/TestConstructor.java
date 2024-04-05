package edu.westga.cs3212.inventory_manager.test.adminpageviewmodel;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.viewmodel.admin.AdminViewModel;

class TestConstructor {

	@Test
	void testValidConstructor() {
		AdminViewModel adminPageVM = new AdminViewModel();
		
		assertNotNull(adminPageVM);
		assertNotNull(adminPageVM.getSelectedUser());
	}

}
