package edu.westga.cs3212.inventory_manager.model.local_impl;

import java.util.HashMap;
import java.util.Map;

public class LocalEmployeeCredentialsManager {
	private Map<String, LocalEmployeeCredentials> employeeCredentialsMap;

    public LocalEmployeeCredentialsManager() {
        this.employeeCredentialsMap = new HashMap<>();
    }

    public LocalEmployeeCredentials createEmployee(String firstName, String lastName, String password, LocalEmployeeCredentials.EmployeeType employeeType) {
        LocalEmployeeCredentials newCredentials = new LocalEmployeeCredentials(firstName, lastName, password, employeeType);
        this.employeeCredentialsMap.put(newCredentials.getEmployeeID(), newCredentials);
        return newCredentials;
    }

    public LocalEmployeeCredentials getEmployeeCredentials(String employeeID) {
        return this.employeeCredentialsMap.get(employeeID);
    }
}
