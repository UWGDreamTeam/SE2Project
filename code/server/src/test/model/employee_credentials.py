'''
Created on Mar 20, 2024

@author: Vitor dos Santos
'''
import unittest
from model.employee_credentials import EmployeeCredentials


class TestConstructor(unittest.TestCase):


    def testIDIsNone(self):
        with self.assertRaises(Exception):
            EmployeeCredentials(None, "firstName", "lastName", "password", "manager")
            
    def testFirstNameIsNone(self):
        with self.assertRaises(Exception):
            EmployeeCredentials("id", None, "lastName", "password", "manager")
            
    def testLastNameIsNone(self):
        with self.assertRaises(Exception):
            EmployeeCredentials("id", "firstName", None, "password", "manager")
            
    def testPasswordIsNone(self):
        with self.assertRaises(Exception):
            EmployeeCredentials("id", "firstName", "lastName", None, "manager")
            
    def testRoleIsNone(self):
        with self.assertRaises(Exception):
            EmployeeCredentials("id", "firstName", "lastName", "password", None) 
            
    def testCreateValidEmployeeCredentials(self):
        result = EmployeeCredentials("id", "firstName", "lastName", "password", "manager") 
        
        self.assertEqual("id", result.getEmployeeID(), "checking id")
        self.assertEqual("firstName", result.getFirstName(), "checking first name")
        self.assertEqual("lastName", result.getLastName(), "checking last name")
        self.assertEqual("password", result.getPassword(), "checking password")
        self.assertEqual("manager", result.getRole(), "checking role")


if __name__ == "__main__":
    unittest.main()