package edu.westga.cs3212.inventory_manager.model.local_impl;


public class LocalEmployeeCredentials {
	public enum EmployeeType {
        MANAGER,
        WORKER
    }

    private String employeeID;
    private String firstName;
    private String lastName;
    private String password;
    private EmployeeType employeeType;

    public LocalEmployeeCredentials(String employeeID, String firstName, String lastName, String password, EmployeeType employeeType) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.employeeType = employeeType;
    }

    // Getters and Setters
    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    } 
}
