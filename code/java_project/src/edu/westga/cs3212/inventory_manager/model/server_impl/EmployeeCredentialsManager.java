package edu.westga.cs3212.inventory_manager.model.server_impl;

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

	public static LocalEmployeeCredentials getEmployee(String employeeID) {
		checkValidEmployeeID(employeeID);
		Map<String, Object> requestData = Map.of("EmployeeID", employeeID);
		Map<String, Object> dataMap = Server.sendRequestAndGetResponse("getEmployee", Map.of("data", requestData));
		
		LocalEmployeeCredentials employee = parseEmployee(dataMap, employeeID);
		
		return employee;
	}
	
	public static void clearCredentials() {
		Server.sendRequestAndGetResponse("clearCredentials", Map.of("data", "none"));
	}
	
	private static void checkValidEmployeeID(String employeeID) {
		if (employeeID == null) {
			throw new IllegalArgumentException(Constants.EMPLOYEE_ID_CANNOT_BE_NULL_OR_EMPTY);
		}
	}
	
	private static LocalEmployeeCredentials parseEmployee(Map<String, Object> employeeData, String employeeID) {
		String firstName = (String) employeeData.get("FirstName");
		String lastName = (String) employeeData.get("LastName");
		String password = (String) employeeData.get("Password");
		String roleString = (String) employeeData.get("EmployeeType");
		EmployeeType role = convertEmployeeType(roleString);
		
		LocalEmployeeCredentials employee = new LocalEmployeeCredentials(
				employeeID, 
				firstName,
				lastName,
				password,
				role);
		
		return employee;
	}
	
	private static EmployeeType convertEmployeeType(String role) {
		if (role.equals("manager")) {
			return EmployeeType.MANAGER;
		} else {
			return EmployeeType.WORKER;
		}
	}
}
