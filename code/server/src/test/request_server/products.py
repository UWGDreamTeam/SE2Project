'''
Created on April 4, 2024

@author: Jacob Haas
'''
import unittest
from request_server.products import (
    add_product, update_product, produce_product, delete_product,
    get_product, get_quantity_of_product, get_all_products, clear_product_inventory
)
from model.utilities import log
from request_server.components import components

class TestProductManagement(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        log("Starting Product System tests.")

    def setUp(self):
        clear_product_inventory()
        components.clear()
        components['comp1'] = {"Name": "Component 1", "ProductionCost": 1.00, "Quantity": 100}

    def test_add_and_get_product(self):
        product_data = {
            "Name": "Product 1",
            "Quantity": 10,
            "Recipe": [{"ComponentID": "comp1", "Quantity": 2}],
            "SalePrice": 20.00
        }
        add_result = add_product(product_data)
        self.assertTrue('success' in add_result['status'])
        product_id = add_result['data']['ProductID']

        get_data = {"ProductID": product_id}
        get_result = get_product(get_data)
        self.assertEqual(get_result['status'], 'success')
        self.assertEqual(get_result['data']['Name'], "Product 1")

    def test_update_product(self):
        product_data = {
            "Name": "Product 2",
            "Quantity": 20,
            "Recipe": [{"ComponentID": "comp1", "Quantity": 3}],
            "SalePrice": 30.00
        }
        add_result = add_product(product_data)
        product_id = add_result['data']['ProductID']

        update_data = {
            "ProductID": product_id,
            "Name": "Product 2 Updated",
            "Quantity": 15,
            "Recipe": [{"ComponentID": "comp1", "Quantity": 2}],
            "SalePrice": 35.00
        }
        update_result = update_product(update_data)
        self.assertEqual(update_result['status'], 'success')

    def test_produce_product(self):
        product_data = {
            "Name": "Product 3",
            "Quantity": 5,
            "Recipe": [{"ComponentID": "comp1", "Quantity": 1}],
            "SalePrice": 15.00
        }
        add_result = add_product(product_data)
        product_id = add_result['data']['ProductID']

        produce_data = {"ProductID": product_id, "Quantity": 5}
        produce_result = produce_product(produce_data)
        self.assertEqual(produce_result['status'], 'success')

    def test_delete_product(self):
        product_data = {
            "Name": "Product 4",
            "Quantity": 50,
            "Recipe": [{"ComponentID": "comp1", "Quantity": 5}],
            "SalePrice": 100.00
        }
        add_result = add_product(product_data)
        product_id = add_result['data']['ProductID']

        delete_data = {"ProductID": product_id}
        delete_result = delete_product(delete_data)
        self.assertEqual(delete_result['status'], 'success')

    def test_get_quantity_of_product(self):
        product_data = {
            "Name": "Product 5",
            "Quantity": 30,
            "Recipe": [{"ComponentID": "comp1", "Quantity": 1}],
            "SalePrice": 40.00
        }
        add_result = add_product(product_data)
        product_id = add_result['data']['ProductID']

        quantity_data = {"ProductID": product_id}
        quantity_result = get_quantity_of_product(quantity_data)
        self.assertEqual(quantity_result['status'], 'success')
        self.assertEqual(quantity_result['data']['Quantity'], 30)

    def test_get_all_products(self):
        add_product({"Name": "Product 6", "Quantity": 10, "Recipe": [{"ComponentID": "comp1", "Quantity": 2}], "SalePrice": 60.00})
        add_product({"Name": "Product 7", "Quantity": 20, "Recipe": [{"ComponentID": "comp1", "Quantity": 1}], "SalePrice": 70.00})
        
        all_products_result = get_all_products()
        self.assertEqual(all_products_result['status'], 'success')
        self.assertTrue(len(all_products_result['data']) > 1)

    def test_clear_product_inventory(self):
        add_product({"Name": "Product 8", "Quantity": 40, "Recipe": [{"ComponentID": "comp1", "Quantity": 3}], "SalePrice": 80.00})
        clear_result = clear_product_inventory()
        self.assertEqual(clear_result['status'], 'success')
        all_products_result = get_all_products()
        self.assertEqual(len(all_products_result['data']), 0)

    @classmethod
    def tearDownClass(cls):
        log("Completed Product System tests.")

if __name__ == '__main__':
    unittest.main()

