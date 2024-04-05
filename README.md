# Inventory Manager

The Inventory Manager is a system designed for efficient and straightforward inventory management, with a wide array of components and products derived from these components pre-loaded. It facilitates the seamless monitoring of orders and stock quantity, ensuring operational fluency and accuracy. Additionally, the system is built with role-based access control, allowing for differentiated access levels and functionality based on each employee's role. Whether for administrative oversight or day-to-day operations, the Inventory Manager system enhances productivity and simplifies the complexities of inventory management.

### Getting Started

### Requirements
- Python 3.10
- pyzmq 23.2.0 for server-side communication
- JavaFX SDK version 20.0.1 for the Java application's GUI
- JeroMQ 0.3.4 included within the project for Java to ZeroMQ communication

### Setting Up the Environment

Server

1. Ensure Python 3.10 is installed on your system.
2. Install pyzmq version 23.2.0 using pip:

```bash
pip install pyzmq==23.2.0
```
Java Application

1. Ensure you have a Java Development Kit (JDK) installed that is compatible with JavaFX 20.0.1.
2. The JavaFX SDK 20.0.1 should be located within the project in the ./lib/javafx-sdk directory.
3. JeroMQ 0.3.4 is already included in the project and should not require additional setup.

## Running the Application

Start the Server

1. Navigate to the server directory

```bash
cd /path/to/InventoryManagerServer/src/request_server/
```

2. Run the server script:

```python
python server.py
```
- You should see the following message if the server starts successfully:

```python
Server: Waiting for request...
```

Start the Java Application

1. Navigate to the Java application directory:

```bash
cd /path/to/InventoryManager/src/edu/westga/cs3212/inventory_manager/
```

2. Run Main.java with the required VM arguments:

- For IDEs, configure the VM options in the run configurations:
  ```java
  --module-path "./lib/javafx-sdk/lib" --add-modules javafx.controls,javafx.fxml
  ```
- Or, if running from the command line, include the VM arguments like so:
  ```java
  java --module-path "./lib/javafx-sdk/lib" --add-modules javafx.controls,javafx.fxml -cp . Main
  ```
- The VM arguments below assume you are using the JavaFX within the project, for a different SDK, change "./lib/javafx-sdk/lib" with your new path.

## Login Credentials

Use the following credentials to log in to the application with different user roles:

- Admin
  - EmployeeID: "aa0001"
  - Password: "admin"
- Worker
  - EmployeeID: "ww0001"
  - Password: "worker"
 
  
