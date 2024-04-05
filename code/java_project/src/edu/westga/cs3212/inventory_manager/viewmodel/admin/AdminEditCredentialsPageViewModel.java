package edu.westga.cs3212.inventory_manager.viewmodel.admin;

import edu.westga.cs3212.inventory_manager.model.credentials.EmployeeType;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentials;
import edu.westga.cs3212.inventory_manager.model.server.credentials.EmployeeCredentialsManager;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AdminEditCredentialsPageViewModel {

	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty password;
	private ObjectProperty<LocalEmployeeCredentials> selectedUserProperty;
	private ObjectProperty<EmployeeType> selectedEmployeeType;
	
	/**
	 * Constructs an AdminEditCredentialsPageViewModel initializing the properties for managing user credentials.
	 * 
	 * Initializes properties for first name, last name, password,
	 * selected user, and selected employee type. These properties are bound to UI components in the view.
	 *
	 * @precondition None, as this is a constructor initializing properties.
	 * @postcondition A new instance of AdminEditCredentialsPageViewModel is created with initialized properties.
	 */
	public AdminEditCredentialsPageViewModel() {
		this.firstName = new SimpleStringProperty();
		this.lastName = new SimpleStringProperty();
		this.password = new SimpleStringProperty();
		this.selectedUserProperty = new SimpleObjectProperty<>();
		this.selectedEmployeeType = new SimpleObjectProperty<>();
	}

	/**
	 * Returns the property for the first name of the selected user.
	 * 
	 * @return The StringProperty for the first name.
	 *
	 * @precondition None.
	 * @postcondition None.
	 */
	public StringProperty getFirstName() {
		return this.firstName;
	}

	/**
	 * Returns the property for the last name of the selected user.
	 * 
	 * @return The StringProperty for the last name.
	 *
	 * @precondition None.
	 * @postcondition None.
	 */
	public StringProperty getLastName() {
		return this.lastName;
	}

	/**
	 * Returns the property for the password of the selected user.
	 * 
	 * @return The StringProperty for the password.
	 *
	 * @precondition None.
	 * @postcondition None.
	 */
	public StringProperty getPassword() {
		return this.password;
	}
	
	/**
	 * Returns the property for the selected employee type.
	 * 
	 * @return The ObjectProperty for the selected employee type.
	 *
	 * @precondition None.
	 * @postcondition None.
	 */
	public ObjectProperty<EmployeeType> getSelectedEmployeeType() {
		return this.selectedEmployeeType;
	}
	
	/**
	 * Returns the property for the selected user.
	 * 
	 * @return The ObjectProperty for the selected user.
	 *
	 * @precondition None.
	 * @postcondition None.
	 */
	public ObjectProperty<LocalEmployeeCredentials> getSelectedUser() {
		return this.selectedUserProperty;
	}

	/**
	 * Sets the selected user and updates the corresponding properties to reflect the user's details.
	 * 
	 * @param selectedUser The LocalEmployeeCredentials instance of the selected user.
	 *
	 * @precondition selectedUser is not null.
	 * @postcondition firstName, lastName, password, and selectedEmployeeType properties are updated to reflect the selected user's details.
	 */
	public void setSelectedUser(LocalEmployeeCredentials selectedUser) {
		this.selectedUserProperty.set(selectedUser);
		
		this.firstName.set(selectedUser.getFirstName());
		this.lastName.set(selectedUser.getLastName());
		this.password.set(selectedUser.getPassword());
		this.selectedEmployeeType.set(selectedUser.getEmployeeType());
	}

	/**
	 * Edits the details of the currently selected user based on the values in the properties.
	 *
	 * Updates the selected user's first name, last name, password, and employee type with the values from the corresponding properties.
	 *
	 * @precondition The selectedUser property must have been previously set with a non-null value.
	 * @postcondition The selected user's details are updated with the values from the firstName, lastName, password, and selectedEmployeeType properties.
	 */
	public void editUser() {
		String newFirstName = this.firstName.getValue();
		String newLastName = this.lastName.getValue();
		String newPassword = this.password.getValue();
		EmployeeType newEmployeeType = this.selectedEmployeeType.getValue();
		
		LocalEmployeeCredentials selectedUser = this.selectedUserProperty.getValue();
		
		selectedUser.setFirstName(newFirstName);
		selectedUser.setLastName(newLastName);
		selectedUser.setPassword(newPassword);
		selectedUser.setEmployeeType(newEmployeeType);
		EmployeeCredentialsManager.removeEmployee(selectedUser.getEmployeeID());
		EmployeeCredentialsManager.addEmployee(newFirstName, newLastName, newPassword, newEmployeeType);
	}
}
