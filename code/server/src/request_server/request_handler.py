'''
Created on March 21, 2024

@author: Group 1
'''

import json
from model.products import add_product, update_product, delete_product, get_product, produce_product, get_quantity_of_product
from model.components import add_component, update_component, delete_component, get_component, get_quantity_of_component, order_component
from model.orders import create_order, delete_order, update_order, get_order
from model.utilities import log
from request_server.authentication_request_handler import attemptLogin, registerUser, updateUser, removeUser, getEmployee, clear

request_handlers = {
    "addComponent": add_component,
    "deleteComponent": delete_component,
    "updateComponent": update_component,
    "getComponent": get_component,
    "getQuantityOfComponent": get_quantity_of_component,
    "orderComponent": order_component,
    
    "addProduct": add_product,
    "produceProduct": produce_product,
    "deleteProduct": delete_product,
    "updateProduct": update_product,
    "getProduct": get_product,
    "getQuantityOfProduct": get_quantity_of_product,
    
    "createOrder": create_order,
    "deleteOrder": delete_order,
    "updateOrder": update_order,
    "getOrder": get_order,
    
    "attemptLogin": attemptLogin,
    "registerUser": registerUser,
    "updateUser": updateUser,
    "removeUser": removeUser,
    "getEmployee": getEmployee,
    "clearCredentials" : clear
}

def handle_request(request_str):
    log(f"Handling request: {request_str}")
    try:
        request_data = json.loads(request_str)
        request_type = request_data.get('type')
        
        data = request_data.get('data', {})
        handler = request_handlers.get(request_type)

        if handler:
            response = handler(data)
            return json.dumps(response)
        else:
            return json.dumps({"status": "error", "message": "Unknown request type"})

    except json.JSONDecodeError:
        return json.dumps({"status": "error", "message": "Invalid JSON format"})