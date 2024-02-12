package edu.westga.cs3212.inventory_manager.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeTableView;
import javafx.scene.text.Text;

public class InventoryPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TabPane InvendoryTreeView;

    @FXML
    private Text employeeFirstLastName;

    @FXML
    private Text employeeId;

    @FXML
    private Text employeeRole;

    @FXML
    private Tab productsTabPage;

    @FXML
    private TreeTableView<?> productsTableView;

    @FXML
    private Tab rawMaterialTabPage;

    @FXML
    private TreeTableView<?> rawMaterialsTableView;

    @FXML
    void addProdcutManager_OnClick(ActionEvent event) {

    }

    @FXML
    void addRmButtonManager_OnClick(ActionEvent event) {

    }

    @FXML
    void editRmButtonManager_OnClick(ActionEvent event) {

    }

    @FXML
    void homePageButton_OnClick(ActionEvent event) {

    }

    @FXML
    void logOutButton_OnClick(ActionEvent event) {

    }

    @FXML
    void orderRmButtonManager_OnClick(ActionEvent event) {

    }

    @FXML
    void ordersPageButton_OnClick(ActionEvent event) {

    }

    @FXML
    void produceButton_OnClick(ActionEvent event) {

    }

    @FXML
    void removeRmButtonManager_OnClick(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert this.InvendoryTreeView != null : "fx:id=\"InvendoryTreeView\" was not injected: check your FXML file 'InventoryPage.fxml'.";
        assert this.employeeFirstLastName != null : "fx:id=\"employeeFirstLastName\" was not injected: check your FXML file 'InventoryPage.fxml'.";
        assert this.employeeId != null : "fx:id=\"employeeId\" was not injected: check your FXML file 'InventoryPage.fxml'.";
        assert this.employeeRole != null : "fx:id=\"employeeRole\" was not injected: check your FXML file 'InventoryPage.fxml'.";
        assert this.productsTabPage != null : "fx:id=\"productsTabPage\" was not injected: check your FXML file 'InventoryPage.fxml'.";
        assert this.productsTableView != null : "fx:id=\"productsTableView\" was not injected: check your FXML file 'InventoryPage.fxml'.";
        assert this.rawMaterialTabPage != null : "fx:id=\"rawMaterialTabPage\" was not injected: check your FXML file 'InventoryPage.fxml'.";
        assert this.rawMaterialsTableView != null : "fx:id=\"rawMaterialsTableView\" was not injected: check your FXML file 'InventoryPage.fxml'.";

    }

}
