'''
Created on Mar 21, 2024

@author: Vitor dos Santos
'''
from model.employee_credentials import EmployeeCredentials
from model.utilities import log

employees = {}


    
def attemptLogin(data):
    """
    Attempts to log in an employee by verifying their password against the stored credentials.

    Parameters:
        data (dict): Must contain 'Id' as the employee ID and 'password' for verification.

    Preconditions:
        - 'data' must not be None and should be a dictionary.
        - 'Id' must correspond to an existing employee in the global 'employees' dictionary.

    Postconditions:
        - None

    Returns:
        bool: True if the password is correct, False otherwise.
    """
    employee_id = data.get("Id")
    password = data.get("password")
    
    if len(employees.keys()) == 0:
        return False
    
    elif employee_id not in employees.keys():
        return False
    
    else:
        employee = employees.get(employee_id)
        return password == employee.getPassword()
    
def clear():
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
    
    employee_id = data.get("Id")
    
    if employee_id in employees.keys():
        log(f"User {employee_id} already exists")
        return False
    
    else:
        first_name = data.get("firstName")
        last_name = data.get("lastName")
        password = data.get("password")
        role = data.get("role")
        
        employee = EmployeeCredentials(employee_id, first_name, last_name, password, role)
        
        employees[employee_id] = employee
        log(f"User {employee_id} Registered successfully")
        return True
    
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
        
def getUser(employee_id):
    """
    Retrieves a user's credentials from the global employees dictionary.

    Parameters:
        employee_id (str): The ID of the employee whose credentials are to be retrieved.

    Preconditions:
        - 'employee_id' must correspond to an existing employee in the global 'employees' dictionary.

    Postconditions:
        - None.

    Returns:
        EmployeeCredentials: The credentials of the employee if they exist, None otherwise.
    """
    
    if employee_id not in employees.keys():
        log(f"User {employee_id} not found")
        return None
    
    return employees.get(employee_id)
    

    
    
    
    
    
    
    
    
    
    
        