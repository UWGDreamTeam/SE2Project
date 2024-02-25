package edu.westga.cs3212.inventory_manager.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditComponentPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField costTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField numberOfRecipesTextField;

    @FXML
    private TextField quantityTextField;

    @FXML
    void Cancel(ActionEvent event) {

    }

    @FXML
    void UpdateEditingComponent(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert costTextField != null : "fx:id=\"costTextField\" was not injected: check your FXML file 'EditComponentPage.fxml'.";
        assert nameTextField != null : "fx:id=\"nameTextField\" was not injected: check your FXML file 'EditComponentPage.fxml'.";
        assert numberOfRecipesTextField != null : "fx:id=\"numberOfRecipesTextField\" was not injected: check your FXML file 'EditComponentPage.fxml'.";
        assert quantityTextField != null : "fx:id=\"quantityTextField\" was not injected: check your FXML file 'EditComponentPage.fxml'.";

    }

}

