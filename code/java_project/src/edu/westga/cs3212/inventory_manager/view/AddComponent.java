package edu.westga.cs3212.inventory_manager.view;

import edu.westga.cs3212.inventory_manager.viewmodel.AddComponentViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddComponent {

    private static final String UNABLE_TO_ADD_COMPONENT_PLEASE_CHECK_COMPONENT_INFORMATION_AND_TRY_AGAIN = "Unable to add component, please check component information and try again";
    private static final String COMPONENT_ADDED_SUCCESSFULLY = "Component Added Successfully";

    @FXML
    private TextField cost;

    @FXML
    private TextField name;

    @FXML
    private TextField quantity;

    private AddComponentViewModel addComponentVM;

    @FXML
    void initialize() {
	this.addComponentVM = new AddComponentViewModel();

	this.cost.textProperty().bindBidirectional(this.addComponentVM.getCost());
	this.name.textProperty().bindBidirectional(this.addComponentVM.getName());
	this.quantity.textProperty().bindBidirectional(this.addComponentVM.getQuantity());
    }

    @FXML
    void add(ActionEvent event) {

	try {
	    boolean result = this.addComponentVM.add();
	    if (result) {
		Alert successPopup = new Alert(AlertType.INFORMATION);
		successPopup.setContentText(COMPONENT_ADDED_SUCCESSFULLY);
		successPopup.showAndWait();
		this.closeWindow(event);
	    } else {
		Alert errorPopup = new Alert(AlertType.ERROR);
		errorPopup.setContentText(UNABLE_TO_ADD_COMPONENT_PLEASE_CHECK_COMPONENT_INFORMATION_AND_TRY_AGAIN);
		errorPopup.showAndWait();
	    }
	} catch (IllegalArgumentException e) {
	    Alert errorPopup = new Alert(AlertType.ERROR);
	    errorPopup.setContentText(UNABLE_TO_ADD_COMPONENT_PLEASE_CHECK_COMPONENT_INFORMATION_AND_TRY_AGAIN);
	    errorPopup.showAndWait();
	}
    }

    @FXML
    void cancel(ActionEvent event) {
	this.closeWindow(event);
    }

    private void closeWindow(ActionEvent event) {
	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	stage.close();
    }
}
