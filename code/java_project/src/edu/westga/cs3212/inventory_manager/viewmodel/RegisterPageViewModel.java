package edu.westga.cs3212.inventory_manager.viewmodel;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentialsManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RegisterPageViewModel {

    private final LocalEmployeeCredentialsManager credentialsManager;
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private final StringProperty confirmPassword = new SimpleStringProperty();
    private final StringProperty employeeType = new SimpleStringProperty();

    public RegisterPageViewModel() {
        this.credentialsManager = new LocalEmployeeCredentialsManager();
    }

    public void registerEmployee() {
        if (!this.password.get().equals(this.confirmPassword.get())) {
            throw new IllegalArgumentException("Error: Passwords do not match.");
        }
        try {
            this.credentialsManager.addEmployee(
                this.firstName.get(),
                this.lastName.get(),
                this.password.get(),
                this.employeeType.get()
            );
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    
    public StringProperty firstNameProperty() {
        return this.firstName;
    }

    public StringProperty lastNameProperty() {
        return this.lastName;
    }

    public StringProperty passwordProperty() {
        return this.password;
    }

    public StringProperty confirmPasswordProperty() {
        return this.confirmPassword;
    }

    public StringProperty employeeTypeProperty() {
        return this.employeeType;
    }
}
