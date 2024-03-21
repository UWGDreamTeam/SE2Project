import random
import string

def generate_unique_id(exclude_ids):
    while True:
        new_id = ''.join(random.choices(string.ascii_letters + string.digits, k=4))
        if new_id not in exclude_ids:
            return new_id

def log(msg):
    print(f"Server: {msg}")
