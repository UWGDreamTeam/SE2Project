package edu.westga.cs3212.inventory_manager.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs3212.inventory_manager.model.Item;
import edu.westga.cs3212.inventory_manager.viewmodel.EditComponentViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditComponentPage {

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
		if (this.editComponentVM.update()) {
			this.closeWindow(event);
		}
	}
}
