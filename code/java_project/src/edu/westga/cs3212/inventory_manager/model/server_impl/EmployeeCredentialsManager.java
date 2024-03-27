package edu.westga.cs3212.inventory_manager.model.server_impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.EmployeeType;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentials;

/**
 * Handles the the employee credentials for the system, including CRUD operations and
 * authentication via interactions with a server.
 * 
 * @author Vitor dos Santos
 * @version Spring 2024
 */
public class EmployeeCredentialsManager {
	

	/**
	 * Adds a new employee to the system by sending their credentials to the server.
	 * 
	 * @param password     	The password for the new employee.
	 * @param role 			The type of the employee (e.g., ADMIN, USER).
	 * @param firstName    	The first name of the new employee.
	 * @param lastName     	The last name of the new employee.
	 * 
	 * @precondition password != null && !password.isBlank && employeeType != null
	 *               && !role.isBlank() && firstName != null &&
	 *               !firstName.isBlank() && lastName != null && !lastName.isBlank()
	 *               
	 * @postcondition A new employee is registered in the system with the provided credentials.
	 * 
	 * @return The employee ID assigned by the server if the addition is successful.
	 * 
	 * @throws IllegalArgumentException If the employee object is null.
	 */
	public static String addEmployee(String firstName, String lastName, String password, EmployeeType role) {
		
		checkForValidFirstName(firstName);
		checkForValidLastName(lastName);
		checkForValidPassword(password);
		checkForValidRole(role);
		
		Map<String, Object> requestData = Map.of("data",
				Map.of("FirstName", firstName, 
						"LastName", lastName,
						"Password", password,
						"Role", role
						));

		Map<String, Object> dataMap = Server.sendRequestAndGetResponse("registerUser", requestData);
		return (String) dataMap.get("EmployeeID");
	}
	
	private static void checkForValidRole(EmployeeType role) {
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
	
	/**
	 * Attempts to log in a user by verifying their credentials with the server.
	 * 
	 * @param employeeID The ID of the employee attempting to log in. Must not be null or empty.
	 * @param password The password of the employee attempting to log in. Must not be null or empty.
	 * 
	 * @precondition The employeeID and password must not be null or empty.
	 * @postcondition The method contacts the server to verify the credentials.
	 * 
	 * @return True if the login is successful (i.e., credentials are valid according to the server), false otherwise.
	 * 
	 * @throws IllegalArgumentException If the employeeID is null or empty.
	 */
	public static boolean attemptLogin(String employeeID, String password) {
		checkValidEmployeeID(employeeID);
		
		Map<String, Object> requestData = Map.of("data",
				Map.of("EmployeeID", employeeID, "Password", password));
		
		Map<String, Object> dataMap = Server.sendRequestAndGetResponse("attemptLogin", requestData);
		
		return "true".equalsIgnoreCase((String) dataMap.get("LoginStatus"));
	}

	/**
	 * Retrieves an employee's credentials from the system by their ID.
	 * 
	 * @param employeeID The ID of the employee whose credentials are being retrieved. Must not be null or empty.
	 * 
	 * @precondition The employeeID must not be null or empty.
	 * @postcondition Contacts the server to fetch the employee's credentials.
	 * 
	 * @return A {@link LocalEmployeeCredentials} object containing the retrieved employee's credentials. 
	 * 
	 * @throws IllegalArgumentException If the employeeID is null or empty.
	 */
	public static LocalEmployeeCredentials getEmployeeCredentials(String employeeID) {
		
		checkValidEmployeeID(employeeID);
		
		Map<String, Object> requestData = Map.of("EmployeeID", employeeID);
		Map<String, Object> dataMap = Server.sendRequestAndGetResponse("getEmployee", Map.of("data", requestData));
		
		LocalEmployeeCredentials employee = parseEmployee(dataMap, employeeID);
		
		return employee;
	}
	
	/**
	 * Retrieves a list of all employees from the server. This method sends a request to the server to get a list of
	 * employees. The response is then parsed to create a list of {@link LocalEmployeeCredentials}.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return A list of {@link LocalEmployeeCredentials} representing all employees fetched from the server.
	 * 
	 * @throws ServerCommunicationException If there is an issue communicating with the server or if the server response
	 *         cannot be parsed into a list of employees.
	 */
	public static List<LocalEmployeeCredentials> getEmployees() {
		Map<String, Object> requestData = Map.of("NoArgs", "NoArgs");
		Map<String, Object> dataMap = Server.sendRequestAndGetResponse("getEmployeesList", Map.of("data", requestData));
		
		List<LocalEmployeeCredentials> employees = parseEmployeesList(dataMap);
		
		return List.copyOf(employees);
	}
	
	private static List<LocalEmployeeCredentials> parseEmployeesList(Map<String, Object> dataMap) {
		
		List<LocalEmployeeCredentials> employees = new ArrayList<>();
	    
        @SuppressWarnings("unchecked")
		Map<String, Map<String, String>> employeesData = (Map<String, Map<String, String>>) dataMap.get("data");
        
        for (Map.Entry<String, Map<String, String>> entry : employeesData.entrySet()) {
            String employeeID = entry.getKey();
            Map<String, String> employeeInfo = entry.getValue();
            
            String firstName = employeeInfo.get("FirstName");
            String lastName = employeeInfo.get("LastName");
            String password = employeeInfo.get("Password");
            String roleStr = employeeInfo.get("Role");
            
            EmployeeType role = convertEmployeeType(roleStr);
            
            LocalEmployeeCredentials employee = new LocalEmployeeCredentials(employeeID, firstName, lastName, password, role);
            employees.add(employee);
        }
	    
	    return employees;
	}
	
	/**
	 * Removes an employee from the system based on their unique employee ID. This method sends a request to the server
	 * to remove a user identified by the provided {@code employeeID}. The method checks the validity of the employee ID
	 * before sending the request. After the request, it interprets the server's response to determine whether the removal
	 * was successful.
	 *
	 * @precondition 	employeeID != null && 
	 * 					employeeID.isBlank() == false
	 * @postcondition 	If the employee exists and is successfully removed, the server will return a status indicating success.
	 *                	If the employee does not exist, or the removal is unsuccessful, the server's response will indicate failure.
	 *
	 * @param 	employeeID The unique identifier of the employee to be removed. Must not be null or empty.
	 * 
	 * @return 	{@code true} if the server successfully removed the employee, {@code false} otherwise. The method interprets
	 *         	the server's response, specifically looking for a "true" string value (case-insensitive) in the "RemoveStatus"
	 *         	field of the response data map.
	 * @throws 	IllegalArgumentException If the provided {@code employeeID} is null or does not meet the validity criteria
	 *         	established by {@link #checkValidEmployeeID(String)}. This includes checks for null or empty employee ID values.
	 */
	public static boolean removeEmployee(String employeeID) {
		checkValidEmployeeID(employeeID);
		
		Map<String, Object> requestData = Map.of("data",
				Map.of("EmployeeID", employeeID));
		
		Map<String, Object> dataMap = Server.sendRequestAndGetResponse("removeUser", requestData);
		
		return "true".equalsIgnoreCase((String) dataMap.get("RemoveStatus"));
	}

	/**
	 * Sends a request to the server to clear all employee credentials from the system.
	 * 
	 * @precondition 	None.
	 * @postcondition 	List of employees in the server is empty. 
	 * 					This might be used for resetting the system or during testing.
	 */
	public static void clearCredentials() {
		Server.sendRequestAndGetResponse("clearCredentials", Map.of("data", "none"));
	}
	
	/**
	 * Check valid employee ID.
	 *
	 * @param employeeID the employee ID
	 * @throws IllegalArgumentException If the employeeID is null or blank.
	 * 
	 */
	private static void checkValidEmployeeID(String employeeID) {
		if (employeeID == null || employeeID.isBlank()) {
			throw new IllegalArgumentException(Constants.EMPLOYEE_ID_CANNOT_BE_NULL_OR_EMPTY);
		}
	}
	
	/**
	 * Parses the employee.
	 *
	 * @param employeeData the employee data
	 * @param employeeID the employee ID
	 * @return (LocalEmployeeCredentials) the local employee credentials
	 */
	private static LocalEmployeeCredentials parseEmployee(Map<String, Object> employeeData, String employeeID) {
		String firstName = (String) employeeData.get("FirstName");
		String lastName = (String) employeeData.get("LastName");
		String password = (String) employeeData.get("Password");
		String roleString = (String) employeeData.get("Role");
		EmployeeType role = convertEmployeeType(roleString);
		
		LocalEmployeeCredentials employee = new LocalEmployeeCredentials(
				employeeID, 
				firstName,
				lastName,
				password,
				role);
		
		return employee;
	}
	
	/**
	 * Convert employee type.
	 *
	 * @param role the employee role
	 * @return the employee type
	 */
	private static EmployeeType convertEmployeeType(String role) {
		if ("manager".equalsIgnoreCase(role)) {
			return EmployeeType.MANAGER;
		} else {
			return EmployeeType.WORKER;
		}
	}
}
