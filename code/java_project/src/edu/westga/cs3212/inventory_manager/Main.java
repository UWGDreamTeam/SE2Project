package edu.westga.cs3212.inventory_manager;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Entry point for the program
 *
 * @author	Group 5
 * @version Spring 2024
 */
public class Main extends Application {
	public static final String WINDOW_TITLE = "Inventory Manager";
	public static final String LANDING_PAGE = "view/InventoryPage.fxml";
	public static final String ADD_PAGE = "view/AddComponentPage.fxml";
	public static final String HOME_PAGE = "view/HomePage.fxml";
	public static final String REGISTER_PAGE = "view/RegisterPage.fxml";
	public static final String LOGIN_PAGE = "view/LoginPage.fxml";
	public static final String ORDER_PAGE = "view/OrderPage.fxml";

	/**
	 * JavaFX entry point.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @throws IOException
	 */
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource(Main.LANDING_PAGE));
		Scene scene = new Scene(parent);
		primaryStage.setTitle(WINDOW_TITLE);
		primaryStage.setScene(scene);
		primaryStage.show();
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
