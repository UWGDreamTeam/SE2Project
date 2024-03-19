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
 * @author Group 5
 * @version Spring 2024
 */
public class Main extends Application {
    public static final String WINDOW_TITLE = "Inventory Manager";
    public static final String INVENTORY_PAGE = "view/InventoryPage.fxml";
    public static final String ADD_PAGE = "view/AddComponentPage.fxml";
    public static final String HOME_PAGE = "view/HomePage.fxml";
    public static final String REGISTER_PAGE = "view/RegisterPage.fxml";
    public static final String LOGIN_PAGE = "view/LoginPage.fxml";
    public static final String ORDER_PAGE = "view/OrderPage.fxml";
    public static final String EDIT_COMPONENT_PAGE = "view/EditComponentPage.fxml";
    public static final String ADD_PRODUCT_PAGE = "view/AddProductPage.fxml";
    public static final String EDIT_PRODUCT_PAGE = "view/EditProductPage.fxml";

    private static Stage primaryStage;

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
	primaryStage = stage;
	Parent parent = FXMLLoader.load(getClass().getResource(Main.LOGIN_PAGE));
	Scene scene = new Scene(parent);
	primaryStage.setTitle(WINDOW_TITLE);
	primaryStage.setScene(scene);
	primaryStage.show();
    }

    /**
     * Provides access to the primary stage of the application. This static method
     * allows other parts of the application to retrieve the primary stage to update
     * or modify the scene.
     *
     * @return The primary stage of the application.
     * @precondition none
     * @postcondition none
     */
    public static Stage getPrimaryStage() {
	return primaryStage;
    }

    /**
     * Primary Java entry point.
     *
     * @precondition none
     * @postcondition none
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
	Main.launch(args);
    }
}
