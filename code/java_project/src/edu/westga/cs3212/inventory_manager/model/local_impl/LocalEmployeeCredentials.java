package edu.westga.cs3212.inventory_manager.model.local_impl;

import edu.westga.cs3212.inventory_manager.model.Constants;

/**
 * Represents credentials for an employee in the inventory manager system.
 * This includes the employee's first name, last name, password, type, and a uniquely generated ID.
 * 
 * @author Jacob Haas
 */
public class LocalEmployeeCredentials {
	
    private String employeeID;
    private String firstName;
    private String lastName;
    private String password;
    private EmployeeType employeeType;

    /**
     * Creates a new set of credentials for an employee.
     * 
     * @precondition firstName != null && !firstName.isEmpty() &&
     *              lastName != null && !lastName.isEmpty() &&
     *              password != null && !password.isEmpty() &&
     *              employeeType != null
     * 
     * @precondition firstName != null && !firstName.isEmpty() &&
     *             lastName != null && !lastName.isEmpty() &&
     *             password != null && !password.isEmpty() &&
     *             employeeType != null
     *             
     * @postcondition getEmployeeID() != null && !getEmployeeID().isEmpty() &&
     *               getFirstName().equals(firstName) &&
     *               getLastName().equals(lastName) &&
     *               getPassword().equals(password) &&
     *               getEmployeeType().equals(employeeType)
     * 
     * @param employeeID   the unique id of the employee
     * @param firstName    the first name of the employee
     * @param lastName     the last name of the employee
     * @param password     the password for the employee's account
     * @param employeeType the type of employee (MANAGER or WORKER)
     * @param employeeID   the unique ID for the employee
     */
    public LocalEmployeeCredentials(String employeeID, String firstName, String lastName, String password, EmployeeType employeeType) {        
        this.setEmployeeID(employeeID);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPassword(password);
        this.setEmployeeType(employeeType);
    }
	
    private void checkForValidEmployeeID(String employeeID) {
        if (employeeID == null || employeeID.trim().isEmpty()) {
			throw new IllegalArgumentException(Constants.EMPLOYEE_ID_CANNOT_BE_NULL);
		}
    }
    
	private void checkForValidFirstName(String firstName) {
		if (firstName == null || firstName.trim().isEmpty()) {
			throw new IllegalArgumentException(Constants.FIRST_NAME_CANNOT_BE_NULL);
		}
	}
	
	private void checkForValidLastName(String lastName) {
		if (lastName == null || lastName.trim().isEmpty()) {
			throw new IllegalArgumentException(Constants.LAST_NAME_CANNOT_BE_NULL);
		}
	}
	
	private void checkForValidPassword(String password) {
		if (password == null || password.trim().isEmpty()) {
			throw new IllegalArgumentException(Constants.PASSWORD_CANNOT_BE_NULL);
		}
	}
	
	private EmployeeType checkForValidEmployeeType(EmployeeType employeeType) {
		if (employeeType == null) {
			throw new IllegalArgumentException(Constants.EMPLOYEE_TYPE_CANNOT_BE_NULL);
		}
		return employeeType;
	}

    /**
     * Gets the unique ID for the employee.
     * 
     * @return the employee ID
     */
    public String getEmployeeID() {
        return this.employeeID;
    }

    /**
     * Gets the first name of the employee.
     * 
     * @return the first name of the employee
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Sets the first name of the employee.
     * 
     * @param firstName the new first name of the employee
     */
    public void setFirstName(String firstName) {
    	this.checkForValidFirstName(firstName);
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the employee.
     * 
     * @return the last name of the employee
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Sets the last name of the employee.
     * 
     * @param lastName the new last name of the employee
     */
    public void setLastName(String lastName) {
    	this.checkForValidLastName(lastName);
        this.lastName = lastName;
    }

    /**
     * Gets the password for the employee's account.
     * 
     * @return the password of the employee
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets the password for the employee's account.
     * 
     * @param password the new password for the employee
     */
    public void setPassword(String password) {
    	this.checkForValidPassword(password);
        this.password = password;
    }

    /**
     * Gets the type of the employee (MANAGER or WORKER).
     * 
     * @return the employee type
     */
    public EmployeeType getEmployeeType() {
        return this.employeeType;
    }

    /**
     * Sets the type of the employee (MANAGER or WORKER).
     * 
     * @param employeeType the new type of the employee
     */
    public void setEmployeeType(EmployeeType employeeType) {
    	this.checkForValidEmployeeType(employeeType);
        this.employeeType = employeeType;
    }

    /**
     * Sets the unique ID for the employee.
     * 
     * @precondition employeeID != null && !employeeID.isEmpty()
     * @postcondition getEmployeeID().equals(employeeID)
     * 
     * @param employeeID
     */
	public void setEmployeeID(String employeeID) {
		this.checkForValidEmployeeID(employeeID);
		this.employeeID = employeeID;
	} 
}
