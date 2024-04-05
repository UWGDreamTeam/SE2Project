package edu.westga.cs3212.inventory_manager.test.adminpageviewmodel;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.credentials.EmployeeType;
import edu.westga.cs3212.inventory_manager.model.credentials.LocalEmployeeCredentials;
import edu.westga.cs3212.inventory_manager.model.server.credentials.EmployeeCredentialsManager;
import edu.westga.cs3212.inventory_manager.viewmodel.admin.AdminViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

class TestRemoveUser {
	
	@Test
	void testRemoveUser() {
		//Arrange
		EmployeeCredentialsManager.addEmployee("testName", "testLastName", "testPassword", "manager");
		
		String fakeID = "";
		
		for (LocalEmployeeCredentials curr: EmployeeCredentialsManager.getEmployees()) {
			if (curr.getFirstName().equals("testName")) {
				fakeID = curr.getEmployeeID();
			}
		}
		
		AdminViewModel adminPageVM = new AdminViewModel();
		
		LocalEmployeeCredentials selectedUser = EmployeeCredentialsManager.getEmployeeCredentials(fakeID);
		ObjectProperty<LocalEmployeeCredentials> selectedUserView = new SimpleObjectProperty<LocalEmployeeCredentials>(selectedUser);
		
		adminPageVM.getSelectedUser().bindBidirectional(selectedUserView);
		
		//Act && Assert
		assertTrue(adminPageVM.removeUser());
	}
	
	@Test
	void testRemoveNotInSystem() {
		AdminViewModel adminPageVM = new AdminViewModel();
		
		LocalEmployeeCredentials selectedUser = new LocalEmployeeCredentials("tt0001", "testName1", "testLastName1", "testPassword1", EmployeeType.WORKER);
		ObjectProperty<LocalEmployeeCredentials> selectedUserView = new SimpleObjectProperty<LocalEmployeeCredentials>(selectedUser);
		
		adminPageVM.getSelectedUser().bindBidirectional(selectedUserView);
		
		//Act && Assert
		assertThrows(IllegalArgumentException.class, () -> {
			adminPageVM.removeUser();
		});
	}
}
