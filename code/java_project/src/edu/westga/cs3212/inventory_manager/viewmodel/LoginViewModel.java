package edu.westga.cs3212.inventory_manager.viewmodel;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentialsManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel {

	private final LocalEmployeeCredentialsManager manager;
	private final StringProperty employeeID;
	private final StringProperty password;

	/**
	 * ViewModel for the Login Page, encapsulating the logic for user authentication
	 * and interaction with the LocalEmployeeCredentialsManager. It provides
	 * properties for binding to UI components in a JavaFX application.
	 * 
	 * @author Jason Nunez
	 */
	public LoginViewModel() {
		this.manager = new LocalEmployeeCredentialsManager();
		this.employeeID = new SimpleStringProperty();
		this.password = new SimpleStringProperty();
	}

	/**
	 * Attempts to authenticate the user with the given employee ID and password.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return true if the user is successfully authenticated, false otherwise
	 */
	public boolean attemptLogin() {
		String employeeID = this.employeeID.get();
		String password = this.password.get();
		boolean result;
		try {
			result = this.manager.attemptLogin(employeeID, password);
		} catch (Exception exception) {
			result = false;
		}
		return result;
	}

	/**
	 * Returns the employee ID property for binding to a UI component.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the employee ID property
	 */
	public StringProperty employeeIDProperty() {
		return this.employeeID;
	}

	/**
	 * Returns the password property for binding to a UI component.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the password property
	 */
	public StringProperty passwordProperty() {
		return this.password;
	}
}
