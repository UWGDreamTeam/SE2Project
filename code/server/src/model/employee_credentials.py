'''
Created on Mar 20, 2024.

@author: Vitor dos Santos
'''
class EmployeeCredentials:
    """
    A class for storing basic credentials of an employee.
    
    This class encapsulates the employee's ID, first and last names, password, and role.
    """
    
    def __init__(self, employee_id, first_name, last_name, password, employee_role):
        """
        Initializes a new EmployeeCredentials instance with the provided employee information.

        Params:
        employee_id (str): The unique ID of the employee.
        first_name (str): The first name of the employee.
        last_name (str): The last name of the employee.
        password (str): The password for the employee.
        employee_role (str): The role of the employee.

        Preconditions:
        - employee_id  != None
        - first_name != None
        - last_name != None
        - password != None
        - employee_role != None

        Postconditions:
        - self.getEmployeeID() == employee_id
        - self.getFirstName() == first_name
        - self.getLastName() == last_name
        - self.getPassword() == password
        - self.getRole() == employee_role

        Raises:
        Exception: If any of the parameters are None.
        """
        
        if (employee_id == None):
            raise Exception("Employee ID is None")
        
        if (first_name == None):
            raise Exception("First Name is None")
        
        if (last_name == None):
            raise Exception("Last name is None")
        
        if (password == None):
            raise Exception("Password is None")
        
        if (employee_role == None):
            raise Exception("Role is None")
        
        self._employee_id = employee_id
        self._first_name = first_name
        self._last_name = last_name
        self._password = password
        self._employee_role = employee_role
        
    def getEmployeeID(self):
        """
        Returns the employee ID.

        Preconditions:
        - The employee ID has been set during initialization.

        Postconditions:
        - None.

        Return:
        str: The ID of the employee.
        """
        return self._employee_id
        
    def getFirstName(self):
        """
        Returns the first name of the employee.

        Preconditions:
        - The first name has been set during initialization.

        Postconditions:
        - None.

        Return:
        str: The first name of the employee.
        """
        return self._first_name
    
    def getLastName(self):
        """
        Returns the last name of the employee.

        Preconditions:
        - The last name has been set during initialization.

        Postconditions:
        - None.

        Return:
        str: The last name of the employee.
        """
        return self._last_name
        
    def getPassword(self):
        """
        Returns the password of the employee.

        Preconditions:
        - The password has been set during initialization.

        Postconditions:
        - None.

        Return:
        str: The password of the employee.
        """
        return self._password
    
    def getRole(self):
        """
        Returns the role of the employee.

        Preconditions:
        - The role has been set during initialization.

        Postconditions:
        - None.

        Return:
        str: The role of the employee.
        """
        return self._employee_role
    