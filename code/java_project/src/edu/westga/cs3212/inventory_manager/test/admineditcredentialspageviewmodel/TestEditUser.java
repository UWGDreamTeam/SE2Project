package edu.westga.cs3212.inventory_manager.test.admineditcredentialspageviewmodel;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.credentials.EmployeeType;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentials;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentialsManager;
import edu.westga.cs3212.inventory_manager.model.server.credentials.EmployeeCredentialsManager;
import edu.westga.cs3212.inventory_manager.viewmodel.admin.AdminEditCredentialsPageViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class TestEditUser {
	
	@AfterEach
	void clean() {
		
	}

	@Test
	void testRemoveUser() {
		//Arrange
		EmployeeCredentialsManager.addEmployee("testEditFirstName", "testEditLastName", "testEditPassword", "manager");
		
		String fakeID = "";
		
		for (LocalEmployeeCredentials curr: EmployeeCredentialsManager.getEmployees()) {
			if (curr.getFirstName().equals("testEditFirstName")) {
				fakeID = curr.getEmployeeID();
			}
		}
	
		LocalEmployeeCredentials selectedUser = EmployeeCredentialsManager.getEmployeeCredentials(fakeID);
		
		StringProperty testFirstName = new SimpleStringProperty();
		StringProperty testLastName = new SimpleStringProperty();
		StringProperty testPassword = new SimpleStringProperty();
		
		ObjectProperty<EmployeeType> selectedEmploType = new SimpleObjectProperty<EmployeeType>(EmployeeType.MANAGER);
		ObjectProperty<LocalEmployeeCredentials> selectedUserView = new SimpleObjectProperty<LocalEmployeeCredentials>(selectedUser);
		
		AdminEditCredentialsPageViewModel adminEditPageVM = new AdminEditCredentialsPageViewModel();
		
		adminEditPageVM.setSelectedUser(selectedUserView.getValue());
		
		adminEditPageVM.getFirstName().bindBidirectional(testFirstName);
		adminEditPageVM.getLastName().bindBidirectional(testLastName);
		adminEditPageVM.getPassword().bindBidirectional(testPassword);
		adminEditPageVM.getSelectedEmployeeType().bindBidirectional(selectedEmploType);
		adminEditPageVM.getSelectedUser().bindBidirectional(selectedUserView);
		
		testFirstName.setValue("newEditFirstName");
		testLastName.setValue("newEditLastName");
		testPassword.setValue("newEditPassword");
		selectedEmploType.setValue(EmployeeType.WORKER);
		
		//Act
		adminEditPageVM.editUser();
		
		String actualFirstName = adminEditPageVM.getSelectedUser().getValue().getFirstName();
		String actualLastName = adminEditPageVM.getSelectedUser().getValue().getLastName();
		String actualpassword = adminEditPageVM.getSelectedUser().getValue().getPassword();
		EmployeeType actualEmployeeType = adminEditPageVM.getSelectedUser().getValue().getEmployeeType();
		
		//Assert
		assertEquals("newEditFirstName", actualFirstName);
		assertEquals("newEditLastName", actualLastName);
		assertEquals("newEditPassword", actualpassword);
		assertEquals(EmployeeType.WORKER, actualEmployeeType);
		
		//EmployeeCredentialsManager.removeEmployee(fakeID);
	}

}
