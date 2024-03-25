from .utilities import generate_unique_id, log
from model import products

# A dictionary to store orders information with their IDs as keys
orders = {}

def create_order(data):
    """
    Creates a new order with the given data.

    Parameters:
    - data (dict): Contains details of the order, including completion status and a list of products (each with a product ID and quantity).

    Returns:
    - dict: A response object with a status message indicating success or error, and the new order's ID if successful.
    """
    
    log(f"Creating order: {data}")
    order_id = generate_unique_id(set(orders.keys()));
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
        
    production_cost = sum([products[product.get('ProductID')]["ProductionCost"] * product.get('Quantity') for product in product_list])
    sales_price = sum([products[product.get('ProductID')]["SalePrice"] * product.get('Quantity') for product in product_list])
    orders[order_id] = {"CompletionStatus": completion_status, "Products": product_list, "ProductionCost": production_cost, "SalesPrice": sales_price}
    
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
    
    else:
        log(f"Order not found: {order_id}")
        return {"status": "error", "message": "Order not found"}

def update_order(data):
    """
    Updates the details of an existing order.

    Parameters:
    - data (dict): Contains the order ID and new details to update (completion status and products list).

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
    
    else:
        log(f"Order not found: {order_id}")
        return {"status": "error", "message": "Order not found"}
  
  
def get_order(data):
    """
    Retrieves detailed information about a specific order.

    Parameters:
    - data (dict): Contains the ID of the order to retrieve.

    Returns:
    - dict: A response object with a status message indicating success or error, and the order's details if found.
    """
    
    order_id = data.get('OrderID')
    if order_id in orders:
        log(f"Retrieved order: {order_id}")
        return {"status": "success", "data": {"OrderID": order_id, "CompletionStatus": orders[order_id]["CompletionStatus"], "Products": orders[order_id]["Products"], "ProductionCost": orders[order_id]["ProductionCost"], "SalesPrice": orders[order_id]["SalesPrice"]}}
    
    else:
        log(f"Order not found: {order_id}")
        return {"status": "error", "message": "Order not found"}
    