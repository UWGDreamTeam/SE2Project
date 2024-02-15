package edu.westga.cs3212.inventory_manager.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class RegisterPage {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField confirmPasswordTextField;

	@FXML
	private TextField employeeIdTextField;

	@FXML
	private ComboBox<?> employeeTypeComboBox;

	@FXML
	private TextField firstNameTextField;

	@FXML
	private TextField lastNameTextField;

	@FXML
	private TextField passwordTextField;

	@FXML
	void cancelRegistration(ActionEvent event) {

	}

	@FXML
	void registerEmployee(ActionEvent event) {

	}

	@FXML
	void selectEmployeeType(ActionEvent event) {

	}

	@FXML
	void initialize() {
		assert confirmPasswordTextField != null : "fx:id=\"confirmPasswordTextField\" was not injected: check your FXML file 'RegisterPage.fxml'.";
		assert employeeIdTextField != null : "fx:id=\"employeeIdTextField\" was not injected: check your FXML file 'RegisterPage.fxml'.";
		assert employeeTypeComboBox != null : "fx:id=\"employeeTypeComboBox\" was not injected: check your FXML file 'RegisterPage.fxml'.";
		assert firstNameTextField != null : "fx:id=\"firstNameTextField\" was not injected: check your FXML file 'RegisterPage.fxml'.";
		assert lastNameTextField != null : "fx:id=\"lastNameTextField\" was not injected: check your FXML file 'RegisterPage.fxml'.";
		assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'RegisterPage.fxml'.";

	}

}


