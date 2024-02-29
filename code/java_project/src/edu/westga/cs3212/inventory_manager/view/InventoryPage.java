package edu.westga.cs3212.inventory_manager.view;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import edu.westga.cs3212.inventory_manager.Main;
import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Item;
import edu.westga.cs3212.inventory_manager.model.Product;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;
import edu.westga.cs3212.inventory_manager.viewmodel.InventoryViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InventoryPage {

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
    private TableView<Component> componentsTableView;
    
    @FXML
    private TableColumn<Item, String> idColumn;
    
    @FXML
    private TableColumn<Item, String> nameColumn;
    
    @FXML
    private TableColumn<Item, Number> costColumn;
    
    @FXML
    private TableColumn<Component, Integer> quantityColumn;

    @FXML
    private TableColumn<Component, Number> recipesColumn;
    
    @FXML
    private TableView<Product> productsTableView;
    
    @FXML
    private TableColumn<Product, String> productIDColumn;
    
    @FXML
    private TableColumn<Product, String> productNameColumn;
    
    @FXML
    private TableColumn<Product, Number> productProductionCostColumn;
    
    @FXML
    private TableColumn<Product, Integer> productQuantityColumn;
    
    @FXML
    private TableColumn<Product, Number> productSellingPrice;
    
    @FXML
    private Text employeeFullNameLabel;

    @FXML
    private Text employeeIdLabel;

    @FXML
    private Text employeeRoleLabel;

    @FXML
    private TabPane invendoryTreeView;

    @FXML
    private Tab productsTabPage;

    private InventoryViewModel inventoryVM;
    private LocalComponentInventory localComponentInventory;
    private LocalProductInventory localProductInventory;

    /* GENERAL */
    
    @FXML
    void initialize() {
        this.localComponentInventory = new LocalComponentInventory();
        this.localProductInventory = new LocalProductInventory();
        this.inventoryVM = new InventoryViewModel(localComponentInventory, localProductInventory);
        
        this.inventoryVM.getSelectedComponent().bind(this.componentsTableView.getSelectionModel().selectedItemProperty());
        this.inventoryVM.getSelectedProduct().bind(this.productsTableView.getSelectionModel().selectedItemProperty());
        
        this.setupComponentsTableView();
        this.setupProductsTableView();
        this.setupComponentButtons();
        this.setupProductButtons();
    }

	private void setupProductButtons() {
		this.editProductButton.disableProperty().bind(
	            Bindings.isNull(this.productsTableView.getSelectionModel().selectedItemProperty())
	        );

	        this.productProduceButton.disableProperty().bind(
	            Bindings.isNull(this.productsTableView.getSelectionModel().selectedItemProperty())
	        );
	        this.removeProductButton.disableProperty().bind(
	            Bindings.isNull(this.productsTableView.getSelectionModel().selectedItemProperty())
	        );
	}

	private void setupComponentButtons() {
	        this.componentEditButton.disableProperty().bind(
	            Bindings.isNull(this.componentsTableView.getSelectionModel().selectedItemProperty())
	        );
	        this.componentOrderButton.disableProperty().bind(
	            Bindings.isNull(this.componentsTableView.getSelectionModel().selectedItemProperty())
	        );
	        this.componentRemoveButton.disableProperty().bind(
	            Bindings.isNull(this.componentsTableView.getSelectionModel().selectedItemProperty())
	        );
	}

	private void setupProductsTableView() {
		this.refreshProductsTableView();
		
		this.productIDColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getID()));
		this.productNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		this.productProductionCostColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getProductionCost()));
		this.productQuantityColumn.setCellValueFactory(cellData -> {
			try {
				int quantity = this.localProductInventory.getQuantityOfItem(cellData.getValue());
				return new SimpleIntegerProperty(quantity).asObject();
			} catch (IllegalArgumentException e) {
				return new SimpleIntegerProperty(0).asObject();
			}
		});
		this.productSellingPrice.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getSalePrice()));
		
	}

	private void refreshProductsTableView() {
		this.productsTableView.setItems(this.inventoryVM.getObservableProductList());
		this.productsTableView.refresh();
	}

	private void setupComponentsTableView() {
        this.refreshComponentsTableView();

        this.idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getID()));
        this.nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        this.costColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getProductionCost()));
        this.quantityColumn.setCellValueFactory(cellData -> {
            try {
                int quantity = this.localComponentInventory.getQuantityOfItem(cellData.getValue());
                return new SimpleIntegerProperty(quantity).asObject();
            } catch (IllegalArgumentException e) {
                return new SimpleIntegerProperty(0).asObject();
            }
        });
	}

	@FXML
    void homePageButtonOnClick(ActionEvent event) throws IOException {
    	
    	try {
			Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        Parent parent;
			parent = FXMLLoader.load(Main.class.getResource(Main.HOME_PAGE));
			Scene currentScene = currentStage.getScene();
	        currentScene.setRoot(parent);
	        currentStage.setTitle("Home Page");
		} catch (IOException e) {
			Alert errorPopup = new Alert(AlertType.ERROR);
    		errorPopup.setContentText(e.getMessage());
    		errorPopup.showAndWait();
		}
    }
	
	@FXML
    void orderPageButtonOnClick(ActionEvent event) throws IOException {
    	
    	try {
			Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        Parent parent;
			parent = FXMLLoader.load(Main.class.getResource(Main.ORDER_PAGE));
			Scene currentScene = currentStage.getScene();
	        currentScene.setRoot(parent);
	        currentStage.setTitle("Order Page");
		} catch (IOException e) {
			Alert errorPopup = new Alert(AlertType.ERROR);
    		errorPopup.setContentText(e.getMessage());
    		errorPopup.showAndWait();
		}
    }
    
    @FXML
    void logOutButtonOnClick(ActionEvent event) {
    	
		try {
	        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        Parent parent = FXMLLoader.load(Main.class.getResource(Main.LOGIN_PAGE));
	        Scene currentScene = currentStage.getScene();
	        currentScene.setRoot(parent);
	        currentStage.setTitle(Main.WINDOW_TITLE);
		} catch (IOException e) {
			Alert errorPopup = new Alert(AlertType.ERROR);
    		errorPopup.setContentText(e.getMessage());
    		errorPopup.showAndWait();
		}
        
    }
    
    /* COMPONENTS TAB*/
    
    @FXML
    void addComponentButtonManagerOnClick(ActionEvent event) throws IOException {
    	Stage modalStage = new Stage();
    	Parent parent = FXMLLoader.load(Main.class.getResource(Main.ADD_PAGE));
		Scene scene = new Scene(parent);
		
		modalStage.setTitle(Main.WINDOW_TITLE);
		modalStage.setScene(scene);
		modalStage.initModality(Modality.WINDOW_MODAL);
		modalStage.initOwner(((Node) event.getSource()).getScene().getWindow());
		modalStage.showAndWait();
		
		this.refreshComponentsTableView();
    }
    
    @FXML
    void removeComponentButtonManagerOnClick(ActionEvent event) {
    	this.inventoryVM.removeComponent();
    	
    	this.refreshComponentsTableView();
    }
    
    @FXML
    void editComponentButtonManagerOnClick(ActionEvent event) throws IOException {
    	Item selectedItem = this.componentsTableView.getSelectionModel().getSelectedItem();
    	 
		FXMLLoader loader = new FXMLLoader(Main.class.getResource(Main.EDIT_COMPONENT_PAGE));
	    Parent parent = loader.load();
    	
	    EditComponentPage editComponentPage = loader.getController();
    	editComponentPage.initializeWithItem(selectedItem);
    	
    	Stage modalStage = new Stage();
		Scene scene = new Scene(parent);
		modalStage.setTitle(Main.WINDOW_TITLE);
		modalStage.setScene(scene);
		modalStage.initModality(Modality.WINDOW_MODAL);
		modalStage.initOwner(((Node) event.getSource()).getScene().getWindow());
		modalStage.showAndWait();
		
		this.refreshComponentsTableView();
    }
    
    @FXML
    void orderComponentButtonManagerOnClick(ActionEvent event) {
    	TextInputDialog dialog = new TextInputDialog("1"); // Default value is 1
        dialog.setTitle("Order Component");
        dialog.setHeaderText("Order More Components");
        dialog.setContentText("Please enter the quantity:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                int quantity = Integer.parseInt(result.get());
                if (quantity > 0) {
                    Component selectedComponent = componentsTableView.getSelectionModel().getSelectedItem();
                    if (selectedComponent != null) {
                        this.inventoryVM.orderComponent(selectedComponent, quantity);
                        this.refreshComponentsTableView();
                    } else {
                        showAlert("Order Error", "No component selected.", AlertType.ERROR);
                    }
                } else {
                    showAlert("Invalid Quantity", "Please enter a positive number.", AlertType.ERROR);
                }
            } catch (NumberFormatException e) {
                showAlert("Invalid Input", "Please enter a valid number.", AlertType.ERROR);
            }
        }
        this.refreshComponentsTableView();
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

	private void refreshComponentsTableView() {
		this.componentsTableView.setItems(this.inventoryVM.getObservableComponentList());
		this.componentsTableView.refresh();
	}
    
    /* PRODUCT TABS */
    
    @FXML
    void addProdcutManagerOnClick(ActionEvent event) throws IOException {
    	Stage modalStage = new Stage();
    	Parent parent = FXMLLoader.load(Main.class.getResource(Main.ADD_PRODUCT_PAGE));
		Scene scene = new Scene(parent);
		
		modalStage.setTitle(Main.WINDOW_TITLE);
		modalStage.setScene(scene);
		modalStage.initModality(Modality.WINDOW_MODAL);
		modalStage.initOwner(((Node) event.getSource()).getScene().getWindow());
		modalStage.showAndWait();
		
		this.refreshComponentsTableView();
    }

    @FXML
    void editProductManagerOnClick(ActionEvent event) throws IOException {
    	Item selectedItem = this.productsTableView.getSelectionModel().getSelectedItem();
   	 
		FXMLLoader loader = new FXMLLoader(Main.class.getResource(Main.EDIT_PRODUCT_PAGE));
	    Parent parent = loader.load();
    	
	    EditProduct editProduct = loader.getController();
    	editProduct.initalizeWithItem(selectedItem);
    	
    	Stage modalStage = new Stage();
		Scene scene = new Scene(parent);
		modalStage.setTitle(Main.WINDOW_TITLE);
		modalStage.setScene(scene);
		modalStage.initModality(Modality.WINDOW_MODAL);
		modalStage.initOwner(((Node) event.getSource()).getScene().getWindow());
		modalStage.showAndWait();
		
		this.refreshComponentsTableView();
    }

    @FXML
    void produceProductButtonOnClick(ActionEvent event) {
    	try {
    		this.inventoryVM.produceProduct(this.productsTableView.getSelectionModel().getSelectedItem(), 1);
		} catch (IllegalArgumentException e) {
			Alert errorPopup = new Alert(AlertType.ERROR);
			errorPopup.setContentText(e.getMessage());
			errorPopup.showAndWait();
		}
    	this.refreshProductsTableView();
    }

    @FXML
    void removeProductManagerOnClick(ActionEvent event) {
    	this.inventoryVM.removeProduct();
    	this.refreshProductsTableView();
    }
}