package edu.westga.cs3212.inventory_manager.view;

import java.io.IOException;

import edu.westga.cs3212.inventory_manager.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The code-behind for the OrdersPage view.
 * 
 * @author Group 1
 * @version Spring 2024
 */
public class OrdersPage {

    @FXML
    private Tab closedOrdersTabPage;
    
    @FXML
    private Tab openOrdersTabPage;

    @FXML
    private Text employeeFullNameLabel;

    @FXML
    private Text employeeIdLabel;

    @FXML
    private Text employeeRoleLabel;

    @FXML
    void homePageButtonOnClick(ActionEvent event) throws IOException {
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	Parent parent = FXMLLoader.load(Main.class.getResource(Main.HOME_PAGE));
    	Scene scene = stage.getScene();
    	scene.setRoot(parent);
    	stage.setTitle("Home Page");
    }

    @FXML
    void inventoryPageButtonOnClick(ActionEvent event) throws IOException {
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	Parent parent = FXMLLoader.load(Main.class.getResource(Main.INVENTORY_PAGE));
    	Scene scene = stage.getScene();
    	scene.setRoot(parent);
    	stage.setTitle("Inventory Page");
    }

    @FXML
    void logOutButtonOnClick(ActionEvent event) {
    	// TODO
    }
    
	public void initialize() {

	}

}

