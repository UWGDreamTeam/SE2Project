package edu.westga.cs3212.inventory_manager;

import java.io.IOException;

import edu.westga.cs3212.inventory_manager.model.credentials.EmployeeType;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentials;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;

/**
 * Entry point for the program
 *
 * @author	Group 1
 * @version Spring 2024
 */
public class Main extends Application {
	public static final String WINDOW_TITLE = "Inventory Manager";
	public static final String INVENTORY_PAGE = "view/InventoryPage.fxml";
	public static final String ADD_PAGE = "view/AddComponentPage.fxml";
	public static final String HOME_PAGE = "view/HomePage.fxml";
	public static final String REGISTER_PAGE = "view/RegisterPage.fxml";
	public static final String LOGIN_PAGE = "view/login/LoginPage.fxml";
	public static final String ORDER_PAGE = "view/OrderPage.fxml";
	public static final String EDIT_COMPONENT_PAGE = "view/EditComponentPage.fxml";
	public static final String ADD_PRODUCT_PAGE = "view/AddProductPage.fxml";
	public static final String EDIT_PRODUCT_PAGE = "view/EditProductPage.fxml";
	public static final String ADMIN_PAGE = "view/AdminPage.fxml";
	public static final String ADMIN_EDIT_CREDENTIALS_PAGE = "view/AdminEditCredentialsPage.fxml";
	private static final String COMMA_SEPERATION = ", ";
	private static Stage primaryStage;
	
	private static LocalEmployeeCredentials loggedInEmployee;

	/**
	 * JavaFX entry point.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @throws IOException
	 */
	@Override
	public void start(Stage stage) throws IOException {
		DemoDataUtility.createDemoComponents();
		DemoDataUtility.createDemoProducts();
		DemoDataUtility.createDemoOrders();
		primaryStage = stage;
		Parent parent = FXMLLoader
				.load(getClass().getResource(Main.LOGIN_PAGE));
		Scene scene = new Scene(parent);
		primaryStage.setTitle(WINDOW_TITLE);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Provides access to the primary stage of the application. This static
	 * method allows other parts of the application to retrieve the primary
	 * stage to update or modify the scene.
	 *
	 * @return The primary stage of the application.
	 * @precondition none
	 * @postcondition none
	 */
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
	/**
	 * Sets the logged-in employee's credentials.
	 * 
	 * This method stores the credentials of the currently logged-in employee,
	 * allowing other parts of the application to access user-related information.
	 * 
	 * @precondition employee should not be null
	 * @postcondition loggedInEmployee is set to the provided employee credentials, making them accessible throughout the application.
	 * 
	 * @param employee The credentials of the logged-in employee.
	 */
	public static void setLoggedInEmployee(LocalEmployeeCredentials employee) {
		loggedInEmployee = employee;
    }
	
	/**
	 * Retrieves the logged-in employee's credentials.
	 * 
	 * This method returns the credentials of the currently logged-in employee,
	 * which can be used throughout the application to check permissions,
	 * access user information, and more.
	 * 
	 * @precondition The employee must be logged in before calling this method.
	 * @postcondition Returns the current logged-in employee's credentials.
	 * 
	 * @return The credentials of the currently logged-in employee.
	 */
    public static LocalEmployeeCredentials getLoggedInEmployee() {
        return loggedInEmployee;
    }
    
    /**
	 * Checks if the logged in employee is a manager.
	 * 
	 * This method checks the employee type of the currently logged-in employee.
	 * This can be used to determine employee permissions thorughout the application.
	 * 
	 * @precondition The employee must be logged in before calling this method.
	 * @postcondition Returns the current logged-in employee's EmployeType. Either MANAGER or WORKER.
	 * 
	 * @return true if the logged in employee is a manager, false otherwise
	 */
    public static boolean isLoggedInEmployeeManager() {
        EmployeeType role  = Main.getLoggedInEmployee().getEmployeeType();

        return role == EmployeeType.MANAGER;
    }
    
    /**
     * Creates a summary of the currently logged-in employee.
     * 
     * @param fullNameLabel the label to display the full name
     * @param employeeIdLabel the label to display the employee ID
     * @param workerTypeLabel the label to display the worker type
     */
    public static void createSummary(Text fullNameLabel, Text employeeIdLabel, Text workerTypeLabel) {
    	LocalEmployeeCredentials user = Main.getLoggedInEmployee();
		fullNameLabel.setText(user.getLastName() + COMMA_SEPERATION + user.getFirstName());
		employeeIdLabel.setText(user.getEmployeeID());
		workerTypeLabel.setText(user.getEmployeeType().toString());
    }

	/**
	 * Primary Java entry point.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) {
		Main.launch(args);
	}
}
