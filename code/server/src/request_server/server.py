import zmq
from request_server import constants
import json
import random
import string

components = {}

def generate_unique_id():
    while True:
        # Generate a random 4-character alphanumeric ID
        new_id = ''.join(random.choices(string.ascii_letters + string.digits, k=4))
        if new_id not in components:
            return new_id

def log(msg):
    print(f"Server: {msg}")

def add_component(data):
    component_id = generate_unique_id()
    name = data.get('Name')
    production_cost = data.get('ProductionCost')
    if not component_id or not name or production_cost is None:
        log(f"Missing data for adding component: {data}")
        return {"status": "error", "message": "Missing data for adding component"}

    if component_id in components:
        log(f"Component already exists: {component_id}")
        return {"status": "error", "message": "Component already exists"}

    components[component_id] = {"Name": name, "ProductionCost": production_cost}
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

def edit_component(data):
    component_id = data.get('ComponentID')
    new_name = data.get('Name')
    new_production_cost = data.get('ProductionCost')
    if component_id in components:
        components[component_id] = {"Name": new_name, "ProductionCost": new_production_cost}
        log(f"Edited component: {component_id}")
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
    "editComponent": edit_component,
    "getComponent": get_component,
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
