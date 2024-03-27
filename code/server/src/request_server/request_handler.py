'''
Created on March 21, 2024

@author: Group 1
'''

import json
from model.products import add_product, update_product, delete_product, get_product, produce_product, get_quantity_of_product
from model.components import add_component, update_component, delete_component, get_component, get_quantity_of_component, order_component
from model.orders import create_order, delete_order, update_order, get_order
from model.utilities import log
from request_server.authentication_request_handler import attemptLogin, registerUser, updateUser, removeUser, getEmployee, clear, getEmployeesList

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
    "clearCredentials" : clear,
    "getEmployeesList" : getEmployeesList
}

def handle_request(request_str):
    """
    Handles a given request string by parsing it, executing the corresponding handler,
        and returning the response.
    The function logs the incoming request, attempts to parse it as JSON, extracts the
     request type, and delegates the request to the appropriate handler based on the
     `request_handlers` mapping. If the request type is unknown or the request cannot
     be parsed, an error response is returned.

    Args:
        request_str (str): The request in JSON format.

    Returns:
        str: The response in JSON format, including the outcome of the request or an error message.
    """
    log(f"Handling request: {request_str}")
    try:
        request_data = json.loads(request_str)
        request_type = request_data.get('type')
        data = request_data.get('data', {})
        handler = request_handlers.get(request_type)

        if len(data) == 0:
            response = handler()
            
            return json.dumps(response)
        if handler:
            response = handler(data)
            return json.dumps(response)
        return json.dumps({"status": "error", "message": "Unknown request type"})

    except json.JSONDecodeError:
        return json.dumps({"status": "error", "message": "Invalid JSON format"})
    