'''
Created on Mar 20, 2024

@author: Vitor dos Santos
'''
import unittest
from request_server.authentication_request_handler import generate_username, attemptLogin, registerUser, updateUser, removeUser, getEmployee, clear, getEmployeesList
from model.utilities import log
class TestEmployeeManagement(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        # This method will run once before all tests
        log("Starting Employee Management System tests.")

    def setUp(self):
        # This method will run before every test
        clear()  # Ensure a clean state before each test

    def test_generate_username(self):
        # Test the generate_username function
        username = generate_username("John", "Doe")
        self.assertEqual(username, "jd0001")

    def test_register_and_retrieve_user(self):
        # Test registering a user and retrieving their information
        user_data = {
            "FirstName": "Alice",
            "LastName": "Johnson",
            "Password": "password123",
            "Role": "Manager"
        }
        register_result = registerUser(user_data)
        self.assertTrue('success' in register_result['status'])
        
        # Attempt to retrieve the registered user
        employee_data = {"EmployeeID": register_result['data']['EmployeeID']}
        retrieved_user = getEmployee(employee_data)
        self.assertEqual(retrieved_user['status'], 'success')
        self.assertEqual(retrieved_user['data']['FirstName'], "Alice")
        self.assertEqual(retrieved_user['data']['LastName'], "Johnson")

    def test_attempt_login_successful(self):
        # Test successful login attempt
        user_data = {
            "FirstName": "Bob",
            "LastName": "Smith",
            "Password": "bob123",
            "Role": "Employee"
        }
        registerUser(user_data)
        employee_id = generate_username("Bob", "Smith")
        login_data = {"EmployeeID": employee_id, "Password": "bob123"}
        login_result = attemptLogin(login_data)
        self.assertEqual(login_result['status'], 'success')

    def test_get_employees_list(self):
        # Test getting the list of employees
        user_data = {
            "FirstName": "Carol",
            "LastName": "Adams",
            "Password": "carol123",
            "Role": "Employee"
        }
        registerUser(user_data)
        employees_list = getEmployeesList({})
        self.assertEqual(employees_list['status'], 'success')
        self.assertTrue(len(employees_list['data']) > 0)

    def test_update_user(self):
        # Test updating an existing user
        user_data = {
            "FirstName": "Dave",
            "LastName": "Brown",
            "Password": "dave123",
            "Role": "Employee"
        }
        register_result = registerUser(user_data)
        update_data = {
            "EmployeeID": register_result['data']['EmployeeID'],
            "FirstName": "David",
            "LastName": "Brown",
            "Password": "dave456",
            "Role": "Administrator"
        }
        update_result = updateUser(update_data)
        self.assertEqual(update_result['status'], 'success')

    def test_remove_user(self):
        # Test removing a user
        user_data = {
            "FirstName": "Eve",
            "LastName": "White",
            "Password": "eve123",
            "Role": "Employee"
        }
        register_result = registerUser(user_data)
        remove_data = {"EmployeeID": register_result['data']['EmployeeID']}
        remove_result = removeUser(remove_data)
        self.assertEqual(remove_result['status'], 'success')

    def test_clear_employees(self):
        # Test clearing all employees
        clear_result = clear({})
        self.assertEqual(clear_result['status'], 'success')

    @classmethod
    def tearDownClass(cls):
        # This method will run once after all tests are done
        log("Completed Employee Management System tests.")

# This allows you to run the tests from the command line
if __name__ == '__main__':
    unittest.main()
