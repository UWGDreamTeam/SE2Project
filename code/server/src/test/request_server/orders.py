'''
Created on Apr 4, 2024

@author: Jacob Haas
'''
import unittest
from request_server.orders import (create_order, delete_order, update_order, get_order,
                                     get_orders_by_status, clear_order_inventory, get_all_orders)
from model.utilities import log
from request_server.products import products

class TestOrderManagement(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        log("Starting Order Management System tests.")

    def setUp(self):
        clear_order_inventory()
        products.clear()
        products['prod1'] = {"Name": "Product 1", "Quantity": 100, "SalePrice": 10.00, "ProductionCost": 5.00}

    def test_create_and_get_order(self):
        order_data = {
            "CompletionStatus": "Pending",
            "Products": [{"ProductID": "prod1", "Quantity": 10}]
        }
        create_result = create_order(order_data)
        self.assertTrue('success' in create_result['status'])
        order_id = create_result['data']['OrderID']

        get_data = {"OrderID": order_id}
        get_result = get_order(get_data)
        self.assertEqual(get_result['status'], 'success')
        self.assertEqual(get_result['data']['CompletionStatus'], "Pending")

    def test_update_order(self):
        order_data = {
            "CompletionStatus": "Pending",
            "Products": [{"ProductID": "prod1", "Quantity": 10}]
        }
        create_result = create_order(order_data)
        order_id = create_result['data']['OrderID']

        update_data = {
            "OrderID": order_id,
            "CompletionStatus": "Completed",
            "Products": [{"ProductID": "prod1", "Quantity": 5}]
        }
        update_result = update_order(update_data)
        self.assertEqual(update_result['status'], 'success')

    def test_delete_order(self):
        order_data = {
            "CompletionStatus": "Pending",
            "Products": [{"ProductID": "prod1", "Quantity": 10}]
        }
        create_result = create_order(order_data)
        order_id = create_result['data']['OrderID']

        delete_data = {"OrderID": order_id}
        delete_result = delete_order(delete_data)
        self.assertEqual(delete_result['status'], 'success')

    def test_get_orders_by_status(self):
        create_order({"CompletionStatus": "Pending", "Products": [{"ProductID": "prod1", "Quantity": 10}]})
        create_order({"CompletionStatus": "Completed", "Products": [{"ProductID": "prod1", "Quantity": 5}]})

        by_status_data = {"CompletionStatus": "Pending"}
        by_status_result = get_orders_by_status(by_status_data)
        self.assertEqual(by_status_result['status'], 'success')
        self.assertTrue(len(by_status_result['data']) > 0)

    def test_get_all_orders(self):
        create_order({"CompletionStatus": "Pending", "Products": [{"ProductID": "prod1", "Quantity": 10}]})
        create_order({"CompletionStatus": "Completed", "Products": [{"ProductID": "prod1", "Quantity": 5}]})


        all_orders_result = get_all_orders()
        self.assertEqual(all_orders_result['status'], 'success')
        self.assertTrue(len(all_orders_result['data']) > 1)

    def test_clear_all_orders(self):
        create_order({"CompletionStatus": "Pending", "Products": [{"ProductID": "prod1", "Quantity": 10}]})
        clear_result = clear_order_inventory()
        self.assertEqual(clear_result['status'], 'success')
        all_orders_result = get_all_orders()
        self.assertEqual(len(all_orders_result['data']), 0)

    @classmethod
    def tearDownClass(cls):
        log("Completed Order Management System tests.")

if __name__ == '__main__':
    unittest.main()
