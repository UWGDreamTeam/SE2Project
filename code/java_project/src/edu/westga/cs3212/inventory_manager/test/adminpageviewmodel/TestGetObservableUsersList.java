package edu.westga.cs3212.inventory_manager.test.adminpageviewmodel;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentials;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentialsManager;
import javafx.collections.FXCollections;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.viewmodel.AdminPageViewModel;

class TestGetObservableUsersList {

	@Test
	void testGetList() {
		AdminPageViewModel adminPageVM = new AdminPageViewModel();
		
		LocalEmployeeCredentialsManager manager = new LocalEmployeeCredentialsManager();
		int expectedSize = manager.getEmployees().size();
		
		ArrayList<LocalEmployeeCredentials> testList = new ArrayList<LocalEmployeeCredentials>();
		assertEquals(adminPageVM.getObservableUsersList().getClass(), FXCollections.observableArrayList(testList).getClass());
		assertEquals(adminPageVM.getObservableUsersList().size(), expectedSize);
	}

}
