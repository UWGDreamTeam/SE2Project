package edu.westga.cs3212.inventory_manager.view.order;

import java.io.IOException;
import java.util.Optional;

import edu.westga.cs3212.inventory_manager.Main;
import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.warehouse.CompletionStatus;
import edu.westga.cs3212.inventory_manager.model.warehouse.Order;
import edu.westga.cs3212.inventory_manager.viewmodel.order.OrderViewModel;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The OrderPage class is the controller for the OrdersPage view. It handles
 * user interactions and manages the display of orders. It uses an
 * OrderPageViewModel to interact with the underlying data model.
 * 
 * @author Group 1
 * @version Spring 2024
 */
public class OrderPage {

	@FXML
	private Button adminButton;
	
	@FXML
	private TableView<Order> openOrders;

	@FXML
	private TableView<Order> closedOrders;

	@FXML
	private TableColumn<Order, String> openOrderNumberColumn;

	@FXML
	private TableColumn<Order, String> openProductionCostColumn;

	@FXML
	private TableColumn<Order, String> openFulfillmentStatusColumn;

	@FXML
	private TableColumn<Order, String> openSalesPriceColumn;

	@FXML
	private TableColumn<Order, String> closedOrderNumberColumn;

	@FXML
	private TableColumn<Order, String> closedProductionCostColumn;

	@FXML
	private TableColumn<Order, String> closedFulfillmentStatusColumn;

	@FXML
	private TableColumn<Order, String> closedSalesPriceColumn;

	@FXML
	private TabPane ordersTabPane;

	@FXML
	private Text fullNameLabel;
	
	@FXML
	private Text employeeIdLabel;
	
	@FXML
	private Text workerTypeLabel;

	@FXML
	private Text employeeRoleLabel;

	@FXML
	private Button fulfillButton;

	private OrderViewModel viewModel;
	
	@FXML
	void initialize() {
		this.viewModel = new OrderViewModel();

		this.bindTableColumns();

		this.openOrders.getItems().addAll(this.viewModel.getIncompleteOrders());
		this.closedOrders.getItems().addAll(this.viewModel.getCompleteOrders());

		this.viewModel.getSelectedOrderProperty().bind(
				this.openOrders.getSelectionModel().selectedItemProperty());

		BooleanBinding isClosedOrdersTabSelected = this.ordersTabPane
				.getSelectionModel().selectedIndexProperty().isEqualTo(1);

		BooleanBinding shouldDisableFulfillButton = this.viewModel
				.getSelectedOrderProperty().isNull()
				.or(isClosedOrdersTabSelected);

		this.setPermissions();
		this.fulfillButton.disableProperty().bind(shouldDisableFulfillButton);
		Main.createSummary(this.fullNameLabel, this.employeeIdLabel, this.workerTypeLabel);
	}
	
	@FXML
	void homePageButtonOnClick(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Parent parent = FXMLLoader.load(Main.class.getResource(Main.HOME_PAGE));
		Scene scene = stage.getScene();
		scene.getStylesheets().add(Main.class.getResource(Constants.STYLESHEET_PATH).toExternalForm());
		scene.setRoot(parent);
		stage.setTitle(Constants.HOME_PAGE_TITLE);
	}

	@FXML
	void inventoryPageButtonOnClick(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Parent parent = FXMLLoader.load(Main.class.getResource(Main.INVENTORY_PAGE));
		Scene scene = stage.getScene();
		scene.getStylesheets().add(Main.class.getResource(Constants.STYLESHEET_PATH).toExternalForm());
		scene.setRoot(parent);
		stage.setTitle(Constants.INVENTORY_PAGE_TITLE);
	}
	
	@FXML
	void adminPageButtonOnClick(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Parent parent = FXMLLoader.load(Main.class.getResource(Main.ADMIN_PAGE));
		Scene scene = new Scene(parent);
		scene.getStylesheets().add(Main.class.getResource(Constants.STYLESHEET_PATH).toExternalForm());
		stage.setScene(scene);
		stage.setTitle(Constants.ADMIN_PAGE_TITLE);
		stage.show();
	}

	@FXML
	void logOutButtonOnClick(ActionEvent event) {
		try {
			Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Parent parent = FXMLLoader.load(Main.class.getResource(Main.LOGIN_PAGE));
			Scene currentScene = currentStage.getScene();
			currentScene.getStylesheets().add(Main.class.getResource(Constants.STYLESHEET_PATH).toExternalForm());
			currentScene.setRoot(parent);
			currentStage.setTitle(Main.WINDOW_TITLE);
			currentStage.sizeToScene();
		} catch (IOException e) {
			Alert errorPopup = new Alert(AlertType.ERROR);
			errorPopup.setContentText(e.getMessage());
			errorPopup.showAndWait();
		}
	}
	
	private void setPermissions() {
		if (!this.viewModel.isManager()) {
			this.adminButton.setDisable(true);
		}
	}

	private void bindTableColumns() {
		this.openOrderNumberColumn
				.setCellValueFactory(new PropertyValueFactory<>("ID"));
		this.openFulfillmentStatusColumn.setCellValueFactory(cellData -> {
			String status = cellData.getValue()
					.getCompletionStatus() == CompletionStatus.COMPLETE
							? "Completed"
							: "Incomplete";
			return new SimpleStringProperty(status);
		});

		this.openSalesPriceColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(String
						.format("%.2f", cellData.getValue().getSalePrice())));
		this.openProductionCostColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.format("%.2f",
						cellData.getValue().getProductionCost())));

		this.closedOrderNumberColumn
				.setCellValueFactory(new PropertyValueFactory<>("ID"));
		this.closedFulfillmentStatusColumn.setCellValueFactory(cellData -> {
			String status = cellData.getValue()
					.getCompletionStatus() == CompletionStatus.COMPLETE
							? "Completed"
							: "Incomplete";
			return new SimpleStringProperty(status);
		});

		this.closedSalesPriceColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(String
						.format("%.2f", cellData.getValue().getSalePrice())));
		this.closedProductionCostColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.format("%.2f",
						cellData.getValue().getProductionCost())));
	}

	@FXML
	void fullfillButtonOnClick(ActionEvent event) {
		Order selectedOrder = this.openOrders.getSelectionModel()
				.getSelectedItem();
		if (selectedOrder != null) {
			Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
			confirmationDialog.setTitle("Confirm Fulfillment");
			confirmationDialog.setHeaderText(null);
			confirmationDialog.setContentText(
					"Are you sure you want to fulfill the selected order?");

			Optional<ButtonType> result = confirmationDialog.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				this.viewModel.fulfillSelectedOrder(selectedOrder);
				this.refreshTablesAndClearSelection();
			}
		}
	}

	private void refreshTablesAndClearSelection() {
		this.openOrders.getItems().setAll(this.viewModel.getIncompleteOrders());
		this.closedOrders.getItems().setAll(this.viewModel.getCompleteOrders());
		this.openOrders.getSelectionModel().clearSelection();

	}
}
