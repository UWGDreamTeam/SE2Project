package edu.westga.cs3212.inventory_manager.viewmodel.admin;

import java.util.ArrayList;

import edu.westga.cs3212.inventory_manager.model.credentials.LocalEmployeeCredentials;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentialsManager;
import edu.westga.cs3212.inventory_manager.model.server.credentials.EmployeeCredentialsManager;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The Class AdminPageViewModel.
 * 
 * @author Vitor dos Santos
 * @version Spring 2024
 */
public class AdminViewModel {
	
	private ObjectProperty<LocalEmployeeCredentials> selectedUser;

	/**
	 * Instantiates a new admin page view model.
	 * @preconditions none
     * @postconditions 	this.getSelectedUser() != null && 
     * 					this.credentialsManager != null
	 */
	public AdminViewModel() {
		this.selectedUser = new SimpleObjectProperty<>();
	}

	/**
	 * Removes the user.
	 * 
	 * @precondition none
	 * @postcondition 	this.credentialsManager.getEmployees().size() ==
	 * 					this.credentialsManager.getEmployees().size()@prev + 1 
	 *
	 * @return true, if successful
	 */
	public boolean removeUser() {
		String employeeId = this.selectedUser.getValue().getEmployeeID();
		return EmployeeCredentialsManager.removeEmployee(employeeId);
	}
	
	/**
     * Provides an observable list of employees for the UI, derived from the Local Component Credentials Manager.
     * 
     * @preconditions none
     * @postconditions none
     * 
     * @return ObservableList&lt;LocalEmployeeCredentials&gt; An observable list of all employees in the system.
     */
	public ObservableList<LocalEmployeeCredentials> getObservableUsersList() {
		ArrayList<LocalEmployeeCredentials> users = new ArrayList<>();
		for (LocalEmployeeCredentials user : EmployeeCredentialsManager.getEmployees()) {
			users.add(user);
		}
		return FXCollections.observableArrayList(users);
	}
	
	/**
	 * Gets the selected user.
	 *
	 * @preconditions none
	 * @postcondition none
	 * 
	 * @return the selected user
	 */
	public ObjectProperty<LocalEmployeeCredentials> getSelectedUser() {
		return this.selectedUser;
	}
}
