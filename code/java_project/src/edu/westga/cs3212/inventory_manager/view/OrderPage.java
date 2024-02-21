package edu.westga.cs3212.inventory_manager.view;

import java.io.IOException;

import edu.westga.cs3212.inventory_manager.Main;
import edu.westga.cs3212.inventory_manager.model.Order;
import edu.westga.cs3212.inventory_manager.viewmodel.OrderPageViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The OrderPage class is the controller for the OrdersPage view.
 * It handles user interactions and manages the display of orders.
 * It uses an OrderPageViewModel to interact with the underlying data model.
 * 
 * @author Group 1
 * @version Spring 2024
 */
public class OrderPage {
    
    @FXML
    private TableView<Order> openOrders;
    
    @FXML
    private TableView<Order> closedOrders;
    
    @FXML
    private TableColumn<Order, String> openOrderNumberColumn;
    
    @FXML
    private TableColumn<Order, String> openDateCreatedColumn;
    
    @FXML
    private TableColumn<Order, String> openFulfillmentStatusColumn;
    
    @FXML
    private TableColumn<Order, String> openTotalColumn;
    
    @FXML
    private TableColumn<Order, String> closedOrderNumberColumn;
    
    @FXML
    private TableColumn<Order, String> closedDateCreatedColumn;
    
    @FXML
    private TableColumn<Order, String> closedFulfillmentStatusColumn;
    
    @FXML
    private TableColumn<Order, String> closedTotalColumn;

    @FXML
    private Text employeeFullNameLabel;

    @FXML
    private Text employeeIdLabel;

    @FXML
    private Text employeeRoleLabel;
    
    private OrderPageViewModel viewModel;

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
    
    @FXML
	void initialize() {
    	this.viewModel = new OrderPageViewModel();
    	this.bindTableColumns();
    	
    	this.openOrders.getItems().addAll(this.viewModel.getOpenOrders());
    	this.closedOrders.getItems().addAll(this.viewModel.getClosedOrders());
	}
    
    private void bindTableColumns() {
    	this.openOrderNumberColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    	this.openDateCreatedColumn.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
    	this.openFulfillmentStatusColumn.setCellValueFactory(cellData -> {
            String status = cellData.getValue().isCompleted() ? "Completed" : "Incomplete";
            return new SimpleStringProperty(status);
        });
        
    	this.closedOrderNumberColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    	this.closedDateCreatedColumn.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
    	closedFulfillmentStatusColumn.setCellValueFactory(cellData -> {
            String status = cellData.getValue().isCompleted() ? "Completed" : "Incomplete";
            return new SimpleStringProperty(status);
        });
    }
    


}

