package edu.westga.cs3212.inventory_manager.model.local_impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.EmployeeCredentialsStorage;
import edu.westga.cs3212.inventory_manager.model.EmployeeType;
import edu.westga.cs3212.inventory_manager.model.SystemCredentialsManager;

/**
 * Manages employee credentials locally by storing, retrieving, updating, and
 * deleting employee information in a local JSON file.
 * 
 * @author Jacob Haas
 */
public class LocalEmployeeCredentialsManager extends SystemCredentialsManager {
	private static Map<String, LocalEmployeeCredentials> employeeCredentialsMap;

	/**
	 * Initializes the credentials manager and loads existing credentials from
	 * storage.
	 * 
	 * @precondition none
	 * @postcondition getEmployees().size() == 0
	 */
	public LocalEmployeeCredentialsManager() {
		LocalEmployeeCredentialsManager.employeeCredentialsMap = new HashMap<>();
		LocalEmployeeCredentialsManager.employeeCredentialsMap = EmployeeCredentialsStorage
				.load(Constants.EMPLOYEE_CREDENTIAL_FILE_LOCATION);
	}

	/**
	 * Retrieves the credentials for a given employee ID.
	 * 
	 * @param employeeID
	 *            The unique identifier for the employee.
	 * 
	 * @precondition employeeID != null && !employeeID.isBlank()
	 * @postcondition none
	 * 
	 * @return The credentials associated with the employee ID or null if not
	 *         found.
	 */
	public LocalEmployeeCredentials getEmployeeCredentials(String employeeID) {
		this.checkForValidEmployeeID(employeeID);
		return LocalEmployeeCredentialsManager.employeeCredentialsMap
				.get(employeeID);
	}

	/**
	 * Adds a new employee's credentials to the local storage.
	 * 
	 * @param password
	 *            The password for the new employee.
	 * @param employeeType
	 *            The type of the employee (e.g., ADMIN, USER).
	 * @param firstName
	 *            The first name of the new employee.
	 * @param lastName
	 *            The last name of the new employee.
	 * 
	 * @precondition password != null && !password.isBlank && employeeType !=
	 *               null && !employeeType.isBlank() && firstName != null &&
	 *               !firstName.isBlank() && lastName != null &&
	 *               !lastName.isBlank()
	 * 
	 * @postcondition getEmployees().size() == getEmployees().size()@prev + 1
	 */
	@Override
	public void addEmployee(String firstName, String lastName, String password,
			String employeeType) {
		this.checkForValidFirstName(firstName);
		this.checkForValidLastName(lastName);
		this.checkForValidPassword(password);
		this.checkForValidEmployeeType(employeeType);
		String employeeID = this.generateUniqueEmployeeID();
		EmployeeType type = this.checkForValidEmployeeType(employeeType);

		LocalEmployeeCredentials newEmployee = new LocalEmployeeCredentials(
				employeeID, firstName, lastName, password, type);
		while (LocalEmployeeCredentialsManager.employeeCredentialsMap
				.containsKey(newEmployee.getEmployeeID())) {
			newEmployee.setEmployeeID(this.generateUniqueEmployeeID());
		}
		LocalEmployeeCredentialsManager.employeeCredentialsMap
				.put(newEmployee.getEmployeeID(), newEmployee);
		EmployeeCredentialsStorage.save(
				LocalEmployeeCredentialsManager.employeeCredentialsMap,
				Constants.EMPLOYEE_CREDENTIAL_FILE_LOCATION);
	}

	/**
	 * Generates a unique employee ID. Example: 1244574e
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return A unique employee ID.
	 */
	public String generateUniqueEmployeeID() {
		return UUID.randomUUID().toString().replace("-", "").substring(0, 8);
	}

	private EmployeeType checkForValidEmployeeType(String employeeType) {
		if (employeeType == null || employeeType.isBlank()) {
			throw new IllegalArgumentException(
					Constants.EMPLOYEE_TYPE_CANNOT_BE_NULL_OR_EMPTY);
		}
		try {
			return EmployeeType.valueOf(employeeType.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(
					Constants.EMPLOYEE_MUST_BE_VALID_TYPE);
		}
	}

	private void checkForValidPassword(String password) {
		if (password == null || password.isBlank()) {
			throw new IllegalArgumentException(
					Constants.PASSWORD_CANNOT_BE_NULL_OR_EMPTY);
		}
	}

	private void checkForValidLastName(String lastName) {
		if (lastName == null || lastName.isBlank()) {
			throw new IllegalArgumentException(
					Constants.LAST_NAME_CANNOT_BE_NULL_OR_EMPTY);
		}
	}

	private void checkForValidEmployeeID(String employeeID) {
		if (employeeID == null || employeeID.isBlank()) {
			throw new IllegalArgumentException(
					Constants.EMPLOYEE_ID_CANNOT_BE_NULL_OR_EMPTY);
		}
	}

	private void checkForValidFirstName(String firstName) {
		if (firstName == null || firstName.isBlank()) {
			throw new IllegalArgumentException(
					Constants.FIRST_NAME_CANNOT_BE_NULL_OR_EMPTY);
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
		return List
				.copyOf(LocalEmployeeCredentialsManager.employeeCredentialsMap
						.values());
	}

	/**
	 * Removes an employee's credentials from local storage.
	 * 
	 * @precondition employeeID != null && getEmployeeIDs().contains(employeeID)
	 *               && !employeeID.isBlank()
	 * 
	 * @postcondition getEmployees().size() == getEmployees().size()@prev - 1
	 * 
	 * @param employeeID
	 *            The unique identifier for the employee to be removed.
	 * @return true if the employee was successfully removed, false otherwise.
	 */
	@Override
	public boolean removeEmployee(String employeeID) {
		this.checkForValidEmployeeID(employeeID);
		if (LocalEmployeeCredentialsManager.employeeCredentialsMap
				.containsKey(employeeID)) {
			LocalEmployeeCredentialsManager.employeeCredentialsMap
					.remove(employeeID);
			EmployeeCredentialsStorage.save(
					LocalEmployeeCredentialsManager.employeeCredentialsMap,
					Constants.EMPLOYEE_CREDENTIAL_FILE_LOCATION);
			return true;
		}
		return false;
	}

	/**
	 * Updates the password for a given employee.
	 * 
	 * @precondition employeeID != null && !employeeID.isBlank() && password !=
	 *               null && !password.isBlank()
	 * @postcondition getEmployeePassword(employeeID).equals(password)
	 * 
	 * @param employeeID
	 *            The unique identifier for the employee.
	 * @param password
	 *            The new password for the employee.
	 * @return true if the password was successfully updated, false otherwise.
	 */
	@Override
	public boolean updateEmployeePassword(String employeeID, String password) {
		this.checkForValidEmployeeID(employeeID);
		this.checkForValidPassword(password);
		if (LocalEmployeeCredentialsManager.employeeCredentialsMap
				.containsKey(employeeID)) {
			LocalEmployeeCredentials employee = LocalEmployeeCredentialsManager.employeeCredentialsMap
					.get(employeeID);
			employee.setPassword(password);
			EmployeeCredentialsStorage.save(
					LocalEmployeeCredentialsManager.employeeCredentialsMap,
					Constants.EMPLOYEE_CREDENTIAL_FILE_LOCATION);
			return true;
		}
		return false;
	}

	/**
	 * Attempts to login with the provided employee ID and password.
	 * 
	 * @precondition employeeID != null && !employeeID.isBlank() && password !=
	 *               null && !password.IsBlank()
	 * @postcondition none
	 * 
	 * @param employeeID
	 *            The employee's unique identifier.
	 * @param password
	 *            The password for the employee.
	 * @return true if login is successful, false otherwise.
	 */
	public boolean attemptLogin(String employeeID, String password) {
		this.checkForValidEmployeeID(employeeID);
		this.checkForValidPassword(password);
		if (LocalEmployeeCredentialsManager.employeeCredentialsMap
				.containsKey(employeeID)) {
			LocalEmployeeCredentials employee = LocalEmployeeCredentialsManager.employeeCredentialsMap
					.get(employeeID);
			if (employee.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Retrieves the password for a given employee ID.
	 * 
	 * @precondition employeeID != null && !employeeID.isBlank()
	 * @postcondition none
	 * 
	 * @param employeeID
	 *            The unique identifier for the employee.
	 * @return The password of the employee or null if not found.
	 */
	@Override
	public String getEmployeePassword(String employeeID) {
		this.checkForValidEmployeeID(employeeID);
		if (LocalEmployeeCredentialsManager.employeeCredentialsMap
				.containsKey(employeeID)) {
			return LocalEmployeeCredentialsManager.employeeCredentialsMap
					.get(employeeID).getPassword();
		}
		return null;
	}
}
