'''
Created on Mar 21, 2024

@author: Vitor dos Santos
'''
from model.employee_credentials import EmployeeCredentials
from model.utilities import log

employees = {}

def registerUser(data):
    """
    Registers a new user with the provided credentials and adds them to the global employees dictionary.

    Parameters:
        data (dict): Must contain 'Id', 'firstName', 'lastName', 'password', and 'role'.

    Preconditions:
        - 'data' must not be None and should be a dictionary.
        - 'Id' must not already be present in the global 'employees' dictionary.

    Postconditions:
        - If successful, a new EmployeeCredentials object is created and added to 'employees'.

    Returns:
        bool: True if the registration is successful, False if the user already exists.
    """
    
    employee_id = data.get("EmployeeID")
    
    if employee_id in employees.keys():
        log(f"User {employee_id} already exists")
        return {"status": "error", "message": "User already exists"}
    
    else:
        first_name = data.get("FirstName")
        last_name = data.get("LastName")
        password = data.get("Password")
        role = data.get("Role")
        employee = EmployeeCredentials(employee_id, first_name, last_name, password, role)
        
        employees[employee_id] = employee
        log(f"User {employee_id} Registered successfully")
        return {"status": "success", "data": {"EmployeeID": employee_id}}
    
def getEmployee(data):
    """
    Retrieves a employee's credentials from the global employees dictionary.

    Parameters:
        data (dict): Must contain "EmployeeID" of the employee whose credentials are to be retrieved.

    Preconditions:
        - 'employee_id' must correspond to an existing employee in the global 'employees' dictionary.

    Postconditions:
        - None.

    Returns:
        EmployeeCredentials: The credentials of the employee if they exist, None otherwise.
    """
    employee_id = data.get("EmployeeID")
    
    if employee_id not in employees.keys():
        log(f"User {employee_id} not found")
        return {"status": "error", "message": "User does not exists"}
    
    employee = employees.get(employee_id)
    
    firstName = employee.getFirstName()
    lastName = employee.getLastName()
    password = employee.getPassword()
    role = employee.getRole()
    
    log(f"User {employee_id} found successfully!")
    return {"status": "success", "data": {"EmployeeID": employee_id, "FirstName" : firstName, "LastName": lastName, "Password" : password, "Role" : role}}

def attemptLogin(data):
    """
    Attempts to log in an employee by verifying their password against the stored credentials.

    Parameters:
        data (dict): Must contain 'EmployeeID' as the employee ID and 'password' for verification.

    Preconditions:
        - 'data' must not be None and should be a dictionary.
        - 'Id' must correspond to an existing employee in the global 'employees' dictionary.

    Postconditions:
        - None

    Returns:
        bool: True if the password is correct, False otherwise.
    """
    employee_id = data.get("EmployeeID")
    password = data.get("Password")
    
    if len(employees.keys()) == 0:
        return False
    
    elif employee_id not in employees.keys():
        return {"status": "error", "message": "Invalid credentials for Login"}
    
    else:
        employee = employees.get(employee_id)
        is_valid = password == employee.getPassword()
        
        if is_valid:
            log("Login Successful")
            return {"status": "success", "data": {"LoginStatus": "true"}}
        else:
            return {"status": "error", "message": "Invalid credentials for Login"}
    
def getEmployeesList(data):
    """
    Retrieves a list of all employee credentials stored in the global employees dictionary and formats them into a structured dictionary.

    Parameters:
        None.

    Preconditions:
        - The global 'employees' dictionary is accessible.
        - The function does not require any input parameters.

    Postconditions:
        - If the 'employees' dictionary is not empty, returns a structured dictionary containing employee credentials.
        - If the 'employees' dictionary is empty, an exception is raised, indicating that there are no employees to list.

    Returns:
        dict: A structured dictionary with the status and a nested dictionary of employee credentials if the 'employees' dictionary is not empty.
        
    Raises:
        Exception: If the 'employees' dictionary is empty, indicating there are no employees to list.
    """
    if len(employees) == 0:
        raise Exception("Employees list is empty")
    
    employees_dict = {"status": "success", "data": {}}
    
    for employee_id, employee in employees.items():
        employees_dict["data"][employee_id] = {
            "FirstName": employee.getFirstName(),
            "LastName": employee.getLastName(),
            "Password": employee.getPassword(),
            "Role": employee.getRole()
        }
    
    return {"status": "success", "data": employees_dict}
    
def updateUser(data):
    """
    Updates the credentials for an existing employee in the global employees dictionary.

    Parameters:
        data (dict): Must contain 'Id', 'firstName', 'lastName', 'password', and 'role'.

    Preconditions:
        - 'data' must not be None and should be a dictionary.
        - 'Id' must correspond to an existing employee in the global 'employees' dictionary.

    Postconditions:
        - The EmployeeCredentials object for the given 'Id' is updated with new data.

    Returns:
        bool: True if the update is successful, False if the user does not exist.
    """
    
    employee_id = data.get("Id")
    
    if employee_id not in employees.keys():
        log(f"User {employee_id} not found for updating")
        return False
    
    else:
        first_name = data.get("firstName")
        last_name = data.get("lastName")
        password = data.get("password")
        role = data.get("role")
        
        employee = EmployeeCredentials(employee_id, first_name, last_name, password, role)
        
        employees[employee_id] = employee
        log(f"User {employee_id} updated successfully")
        return True
        
def removeUser(employee_id):
    """
    Removes an employee's credentials from the global employees dictionary.

    Parameters:
        employee_id (str): The ID of the employee to remove.

    Preconditions:
        - 'employee_id' must correspond to an existing employee in the global 'employees' dictionary.

    Postconditions:
        - The EmployeeCredentials object for the given 'employee_id' is removed from 'employees'.

    Returns:
        bool: True if the removal is successful, False if the user does not exist.
    """
    
    if employee_id not in employees.keys():
        log(f"User {employee_id} not found for removing")
        return False
        
    else:
        employees.pop(employee_id)
        log(f"User {employee_id} removed successfully")
        
        return True
    
def clear(data):
    """
    Clears all employee credentials from the global employees dictionary.

    Parameters:
        None.

    Preconditions:
        - None.

    Postconditions:
        - The global 'employees' dictionary is emptied, removing all employee credentials.
        
    Returns:
        None.
    """
    employees.clear()
    log("Employees collection is clear")
    return {"status": "success", "message": "List of employees is empty"}
    
    
    
    
    
    
    
    
    
        