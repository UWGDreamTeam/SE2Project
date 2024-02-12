package edu.westga.cs3212.inventory_manager.model.local_impl;
import java.util.UUID;

/**
 * Represents credentials for an employee in the inventory manager system.
 * This includes the employee's first name, last name, password, type, and a uniquely generated ID.
 */
public class LocalEmployeeCredentials {
	
	/**
     * Enum representing the type of employee.
     * MANAGER for employees with managerial rights, and WORKER for regular employees.
     */
	public enum EmployeeType {
        MANAGER,
        WORKER
    }

    private String employeeID;
    private String firstName;
    private String lastName;
    private String password;
    private EmployeeType employeeType;

    /**
     * Creates a new set of credentials for an employee.
     * 
     * @param firstName    the first name of the employee
     * @param lastName     the last name of the employee
     * @param password     the password for the employee's account
     * @param employeeType the type of employee (MANAGER or WORKER)
     * @throws IllegalArgumentException if any argument is null or empty (for strings)
     */
    public LocalEmployeeCredentials(String firstName, String lastName, String password, EmployeeType employeeType) {
    	if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty.");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty.");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }
        if (employeeType == null) {
            throw new IllegalArgumentException("Employee type cannot be null.");
        }
		
    	this.employeeID = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.employeeType = employeeType;
    }

    /**
     * Gets the unique ID for the employee.
     * 
     * @return the employee ID
     */
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     * Gets the first name of the employee.
     * 
     * @return the first name of the employee
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the employee.
     * 
     * @param firstName the new first name of the employee
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the employee.
     * 
     * @return the last name of the employee
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the employee.
     * 
     * @param lastName the new last name of the employee
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the password for the employee's account.
     * 
     * @return the password of the employee
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the employee's account.
     * 
     * @param password the new password for the employee
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the type of the employee (MANAGER or WORKER).
     * 
     * @return the employee type
     */
    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    /**
     * Sets the type of the employee (MANAGER or WORKER).
     * 
     * @param employeeType the new type of the employee
     */
    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    } 
}
