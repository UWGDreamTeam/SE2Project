#!/usr/bin/env python3
"""
This module is responsible for handling requests within the server.
It defines a mapping of request types to their corresponding handler functions.
These handlers are imported from various parts of the application, including product management,
component management, and order processing.
The module leverages a centralized request handling function `handle_request` to
interpret and process incoming request strings in JSON format,
execute the corresponding action, and return a response in JSON format.
This approach facilitates the expansion and maintenance of the server's capabilities by
 providing a clear and modular way to add or
update request handlers as needed.

Functions:
    handle_request(request_str): Parses the request string, executes the
        corresponding request handler, and returns the response.

Imports:
    - JSON for parsing and constructing responses.
    - Various request handling functions from the products, components,
        and orders modules for specific actions.
    - The logging utility from request_server.utilities for logging request handling activities.
"""

import json
from request_server.products import (
    add_product, update_product, delete_product,
    get_product, produce_product, get_quantity_of_product,
    get_all_products, clear_product_inventory
)
from request_server.components import (
    add_component, update_component, delete_component,
    get_component, get_quantity_of_component, order_component,
    get_all_components, clear_component_inventory
)
from request_server.orders import (
    create_order, update_order, delete_order, get_order,
    clear_all_orders, get_orders_by_status,
    clear_order_inventory, get_all_orders
)

from request_server.authentication_request_handler import (
    attemptLogin, registerUser, updateUser, removeUser,
    getEmployee, clear, getEmployeesList
)

from request_server.utilities import log

__author__ = 'Jason Nunez'
__version__ = '1.0.0'
__pylint__ = 'Version 2.14.5'

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
    
    "getComponents": get_all_components,
    "clearProductInventory": clear_product_inventory,
    "clearComponentInventory": clear_component_inventory,
    "clearOrderInventory": clear_order_inventory,
    "getProducts": get_all_products,
    "clearOrders": clear_all_orders,
    "getOrdersByCompletionStatus": get_orders_by_status,
    "getOrders": get_all_orders,
    
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
