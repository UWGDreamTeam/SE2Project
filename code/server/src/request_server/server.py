from request_server import constants
from request_server.request_handler import handle_request
from request_server.utilities import log
import zmq

successMessage = "Success";
errorMessage = "Error";

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
