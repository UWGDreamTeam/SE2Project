from .utilities import generate_unique_id, log

components = {}

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
    component_id = generate_unique_id(set(components.keys()))
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