package edu.westga.cs3212.inventory_manager.model.server.credentials;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.credentials.EmployeeType;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentials;
import edu.westga.cs3212.inventory_manager.model.server.Server;

/**
 * Handles the the employee credentials for the system, including CRUD
 * operations and authentication via interactions with a server.
 * 
 * @author Vitor dos Santos
 * @version Spring 2024
 */
public class EmployeeCredentialsManager {

	private static final String ROLE_STRING_CONST = "Role";
	private static final String LAST_NAME_STRING_CONST = "LastName";
	private static final String FIRST_NAME_STRING_CONST = "FirstName";
	private static final String PASSWORD_STRING_CONST = "Password";
	private static final String REMOVE_STATUS_STRING_CONST = "RemoveStatus";
	private static final String TRUE_REPONSE_STRING_CONST = "true";
	private static final String LOGIN_STATUS_STRING_CONST = "LoginStatus";
	private static final String EMPLOYEE_ID_STRING_CONST = "EmployeeID";
	private static final String MANAGER_ROLE_CONST = "manager";
	private static final String CLEAR_CREDENTIALS_REQUEST_TYPE = "clearCredentials";
	private static final String REMOVE_USER_REQUEST_TYPE = "removeUser";
	private static final String GET_EMPLOYEES_LIST_REQUEST_TYPE = "getEmployeesList";
	private static final String GET_EMPLOYEE_REQUEST_TYPE = "getEmployee";
	private static final String ATTEMPT_LOGIN_REQUEST_TYPE = "attemptLogin";
	private static final String REGISTER_USER_REQUEST_TYPE = "registerUser";
	private static final String DATA_KEY = "data";
	
	private EmployeeCredentialsManager() {
		
	}

	/**
	 * Adds a new employee to the system by sending their credentials to the server.
	 * 
	 * @param firstName The first name of the new employee. Must not be null or
	 *                  empty.
	 * @param lastName  The last name of the new employee. Must not be null or
	 *                  empty.
	 * @param password  The password for the new employee. Must not be null or
	 *                  empty.
	 * @param role      The role of the new employee (e.g., EmployeeType.ADMIN,
	 *                  EmployeeType.USER).
	 * 
	 * @precondition firstName != null && !firstName.isBlank() && lastName != null
	 *               && !lastName.isBlank() && password != null &&
	 *               !password.isBlank() && role != null
	 * 
	 * @postcondition A new employee is registered in the system with the provided
	 *                credentials.
	 * 
	 * @return The employee ID assigned by the server if the addition is successful.
	 * 
	 * @throws IllegalArgumentException If any of the input parameters are null or
	 *                                  do not meet validity criteria.
	 */
	public static String addEmployee(String firstName, String lastName, String password, EmployeeType role) {

		checkForValidFirstName(firstName);
		checkForValidLastName(lastName);
		checkForValidPassword(password);
		checkForValidRole(role);

		Map<String, Object> requestData = Map.of(DATA_KEY, Map.of(FIRST_NAME_STRING_CONST, firstName,
				LAST_NAME_STRING_CONST, lastName, PASSWORD_STRING_CONST, password, ROLE_STRING_CONST, role));

		Map<String, Object> dataMap = Server.sendRequestAndGetResponse(REGISTER_USER_REQUEST_TYPE, requestData);
		return (String) dataMap.get(EMPLOYEE_ID_STRING_CONST);
	}
	
	/**
	 * Adds a new employee to the system by sending their credentials to the server.
	 * 
	 * @param firstName The first name of the new employee. Must not be null or
	 *                  empty.
	 * @param lastName  The last name of the new employee. Must not be null or
	 *                  empty.
	 * @param password  The password for the new employee. Must not be null or
	 *                  empty.
	 * @param role      The role of the new employee (e.g., EmployeeType.ADMIN,
	 *                  EmployeeType.USER).
	 * 
	 * @precondition firstName != null && !firstName.isBlank() && lastName != null
	 *               && !lastName.isBlank() && password != null &&
	 *               !password.isBlank() && role != null
	 * 
	 * @postcondition A new employee is registered in the system with the provided
	 *                credentials.
	 * 
	 * @return The employee ID assigned by the server if the addition is successful.
	 * 
	 * @throws IllegalArgumentException If any of the input parameters are null or
	 *                                  do not meet validity criteria.
	 */
	public static String addEmployee(String firstName, String lastName, String password, String roleStr) {
		checkForValidFirstName(firstName);
		checkForValidLastName(lastName);
		checkForValidPassword(password);
		checkForValidRole(roleStr);

		EmployeeType role = convertEmployeeType(roleStr);
		
		return addEmployee(firstName, lastName, password, role);
	}

	/**
	 * Attempts to log in a user by verifying their credentials with the server.
	 * 
	 * @param employeeID The ID of the employee attempting to log in. Must not be
	 *                   null or empty.
	 * @param password   The password of the employee attempting to log in. Must not
	 *                   be null or empty.
	 * 
	 * @precondition The employeeID and password must not be null or empty.
	 * @postcondition The method contacts the server to verify the credentials.
	 * 
	 * @return True if the login is successful (i.e., credentials are valid
	 *         according to the server), false otherwise.
	 * 
	 * @throws IllegalArgumentException If the employeeID is null or empty.
	 */
	public static boolean attemptLogin(String employeeID, String password) {
		checkValidEmployeeID(employeeID);

		Map<String, Object> requestData = Map.of(DATA_KEY,
				Map.of(EMPLOYEE_ID_STRING_CONST, employeeID, PASSWORD_STRING_CONST, password));

		Map<String, Object> dataMap = Server.sendRequestAndGetResponse(ATTEMPT_LOGIN_REQUEST_TYPE, requestData);

		return TRUE_REPONSE_STRING_CONST.equalsIgnoreCase((String) dataMap.get(LOGIN_STATUS_STRING_CONST));
	}

	/**
	 * Retrieves an employee's credentials from the system by their ID.
	 * 
	 * @param employeeID The ID of the employee whose credentials are being
	 *                   retrieved. Must not be null or empty.
	 * 
	 * @precondition The employeeID must not be null or empty.
	 * @postcondition Contacts the server to fetch the employee's credentials.
	 * 
	 * @return A {@link LocalEmployeeCredentials} object containing the retrieved
	 *         employee's credentials.
	 * 
	 * @throws IllegalArgumentException If the employeeID is null or empty.
	 */
	public static LocalEmployeeCredentials getEmployeeCredentials(String employeeID) {

		checkValidEmployeeID(employeeID);

		Map<String, Object> requestData = Map.of(EMPLOYEE_ID_STRING_CONST, employeeID);
		Map<String, Object> dataMap = Server.sendRequestAndGetResponse(GET_EMPLOYEE_REQUEST_TYPE,
				Map.of(DATA_KEY, requestData));

		LocalEmployeeCredentials employee = parseEmployee(dataMap, employeeID);

		return employee;
	}

	/**
	 * Retrieves a list of all employees from the server. This method sends a
	 * request to the server to get a list of employees. The response is then parsed
	 * to create a list of {@link LocalEmployeeCredentials}.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return A list of {@link LocalEmployeeCredentials} representing all employees
	 *         fetched from the server.
	 * 
	 * @throws ServerCommunicationException If there is an issue communicating with
	 *                                      the server or if the server response
	 *                                      cannot be parsed into a list of
	 *                                      employees.
	 */
	public static List<LocalEmployeeCredentials> getEmployees() {
		Map<String, Object> dataMap = Server.sendRequestAndGetResponse(GET_EMPLOYEES_LIST_REQUEST_TYPE);

		List<LocalEmployeeCredentials> employees = parseEmployeesList(dataMap);

		return List.copyOf(employees);
	}

	private static List<LocalEmployeeCredentials> parseEmployeesList(Map<String, Object> dataMap) {

		List<LocalEmployeeCredentials> employees = new ArrayList<>();

		@SuppressWarnings("unchecked")
		Map<String, Map<String, String>> employeesData = (Map<String, Map<String, String>>) dataMap.get(DATA_KEY);

		for (Map.Entry<String, Map<String, String>> entry : employeesData.entrySet()) {
			String employeeID = entry.getKey();
			Map<String, String> employeeInfo = entry.getValue();

			String firstName = employeeInfo.get(FIRST_NAME_STRING_CONST);
			String lastName = employeeInfo.get(LAST_NAME_STRING_CONST);
			String password = employeeInfo.get(PASSWORD_STRING_CONST);
			String roleStr = employeeInfo.get(ROLE_STRING_CONST);

			EmployeeType role = convertEmployeeType(roleStr);

			LocalEmployeeCredentials employee = new LocalEmployeeCredentials(employeeID, firstName, lastName, password,
					role);
			employees.add(employee);
		}

		return employees;
	}

	/**
	 * Removes an employee from the system based on their unique employee ID. This
	 * method sends a request to the server to remove a user identified by the
	 * provided {@code employeeID}. The method checks the validity of the employee
	 * ID before sending the request. After the request, it interprets the server's
	 * response to determine whether the removal was successful.
	 *
	 * @precondition employeeID != null && employeeID.isBlank() == false
	 * @postcondition If the employee exists and is successfully removed, the server
	 *                will return a status indicating success. If the employee does
	 *                not exist, or the removal is unsuccessful, the server's
	 *                response will indicate failure.
	 *
	 * @param employeeID The unique identifier of the employee to be removed. Must
	 *                   not be null or empty.
	 * 
	 * @return {@code true} if the server successfully removed the employee,
	 *         {@code false} otherwise. The method interprets the server's response,
	 *         specifically looking for a "true" string value (case-insensitive) in
	 *         the "RemoveStatus" field of the response data map.
	 * @throws IllegalArgumentException If the provided {@code employeeID} is null
	 *                                  or does not meet the validity criteria
	 *                                  established by
	 *                                  {@link #checkValidEmployeeID(String)}. This
	 *                                  includes checks for null or empty employee
	 *                                  ID values.
	 */
	public static boolean removeEmployee(String employeeID) {
		checkValidEmployeeID(employeeID);

		Map<String, Object> requestData = Map.of(DATA_KEY, Map.of(EMPLOYEE_ID_STRING_CONST, employeeID));

		Map<String, Object> dataMap = Server.sendRequestAndGetResponse(REMOVE_USER_REQUEST_TYPE, requestData);

		return TRUE_REPONSE_STRING_CONST.equalsIgnoreCase((String) dataMap.get(REMOVE_STATUS_STRING_CONST));
	}

	/**
	 * Sends a request to the server to clear all employee credentials from the
	 * system.
	 * 
	 * @precondition None.
	 * @postcondition List of employees in the server is empty. This might be used
	 *                for resetting the system or during testing.
	 */
	public static void clearCredentials() {
		Server.sendRequestAndGetResponse(CLEAR_CREDENTIALS_REQUEST_TYPE);
	}

	private static LocalEmployeeCredentials parseEmployee(Map<String, Object> employeeData, String employeeID) {
		String firstName = (String) employeeData.get(FIRST_NAME_STRING_CONST);
		String lastName = (String) employeeData.get(LAST_NAME_STRING_CONST);
		String password = (String) employeeData.get(PASSWORD_STRING_CONST);
		String roleString = (String) employeeData.get(ROLE_STRING_CONST);
		EmployeeType role = convertEmployeeType(roleString);

		LocalEmployeeCredentials employee = new LocalEmployeeCredentials(employeeID, firstName, lastName, password,
				role);

		return employee;
	}

	private static EmployeeType convertEmployeeType(String role) {
		if (MANAGER_ROLE_CONST.equalsIgnoreCase(role)) {
			return EmployeeType.MANAGER;
		} else {
			return EmployeeType.WORKER;
		}
	}

	private static void checkValidEmployeeID(String employeeID) {
		if (employeeID == null || employeeID.isBlank()) {
			throw new IllegalArgumentException(Constants.EMPLOYEE_ID_CANNOT_BE_NULL_OR_EMPTY);
		}
	}

	private static void checkForValidRole(EmployeeType role) {
		if (role == null) {
			throw new IllegalArgumentException(Constants.EMPLOYEE_TYPE_CANNOT_BE_NULL_OR_EMPTY);
		}
	}
	
	private static void checkForValidRole(String role) {
		if (role == null) {
			throw new IllegalArgumentException(Constants.EMPLOYEE_TYPE_CANNOT_BE_NULL_OR_EMPTY);
		}
	}

	private static void checkForValidPassword(String password) {
		if (password == null || password.isBlank()) {
			throw new IllegalArgumentException(Constants.PASSWORD_CANNOT_BE_NULL_OR_EMPTY);
		}
	}

	private static void checkForValidLastName(String lastName) {
		if (lastName == null || lastName.isBlank()) {
			throw new IllegalArgumentException(Constants.LAST_NAME_CANNOT_BE_NULL_OR_EMPTY);
		}
	}

	private static void checkForValidFirstName(String firstName) {
		if (firstName == null || firstName.isBlank()) {
			throw new IllegalArgumentException(Constants.FIRST_NAME_CANNOT_BE_NULL_OR_EMPTY);
		}
	}
}
