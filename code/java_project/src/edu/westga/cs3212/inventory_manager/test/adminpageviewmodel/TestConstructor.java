package edu.westga.cs3212.inventory_manager.test.adminpageviewmodel;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.viewmodel.AdminPageViewModel;

class TestConstructor {

	@Test
	void testValidConstructor() {
		AdminPageViewModel adminPageVM = new AdminPageViewModel();
		
		assertNotNull(adminPageVM);
		assertNotNull(adminPageVM.getSelectedUser());
	}

}
