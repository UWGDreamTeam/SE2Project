package edu.westga.cs3212.inventory_manager.viewmodel;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentialsManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginPageViewModel {

	private final LocalEmployeeCredentialsManager manager;
	private final StringProperty employeeID;
	private final StringProperty password;
    
	public LoginPageViewModel() {
		this.manager = new LocalEmployeeCredentialsManager();
		this.employeeID = new SimpleStringProperty();
		this.password = new SimpleStringProperty();
	}
	
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
	
	public StringProperty employeeIDProperty() {
		return this.employeeID;
	}
	
	public StringProperty passwordProperty() {
		return this.password;
	}
}
