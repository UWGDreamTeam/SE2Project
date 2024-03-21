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
    public static final String PRODUCT_ID_CANNOT_BE_NULL = "Product ID cannot be null";
    public static final String PRODUCT_ID_CANNOT_BE_BLANK = "Product ID cannot be empty";
    public static final String PASSWORDS_DO_NOT_MATCH = "Error: Passwords do not match.";
    public static final String REGISTRATION_ERROR = "Registration Error";
    public static final String MUST_FILL_OUT_ALL_FIELDS = "All fields must be filled out.";
    public static final String REGISTRATION_SUCCESSFUL = "Registration Successful";
    public static final String EMPLOYEE_REGISTRATION_SUCCESSFUL = "The employee has been successfully registered.";
    public static final String COMPONENT_QUANTITIES_FILE_LOCATION = "componentQuantities.json";
    public static final String ORDER_FILE_PATH = "orders.json";
    public static final String PRODUCT_NAME_CANNOT_BE_NULL = "Product name cannot be null";
    public static final String PRODUCT_NAME_CANNOT_BE_BLANK = "Product name cannot be blank";
    public static final String RECIPE_CANNOT_BE_NULL = "Recipe cannot be null";
    public static final String RECIPE_CANNOT_BE_EMPTY = "Recipe cannot be empty";
    public static final String PRODUCT_QUANTITY_CANNOT_BE_NEGATIVE = "Product quantity cannot be negative";
    public static final String PRODUCT_SALE_PRICE_CANNOT_BE_NEGATIVE = "Product sale price cannot be negative";
    public static final String INVALID_RESPONSE_FROM_SERVER = "Invalid response from server";
    public static final String COMPLETION_STATUS_CANNOT_BE_NULL = "Completion status cannot be null";
    public static final String ITEMS_CANNOT_BE_NULL = "Items cannot be null";
    public static final String ITEMS_CANNOT_BE_EMPTY = "Items cannot be empty";
    public static final String ORDER_ID_CANNOT_BE_NULL = "Order ID cannot be null";
    public static final String ORDER_ID_CANNOT_BE_BLANK = "Order ID cannot be blank";
    public static final int MINIMUM_QUANTITY_SPINNER_VALUE = 0;
    public static final int MAXIMUM_QUANTITY_SPINNER_VALUE = 100;
    public static final String COMPONENT_ID_CANNOT_BE_NULL = "Component ID cannot be null";
    public static final String COMPONENT_ID_CANNOT_BE_BLANK = "Component ID cannot be blank";
    public static final String COMPONENT_NAME_CANNOT_BE_NULL = "Component name cannot be null";
    public static final String COMPONENT_NAME_CANNOT_BE_BLANK = "Component name cannot be blank";
    public static final double MINIMUM_PRODUCTION_COST = 0;
    public static final String PRODUCT_INVENTORY_CANNOT_BE_INSTANTIATED = "Product inventory cannot be instantiated";
    public static final double MINIMUM_SALES_PRICE = 0;
    public static final String ORDER_INVENTORY_CANNOT_BE_INSTANTIATED = "Order inventory cannot be instantiated";
    

    private Constants() {
	throw new IllegalStateException("Constants class");
    }
}
