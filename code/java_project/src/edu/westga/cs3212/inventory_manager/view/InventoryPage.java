package edu.westga.cs3212.inventory_manager.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs3212.inventory_manager.Main;
import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Item;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;
import edu.westga.cs3212.inventory_manager.viewmodel.InventoryViewModel;
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
import javafx.scene.control.TreeTableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InventoryPage {

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
    private Text employeeFullNameLabel;

    @FXML
    private Text employeeIdLabel;

    @FXML
    private Text employeeRoleLabel;

    @FXML
    private TabPane invendoryTreeView;

    @FXML
    private TreeTableView<?> productsTableView;

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
        
        this.setupComponentsTableView();
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
    void logOutButtonOnClick(ActionEvent event) {
    	
		try {
			/* TO DO, IMPLEMENT LOGOUT FEATURE */
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
    	//TO DO
    }

	private void refreshComponentsTableView() {
		this.componentsTableView.setItems(this.inventoryVM.getObservableComponentList());
	}
    
    /* PRODUCT TABS */
    
    @FXML
    void addProdcutManagerOnClick(ActionEvent event) throws IOException {
    	//TO DO
    }

    
    @FXML
    void ordersPageButtonOnClick(ActionEvent event) {
    	//TO DO
    }

    @FXML
    void editProductManagerOnClick(ActionEvent event) {
    	//TO DO
    }

    @FXML
    void produceProductButtonOnClick(ActionEvent event) {
    	//TO DO
    }

    @FXML
    void removeProductManagerOnClick(ActionEvent event) {
    	//TO DO
    }
}