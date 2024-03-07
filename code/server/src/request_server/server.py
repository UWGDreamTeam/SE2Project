import zmq
from request_server import constants

systems_db = {}

def log(message):
    print(f"SERVER::{message}")

def handle_request(request_str):
    parts = request_str.split(',')
    request_type = parts[0]
    handler = request_handlers.get(request_type, lambda x: "bad format")
    return handler(parts[1:])

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
