package edu.westga.cs3212.inventory_manager.test.adminpageviewmodel;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.server.credentials.EmployeeCredentialsManager;
import edu.westga.cs3212.inventory_manager.viewmodel.admin.AdminViewModel;

class TestGetObservableUsersList {

	@Test
	void testGetEmptyList() {
		EmployeeCredentialsManager.clearCredentials();
		
		AdminViewModel adminPageVM = new AdminViewModel();
		
		assertThrows(IllegalArgumentException.class, () -> {
			adminPageVM.getObservableUsersList();
		});
	}

}
