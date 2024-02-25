package edu.westga.cs3212.inventory_manager.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditProductPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField numberOfIngredientsTextField;

    @FXML
    private TextField productionCostTextField;

    @FXML
    private TextField quantityTextField;

    @FXML
    private TextField sellingPriceTextField;

    @FXML
    void Cancel(ActionEvent event) {

    }

    @FXML
    void UpdateEditingProduct(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert nameTextField != null : "fx:id=\"nameTextField\" was not injected: check your FXML file 'EditProductPage.fxml'.";
        assert numberOfIngredientsTextField != null : "fx:id=\"numberOfIngredientsTextField\" was not injected: check your FXML file 'EditProductPage.fxml'.";
        assert productionCostTextField != null : "fx:id=\"productionCostTextField\" was not injected: check your FXML file 'EditProductPage.fxml'.";
        assert quantityTextField != null : "fx:id=\"quantityTextField\" was not injected: check your FXML file 'EditProductPage.fxml'.";
        assert sellingPriceTextField != null : "fx:id=\"sellingPriceTextField\" was not injected: check your FXML file 'EditProductPage.fxml'.";

    }

}

