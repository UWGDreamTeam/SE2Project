package edu.westga.cs3212.inventory_manager.view.login;

import java.io.IOException;

import edu.westga.cs3212.inventory_manager.Main;
import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.viewmodel.login.LoginViewModel;
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

public class Login {

	@FXML
	private Text companyTitleLabel;

	@FXML
	private TextField employeeIDTextField;

	@FXML
	private PasswordField passwordTextField;

	private LoginViewModel viewModel;

	@FXML
	void attemptLogin(ActionEvent event) {
	    try {
	        if (this.viewModel.attemptLogin()) {
	            this.showHomePage(event);
	        } else {
	            this.showErrorPopup();
	        }
	    } catch (Exception ex) {
	        this.showErrorPopup();
	    }
	}

	@FXML
	void initialize() {
		this.viewModel = new LoginViewModel();
		this.employeeIDTextField.textProperty()
				.bindBidirectional(this.viewModel.employeeIDProperty());
		this.passwordTextField.textProperty()
				.bindBidirectional(this.viewModel.passwordProperty());
	}

	private void showErrorPopup() {
		Alert errorPopup = new Alert(AlertType.ERROR);
		errorPopup.setContentText(Constants.LOGIN_ERROR_MESSAGE);
		errorPopup.showAndWait();
	}

	void showHomePage(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Parent parent = FXMLLoader.load(Main.class.getResource(Main.HOME_PAGE));
		Scene scene = new Scene(parent);
		scene.getStylesheets().add(Main.class.getResource(Constants.STYLESHEET_PATH).toExternalForm());
		stage.setScene(scene);
		stage.setTitle(Constants.HOME_PAGE_TITLE);
		stage.show();
	}
}
