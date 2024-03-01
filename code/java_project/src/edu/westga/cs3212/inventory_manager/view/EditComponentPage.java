package edu.westga.cs3212.inventory_manager.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs3212.inventory_manager.model.Item;
import edu.westga.cs3212.inventory_manager.viewmodel.EditComponentViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class EditComponentPage {

	private static final String UNABLE_TO_UPDATE_COMPONENT_PLEASE_CHECK_COMPONENT_INFORMATION_AND_TRY_AGAIN = "Unable to update component, please check component information and try again";

	private static final String COMPONENT_UPDATED_SUCCESSFULLY = "Component Updated Successfully";

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField costTextField;

	@FXML
	private TextField quantityTextField;

	private EditComponentViewModel editComponentVM;

	@FXML
	void initialize() {
		this.editComponentVM = new EditComponentViewModel();
	}

	/**
	 * Initializes the UI with the data from the specified item for editing.
	 * Binds the item's properties (name, cost, and quantity) to the corresponding input fields in the UI,
	 * allowing for the editing of the item's attributes.
	 *
	 * @param item The item whose data is to be loaded into the UI for editing.
	 * @precondition item != null
	 * @postcondition The UI input fields are bound to the properties of the specified item, enabling their edit.
	 */
	public void initializeWithItem(Item item) {
		this.editComponentVM.setSelectedComponent(item);
		this.nameTextField.textProperty().bindBidirectional(this.editComponentVM.getName());
		this.costTextField.textProperty().bindBidirectional(this.editComponentVM.getCost());
		this.quantityTextField.textProperty().bindBidirectional(this.editComponentVM.getQuantity());
	}

	@FXML
	void cancel(ActionEvent event) {
		this.closeWindow(event);
	}

	private void closeWindow(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}

	@FXML
	void updateEditingComponent(ActionEvent event) {
		try {
            boolean result = this.editComponentVM.update();
            if (result) {
				Alert successPopup = new Alert(AlertType.INFORMATION);
				successPopup.setContentText(COMPONENT_UPDATED_SUCCESSFULLY);
				successPopup.showAndWait();
				this.closeWindow(event);
			} else {
				Alert errorPopup = new Alert(AlertType.ERROR);
				errorPopup.setContentText(UNABLE_TO_UPDATE_COMPONENT_PLEASE_CHECK_COMPONENT_INFORMATION_AND_TRY_AGAIN);
				errorPopup.showAndWait();
			}
        } catch (IllegalArgumentException e) {
			Alert errorPopup = new Alert(AlertType.ERROR);
			errorPopup.setContentText(UNABLE_TO_UPDATE_COMPONENT_PLEASE_CHECK_COMPONENT_INFORMATION_AND_TRY_AGAIN);
			errorPopup.showAndWait();
        }
	}
}
