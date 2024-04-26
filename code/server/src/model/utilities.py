"""
This module contains utility functions for the server, including a unique ID generator and a logging function. The ID generator creates a 4-character string that is guaranteed to be unique among a provided list of IDs. The logging function standardizes the format for server messages.

Functions:
    generate_unique_id(exclude_ids): Generates a unique ID.
    log(msg): Logs a message with a standardized format.
"""
import random
import string

def generate_unique_id(generated_ids):
    """
    Generates a unique ID that is not in the provided list of excluded IDs.

    :param exclude_ids: A list of IDs to exclude from the generation process.
    :return: A unique ID string.
    """
    while True:
        new_id = ''.join(random.choices(string.ascii_letters + string.digits, k=4))
        if new_id not in generated_ids:
            return new_id

def log(msg):
    """
    Logs a message to the console with a server prefix.

    :param msg: The message to log.
    """
    print(f"Server: {msg}")
