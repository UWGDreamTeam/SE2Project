from request_server import constants
import json
import random
import string
import zmq

components = {}
products = {}

def generate_unique_id():
    while True:
        # Generate a random 4-character alphanumeric ID
        new_id = ''.join(random.choices(string.ascii_letters + string.digits, k=4))
        if new_id not in components and new_id not in products:
            return new_id

def log(msg):
    print(f"Server: {msg}")

def add_product(data):
    product_id = generate_unique_id()
    name = data.get('Name')
    quantity = data.get('Quantity')
    recipe = data.get('Recipe')
    if not product_id or not name or quantity is None or not recipe:
        log(f"Missing data for adding product: {data}")
        return {"status": "error", "message": "Missing data for adding product"}
    for recipe_item in recipe:
        if recipe_item.get('ComponentID') not in components:
            log(f"Component not found: {recipe_item.get('ComponentID')}")
            return {"status": "error", "message": "Component not found"}
    for recipe_item in recipe:
        if recipe_item.get('Quantity') is None:
            log(f"Missing quantity for component: {recipe_item.get('ComponentID')}")
            return {"status": "error", "message": "Missing quantity for component"}
    production_cost = sum([components[recipe_item.get('ComponentID')]["ProductionCost"] * recipe_item.get('Quantity') for recipe_item in recipe])
    products[product_id] = {"Name": name, "Quantity": quantity, "Recipe": recipe, "ProductionCost": production_cost}
    log(f"Added product: {product_id}")
    return {"ProductID": product_id}

def update_product(data):
    product_id = data.get('ProductID')
    new_name = data.get('Name')
    new_quantity = data.get('Quantity')
    new_recipe = data.get('Recipe')
    if not product_id or not new_name or new_quantity is None or not new_recipe:
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
        products[product_id] = {"Name": new_name, "Quantity": new_quantity, "Recipe": new_recipe, "ProductionCost": production_cost}
        log(f"Updated product: {product_id}")
        return {"status": "success"}
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
    if products[product_id]["Quantity"] < quantity:
        log(f"Insufficient quantity for product: {product_id}")
        return {"status": "error", "message": "Insufficient quantity for product"}
    products[product_id]["Quantity"] += quantity
    return {"status": "success"}

def remove_product(data):
    product_id = data.get('ProductID')
    if not product_id:
        log(f"Missing data for removing product: {data}")
        return {"status": "error", "message": "Missing data for removing product"}
    if product_id in products:
        del products[product_id]
        log(f"Removed product: {product_id}")
        return {"status": "success"}
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
        return {"status": "success"}
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
        if components[component_id]["Quantity"] < quantity:
            log(f"Insufficient quantity for component: {component_id}")
            return {"status": "error", "message": "Insufficient quantity for component"}
        components[component_id]["Quantity"] += quantity
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
    return {"ComponentID": component_id}


def remove_component(data):
    component_id = data.get('ComponentID')
    if component_id and component_id in components:
        del components[component_id]
        log(f"Removed component: {component_id}")
        return {"status": "success"}
    else:
        log(f"Component not found: {component_id}")
        return {"status": "error", "message": "Component not found"}

def get_component(data):
    component_id = data.get('ComponentID')
    if component_id in components:
        log(f"Retrieved component: {component_id}")
        return components[component_id]
    else:
        log(f"Component not found: {component_id}")
        return {"status": "error", "message": "Component not found"}

def handle_request(request_str):
    try:
        request_data = json.loads(request_str)
        request_type = request_data.get('type')
        parts = request_data.get('data', [])
        handler = request_handlers.get(request_type, lambda x: {"status": "error", "message": "Bad format or unknown request"})
        response = handler(parts)
        return json.dumps({"status": "success", "data": response})
    except json.JSONDecodeError:
        return json.dumps({"status": "error", "message": "Invalid JSON format"})


request_handlers = {
    "addComponent": add_component,
    "removeComponent": remove_component,
    "updateComponent": update_component,
    "getComponent": get_component,
    "addProduct": add_product,
    "produceProduct": produce_product,
    "removeProduct": remove_product,
    "updateProduct": update_product
}


def main(protocol, ipAddress, port):
    context = zmq.Context()
    socket = context.socket(zmq.REP)
    socket.bind(f"{protocol}://{ipAddress}:{port}")
    
    while True:
        log("Waiting for request...")
        request_str = socket.recv_string()
        log(f"Received request: {request_str}")
        if request_str == "exit":
            break
        else:
            response = handle_request(request_str)
            socket.send_string(response)

    socket.close()
    context.term()

if __name__ == "__main__":
    main(constants.PROTOCOL, constants.IP_ADDRESS, constants.PORT)
