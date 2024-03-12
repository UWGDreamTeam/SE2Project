package edu.westga.cs3212.inventory_manager.viewmodel;

import java.util.ArrayList;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentials;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentialsManager;
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
public class AdminPageViewModel {
	
	/** The selected user. */
	private ObjectProperty<LocalEmployeeCredentials> selectedUser;
	private LocalEmployeeCredentialsManager credentialsManager;

	/**
	 * Instantiates a new admin page view model.
	 */
	public AdminPageViewModel() {
		this.credentialsManager = new LocalEmployeeCredentialsManager();
		this.selectedUser = new SimpleObjectProperty<LocalEmployeeCredentials>();
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
		return this.credentialsManager.removeEmployee(employeeId);
	}
	
	/**
     * Provides an observable list of employees for the UI, derived from the Local Component Credentials Manager.
     * 
     * @return An observable list of all employees in the system.
     */
	public ObservableList<LocalEmployeeCredentials> getObservableUsersList() {
		ArrayList<LocalEmployeeCredentials> users = new ArrayList<LocalEmployeeCredentials>();
		for (LocalEmployeeCredentials user : this.credentialsManager.getEmployees()) {
			users.add(user);
		}
		return FXCollections.observableArrayList(users);
	}
	
	/**
	 * Gets the selected user.
	 *
	 * @return the selected user
	 */
	public ObjectProperty<LocalEmployeeCredentials> getSelectedUser() {
		return this.selectedUser;
	}
}
