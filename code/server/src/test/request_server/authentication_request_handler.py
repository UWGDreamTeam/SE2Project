'''
Created on Mar 20, 2024

@author: Vitor dos Santos
'''
import unittest
from request_server.authentication_request_handler import attemptLogin, registerUser, updateUser, removeUser, getUser, clear, get_employees_list

class TestEmployeeManagement(unittest.TestCase):

    def setUp(self):
        clear()
        self.test_data = {
            "Id": "123",
            "firstName": "John",
            "lastName": "Doe",
            "password": "password123",
            "role": "Manager"
        }
        registerUser(self.test_data)

    def test_attemptLogin_success(self):
        self.assertTrue(attemptLogin({"Id": "123", "password": "password123"}))

    def test_attemptLogin_failure(self):
        self.assertFalse(attemptLogin({"Id": "123", "password": "wrongpassword"}))

    def test_registerUser_existing_user(self):
        result = registerUser(self.test_data)
        self.assertFalse(result)

    def test_updateUser_success(self):
        updated_data = self.test_data.copy()
        updated_data["firstName"] = "Jane"
        
        self.assertTrue(updateUser(updated_data))
        self.assertEqual(getUser("123").getFirstName(), "Jane")

    def test_updateUser_nonexistent_user(self):
        updated_data = self.test_data.copy()
        updated_data["Id"] = "456"
        self.assertFalse(updateUser(updated_data))

    def test_removeUser_success(self):
        self.assertTrue(removeUser("123"))
        self.assertIsNone(getUser("123"))

    def test_removeUser_nonexistent_user(self):
        self.assertFalse(removeUser("456"))

    def test_getUser_existing_user(self):
        self.assertIsNotNone(getUser("123"))
        self.assertEqual(getUser("123").getFirstName(), "John")

    def test_getUser_nonexistent_user(self):
        self.assertIsNone(getUser("456"))
        
    def test_get_employees_list_non_empty(self):
        employees_list = list(get_employees_list())
        self.assertEqual(len(employees_list), 1)
        self.assertEqual(employees_list[0].getEmployeeID(), "123")

    def test_get_employees_list_empty(self):
        clear()
        with self.assertRaises(Exception) as context:
            get_employees_list()
        self.assertTrue('Employees list is empty' in str(context.exception))

if __name__ == '__main__':
    unittest.main()
