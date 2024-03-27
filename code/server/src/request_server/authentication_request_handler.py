'''
Created on Mar 21, 2024

@author: Vitor dos Santos
'''
from model.employee_credentials import EmployeeCredentials
from model.utilities import log

employees = {}

def generate_username(first_name, last_name):
    """
    Generates a unique username based on the first character of the first and last names provided,
    followed by a sequential four-digit number starting at '0001'. If a username already exists,
    it increments the number by one to ensure uniqueness.
    
    Parameters:
        first_name (str): The first name of the person.
        last_name (str): The last name of the person.
    
    Pre-conditions:
    - first_name != None
    - last_name != None
    
    Post-conditions:
    - A username is generated that is unique in the 'employees' dictionary.
    
    Returns:
    - str: A unique username in the format <first initial><last initial><four-digit number>.
    """
    base_username = f"{first_name[0].lower()}{last_name[0].lower()}"
    
    count = 1;
    
    for user in employees.keys():
        first = user[0]
        last = user[1]
        
        if base_username == first + last:
            count+=1
    
    username = f"{base_username}{count:04}"
    
    return username
    
def registerUser(data):
    """
    Registers a new user with the provided credentials and adds them to the global employees dictionary.

    Parameters:
        data (dict): Must contain 'FirstName', 'LastName', 'Password', and 'Role'.

    Preconditions:
        - 'data' must not be None and should be a dictionary with all required keys present.
        - A unique 'EmployeeID' is generated and should not already exist in the 'employees' dictionary.

    Postconditions:
        - A new EmployeeCredentials object is created with a unique 'EmployeeID' and added to 'employees'.
    
    Returns:
        dict: A dictionary with the status 'success' and the new 'EmployeeID', or 'error' if the user cannot be registered.
    """
    
    if data == None:
        log(f"Cannot register user, Data map is None")
        return {"status": "error", "message": "Cannot register user, Data map is None"}
    
    first_name = data.get("FirstName")
    last_name = data.get("LastName")
    password = data.get("Password")
    role = data.get("Role")
    
    employee_id = generate_username(first_name, last_name)
    
    employee = EmployeeCredentials(employee_id, first_name, last_name, password, role)
    
    employees[employee_id] = employee
    
    log(f"User {employee_id} Registered successfully")
    return {"status": "success", "data": {"EmployeeID": employee_id}}
    
def getEmployee(data):
    """
    Retrieves an employee's credentials from the global employees dictionary based on their 'EmployeeID'.

    Parameters:
        data (dict): Must contain 'EmployeeID' of the employee whose credentials are to be retrieved.

    Preconditions:
        - 'EmployeeID' must be a key that corresponds to an existing entry in the 'employees' dictionary.

    Postconditions:
        - None.

    Returns:
        dict: A response object with a status 'success' and the employee's data if found, or status 'error' if not found.
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
    Attempts to log in an employee by verifying their 'EmployeeID' and 'Password' against the stored credentials.

    Parameters:
        data (dict): Must contain 'EmployeeID' and 'Password' for verification.

    Preconditions:
        - 'EmployeeID' must correspond to an existing employee in the 'employees' dictionary.

    Postconditions:
        - None.

    Returns:
        dict: A response object with status 'success' and login status if credentials are valid, or status 'error' if invalid.
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
        return {"status": "error", "message": "Employee list is empty"}
    
    employees_dict = {"status": "success", "data": {}}
    
    for employee_id, employee in employees.items():
        employees_dict["data"][employee_id] = {
            "FirstName": employee.getFirstName(),
            "LastName": employee.getLastName(),
            "Password": employee.getPassword(),
            "Role": employee.getRole()
        }
    log(employees_dict)
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
    
    employee_id = data.get("EmployeeID")
    
    if employee_id not in employees.keys():
        log(f"User {employee_id} not found for updating")
        return {"status": "error", "message": "Cannot edit, User does not exist"}
    
    else:
        first_name = data.get("FirstName")
        last_name = data.get("LastName")
        password = data.get("Password")
        role = data.get("Role")
        
        employee = EmployeeCredentials(employee_id, first_name, last_name, password, role)
        
        employees[employee_id] = employee
        log(f"User {employee_id} updated successfully")
        return {"status": "success", "data": {"RemoveStatus": "true"}}
        
def removeUser(data):
    """
    Removes an employee's credentials from the global employees dictionary.

    Parameters:
        data (dic): Must include EmployeeID

    Preconditions:
        - 'employee_id' must correspond to an existing employee in the global 'employees' dictionary.

    Postconditions:
        - The EmployeeCredentials object for the given 'employee_id' is removed from 'employees'.

    Returns:
        bool: True if the removal is successful, False if the user does not exist.
    """
    
    employee_id = data.get("EmployeeID")
    
    if employee_id not in employees.keys():
        log(f"User {employee_id} not found for removing")
        return {"status": "error", "message": "Cannot remove, user does not exist"}
        
    else:
        employees.pop(employee_id)
        log(f"User {employee_id} removed successfully")
        
        return {"status": "success", "data": {"RemoveStatus": "true"}}
    
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
    
    
    
    
    
    
    
    
    
        