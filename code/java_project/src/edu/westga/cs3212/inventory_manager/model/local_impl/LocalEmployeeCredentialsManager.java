package edu.westga.cs3212.inventory_manager.model.local_impl;

import java.util.HashMap;
import java.util.Map;

import edu.wetsga.cs3212.inventory_manager.model.SystemCredentialsManager;

public class LocalEmployeeCredentialsManager extends SystemCredentialsManager {
	private Map<String, LocalEmployeeCredentials> employeeCredentialsMap;

	public LocalEmployeeCredentialsManager() {
		this.employeeCredentialsMap = new HashMap<String, LocalEmployeeCredentials>();
	}

    public LocalEmployeeCredentials createEmployee(String firstName, String lastName, String password, LocalEmployeeCredentials.EmployeeType employeeType) {
        LocalEmployeeCredentials newCredentials = new LocalEmployeeCredentials(firstName, lastName, password, employeeType);
        this.employeeCredentialsMap.put(newCredentials.getEmployeeID(), newCredentials);
        return newCredentials;
    }

    public LocalEmployeeCredentials getEmployeeCredentials(String employeeID) {
        return this.employeeCredentialsMap.get(employeeID);
    }

	@Override
	public String getEmployeePassword(String employeeID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeEmployee(String employeeID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addEmployee(String employeeID, String password, String employeeType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEmployeePassword(String employeeID, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String generateEmployeeID() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean attemptLogin(String employeeID, String password) {
		if (employeeID == null || employeeID.isEmpty() || password == null || password.isEmpty()) {
			return false;
		}
		if (this.employeeCredentialsMap.containsKey(employeeID)) {
			LocalEmployeeCredentials employee = this.employeeCredentialsMap.get(employeeID);
			if (employee.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
}
