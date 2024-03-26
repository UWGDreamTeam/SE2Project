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
	 * @param employee The employee credentials to add. Must not be null.
	 * 
	 * @precondition The employee object must not be null.
	 * @postcondition A new employee is registered in the system with the provided credentials.
	 * 
	 * @return The employee ID assigned by the server if the addition is successful.
	 * 
	 * @throws IllegalArgumentException If the employee object is null.
	 */
	public static String addEmployee(LocalEmployeeCredentials employee) {
		if (employee == null) {
			throw new IllegalArgumentException("Cannot add null employee");
		}
		
		String employeeId = employee.getEmployeeID();
		String firstName = employee.getFirstName();
		String lastName = employee.getLastName();
		String password = employee.getPassword();
		EmployeeType role = employee.getEmployeeType();
		
		Map<String, Object> requestData = Map.of("data",
				Map.of("EmployeeID", employeeId, 
						"FirstName", firstName, 
						"LastName", lastName,
						"Password", password,
						"Role", role
						));

		Map<String, Object> dataMap = Server.sendRequestAndGetResponse("registerUser", requestData);
		return (String) dataMap.get("EmployeeID");
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
	 * @return An object containing the retrieved employee's credentials. If the employee does not exist, the behavior depends on the server's response (typically, an exception is thrown or null is returned).
	 * 
	 * @throws IllegalArgumentException If the employeeID is null or empty.
	 */
	public static LocalEmployeeCredentials getEmployee(String employeeID) {
		
		checkValidEmployeeID(employeeID);
		
		Map<String, Object> requestData = Map.of("EmployeeID", employeeID);
		Map<String, Object> dataMap = Server.sendRequestAndGetResponse("getEmployee", Map.of("data", requestData));
		
		LocalEmployeeCredentials employee = parseEmployee(dataMap, employeeID);
		
		return employee;
	}
	
	public static List<LocalEmployeeCredentials> getEmployees(){
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
	 * Clears all employee credentials from the system.
	 * 
	 * @precondition 	None.
	 * @postcondition 	Sends a request to the server to clear all employee credentials. 
	 * 					This might be used for resetting the system or during testing.
	 */
	public static void clearCredentials() {
		Server.sendRequestAndGetResponse("clearCredentials", Map.of("data", "none"));
	}
	
	/**
	 * Check valid employee ID.
	 *
	 * @param employeeID the employee ID
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
