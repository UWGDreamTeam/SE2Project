from .utilities import generate_unique_id, log
from .components import components

products = {}

def add_product(data):
    log(f"Adding product: {data}")
    product_id = generate_unique_id(set(products.keys()))
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
    recipe = products[product_id]["Recipe"]
    for recipe_item in recipe:
        component_id = recipe_item.get('ComponentID')
        number_of_components_needed = recipe_item.get('Quantity') * quantity
        if component_id not in components:
            log(f"Component not found: {component_id}")
            return {"status": "error", "message": "Component not found"}
        if components[component_id]["Quantity"] - number_of_components_needed < 0:
            log(f"Insufficient quantity for component: {component_id}")
            return {"status": "error", "message": f"Insufficient quantity for component {component_id}"}
    for recipe_item in recipe:
        component_id = recipe_item.get('ComponentID')
        component_quantity = recipe_item.get('Quantity')
        components[component_id]["Quantity"] -= component_quantity * quantity
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