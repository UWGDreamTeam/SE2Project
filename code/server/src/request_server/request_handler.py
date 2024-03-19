import json
from request_server.products import add_product, update_product, delete_product, get_product, produce_product, get_quantity_of_product
from request_server.components import add_component, update_component, delete_component, get_component, get_quantity_of_component, order_component
from request_server.orders import create_order, update_order, delete_order, get_order
from request_server.utilities import log

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

def handle_request(request_str):
    log(f"Handling request: {request_str}")
    try:
        request_data = json.loads(request_str)
        request_type = request_data.get('type')
        data = request_data.get('data', {})
        handler = request_handlers.get(request_type)

        if handler:
            response = handler(data)
            return json.dumps(response)
        else:
            return json.dumps({"status": "error", "message": "Unknown request type"})

    except json.JSONDecodeError:
        return json.dumps({"status": "error", "message": "Invalid JSON format"})