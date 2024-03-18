package edu.westga.cs3212.inventory_manager.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs3212.inventory_manager.model.EmployeeType;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentials;
import edu.westga.cs3212.inventory_manager.viewmodel.AdminEditCredentialsPageViewModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AdminEditCredentialsPage {

	private static final String USER_UPDATED_SUCCESSFULLY = "User Updated Successfully";

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private ComboBox<EmployeeType> roleComboBox;
    
    private AdminEditCredentialsPageViewModel adminEditVM;

    @FXML
	void cancel(ActionEvent event) {
		this.closeWindow(event);
	}

	private void closeWindow(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}

    @FXML
    void save(ActionEvent event) {
    	try {
			this.adminEditVM.editUser();

			Alert successPopup = new Alert(AlertType.INFORMATION);
			successPopup.setContentText(USER_UPDATED_SUCCESSFULLY);
			successPopup.showAndWait();
			this.closeWindow(event);
        } catch (IllegalArgumentException e) {
			Alert errorPopup = new Alert(AlertType.ERROR);
			errorPopup.setContentText(e.getMessage());
			errorPopup.showAndWait();
        }
    }

    @FXML
    void initialize() {
    	this.adminEditVM = new AdminEditCredentialsPageViewModel();
    	
    	this.roleComboBox.setItems(FXCollections.observableArrayList(EmployeeType.values()));
    }

	/**
	 * Initializes the UI with the data from the specified LocalEmployeeCredentials for editing.
	 * Binds the item's properties (firstName, lastName, password and role) to the corresponding input fields in the UI,
	 * allowing for the editing of the item's attributes.
	 *
	 * @param userSelected The user whose data is to be loaded into the UI for editing.
	 * @precondition userSelected != null
	 * @postcondition The UI input fields are bound to the properties of the specified LocalEmployeeCredentials, enabling their edit.
	 */
	public void initializeWithItem(LocalEmployeeCredentials userSelected) {
		this.adminEditVM.setSelectedUser(userSelected);
		
		this.firstNameTextField.textProperty().bindBidirectional(this.adminEditVM.getFirstName());
		this.lastNameTextField.textProperty().bindBidirectional(this.adminEditVM.getLastName());
		this.passwordTextField.textProperty().bindBidirectional(this.adminEditVM.getPassword());
		this.roleComboBox.valueProperty().bindBidirectional(this.adminEditVM.getSelectedEmployeeType());
	}

}
