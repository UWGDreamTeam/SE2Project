package edu.westga.cs3212.inventory_manager.test.adminpageviewmodel;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.EmployeeType;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentials;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentialsManager;
import edu.westga.cs3212.inventory_manager.viewmodel.AdminPageViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

class TestRemoveUser {
	
	@Test
	void testRemoveUser() {
		//Arrange
		LocalEmployeeCredentialsManager fakeCredentialsManager = new LocalEmployeeCredentialsManager();
		fakeCredentialsManager.addEmployee("testName", "testLastName", "testPassword", "manager");
		
		String fakeID = "";
		
		for (LocalEmployeeCredentials curr: fakeCredentialsManager.getEmployees()) {
			if (curr.getFirstName().equals("testName")) {
				fakeID = curr.getEmployeeID();
			}
		}
		
		AdminPageViewModel adminPageVM = new AdminPageViewModel();
		
		LocalEmployeeCredentials selectedUser = fakeCredentialsManager.getEmployeeCredentials(fakeID);
		ObjectProperty<LocalEmployeeCredentials> selectedUserView = new SimpleObjectProperty<LocalEmployeeCredentials>(selectedUser);
		
		adminPageVM.getSelectedUser().bindBidirectional(selectedUserView);
		
		//Act && Assert
		assertTrue(adminPageVM.removeUser());
	}
	
	@Test
	void testRemoveNotInSystem() {
		AdminPageViewModel adminPageVM = new AdminPageViewModel();
		
		LocalEmployeeCredentials selectedUser = new LocalEmployeeCredentials("testID1", "testName1", "testLastName1", "testPassword1", EmployeeType.WORKER);
		ObjectProperty<LocalEmployeeCredentials> selectedUserView = new SimpleObjectProperty<LocalEmployeeCredentials>(selectedUser);
		
		adminPageVM.getSelectedUser().bindBidirectional(selectedUserView);
		
		//Act && Assert
		assertFalse(adminPageVM.removeUser());
	}
}
