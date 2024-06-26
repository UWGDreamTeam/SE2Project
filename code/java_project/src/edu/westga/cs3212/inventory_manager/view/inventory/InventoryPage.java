package edu.westga.cs3212.inventory_manager.view.inventory;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import edu.westga.cs3212.inventory_manager.Main;
import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.ProductInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Item;
import edu.westga.cs3212.inventory_manager.model.warehouse.Product;
import edu.westga.cs3212.inventory_manager.view.inventory.component.EditComponent;
import edu.westga.cs3212.inventory_manager.view.inventory.product.EditProduct;
import edu.westga.cs3212.inventory_manager.viewmodel.inventory.InventoryViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InventoryPage {

	private static final String DEFAULT_QUANTITY = "1";
	private static final String PRODUCE_PRODUCT_TITLE = "Produce Product";
	private static final String ENTER_QUANTITY_MESSAGE = "Enter Quantity to Produce";
	private static final String QUANTITY_PROMPT = "Quantity:";
	private static final String PRODUCTION_ERROR_TITLE = "Production Error";
	private static final String PRODUCE_ERROR_TITLE = "Produce Error";
	private static final String INVALID_QUANTITY_MESSAGE = "Invalid Quantity";
	private static final String ENTER_POSITIVE_NUMBER_MESSAGE = "Please enter a positive number.";
	private static final String INVALID_INPUT_TITLE = "Invalid Input";
	private static final String VALID_NUMBER_MESSAGE = "Please enter a valid number.";
	private static final String NO_PRODUCT_SELECTED = "No product selected.";
	private static final String COMPONENT_REMOVED_TITLE = "Component Removed";
	private static final String COMPONENT_REMOVED_MESSAGE = "The component was successfully removed.";
	private static final String ORDER_COMPONENT_TITLE = "Order Component";
	private static final String ORDER_COMPONENT_HEADER = "Order More Components";
	private static final String ORDER_COMPONENT_MESSAGE = "Please enter the quantity:";
	private static final String ORDER_ERROR_TITLE = "Order Error";
	private static final String NO_COMPONENT_SELECTED = "No component selected.";
	private static final String ORDERED_COMPONENTS_TITLE = "Ordered Components";
	private static final String HOME_PAGE_TITLE = "Home Page";
	private static final String ORDER_PAGE_TITLE = "Order Page";
	private static final String PRODUCT_REMOVED_TITLE = "Product Removed";
	private static final String PRODUCT_REMOVED_MESSAGE = "The product was successfully removed.";
	private static final String RECEIVED_UNITS_FORMAT = "We have received %d units of '%s'.";

	@FXML
	private Button adminButton;

	@FXML
	private Button editProductButton;

	@FXML
	private Button productAddButton;

	@FXML
	private Button productProduceButton;

	@FXML
	private Button removeProductButton;

	@FXML
	private Button componentAddButton;

	@FXML
	private Button componentEditButton;

	@FXML
	private Button componentOrderButton;

	@FXML
	private Button componentRemoveButton;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Tab componentsTabPage;

	@FXML
	private TextField componentSearchTextField;

	@FXML
	private Button componentSearchButton;

	@FXML
	private TableView<Component> componentsTableView;

	@FXML
	private TableColumn<Item, String> idColumn;

	@FXML
	private TableColumn<Item, String> nameColumn;

	@FXML
	private TableColumn<Item, String> costColumn;

	@FXML
	private TableColumn<Component, Integer> quantityColumn;

	@FXML
	private TableColumn<Component, Number> recipesColumn;

	@FXML
	private TableColumn<Item, String> lowStockCostColumn;

	@FXML
	private TableColumn<Item, String> lowStockIDColumn;

	@FXML
	private TableColumn<Item, String> lowStockNameColumn;

	@FXML
	private TableColumn<Component, Integer> lowStockQuantityColumn;

	@FXML
	private Tab lowStockTabPage;

	@FXML
	private TableView<Component> lowStockTableView;

	@FXML
	private Button lowStockComponentOrderButton;

	@FXML
	private Button lowStockComponentRemoveButton;

	@FXML
	private Button lowStockComponentSettingsButton;

	@FXML
	private Tab productsTabPage;

	@FXML
	private TextField productSearchTextField;

	@FXML
	private Button productSearchButton;

	@FXML
	private TableView<Product> productsTableView;

	@FXML
	private TableColumn<Product, String> productIDColumn;

	@FXML
	private TableColumn<Product, String> productNameColumn;

	@FXML
	private TableColumn<Product, String> productProductionCostColumn;

	@FXML
	private TableColumn<Product, Integer> productQuantityColumn;

	@FXML
	private TableColumn<Product, String> productSellingPrice;

	@FXML
	private Text employeeRoleLabel;

	@FXML
	private TabPane inventoryTreeView;

	@FXML
	private Text fullNameLabel;

	@FXML
	private Text employeeIdLabel;

	@FXML
	private Text workerTypeLabel;

	private InventoryViewModel inventoryVM;

	/* GENERAL */

	@FXML
	void initialize() {
		this.inventoryVM = new InventoryViewModel();

		this.inventoryVM.getSelectedComponent().bind(this.componentsTableView
				.getSelectionModel().selectedItemProperty());
		this.inventoryVM.getSelectedProduct().bind(this.productsTableView
				.getSelectionModel().selectedItemProperty());

		this.lowStockComponentOrderButton.disableProperty()
				.bind(Bindings.isNull(this.lowStockTableView.getSelectionModel()
						.selectedItemProperty()));
		this.lowStockComponentRemoveButton.disableProperty()
				.bind(Bindings.isNull(this.lowStockTableView.getSelectionModel()
						.selectedItemProperty()));

		this.setupComponentsTableView();
		this.setupProductsTableView();
		this.setupComponentButtons();
		this.setupProductButtons();
		this.setupLowStockTableView();
		this.setPermissions();
		Main.createSummary(this.fullNameLabel, this.employeeIdLabel,
				this.workerTypeLabel);

	}

	private void setupProductButtons() {
		boolean isManager = this.inventoryVM.isManager();

		this.productAddButton.setDisable(!isManager);
		this.editProductButton.setDisable(!isManager);
		this.removeProductButton.setDisable(!isManager);

		this.editProductButton.disableProperty().bind(Bindings.or(
				Bindings.isNull(this.productsTableView.getSelectionModel()
						.selectedItemProperty()),
				Bindings.not(Bindings.createBooleanBinding(() -> isManager))));
		this.removeProductButton.disableProperty().bind(Bindings.or(
				Bindings.isNull(this.productsTableView.getSelectionModel()
						.selectedItemProperty()),
				Bindings.not(Bindings.createBooleanBinding(() -> isManager))));

		this.productProduceButton.disableProperty()
				.bind(Bindings.isNull(this.productsTableView.getSelectionModel()
						.selectedItemProperty()));
	}

	private void setupComponentButtons() {

		if (!this.inventoryVM.isManager()) {
			this.componentOrderButton.setDisable(true);
			this.componentAddButton.setDisable(true);
			this.componentEditButton.setDisable(true);
			this.componentRemoveButton.setDisable(true);
			this.lowStockTabPage.setDisable(true);

		} else {
			this.componentEditButton.disableProperty()
					.bind(Bindings.isNull(this.componentsTableView
							.getSelectionModel().selectedItemProperty()));
			this.componentOrderButton.disableProperty()
					.bind(Bindings.isNull(this.componentsTableView
							.getSelectionModel().selectedItemProperty()));
			this.componentRemoveButton.disableProperty()
					.bind(Bindings.isNull(this.componentsTableView
							.getSelectionModel().selectedItemProperty()));
		}
	}

	private void setPermissions() {

		if (!this.inventoryVM.isManager()) {
			this.adminButton.setDisable(true);
		}
	}

	private void setupProductsTableView() {
		this.refreshProductsTableView();

		this.productIDColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(
						cellData.getValue().getID()));
		this.productNameColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(
						cellData.getValue().getName()));
		this.productProductionCostColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty("$" + String.format("%.2f",
						cellData.getValue().getProductionCost())));
		this.productQuantityColumn.setCellValueFactory(cellData -> {
			try {
				int quantity = ProductInventory
						.getQuantity(cellData.getValue().getID());
				return new SimpleIntegerProperty(quantity).asObject();
			} catch (IllegalArgumentException e) {
				return new SimpleIntegerProperty(0).asObject();
			}
		});
		this.productSellingPrice
				.setCellValueFactory(cellData -> new SimpleStringProperty(String
						.format("$%.2f", cellData.getValue().getSalePrice())));

	}

	private void refreshProductsTableView() {
		this.productsTableView
				.setItems(this.inventoryVM.getObservableProductList());
		this.productsTableView.refresh();
	}

	private void setupComponentsTableView() {
		this.refreshComponentsTableView();

		this.idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
				cellData.getValue().getID()));
		this.nameColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(
						cellData.getValue().getName()));
		this.costColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty("$" + String.format("%.2f",
						cellData.getValue().getProductionCost())));
		this.quantityColumn.setCellValueFactory(cellData -> {
			try {
				int quantity = ComponentInventory
						.getQuantity(cellData.getValue().getID());
				return new SimpleIntegerProperty(quantity).asObject();
			} catch (IllegalArgumentException e) {
				return new SimpleIntegerProperty(0).asObject();
			}
		});
	}

	private void setupLowStockTableView() {
		this.lowStockTableView
				.setItems(this.inventoryVM.getLowStockComponents());
		this.setupLowStockTableColumns();
		this.refreshLowStockTableView();
		this.updateLowStockTabStyle();

	}

	private void setupLowStockTableColumns() {
		this.lowStockIDColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(
						cellData.getValue().getID()));
		this.lowStockNameColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(
						cellData.getValue().getName()));
		this.lowStockCostColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty("$" + String.format("%.2f",
						cellData.getValue().getProductionCost())));
		this.lowStockQuantityColumn.setCellValueFactory(cellData -> {
			try {
				int quantity = ComponentInventory
						.getQuantity(cellData.getValue().getID());
				return new SimpleIntegerProperty(quantity).asObject();
			} catch (IllegalArgumentException e) {
				return new SimpleIntegerProperty(0).asObject();
			}
		});
	}

	private void refreshLowStockTableView() {
		this.lowStockTableView
				.setItems(this.inventoryVM.getLowStockComponents());
		this.lowStockTableView.refresh();
	}

	private void updateLowStockTabStyle() {
		if (this.lowStockTableView.getItems() == null
				|| this.lowStockTableView.getItems().isEmpty()) {
			this.lowStockTabPage.setStyle("");
		} else {
			this.lowStockTabPage.setStyle("-fx-background-color: #FF6347;");
		}
	}

	@FXML
	void homePageButtonOnClick(ActionEvent event) throws IOException {

		try {
			Stage currentStage = (Stage) ((Node) event.getSource()).getScene()
					.getWindow();
			Parent parent;
			parent = FXMLLoader.load(Main.class.getResource(Main.HOME_PAGE));
			Scene currentScene = currentStage.getScene();
			currentScene.getStylesheets().add(Main.class
					.getResource(Constants.STYLESHEET_PATH).toExternalForm());
			currentScene.setRoot(parent);
			currentStage.setTitle(HOME_PAGE_TITLE);
			currentStage.getIcons()
					.add(Main.getPrimaryStage().getIcons().get(0));
		} catch (IOException e) {
			Alert errorPopup = new Alert(AlertType.ERROR);
			errorPopup.setContentText(e.getMessage());
			errorPopup.showAndWait();
		}
	}

	@FXML
	void ordersPageButtonOnClick(ActionEvent event) throws IOException {

		try {
			Stage currentStage = (Stage) ((Node) event.getSource()).getScene()
					.getWindow();
			Parent parent;
			parent = FXMLLoader.load(Main.class.getResource(Main.ORDER_PAGE));
			Scene currentScene = currentStage.getScene();
			currentScene.getStylesheets().add(Main.class
					.getResource(Constants.STYLESHEET_PATH).toExternalForm());
			currentScene.setRoot(parent);
			currentStage.setTitle(ORDER_PAGE_TITLE);
			currentStage.getIcons()
					.add(Main.getPrimaryStage().getIcons().get(0));
		} catch (IOException e) {
			Alert errorPopup = new Alert(AlertType.ERROR);
			errorPopup.setContentText(e.getMessage());
			errorPopup.showAndWait();
		}
	}

	@FXML
	void logOutButtonOnClick(ActionEvent event) {
		try {
			Stage currentStage = (Stage) ((Node) event.getSource()).getScene()
					.getWindow();
			Parent parent = FXMLLoader
					.load(Main.class.getResource(Main.LOGIN_PAGE));
			Scene currentScene = currentStage.getScene();
			currentScene.getStylesheets().add(Main.class
					.getResource(Constants.STYLESHEET_PATH).toExternalForm());
			currentScene.setRoot(parent);
			currentStage.setTitle(Main.WINDOW_TITLE);
			currentStage.getIcons()
					.add(Main.getPrimaryStage().getIcons().get(0));
			currentStage.sizeToScene();
		} catch (IOException e) {
			Alert errorPopup = new Alert(AlertType.ERROR);
			errorPopup.setContentText(e.getMessage());
			errorPopup.showAndWait();
		}
	}

	@FXML
	void settingLowStockButtonManagerOnClick(ActionEvent event)
			throws IOException {
		this.showLowStockSettingsDialog();
		this.refreshLowStockTableView();
		this.updateLowStockTabStyle();
	}

	@FXML
	void adminPageButtonOnClick(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Parent parent = FXMLLoader
				.load(Main.class.getResource(Main.ADMIN_PAGE));
		Scene scene = new Scene(parent);
		scene.getStylesheets().add(Main.class
				.getResource(Constants.STYLESHEET_PATH).toExternalForm());
		stage.setScene(scene);
		stage.setTitle(Constants.ADMIN_PAGE_TITLE);
		stage.getIcons().add(Main.getPrimaryStage().getIcons().get(0));
		stage.show();
	}

	/* COMPONENTS TAB */

	@FXML
	void addComponentButtonManagerOnClick(ActionEvent event)
			throws IOException {
		Stage modalStage = new Stage();
		Parent parent = FXMLLoader.load(Main.class.getResource(Main.ADD_PAGE));
		Scene scene = new Scene(parent);
		scene.getStylesheets().add(Main.class
				.getResource(Constants.STYLESHEET_PATH).toExternalForm());
		modalStage.setTitle(Main.WINDOW_TITLE);
		modalStage.getIcons().add(Main.getPrimaryStage().getIcons().get(0));
		modalStage.setScene(scene);
		modalStage.initModality(Modality.WINDOW_MODAL);
		modalStage.initOwner(((Node) event.getSource()).getScene().getWindow());
		modalStage.showAndWait();

		this.refreshComponentsTableView();
		this.refreshLowStockTableView();
		this.updateLowStockTabStyle();

	}

	@FXML
	void removeComponentButtonManagerOnClick(ActionEvent event) {
		this.inventoryVM.removeComponent();
		this.refreshComponentsTableView();
		this.refreshLowStockTableView();
		this.updateLowStockTabStyle();
		this.showAlert(COMPONENT_REMOVED_TITLE, COMPONENT_REMOVED_MESSAGE,
				AlertType.INFORMATION);
	}

	@FXML
	void removeLowStockButtonManagerOnClick(ActionEvent event) {
		this.inventoryVM.getSelectedComponent().unbind();

		Item selectedLowStockComponent = this.lowStockTableView
				.getSelectionModel().getSelectedItem();
		this.inventoryVM.setSelectedComponent(selectedLowStockComponent);

		this.inventoryVM.removeComponent();

		this.inventoryVM.getSelectedComponent().bind(this.componentsTableView
				.getSelectionModel().selectedItemProperty());

		this.refreshLowStockTableView();
		this.refreshComponentsTableView();
		this.updateLowStockTabStyle();
		this.showAlert(COMPONENT_REMOVED_TITLE, COMPONENT_REMOVED_MESSAGE,
				AlertType.INFORMATION);
	}

	@FXML
	void editComponentButtonManagerOnClick(ActionEvent event)
			throws IOException {
		Item selectedItem = this.componentsTableView.getSelectionModel()
				.getSelectedItem();

		FXMLLoader loader = new FXMLLoader(
				Main.class.getResource(Main.EDIT_COMPONENT_PAGE));
		Parent parent = loader.load();

		EditComponent editComponentPage = loader.getController();
		editComponentPage.initializeWithItem(selectedItem);

		Stage modalStage = new Stage();
		Scene scene = new Scene(parent);
		scene.getStylesheets().add(Main.class
				.getResource(Constants.STYLESHEET_PATH).toExternalForm());
		modalStage.setTitle(Main.WINDOW_TITLE);
		modalStage.getIcons().add(Main.getPrimaryStage().getIcons().get(0));
		modalStage.setScene(scene);
		modalStage.initModality(Modality.WINDOW_MODAL);
		modalStage.initOwner(((Node) event.getSource()).getScene().getWindow());
		modalStage.showAndWait();

		this.refreshComponentsTableView();
		this.refreshLowStockTableView();
	}

	@FXML
	void orderComponentButtonManagerOnClick(ActionEvent event) {
		Component selectedComponent = this.componentsTableView
				.getSelectionModel().getSelectedItem();
		this.showOrderDialog(selectedComponent, this.componentsTableView);
		this.refreshComponentsTableView();
		this.refreshLowStockTableView();
		this.updateLowStockTabStyle();
	}

	@FXML
	void orderLowStockButtonManagerOnClick(ActionEvent event) {
		Component selectedComponent = this.lowStockTableView.getSelectionModel()
				.getSelectedItem();
		this.showOrderDialog(selectedComponent, this.lowStockTableView);
		this.refreshComponentsTableView();
		this.refreshLowStockTableView();
		this.updateLowStockTabStyle();
	}

	private void showOrderDialog(Component selectedComponent,
			TableView<Component> tableView) {
		TextInputDialog dialog = new TextInputDialog(DEFAULT_QUANTITY);
		dialog.setTitle(ORDER_COMPONENT_TITLE);
		dialog.setHeaderText(ORDER_COMPONENT_HEADER);
		dialog.setContentText(ORDER_COMPONENT_MESSAGE);

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			try {
				int quantity = Integer.parseInt(result.get());
				if (quantity > 0) {
					this.tryToOrderComponent(selectedComponent, quantity,
							tableView);
				} else {
					this.showAlert(INVALID_QUANTITY_MESSAGE,
							ENTER_POSITIVE_NUMBER_MESSAGE, AlertType.ERROR);
				}
			} catch (NumberFormatException e) {
				this.showAlert(INVALID_INPUT_TITLE, VALID_NUMBER_MESSAGE,
						AlertType.ERROR);
			}
		}
	}

	private void showLowStockSettingsDialog() {
		TextInputDialog dialog = new TextInputDialog(
				String.valueOf(this.inventoryVM.getLowStockThreshold()));
		dialog.setTitle("Low Stock Settings");
		dialog.setHeaderText("Set Low Stock Threshold");
		dialog.setContentText("Please enter the threshold:");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			try {
				int quantity = Integer.parseInt(result.get());
				if (quantity > 0) {
					this.inventoryVM.setLowStockThreshold(quantity);
				} else {
					this.showAlert(INVALID_QUANTITY_MESSAGE,
							ENTER_POSITIVE_NUMBER_MESSAGE, AlertType.ERROR);
				}
			} catch (NumberFormatException e) {
				this.showAlert(INVALID_INPUT_TITLE, VALID_NUMBER_MESSAGE,
						AlertType.ERROR);
			}
		}
	}

	private void tryToOrderComponent(Component selectedComponent, int quantity,
			TableView<Component> tableView) {
		if (selectedComponent != null) {
			this.inventoryVM.orderComponent(selectedComponent, quantity);
			this.refreshComponentsTableView();
			this.refreshLowStockTableView();
			String content = String.format(RECEIVED_UNITS_FORMAT, quantity,
					selectedComponent.getName());
			this.showAlert(ORDERED_COMPONENTS_TITLE, content,
					AlertType.INFORMATION);
		} else {
			this.showAlert(ORDER_ERROR_TITLE, NO_COMPONENT_SELECTED,
					AlertType.ERROR);
		}
	}

	private void showAlert(String title, String content,
			Alert.AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setContentText(content);
		alert.showAndWait();
	}

	private void refreshComponentsTableView() {
		this.componentsTableView
				.setItems(this.inventoryVM.getObservableComponentList());
		this.componentsTableView.refresh();
	}

	/* PRODUCT TABS */

	@FXML
	void addProductManagerOnClick(ActionEvent event) throws IOException {
		Stage modalStage = new Stage();
		Parent parent = FXMLLoader
				.load(Main.class.getResource(Main.ADD_PRODUCT_PAGE));
		Scene scene = new Scene(parent);
		scene.getStylesheets().add(Main.class
				.getResource(Constants.STYLESHEET_PATH).toExternalForm());
		modalStage.setTitle(Main.WINDOW_TITLE);
		modalStage.getIcons().add(Main.getPrimaryStage().getIcons().get(0));
		modalStage.setScene(scene);
		modalStage.initModality(Modality.WINDOW_MODAL);
		modalStage.initOwner(((Node) event.getSource()).getScene().getWindow());
		modalStage.showAndWait();

		this.refreshProductsTableView();
	}

	@FXML
	void editProductManagerOnClick(ActionEvent event) throws IOException {
		Item selectedItem = this.productsTableView.getSelectionModel()
				.getSelectedItem();

		FXMLLoader loader = new FXMLLoader(
				Main.class.getResource(Main.EDIT_PRODUCT_PAGE));
		Parent parent = loader.load();

		EditProduct editProduct = loader.getController();
		editProduct.initalizeWithItem(selectedItem);

		Stage modalStage = new Stage();
		Scene scene = new Scene(parent);
		scene.getStylesheets().add(Main.class
				.getResource(Constants.STYLESHEET_PATH).toExternalForm());
		modalStage.setTitle(Main.WINDOW_TITLE);
		modalStage.getIcons().add(Main.getPrimaryStage().getIcons().get(0));
		modalStage.setScene(scene);
		modalStage.initModality(Modality.WINDOW_MODAL);
		modalStage.initOwner(((Node) event.getSource()).getScene().getWindow());
		modalStage.showAndWait();

		this.refreshProductsTableView();
	}

	@FXML
	void onComponentSearchTextChanged(ActionEvent event) {
		this.componentsTableView.setItems(this.inventoryVM
				.searchComponents(this.componentSearchTextField.getText()));
		this.componentsTableView.refresh();
	}

	@FXML
	void onComponentSearchButtonClicked(ActionEvent event) {
		this.componentsTableView.setItems(this.inventoryVM
				.searchComponents(this.componentSearchTextField.getText()));
		this.componentsTableView.refresh();
	}

	@FXML
	void onProductSearchButtonClicked(ActionEvent event) {
		this.productsTableView.setItems(this.inventoryVM
				.searchProducts(this.productSearchTextField.getText()));
		this.productsTableView.refresh();
	}

	@FXML
	void produceProductButtonOnClick(ActionEvent event) {
		TextInputDialog dialog = new TextInputDialog(DEFAULT_QUANTITY);
		dialog.setTitle(PRODUCE_PRODUCT_TITLE);
		dialog.setHeaderText(ENTER_QUANTITY_MESSAGE);
		dialog.setContentText(QUANTITY_PROMPT);

		Optional<String> result = dialog.showAndWait();
		result.ifPresent(quantityString -> {
			try {
				int quantity = Integer.parseInt(quantityString);
				if (quantity > 0) {
					Product selectedProduct = this.productsTableView
							.getSelectionModel().getSelectedItem();
					if (selectedProduct != null) {
						this.attemptToProduceProduct(quantity, selectedProduct);
					} else {
						this.showAlert(PRODUCE_ERROR_TITLE, NO_PRODUCT_SELECTED,
								AlertType.ERROR);
					}
				} else {
					this.showAlert(INVALID_QUANTITY_MESSAGE,
							ENTER_POSITIVE_NUMBER_MESSAGE, AlertType.ERROR);
				}
			} catch (NumberFormatException e) {
				this.showAlert(INVALID_INPUT_TITLE, VALID_NUMBER_MESSAGE,
						AlertType.ERROR);
			}
		});
	}

	private void attemptToProduceProduct(int quantity,
			Product selectedProduct) {
		try {
			this.inventoryVM.produceProduct(selectedProduct,
					quantity);
			this.refreshProductsTableView();
			this.refreshComponentsTableView();
			this.refreshLowStockTableView();
			this.updateLowStockTabStyle();
		} catch (IllegalArgumentException e) {
			this.showAlert(PRODUCTION_ERROR_TITLE,
					e.getMessage(), AlertType.ERROR);
		}
	}

	@FXML
	void removeProductManagerOnClick(ActionEvent event) {
		try {
			this.inventoryVM.removeProduct();
			this.refreshProductsTableView();
			this.showAlert(PRODUCT_REMOVED_TITLE, PRODUCT_REMOVED_MESSAGE,
					AlertType.INFORMATION);
		} catch (IllegalArgumentException e) {
			this.showAlert(PRODUCT_REMOVED_TITLE, e.getMessage(),
					AlertType.ERROR);
		}
	}
}