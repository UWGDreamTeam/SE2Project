package edu.westga.cs3212.inventory_manager.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs3212.inventory_manager.Main;
import edu.westga.cs3212.inventory_manager.model.local_impl.Component;
import edu.westga.cs3212.inventory_manager.model.local_impl.Product;
import edu.westga.cs3212.inventory_manager.viewmodel.InventoryViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
    private TreeTableView<Component> componentsTableView;
    
    @FXML
    private TreeTableView<Product> productsTableView;

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

    /* GENERAL */
    
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
    }
    
    @FXML
    void editComponentButtonManagerOnClick(ActionEvent event) {

    }
    
    @FXML
    void orderComponentButtonManagerOnClick(ActionEvent event) {

    }
    
    @FXML
    void removeComponentButtonManagerOnClick(ActionEvent event) {

    }
    
    /* PRODUCT TABS */
    
    @FXML
    void addProdcutManagerOnClick(ActionEvent event) throws IOException {
    	
    }

    
    @FXML
    void ordersPageButtonOnClick(ActionEvent event) {
    	
    }

    @FXML
    void editProductManagerOnClick(ActionEvent event) {

    }

    @FXML
    void produceProductButtonOnClick(ActionEvent event) {

    }

    @FXML
    void removeProductManagerOnClick(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert this.componentsTabPage != null : "fx:id=\"componentsTabPage\" was not injected: check your FXML file 'InventoryPage.fxml'.";
        assert this.componentsTableView != null : "fx:id=\"componentsTableView\" was not injected: check your FXML file 'InventoryPage.fxml'.";
        assert this.employeeFullNameLabel != null : "fx:id=\"employeeFullNameLabel\" was not injected: check your FXML file 'InventoryPage.fxml'.";
        assert this.employeeIdLabel != null : "fx:id=\"employeeIdLabel\" was not injected: check your FXML file 'InventoryPage.fxml'.";
        assert this.employeeRoleLabel != null : "fx:id=\"employeeRoleLabel\" was not injected: check your FXML file 'InventoryPage.fxml'.";
        assert this.invendoryTreeView != null : "fx:id=\"invendoryTreeView\" was not injected: check your FXML file 'InventoryPage.fxml'.";
        assert this.productsTabPage != null : "fx:id=\"productsTabPage\" was not injected: check your FXML file 'InventoryPage.fxml'.";
        assert this.productsTableView != null : "fx:id=\"productsTableView\" was not injected: check your FXML file 'InventoryPage.fxml'.";
        
        this.inventoryVM = new InventoryViewModel();

    }


}
