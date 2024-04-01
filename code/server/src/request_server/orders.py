#!/usr/bin/env python3
"""
This module is dedicated to managing orders within the inventory system. It facilitates the creation
update, deletion, and retrieval of order details. Each order is identified by a unique ID and
contains details such as the completion status and a list of products with their quantities. The
module checks product availability and validates data integrity during order operations to maintain
consistency across the inventory.

Interaction with the `products` module is crucial for validating product IDs and quantities
during order creation and updates. The module also utilizes utility functions from `.utilities`
for logging and generating unique IDs for new orders.

Functions:
    create_order(data): Creates a new order with specified details, ensuring product
        availability and valid data.
    delete_order(data): Deletes an existing order by its ID.
    update_order(data): Updates details of an existing order, including completion status
        and product list.
    get_order(data): Retrieves detailed information about a specific order by its ID.
"""

from request_server.utilities import generate_unique_id, log
from request_server.products import products

__author__ = 'Jason Nunez'
__version__ = '1.0.0'
__pylint__ = 'Version 2.14.5'

# A dictionary to store orders information with their IDs as keys
orders = {}

def create_order(data):
    """
    Creates a new order with the given data.

    Parameters:
    - data (dict): Contains details of the order, including completion status and a
         list of products (each with a product ID and quantity).

    Returns:
    - dict: A response object with a status message indicating success or error, and
         the new order's ID if successful.
    """
    log(f"Creating order: {data}")
    order_id = generate_unique_id(set(orders.keys()))
    completion_status = data.get('CompletionStatus')
    product_list = data.get('Products')
    if not product_list:
        log(f"Missing data for adding order: {data}")
        return {"status": "error", "message": "Missing data for adding order"}
    for product in product_list:
        product_id = product.get('ProductID')
        quantity = product.get('Quantity')
        if not product_id or quantity is None:
            log(f"Missing data for adding order: {data}")
            return {"status": "error", "message": "Missing data for adding order"}
        if product_id not in products:
            log(f"Product not found: {product_id}")
            return {"status": "error", "message": "Product not found"}
        if products[product_id]["Quantity"] < 0:
            log(f"Quantity of product is negative: {product_id}")
            return {"status": "error", "message": "Quantity of product is negative"}
    production_cost = sum(products[product.get('ProductID')]["ProductionCost"]
                          * product.get('Quantity') for product in product_list)
    sales_price = sum(products[product.get('ProductID')]["SalePrice"] *
                      product.get('Quantity') for product in product_list)

    orders[order_id] = {"CompletionStatus": completion_status, "Products":
                        product_list, "ProductionCost":
    production_cost, "SalesPrice": sales_price}
    log(f"Added order: {order_id}")
    return {"status": "success", "data": {"OrderID": order_id}}

def delete_order(data):
    """
    Deletes an existing order based on the order ID.

    Parameters:
    - data (dict): Contains the ID of the order to delete.

    Returns:
    - dict: A response object with a status message indicating success or error.
    """
    order_id = data.get('OrderID')
    if not order_id:
        log(f"Missing data for removing order: {data}")
        return {"status": "error", "message": "Missing data for removing order"}
    if order_id in orders:
        del orders[order_id]
        log(f"Removed order: {order_id}")
        return {"status": "success", "message": "Order removed"}
    log(f"Order not found: {order_id}")
    return {"status": "error", "message": "Order not found"}

def update_order(data):
    """
    Updates the details of an existing order.

    Parameters:
    - data (dict): Contains the order ID and new details to update
        (completion status and products list).

    Returns:
    - dict: A response object with a status message indicating success or error.
    """
    order_id = data.get('OrderID')
    new_completion_status = data.get('CompletionStatus')
    new_product_list = data.get('Products')
    if not order_id or not new_completion_status or not new_product_list:
        log(f"Missing data for updating order: {data}")
        return {"status": "error", "message": "Missing data for updating order"}
    if order_id in orders:
        orders[order_id] = {"CompletionStatus": new_completion_status, "Products": new_product_list}
        log(f"Updated order: {order_id}")
        return {"status": "success", "message": "Order updated with values"}
    log(f"Order not found: {order_id}")
    return {"status": "error", "message": "Order not found"}


def get_order(data):
    """
    Retrieves detailed information about a specific order.

    Parameters:
    - data (dict): Contains the ID of the order to retrieve.

    Returns:
    - dict: A response object with a status message indicating success or
        error, and the order's details if found.
    """
    order_id = data.get('OrderID')
    if order_id in orders:
        log(f"Retrieved order: {order_id}")
        return {"status": "success", "data": {"OrderID": order_id, "CompletionStatus":
                                              orders[order_id]["CompletionStatus"],
                                              "Products": orders[order_id]["Products"],
                                              "ProductionCost": orders[order_id]["ProductionCost"],
                                              "SalesPrice": orders[order_id]["SalesPrice"]}}

    log(f"Order not found: {order_id}")
    return {"status": "error", "message": "Order not found"}

def clear_all_orders():
    """
    Clears all orders from the system.

    Returns:
    - dict: A response object with a status message indicating success.
    """
    orders.clear()
    log("Cleared all orders")
    return {"status": "success", "message": "All orders cleared"}

def get_orders_by_status(data):
    """
    Retrieves all orders with a specific completion status.

    Parameters:
    - data (dict): Contains the completion status to filter orders.

    Returns:
    - dict: A response object with a status message indicating success or
        error, and a list of orders with the specified status if found.
    """
    completion_status = data.get('CompletionStatus')
    if not completion_status:
        log(f"Missing data for getting orders by status: {data}")
        return {"status": "error", "message": "Missing data for getting orders by status"}
    filtered_orders = {order_id: order for order_id, order in orders.items() if
                       order["CompletionStatus"] == completion_status}
    log(f"Retrieved orders by status: {completion_status}")
    log(f"Orders: {filtered_orders}")
    return {"status": "success", "data": filtered_orders}

def clear_order_inventory():
    """
    Clears all orders from the system.

    Returns:
    - dict: A response object with a status message indicating success.
    """
    orders.clear()
    log("Cleared all orders")
    return {"status": "success", "message": "All orders cleared"}

def get_all_orders():
    """
    Retrieves all orders in the system.
    
    Returns:
    - dict: A response object with a status message indicating success and a list of all orders.
    """
    log("Retrieved all orders")
    return {"status": "success", "data": orders}
