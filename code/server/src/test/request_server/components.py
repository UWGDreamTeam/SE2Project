'''
Created on Apr 4, 2024

@author: Jacob Haas
'''
import unittest
from request_server.components import add_component, update_component, order_component, delete_component, get_component, get_quantity_of_component, get_all_components, clear_component_inventory
from model.utilities import log

class TestComponenet(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        log("Starting Inventory Management System tests.")

    def setUp(self):
        clear_component_inventory()

    def test_add_and_get_component(self):
        component_data = {
            "Name": "Resistor",
            "ProductionCost": 0.10,
            "Quantity": 1000
        }
        add_result = add_component(component_data)
        self.assertTrue('success' in add_result['status'])
        component_id = add_result['data']['ComponentID']

        get_data = {"ComponentID": component_id}
        get_result = get_component(get_data)
        self.assertEqual(get_result['status'], 'success')
        self.assertEqual(get_result['data']['Name'], "Resistor")

    def test_update_component(self):
        component_data = {
            "Name": "Capacitor",
            "ProductionCost": 0.20,
            "Quantity": 500
        }
        add_result = add_component(component_data)
        component_id = add_result['data']['ComponentID']

        update_data = {
            "ComponentID": component_id,
            "Name": "Capacitor",
            "ProductionCost": 0.25,
            "Quantity": 450
        }
        update_result = update_component(update_data)
        self.assertEqual(update_result['status'], 'success')

    def test_order_component(self):
        component_data = {
            "Name": "Inductor",
            "ProductionCost": 0.30,
            "Quantity": 300
        }
        add_result = add_component(component_data)
        component_id = add_result['data']['ComponentID']

        order_data = {"ComponentID": component_id, "Quantity": -50}
        order_result = order_component(order_data)
        self.assertEqual(order_result['status'], 'success')
        self.assertEqual(order_result['data']['Quantity'], 250)

    def test_delete_component(self):
        component_data = {
            "Name": "Transformer",
            "ProductionCost": 2.00,
            "Quantity": 50
        }
        add_result = add_component(component_data)
        component_id = add_result['data']['ComponentID']

        delete_data = {"ComponentID": component_id}
        delete_result = delete_component(delete_data)
        self.assertEqual(delete_result['status'], 'success')

    def test_get_all_components(self):
        add_component({"Name": "Diode", "ProductionCost": 0.05, "Quantity": 1000})
        add_component({"Name": "Transistor", "ProductionCost": 0.35, "Quantity": 500})
        
        all_components_result = get_all_components()
        self.assertEqual(all_components_result['status'], 'success')
        self.assertTrue(len(all_components_result['data']) > 1)

    def test_clear_inventory(self):
        add_component({"Name": "IC", "ProductionCost": 1.50, "Quantity": 200})
        clear_result = clear_component_inventory()
        self.assertEqual(clear_result['status'], 'success')
        all_components_result = get_all_components()
        self.assertEqual(len(all_components_result['data']), 0)

    @classmethod
    def tearDownClass(cls):
        log("Completed Inventory Management System tests.")

if __name__ == '__main__':
    unittest.main()
