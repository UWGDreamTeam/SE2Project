'''
Created on Mar 20, 2024

@author: Vitor dos Santos
'''

class EmployeeCredentials:
    '''
    Stores basic information for an employee credentials
    '''
    
    def __init__(self, employee_id, first_name, last_name, password, employee_role):
        ''' 
            Create a new recipe with the provided information.
            
            @precondition employee_id != None &&
                          first_name != None &&
                          last_name != None &&
                          password != None &&
                          employee_role != None
            
            @postcondition getEmployeeID() == employee_id &&
                            getFirstName() == first_name &&
                            getLastName() == last_name &&
                            getPassword() == password &&
                            getEmployeeRole() == employee_role
                           
            @param employee_id the unique ID of this employee
            @param first_name The first name of this employee
            @param last_name The last name of this employee
            @param password The password of this employee
            @param employee_role The role of this employee
        '''
        
        if (employee_id == None):
            raise Exception("ID is None")
        
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
        ''' 
            Returns the Employee ID
        
            @precondition none
            @postcondition none
            
            @return the employee's ID
        '''
        return self._employee_id
        
    def getFirstName(self):
        ''' 
            Returns the First Name of this employee
        
            @precondition none
            @postcondition none
            
            @return the first name of this employee
        '''
        return self._first_name
    
    def getLastName(self):
        ''' 
            Returns the Last of this employee
        
            @precondition none
            @postcondition none
            
            @return the last name of this employee
        '''
        return self._last_name
        
    def getPassword(self):
        ''' 
            Returns password of this employee
        
            @precondition none
            @postcondition none
            
            @return the password of this employee
        '''
        return self._password
    
    def getRole(self):
        ''' 
            Returns the role of this employee
        
            @precondition none
            @postcondition none
            
            @return the role of this employee
        '''
        return self._employee_role
    