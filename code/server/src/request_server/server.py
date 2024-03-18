from request_server import constants
import json
import random
import string
import zmq

components = {}
products = {}
orders = {}

successMessage = "Success";
errorMessage = "Error";

def generate_unique_id():
    while True:
        # Generate a random 4-character alphanumeric ID
        new_id = ''.join(random.choices(string.ascii_letters + string.digits, k=4))
        if new_id not in components and new_id not in products and new_id not in orders:
            return new_id

def log(msg):
    print(f"Server: {msg}")

def add_product(data):
    log(f"Adding product: {data}")
    product_id = generate_unique_id()
    name = data.get('Name')
    quantity = data.get('Quantity')
    recipe = data.get('Recipe')
    sale_price = data.get('SalePrice')
    if not product_id or not name or quantity is None or not recipe or sale_price is None:
        log(f"Missing data for adding product: {data}")
        return {"status": "error", "message": "Missing data for adding product"}
    for recipe_item in recipe:
        if recipe_item.get('ComponentID') not in components:
            log(f"Component not found: {recipe_item.get('ComponentID')}")
            return {"status": "error", "message": "Component not found"}
        if recipe_item.get('Quantity') is None:
            log(f"Missing quantity for component: {recipe_item.get('ComponentID')}")
            return {"status": "error", "message": "Missing quantity for component"}
    production_cost = sum([components[recipe_item.get('ComponentID')]["ProductionCost"] * recipe_item.get('Quantity') for recipe_item in recipe])
    products[product_id] = {"Name": name, "Quantity": quantity, "Recipe": recipe, "ProductionCost": production_cost, "SalePrice": sale_price}
    log(f"Added product: {product_id}")
    return {"status": "success", "data": {"ProductID": product_id}}

def update_product(data):
    log(f"Updating product: {data}")
    product_id = data.get('ProductID')
    new_name = data.get('Name')
    new_quantity = data.get('Quantity')
    new_recipe = data.get('Recipe')
    new_sale_price = data.get('SalePrice')
    if not product_id or not new_name or new_quantity is None or not new_recipe or new_sale_price is None:
        log(f"Missing data for updating product: {data}")
        return {"status": "error", "message": "Missing data for updating product"}
    if product_id in products:
        for recipe_item in new_recipe:
            if recipe_item.get('ComponentID') not in components:
                log(f"Component not found: {recipe_item.get('ComponentID')}")
                return {"status": "error", "message": "Component not found"}
            if recipe_item.get('Quantity') is None:
                log(f"Missing quantity for component: {recipe_item.get('ComponentID')}")
                return {"status": "error", "message": "Missing quantity for component"}
        production_cost = sum([components[recipe_item.get('ComponentID')]["ProductionCost"] * recipe_item.get('Quantity') for recipe_item in new_recipe])
        products[product_id] = {"Name": new_name, "Quantity": new_quantity, "Recipe": new_recipe, "ProductionCost": production_cost, "SalePrice": new_sale_price}
        log(f"Updated product: {product_id}")
        return {"status": "success", "message": "Product updated with values"}
    else:
        log(f"Product not found: {product_id}")
        return {"status": "error", "message": "Product not found"}

def produce_product(data):    
    product_id = data.get('ProductID')
    quantity = data.get('Quantity')
    if not product_id or quantity is None:
        log(f"Missing data for producing product: {data}")
        return {"status": "error", "message": "Missing data for producing product"}
    if product_id not in products:
        log(f"Product not found: {product_id}")
        return {"status": "error", "message": "Product not found"}
    if products[product_id]["Quantity"] + quantity < 0:
        log(f"Insufficient quantity for product: {product_id}")
        return {"status": "error", "message": "Insufficient quantity for product"}
    products[product_id]["Quantity"] += quantity
    return {"status": "success", "data": {"Quantity": products[product_id]["Quantity"]}}

def delete_product(data):
    product_id = data.get('ProductID')
    if not product_id:
        log(f"Missing data for removing product: {data}")
        return {"status": "error", "message": "Missing data for removing product" }
    if product_id in products:
        del products[product_id]
        log(f"Removed product: {product_id}")
        return {"status": "success", "message": "Product removed"}
    else:
        log(f"Product not found: {product_id}")
        return {"status": "error", "message": "Product not found"}
    
def get_product(data):
    product_id = data.get('ProductID')
    if product_id in products:
        log(f"Retrieved product: {product_id}")
        return {"status": "success", "data": {"ProductID": product_id, "Name": products[product_id]["Name"], "SalePrice": products[product_id]["SalePrice"], "ProductionCost": products[product_id]["ProductionCost"], "Recipe": products[product_id]["Recipe"]}}
    else:
        log(f"Product not found: {product_id}")
        return {"status": "error", "message": "Product not found"}
    
def get_quantity_of_product(data):
    product_id = data.get('ProductID')
    if product_id in products:
        log(f"Retrieved stock of product: {product_id}, {products[product_id]['Quantity']}")
        return {"status": "success", "data": {"Quantity": products[product_id]["Quantity"]}}
    else:
        log(f"Product not found: {product_id}")
        return {"status": "error", "message": "Product not found"}

def update_component(data):
    component_id = data.get('ComponentID')
    new_name = data.get('Name')
    new_production_cost = data.get('ProductionCost')
    new_quantity = data.get('Quantity')
    if not component_id or not new_name or new_production_cost is None or new_quantity is None:
        log(f"Missing data for updating component: {data}")
        return {"status": "error", "message": "Missing data for updating component"}
    if component_id in components:
        components[component_id] = {"Name": new_name, "ProductionCost": new_production_cost, "Quantity": new_quantity}
        log(f"Updated component: {component_id}")
        return {"status": "success", "message": "Component updated with values"}
    else:
        log(f"Component not found: {component_id}")
        return {"status": "error", "message": "Component not found"}

def order_component(data):
    component_id = data.get('ComponentID')
    quantity = data.get('Quantity')
    if not component_id or quantity is None:
        log(f"Missing data for ordering component: {data}")
        return {"status": "error", "message": "Missing data for ordering component"}
    if component_id in components:
        if components[component_id]["Quantity"] + quantity < 0:
            log(f"Insufficient quantity for component: {component_id}")
            return {"status": "error", "message": f"Quantity {quantity} exceeds stock of component {components[component_id]['Quantity']}"}
        components[component_id]["Quantity"] += quantity
        return {"status": "success", "data": {"Quantity": components[component_id]["Quantity"]}}
    else:
        log(f"Component not found: {component_id}")
        return {"status": "error", "message": "Component not found"}

def add_component(data):
    component_id = generate_unique_id()
    name = data.get('Name')
    quantity = data.get('Quantity')
    production_cost = data.get('ProductionCost')
    if not component_id or not name or quantity is None or production_cost is None:
        log(f"Missing data for adding component: {data}")
        return {"status": "error", "message": "Missing data for adding component"}

    if component_id in components:
        log(f"Component already exists: {component_id}")
        return {"status": "error", "message": "Component already exists"}

    components[component_id] = {"Name": name, "ProductionCost": production_cost, "Quantity": quantity}
    log(f"Added component: {component_id}")
    return {"status": "success", "data": {"ComponentID": component_id}}


def delete_component(data):
    component_id = data.get('ComponentID')
    if component_id and component_id in components:
        del components[component_id]
        log(f"Removed component: {component_id}")
        return {"status": "success", "message": "Component removed"}
    else:
        log(f"Component not found: {component_id}")
        return {"status": "error", "message": "Component not found"}

def get_component(data):
    component_id = data.get('ComponentID')
    if component_id in components:
        log(f"Retrieved component: {component_id}")
        return {"status": "success", "data": {"ComponentID": component_id, "Name": components[component_id]["Name"], "ProductionCost": components[component_id]["ProductionCost"]}}
    else:
        log(f"Component not found: {component_id}")
        return {"status": "error", "message": "Component not found"}

def get_quantity_of_component(data):
    component_id = data.get('ComponentID')
    if component_id in components:
        log(f"Retrieved stock of component: {component_id}, {components[component_id]['Quantity']}")
        return {"status": "success", "data": {"Quantity": components[component_id]["Quantity"]}}
    else:
        log(f"Component not found: {component_id}")
        return {"status": "error", "message": "Component not found"}

def handle_request(request_str):
    log(f"Handling request: {request_str}")
    try:
        request_data = json.loads(request_str)
        request_type = request_data.get('type')
        parts = request_data.get('data', {})
        handler = request_handlers.get(request_type)

        if handler:
            response = handler(parts)
            return json.dumps(response)
        else:
            return json.dumps({"status": "error", "message": "Unknown request type"})

    except json.JSONDecodeError:
        return json.dumps({"status": "error", "message": "Invalid JSON format"})


def create_order(data):
    log(f"Creating order: {data}")
    order_id = generate_unique_id();
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
    order_id = data.get('OrderID')
    if order_id in orders:
        log(f"Retrieved order: {order_id}")
        return {"status": "success", "data": {"OrderID": order_id, "CompletionStatus": orders[order_id]["CompletionStatus"], "Products": orders[order_id]["Products"], "ProductionCost": orders[order_id]["ProductionCost"], "SalesPrice": orders[order_id]["SalesPrice"]}}
    else:
        log(f"Order not found: {order_id}")
        return {"status": "error", "message": "Order not found"}

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
}


def main(protocol, ipAddress, port):
    context = zmq.Context()
    socket = context.socket(zmq.REP)
    socket.bind(f"{protocol}://{ipAddress}:{port}")
    
    while True:
        log("Waiting for request...")
        request_str = socket.recv_string()
        if request_str == "exit":
            break
        else:
            response = handle_request(request_str)
            socket.send_string(response)

    socket.close()
    context.term()

if __name__ == "__main__":
    main(constants.PROTOCOL, constants.IP_ADDRESS, constants.PORT)
