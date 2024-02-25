package edu.westga.cs3212.inventory_manager.model;

public final class Constants {

	public static final String EMPLOYEE_ID_CANNOT_BE_NULL_OR_EMPTY = "Employee ID cannot be null or empty";
	public static final String FIRST_NAME_CANNOT_BE_NULL_OR_EMPTY = "First name cannot be null or empty";
	public static final String LAST_NAME_CANNOT_BE_NULL_OR_EMPTY = "Last name cannot be null or empty";
	public static final String PASSWORD_CANNOT_BE_NULL_OR_EMPTY = "Password cannot be null or empty";
	public static final String EMPLOYEE_TYPE_CANNOT_BE_NULL_OR_EMPTY = "Employee type cannot be null or empty";
	public static final String EMPLOYEE_MUST_BE_VALID_TYPE = "Employee must be a valid type";
	public static final String EMPLOYEE_CREDENTIAL_FILE_LOCATION = "employeeCredentials.json";
	public static final String PRODUCT_INVENTORY_FILE_LOCATION = "productInventory.json";
	public static final String COMPONENT_INVENTORY_FILE_LOCATION = "componentInventory.json";
	public static final String LOGIN_ERROR_MESSAGE = "Login failed, please check your credentials and try again";
	public static final String LOGIN_SUCCESS_MESSAGE = "Login successful";
	
	public static final String PASSWORDS_DO_NOT_MATCH = "Error: Passwords do not match.";
	public static final String REGISTRATION_ERROR = "Registration Error";
	public static final String MUST_FILL_OUT_ALL_FIELDS = "All fields must be filled out.";
	public static final String REGISTRATION_SUCCESSFUL = "Registration Successful";
	public static final String EMPLOYEE_REGISTRATION_SUCCESSFUL = "The employee has been successfully registered.";
	
	
	private Constants() {
		throw new IllegalStateException("Constants class");
	}
}
