package edu.westga.cs3212.inventory_manager.model.local_impl;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.SystemCredentialsManager;

import java.lang.reflect.Type;

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
	 * 
	 * @precondition none
	 * @postcondition getEmployees().size() == 0
	 */
	public LocalEmployeeCredentialsManager() {
		this.employeeCredentialsMap = new HashMap<String, LocalEmployeeCredentials>();
		this.loadEmployeeCredentials();
	}

	/**
     * Retrieves the credentials for a given employee ID.
     * 
     * @param employeeID The unique identifier for the employee.
     * 
     * @precondition employeeID != null
     * @postcondition none
     * 
     * @return The credentials associated with the employee ID or null if not found.
     */
	public LocalEmployeeCredentials getEmployeeCredentials(String employeeID) {
		this.checkForValidEmployeeID(employeeID);
		return this.employeeCredentialsMap.get(employeeID);
	}

	/**
     * Adds a new employee's credentials to the local storage.
     * 
     * @param password The password for the new employee.
     * @param employeeType The type of the employee (e.g., ADMIN, USER).
     * @param firstName The first name of the new employee.
     * @param lastName The last name of the new employee.
     * 
     * @precondition password != null && !password.isEmpty() &&
     *              employeeType != null && !employeeType.isEmpty() &&
     *              firstName != null && !firstName.isEmpty() &&
     *              lastName != null && !lastName.isEmpty()
     *              
     * @postcondition getEmployees().size() == getEmployees().size()@prev + 1
     */
    @Override
    public void addEmployee(String firstName, String lastName, String password, String employeeType) {
        this.checkForValidFirstName(firstName);
        this.checkForValidLastName(lastName);
        this.checkForValidPassword(password);
        this.checkForValidEmployeeType(employeeType);
        String employeeID = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        EmployeeType type = this.checkForValidEmployeeType(employeeType);
        LocalEmployeeCredentials newEmployee = new LocalEmployeeCredentials(employeeID, firstName, lastName, password, type);
		while (this.employeeCredentialsMap.containsKey(newEmployee.getEmployeeID())) {
			newEmployee.setEmployeeID(UUID.randomUUID().toString().replace("-", "").substring(0, 8));
		}
        this.employeeCredentialsMap.put(newEmployee.getEmployeeID(), newEmployee);
        this.saveChanges();
    }
    
    private EmployeeType checkForValidEmployeeType(String employeeType) {
		if (employeeType == null || employeeType.trim().isEmpty()) {
			throw new IllegalArgumentException(Constants.EMPLOYEE_TYPE_CANNOT_BE_NULL);
		}
		try {
			EmployeeType type = EmployeeType.valueOf(employeeType.toUpperCase());
			return type;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(Constants.EMPLOYEE_MUST_BE_VALID_TYPE);
		}
    }
    
    private void checkForValidPassword(String password) {
		if (password == null || password.trim().isEmpty()) {
			throw new IllegalArgumentException(Constants.PASSWORD_CANNOT_BE_NULL);
		}
    }
    
	private void checkForValidLastName(String lastName) {
		if (lastName == null || lastName.trim().isEmpty()) {
			throw new IllegalArgumentException(Constants.LAST_NAME_CANNOT_BE_NULL);
		}
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
	
	/**
	 * Retrieves all employees from local storage.   
	 * 
	 * @precondition none
	 * @postcondition none
	 *  
	 * @return An List collection of all employees.
	 */
	public List<LocalEmployeeCredentials> getEmployees() {
		return List.copyOf(this.employeeCredentialsMap.values());
	}

    /**
	 * Removes an employee's credentials from local storage.
	 * 
	 * @precondition employeeID != null &&
	 *                  getEmployeeIDs().contains(employeeID)
	 *                  
	 * @postcondition getEmployees().size() == getEmployees().size()@prev - 1
	 * 
	 * @param employeeID The unique identifier for the employee to be removed.
	 * @return true if the employee was successfully removed, false otherwise.
	 */
	@Override
	public boolean removeEmployee(String employeeID) {
		this.checkForValidEmployeeID(employeeID);
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
	 * @precondition employeeID != null && !employeeID.isEmpty() && 
	 *                 password != null && !password.isEmpty()
	 * @postcondition getEmployeePassword(employeeID).equals(password)
	 *                 
	 * @param employeeID The unique identifier for the employee.
	 * @param password The new password for the employee.
	 * @return true if the password was successfully updated, false otherwise.
	 */
	@Override
	public boolean updateEmployeePassword(String employeeID, String password) {
		this.checkForValidEmployeeID(employeeID);
		this.checkForValidPassword(password);
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
	 * @precondition employeeID != null && !employeeID.isEmpty() &&
	 *                 password != null && !password.isEmpty()
	 * @postcondition none
	 * 
	 * @param employeeID The employee's unique identifier.
	 * @param password The password for the employee.
	 * @return true if login is successful, false otherwise.
	 */
	public boolean attemptLogin(String employeeID, String password) {
		this.checkForValidEmployeeID(employeeID);
		this.checkForValidPassword(password);
		if (this.employeeCredentialsMap.containsKey(employeeID)) {
			LocalEmployeeCredentials employee = this.employeeCredentialsMap.get(employeeID);
			if (employee.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Saves the current state of employee credentials to local storage.
	 */
	private void saveChanges() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (FileWriter writer = new FileWriter(Constants.EMPLOYEE_CREDENTIAL_FILE_LOCATION)) {
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
			String json = new String(Files.readAllBytes(Paths.get(Constants.EMPLOYEE_CREDENTIAL_FILE_LOCATION)));
			Gson gson = new Gson();

			Type type = new TypeToken<HashMap<String, LocalEmployeeCredentials>>() {
				
			}.getType();
			this.employeeCredentialsMap = gson.fromJson(json, type);
		} catch (IOException e) {
			this.employeeCredentialsMap = new HashMap<>();
		}
	}

	/**
	 * Retrieves the password for a given employee ID.
	 * 
	 * @precondition employeeID != null && !employeeID.isEmpty()
	 * @postcondition none
	 * 
	 * @param employeeID The unique identifier for the employee.
	 * @return The password of the employee or null if not found.
	 */
	@Override
	public String getEmployeePassword(String employeeID) {
		this.checkForValidEmployeeID(employeeID);
		if (this.employeeCredentialsMap.containsKey(employeeID)) {
			return this.employeeCredentialsMap.get(employeeID).getPassword();
		}
		return null;
	}
}
