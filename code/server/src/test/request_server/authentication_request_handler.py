import unittest
from request_server.authentication_request_handler import attemptLogin, registerUser, updateUser, removeUser, getUser, clear
# Ensure 'your_module_name_here' is replaced with the actual name of your Python file containing the functions.

class TestEmployeeManagement(unittest.TestCase):

    def setUp(self):
        # This method will run before each test, setting up a clean test environment
        clear()  # Ensure the employee dictionary is empty before each test
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
        updated_data["Id"] = "456"  # Non-existent user ID
        self.assertFalse(updateUser(updated_data))

    def test_removeUser_success(self):
        self.assertTrue(removeUser("123"))
        self.assertIsNone(getUser("123"))

    def test_removeUser_nonexistent_user(self):
        self.assertFalse(removeUser("456"))  # Non-existent user ID

    def test_getUser_existing_user(self):
        self.assertIsNotNone(getUser("123"))
        self.assertEqual(getUser("123").getFirstName(), "John")

    def test_getUser_nonexistent_user(self):
        self.assertIsNone(getUser("456"))  # Non-existent user ID

if __name__ == '__main__':
    unittest.main()
