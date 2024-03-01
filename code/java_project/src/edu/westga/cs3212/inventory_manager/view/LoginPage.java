package edu.westga.cs3212.inventory_manager.view;

import java.io.IOException;

import edu.westga.cs3212.inventory_manager.Main;
import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.viewmodel.LoginViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginPage {

	@FXML
	private Text companyTitleLabel;

	@FXML
	private TextField employeeIDTextField;

	@FXML
	private PasswordField passwordTextField;

	private LoginViewModel viewModel;

	@FXML
	void attemptLogin(ActionEvent event) throws IOException {
		if (this.viewModel.attemptLogin()) {
			this.showSuccessPopup(event);
		} else {
			this.showErrorPopup();
		}
	}

	@FXML
	void initialize() {
		this.viewModel = new LoginViewModel();
		this.employeeIDTextField.textProperty().bindBidirectional(this.viewModel.employeeIDProperty());
		this.passwordTextField.textProperty().bindBidirectional(this.viewModel.passwordProperty());
	}

	private void showErrorPopup() {
		Alert errorPopup = new Alert(AlertType.ERROR);
		errorPopup.setContentText(Constants.LOGIN_ERROR_MESSAGE);
		errorPopup.showAndWait();
	}

	private void showSuccessPopup(ActionEvent event) throws IOException {
		Alert successPopup = new Alert(AlertType.CONFIRMATION);
		successPopup.setContentText(Constants.LOGIN_SUCCESS_MESSAGE);
		successPopup.showAndWait();
		this.closeWindow(event);
		this.showHomePage(event);
	}

	private void closeWindow(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}

	void showHomePage(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Parent parent = FXMLLoader.load(Main.class.getResource(Main.HOME_PAGE));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Home Page");
		stage.show();
	}
}
