#!/usr/bin/env python3
"""
Network Configuration Module

This module defines the basic network configuration for a server application, including the
protocol used for communication, the IP address on which the server listens, and the port number.
These settings are fundamental for establishing the network connection through which the server
communicates with clients or other services. This configuration follows the common TCP/IP model,
using TCP as the communication protocol for reliable data transmission.

Attributes:
    PROTOCOL (str): Specifies the communication protocol to be used. In this case, TCP is
    chosen for reliable, ordered, and error-checked delivery of a stream of bytes.
    IP_ADDRESS (str): The IP address where the server listens for incoming connections.
        '127.0.0.1' indicates that the server is hosted locally
        and accessible only from the same machine.
    PORT (str): The port number as a string on which the server listens for incoming connections.
        '5555' is chosen as the default port for this application, but can be changed
        based on network requirements and to avoid conflicts with other services.
"""
__author__ = 'Jason Nunez'
__version__ = '1.0.0'
__pylint__ = 'Version 2.14.5'

PROTOCOL = "tcp"
IP_ADDRESS = "127.0.0.1"
PORT = "5555"
