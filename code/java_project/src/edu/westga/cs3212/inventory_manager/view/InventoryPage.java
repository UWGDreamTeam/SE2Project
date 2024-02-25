package edu.westga.cs3212.inventory_manager.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs3212.inventory_manager.Main;
import edu.westga.cs3212.inventory_manager.model.Item;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableView;
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
    private TableView<Item> componentsTableView;
    
    @FXML
    private TableColumn<Item, String> idColumn;
    
    @FXML
    private TableColumn<Item, String> nameColumn;
    
    @FXML
    private TableColumn<Item, Number> costColumn;
    
    @FXML
    private TableColumn<Item, Number> quantityColumn;

    @FXML
    private TableColumn<Item, Number> recipesColumn;
    
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

    /* GENERAL */
    
    @FXML
    void initialize() {
    	
        this.inventoryVM = new InventoryViewModel();
        
        this.inventoryVM.getSelectedComponent().bind(this.componentsTableView.getSelectionModel().selectedItemProperty());
        
        this.setupComponentsTableView();
    }

	private void setupComponentsTableView() {
        this.refreshComponentsTableView();

        this.idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        this.nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        this.costColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getUnitCost()));
        this.quantityColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()));
	}
    
    @FXML
    void homePageButtonOnClick(ActionEvent event) throws IOException {
    	
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(Main.class.getResource(Main.HOME_PAGE));
        Scene currentScene = currentStage.getScene();
        
        currentScene.setRoot(parent);
        currentStage.setTitle("Home Page");
    }
    
    @FXML
    void logOutButtonOnClick(ActionEvent event) {
    	//TODO
    }
    
    /* COMPONENTS TAB*/
    
    @FXML
    void addComponentButtonManagerOnClick(ActionEvent event) throws IOException {
    	System.out.println("Size is " + this.inventoryVM.getObservableComponentList().size());
    	Stage modalStage = new Stage();
    	Parent parent = FXMLLoader.load(Main.class.getResource(Main.ADD_PAGE));
		Scene scene = new Scene(parent);
		
		modalStage.setTitle(Main.WINDOW_TITLE);
		modalStage.setScene(scene);
		modalStage.initModality(Modality.WINDOW_MODAL);
		modalStage.initOwner(((Node) event.getSource()).getScene().getWindow());
		modalStage.showAndWait();
		
		System.out.println("Refreshing");
		System.out.println("Size is " + this.inventoryVM.getObservableComponentList().size());
		this.refreshComponentsTableView();
    }
    
    @FXML
    void removeComponentButtonManagerOnClick(ActionEvent event) {
    	this.inventoryVM.removeComponent();
    	
    	this.refreshComponentsTableView();
    }
    
    @FXML
    void editComponentButtonManagerOnClick(ActionEvent event) {
    	//TODO
    }
    
    @FXML
    void orderComponentButtonManagerOnClick(ActionEvent event) {
    	//TODO
    }
    

	private void refreshComponentsTableView() {
		this.componentsTableView.setItems(this.inventoryVM.getObservableComponentList());
	}
    
    /* PRODUCT TABS */
    
    @FXML
    void addProdcutManagerOnClick(ActionEvent event) throws IOException {
    	//TODO
    }

    
    @FXML
    void ordersPageButtonOnClick(ActionEvent event) {
    	//TODO
    }

    @FXML
    void editProductManagerOnClick(ActionEvent event) {
    	//TODO
    }

    @FXML
    void produceProductButtonOnClick(ActionEvent event) {
    	//TODO
    }

    @FXML
    void removeProductManagerOnClick(ActionEvent event) {
    	//TODO
    }
}
