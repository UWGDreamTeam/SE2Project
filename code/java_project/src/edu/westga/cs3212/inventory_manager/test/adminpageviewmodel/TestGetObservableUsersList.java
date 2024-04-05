package edu.westga.cs3212.inventory_manager.test.adminpageviewmodel;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.server.credentials.EmployeeCredentialsManager;
import edu.westga.cs3212.inventory_manager.viewmodel.admin.AdminPageViewModel;

class TestGetObservableUsersList {

	@Test
	void testGetEmptyList() {
		EmployeeCredentialsManager.clearCredentials();
		
		AdminPageViewModel adminPageVM = new AdminPageViewModel();
		
		assertThrows(IllegalArgumentException.class, () -> {
			adminPageVM.getObservableUsersList();
		});
	}

}
