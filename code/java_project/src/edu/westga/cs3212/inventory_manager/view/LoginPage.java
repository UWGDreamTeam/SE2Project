package edu.westga.cs3212.inventory_manager.view;
import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.viewmodel.LoginPageViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginPage {

	@FXML
    private Text companyTitleLabel;

    @FXML
    private TextField employeeIDTextField;

    @FXML
    private TextField passwordTextField;
    
    private LoginPageViewModel viewModel;

    @FXML
    void attemptLogin(ActionEvent event) {
		if (this.viewModel.attemptLogin()) {
			this.showSuccessPopup(event);
		} else {
			this.showErrorPopup();
		}
	}

    @FXML
    void initialize() {
    	this.viewModel = new LoginPageViewModel();
    	this.employeeIDTextField.textProperty().bindBidirectional(this.viewModel.employeeIDProperty());
    	this.passwordTextField.textProperty().bindBidirectional(this.viewModel.passwordProperty());
    }
    
	private void showErrorPopup() {
		Alert errorPopup = new Alert(AlertType.ERROR);
		errorPopup.setContentText(Constants.LOGIN_ERROR_MESSAGE);
		errorPopup.showAndWait();
	}

	private void showSuccessPopup(ActionEvent event) {
		Alert successPopup = new Alert(AlertType.CONFIRMATION);
		successPopup.setContentText(Constants.LOGIN_SUCCESS_MESSAGE);
		successPopup.showAndWait();
		this.closeWindow(event);
	}
    
    private void closeWindow(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	stage.close();
	}
}
