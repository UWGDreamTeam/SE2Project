import zmq
from request_server import constants
import json

components = {}

def get

def log(message):
    print(f"SERVER::{message}")

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

}

def main(protocol, ipAddress, port):
    context = zmq.Context()
    socket = context.socket(zmq.REP)
    socket.bind(f"{protocol}://{ipAddress}:{port}")
    
    while True:
        log("waiting for request...")
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
