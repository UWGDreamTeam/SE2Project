package edu.westga.cs3212.inventory_manager.viewmodel;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentialsManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * ViewModel supporting the registration page, managing interaction between the view and the model,
 * specifically for registering new employees.
 * It encapsulates the data needed for the view and delegates the registration logic to the model.
 * 
 * @author Jacob Haas
 */
public class RegisterPageViewModel {

    private final LocalEmployeeCredentialsManager credentialsManager;
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private final StringProperty confirmPassword = new SimpleStringProperty();
    private final StringProperty employeeType = new SimpleStringProperty();

    /**
     * Initializes a new instance of the RegisterPageViewModel class.
     * 
     * @precondition none
     * @postcondition none
     */
    public RegisterPageViewModel() {
        this.credentialsManager = new LocalEmployeeCredentialsManager();
    }

    /**
     * Registers a new employee with the information provided through the properties.
     * Validation is performed to ensure passwords match.
     * 
     * @precondition none
     * @postcondition If registration is successful, a new employee is added to the system.
     * 
     * @throws IllegalArgumentException If the passwords do not match.
     */
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

    /**
     * Gets the firstName property.
     * 
     * @return The StringProperty for the first name.
     */
    public StringProperty firstNameProperty() {
        return this.firstName;
    }

    /**
     * Gets the lastName property.
     * 
     * @return The StringProperty for the last name.
     */
    public StringProperty lastNameProperty() {
        return this.lastName;
    }

    /**
     * Gets the password property.
     * 
     * @return The StringProperty for the password.
     */
    public StringProperty passwordProperty() {
        return this.password;
    }

    /**
     * Gets the confirmPassword property.
     * 
     * @return The StringProperty for the confirm password.
     */
    public StringProperty confirmPasswordProperty() {
        return this.confirmPassword;
    }

    /**
     * Gets the employeeType property.
     * 
     * @return The StringProperty for the employee type.
     */
    public StringProperty employeeTypeProperty() {
        return this.employeeType;
    }
}
