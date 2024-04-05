package edu.westga.cs3212.inventory_manager.test.adminpageviewmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.westga.cs3212.inventory_manager.model.credentials.EmployeeType;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentials;
import edu.westga.cs3212.inventory_manager.model.server.credentials.EmployeeCredentialsManager;
import edu.westga.cs3212.inventory_manager.viewmodel.admin.AdminPageViewModel;

class TestGetObservableUsersList {

	private AdminPageViewModel adminPageVM;

	@BeforeEach
	public void setUp() {
		this.adminPageVM = new AdminPageViewModel();
	}

	@Test
	void testGetObservableUsersListEmpty() {
		EmployeeCredentialsManager.clearCredentials();

		assertThrows(IllegalArgumentException.class, () -> {
			this.adminPageVM.getObservableUsersList();
		});
	}

	@Test
	void testGetObservableUsersListWithCredentials() {
		ArrayList<LocalEmployeeCredentials> credentials = new ArrayList<>();
		LocalEmployeeCredentials credentials1 = new LocalEmployeeCredentials("user1", "user1", "user1", "user1", EmployeeType.MANAGER);
		LocalEmployeeCredentials credentials2 = new LocalEmployeeCredentials("user2", "user2", "user2", "user2", EmployeeType.MANAGER);
		credentials.add(credentials1);
		credentials.add(credentials2);
		
		EmployeeCredentialsManager.addEmployee("user1", "user1", "user1", "manager");
		EmployeeCredentialsManager.addEmployee("user2", "user2", "user2", "manager");
		
		assertEquals(credentials.size(), this.adminPageVM.getObservableUsersList().size());
	}
}
