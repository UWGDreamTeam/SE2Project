package edu.westga.cs3212.inventory_manager.model;

public abstract class SystemCredentialsManager {

	
	/** Return the password associated with a specified employee ID
	 * 
	 * @precondition employeeID != null &&
	 * 				 getEmployeeIDs().contains(employeeID)
	 * @postcondition none
	 * 
	 * @param employeeID ID of the employee
	 * 
	 * @return password of the employee if getEmployeeIDs().contains(employeeID)
	 * 		   null if !getEmployeeIDs().contains(employeeID)
	 */
	public abstract String getEmployeePassword(String employeeID);
	
	/** Remove an employee with the specified ID
	 * 
	 * @precondition employeeID != null &&
	 * 				 getEmployeeIDs().contains(employeeID)
	 * @postcondition !getEmployeeIDs().contains(employeeID) &&
	 * 				  getEmployeePassword(employeeID) == null
	 * 
	 * @param employeeID ID of the employee
	 * 
	 * @return true if employee removed successfully
	 * 		   false if employee not removed successfully
	 */
	public abstract boolean removeEmployee(String employeeID);
	
	/**
	 * Adds a new employee with the specified details to the manager.
	 * 
	 * @precondition employeeID != null && !employeeID.isEmpty() &&
	 *               !getEmployeeIDs().contains(employeeID) &&
	 *               password != null && !password.isEmpty() &&
	 *               firstName != null && !firstName.isEmpty() &&
	 *               lastName != null && !lastName.isEmpty() &&
	 *               employeeType != null && !employeeType.isEmpty()
	 * @postcondition getEmployeeIDs().contains(employeeID) &&
	 *                getEmployeePassword(employeeID).equals(password) &&
	 *                getEmployeeFirstName(employeeID).equals(firstName) &&
	 *                getEmployeeLastName(employeeID).equals(lastName) &&
	 *                getEmployeeType(employeeID).equals(employeeType)
	 * 
	 * @param firstName The first name of the new employee.
	 * @param lastName The last name of the new employee.
	 * @param password The password for the new employee.
	 * @param employeeType The type of the employee (e.g., ADMIN, USER).
	 * 
	 */
	public abstract void addEmployee(String firstName, String lastName, String password, String employeeType);
	
	/** Update an existing employee's password with the specified new password
	 * 
	 * @precondition employeeID != null && !employeeID.isEmpty() &&
	 * 				 password != null &&
	 * 				 getEmployeeIDs().contains(employeeID)
	 * @postcondition getEmployeeIDs().contains(employeeID) &&
	 * 				  getEmployeePassword(employeeID).equals(password)
	 * 
	 * @param employeeID ID of the employee
	 * @param password new password for the employee
	 * 
	 * @return true if employee's password updated successfully
	 * 		   false if employee's password not updated successfully
	 */
	public abstract boolean updateEmployeePassword(String employeeID, String password);
	
}
