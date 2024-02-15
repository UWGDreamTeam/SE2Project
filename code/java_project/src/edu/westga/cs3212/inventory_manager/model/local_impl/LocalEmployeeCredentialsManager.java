package edu.westga.cs3212.inventory_manager.model.local_impl;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import edu.wetsga.cs3212.inventory_manager.model.SystemCredentialsManager;

/**
 * Manages employee credentials locally by storing, retrieving,
 * updating, and deleting employee information in a local JSON file.
 * 
 * @author Jacob Haas
 */
public class LocalEmployeeCredentialsManager extends SystemCredentialsManager {
	private Map<String, LocalEmployeeCredentials> employeeCredentialsMap;

	/**
	 * Initializes the credentials manager and loads existing credentials from storage.
	 */
	public LocalEmployeeCredentialsManager() {
		this.employeeCredentialsMap = new HashMap<String, LocalEmployeeCredentials>();
		this.loadEmployeeCredentials();
	}

	/**
     * Retrieves the credentials for a given employee ID.
     * 
     * @param employeeID The unique identifier for the employee.
     * @return The credentials associated with the employee ID or null if not found.
     */
	public LocalEmployeeCredentials getEmployeeCredentials(String employeeID) {
		return this.employeeCredentialsMap.get(employeeID);
	}

	/**
     * Adds a new employee's credentials to the local storage.
     * 
     * @param employeeID The unique identifier for the new employee.
     * @param password The password for the new employee.
     * @param employeeType The type of the employee (e.g., ADMIN, USER).
     * @return true if the employee was successfully added, false otherwise.
     */
    @Override
    public boolean addEmployee(String employeeID, String password, String employeeType) {
        if (password == null || password.trim().isEmpty() || employeeType == null) {
            return false;
        }

        EmployeeType type;
        try {
            type = EmployeeType.valueOf(employeeType.toUpperCase());
        } catch (IllegalArgumentException e) {
            return false;
        }

        //TODO Remove hard code values
        LocalEmployeeCredentials newEmployee = new LocalEmployeeCredentials("FirstName", "LastName", password, type);
        this.employeeCredentialsMap.put(newEmployee.getEmployeeID(), newEmployee);

        this.saveChanges();

        return true;
    }

    /**
	 * Removes an employee's credentials from local storage.
	 * 
	 * @param employeeID The unique identifier for the employee to be removed.
	 * @return true if the employee was successfully removed, false otherwise.
	 */
	@Override
	public boolean removeEmployee(String employeeID) {
		if (employeeID == null || employeeID.isEmpty()) {
			return false;
		}
		if (this.employeeCredentialsMap.containsKey(employeeID)) {
			this.employeeCredentialsMap.remove(employeeID);
			this.saveChanges();
			return true;
		}
		return false;
	}

	/**
	 * Updates the password for a given employee.
	 * 
	 * @param employeeID The unique identifier for the employee.
	 * @param password The new password for the employee.
	 * @return true if the password was successfully updated, false otherwise.
	 */
	@Override
	public boolean updateEmployeePassword(String employeeID, String password) {
		if (employeeID == null || employeeID.isEmpty() || password == null || password.isEmpty()) {
			return false;
		}
		if (this.employeeCredentialsMap.containsKey(employeeID)) {
			LocalEmployeeCredentials employee = this.employeeCredentialsMap.get(employeeID);
			employee.setPassword(password);
			this.saveChanges();
			return true;
		}
		return false;
	}

	/**
	 * Attempts to login with the provided employee ID and password.
	 * 
	 * @param employeeID The employee's unique identifier.
	 * @param password The password for the employee.
	 * @return true if login is successful, false otherwise.
	 */
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
	
	//TODO CHANGE JSON LOCATION
	/**
	 * Saves the current state of employee credentials to local storage.
	 */
	private void saveChanges() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (FileWriter writer = new FileWriter("employeeCredentials.json")) {
			gson.toJson(this.employeeCredentialsMap, writer);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Loads employee credentials from local storage into memory.
	 */
	private void loadEmployeeCredentials() {
		try {
			String json = new String(Files.readAllBytes(Paths.get("employeeCredentials.json")));
			Gson gson = new Gson();
			Type type = new TypeToken<HashMap<String, LocalEmployeeCredentials>>(){}.getType();
			this.employeeCredentialsMap = gson.fromJson(json, type);
		} catch (IOException e) {
			this.employeeCredentialsMap = new HashMap<>();
		}
	}

	/**
	 * Retrieves the password for a given employee ID.
	 * 
	 * @param employeeID The unique identifier for the employee.
	 * @return The password of the employee or null if not found.
	 */
	@Override
	public String getEmployeePassword(String employeeID) {
		if (employeeID == null || employeeID.isEmpty()) {
			return null;
		}
		if (this.employeeCredentialsMap.containsKey(employeeID)) {
			return this.employeeCredentialsMap.get(employeeID).getPassword();
		}
		return null;
	}
}
