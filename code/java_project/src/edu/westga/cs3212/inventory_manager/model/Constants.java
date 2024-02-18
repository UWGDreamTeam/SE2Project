package edu.westga.cs3212.inventory_manager.model;

public final class Constants {

	public static final String EMPLOYEE_ID_CANNOT_BE_NULL = "Employee ID cannot be null or empty";
	public static final String FIRST_NAME_CANNOT_BE_NULL = "First name cannot be null or empty";
	public static final String LAST_NAME_CANNOT_BE_NULL = "Last name cannot be null or empty";
	public static final String PASSWORD_CANNOT_BE_NULL = "Password cannot be null or empty";
	public static final String EMPLOYEE_TYPE_CANNOT_BE_NULL = "Employee type cannot be null or empty";
	public static final String EMPLOYEE_MUST_BE_VALID_TYPE = "Employee must be a valid type";
	public static final String EMPLOYEE_CREDENTIAL_FILE_LOCATION = "employeeCredentials.json";
	public static final String LOGIN_ERROR_MESSAGE = "Login failed, please check your credentials and try again";
	public static final String LOGIN_SUCCESS_MESSAGE = "Login successful";
	
	private Constants() {
		throw new IllegalStateException("Constants class");
	}
}
