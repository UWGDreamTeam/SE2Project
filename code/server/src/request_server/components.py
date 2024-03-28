#!/usr/bin/env python3
"""
This module handles the operations related to inventory components. It includes functionalities to
add, update, order, delete, and retrieve components and their quantities from the inventory.
Each component is uniquely identified and managed with details such as name, production cost,
and current stock quantity. These operations ensure the integrity and consistency of the
components' data within the inventory system.

The module leverages utilities for logging activities and generating unique identifiers
for new components, ensuring that each component has a unique ID within the inventory.

Functions:
    add_component(data): Adds a new component to the inventory with specified details.
    update_component(data): Updates the information of an existing component based on
        provided details.
    order_component(data): Adjusts the stock quantity of a component based on orders.
    delete_component(data): Removes a specified component from the inventory.
    get_component(data): Retrieves detailed information about a specific component.
    get_quantity_of_component(data): Retrieves the current stock quantity of a
        specified component.
"""

__author__ = 'Jason Nunez'
__version__ = '1.0.0'
__pylint__ = 'Version 2.14.5'

from .utilities import generate_unique_id, log

# A dictionary to store component information with their IDs as keys
components = {}

def update_component(data):
    """
    Updates the information of an existing component.

    Parameters:
    - data (dict): A dictionary containing the component's ID and its new
        details (name, production cost, and quantity).

    Returns:
    - dict: A response object with a status message indicating success or
        error, and additional data if applicable.
    """
    component_id = data.get('ComponentID')
    new_name = data.get('Name')
    new_production_cost = data.get('ProductionCost')
    new_quantity = data.get('Quantity')
    if (not component_id or not new_name or
        new_production_cost is None or new_quantity is None):
        log(f"Missing data for updating component: {data}")
        return {"status": "error", "message": "Missing data for updating component"}
    if component_id in components:
        components[component_id] = {"Name": new_name,
                                    "ProductionCost": new_production_cost,
                                    "Quantity": new_quantity}
        log(f"Updated component: {component_id}")
        return {"status": "success", "message": "Component updated with values"}
    log(f"Component not found: {component_id}")
    return {"status": "error", "message": "Component not found"}

def order_component(data):
    """
    Adjusts the quantity of a specific component based on an order.

    Parameters:
    - data (dict): A dictionary containing the component's ID and the
        ordered quantity (can be negative).

    Returns:
    - dict: A response object with a status message indicating success or
        error, and the updated quantity if successful.
    """
    component_id = data.get('ComponentID')
    quantity = data.get('Quantity')
    if not component_id or quantity is None:
        log(f"Missing data for ordering component: {data}")
        return {"status": "error", "message": "Missing data for ordering component"}
    if component_id in components:
        if components[component_id]["Quantity"] + quantity < 0:
            log(f"Insufficient quantity for component: {component_id}")
            return {"status": "error",
                    "message":
            f"{quantity} exceeds stock of component {components[component_id]['Quantity']}"}
        components[component_id]["Quantity"] += quantity
        return {"status": "success", "data": {"Quantity": components[component_id]["Quantity"]}}
    log(f"Component not found: {component_id}")
    return {"status": "error", "message": "Component not found"}

def add_component(data):
    """
    Adds a new component to the inventory.

    Parameters:
    - data (dict): A dictionary containing the new component's name, quantity, and production cost.

    Returns:
    - dict: A response object with a status message indicating success or
        error, and the new component's ID if successful.
    """
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

    components[component_id] = {"Name": name, "ProductionCost":
                                production_cost, "Quantity": quantity}
    log(f"Added component: {component_id}")
    return {"status": "success", "data": {"ComponentID": component_id}}


def delete_component(data):
    """
    Deletes a component from the inventory.

    Parameters:
    - data (dict): A dictionary containing the ID of the component to delete.

    Returns:
    - dict: A response object with a status message indicating success or error.
    """
    component_id = data.get('ComponentID')
    if component_id and component_id in components:
        del components[component_id]
        log(f"Removed component: {component_id}")
        return {"status": "success", "message": "Component removed"}
    log(f"Component not found: {component_id}")
    return {"status": "error", "message": "Component not found"}

def get_component(data):
    """
    Retrieves detailed information about a specific component.

    Parameters:
    - data (dict): A dictionary containing the ID of the component to retrieve.

    Returns:
    - dict: A response object with a status message indicating success or error
        , and the component's details if found.
    """
    component_id = data.get('ComponentID')
    if component_id in components:
        log(f"Retrieved component: {component_id}")
        return {"status": "success", "data":
                {"ComponentID": component_id, "Name":
                 components[component_id]["Name"], "ProductionCost":
        components[component_id]["ProductionCost"]}}
    log(f"Component not found: {component_id}")
    return {"status": "error", "message": "Component not found"}

def get_quantity_of_component(data):
    """
    Retrieves the current stock quantity of a specified component.

    Parameters:
    - data (dict): A dictionary containing the ID of the componen
         for which to retrieve the stock quantity.

    Returns:
    - dict: A response object with a status message indicating success or
        error, and the current quantity if the component was found.
    """
    component_id = data.get('ComponentID')
    if component_id in components:
        log(f"Retrieved stock of component: {component_id}, {components[component_id]['Quantity']}")
        return {"status": "success", "data": {"Quantity": components[component_id]["Quantity"]}}
    log(f"Component not found: {component_id}")
    return {"status": "error", "message": "Component not found"}
