#!/usr/bin/env python3
"""
A simple zMQ server example.

This script demonstrates setting up a basic server using the ZeroMQ library to handle
requests in a loop. It waits for a specific message and responds accordingly. The server
can be stopped by sending an "exit" message.
"""

import zmq
from request_server import constants
from request_server.request_handler import handle_request
from request_server.utilities import log


__author__ = 'Jason Nunez'
__version__ = '1.0.0'
__pylint__ = 'Version 2.14.5'

SUCCESS_MESSAGE = "Success"
ERROR_MESSAGE = "Error"

def main(protocol, ip_address, port):
    """
    Main server loop.

    Initializes the zMQ context and socket, binds to the specified address, and waits for
    incoming requests to handle them until an 'exit' message is received.
    """
    context = zmq.Context()
    socket = context.socket(zmq.REP)
    socket.bind(f"{protocol}://{ip_address}:{port}")

    while True:
        log("Waiting for request...")
        request_str = socket.recv_string()
        if request_str == "exit":
            break
        response = handle_request(request_str)
        socket.send_string(response)

    socket.close()
    context.term()

if __name__ == '__main__':
    main(constants.PROTOCOL, constants.IP_ADDRESS, constants.PORT)
