package edu.westga.cs3212.inventory_manager.view;

import edu.westga.cs3212.inventory_manager.model.local_impl.EmployeeType;
import edu.westga.cs3212.inventory_manager.viewmodel.RegisterPageViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class RegisterPage {

    private RegisterPageViewModel viewModel = new RegisterPageViewModel();

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField confirmPasswordTextField;

    @FXML
    private ComboBox<EmployeeType> employeeTypeComboBox;

    @FXML
    public void initialize() {
        this.bindToViewModel();
        this.setupEmployeeTypeComboBox();
    }

    private void bindToViewModel() {
        this.firstNameTextField.textProperty().bindBidirectional(this.viewModel.firstNameProperty());
        this.lastNameTextField.textProperty().bindBidirectional(this.viewModel.lastNameProperty());
        this.passwordTextField.textProperty().bindBidirectional(this.viewModel.passwordProperty());
        this.confirmPasswordTextField.textProperty().bindBidirectional(this.viewModel.confirmPasswordProperty());
        
        this.employeeTypeComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            this.viewModel.employeeTypeProperty().set(newVal.toString());
        });
    }

    private void setupEmployeeTypeComboBox() {
        this.employeeTypeComboBox.getItems().setAll(EmployeeType.values());
    }

    @FXML
    void registerEmployee() {

        String firstName = this.firstNameTextField.getText();
        String lastName = this.lastNameTextField.getText();
        String password = this.passwordTextField.getText();
        String confirmPassword = this.confirmPasswordTextField.getText();
        EmployeeType selectedType = this.employeeTypeComboBox.getValue();

        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty() || password == null || password.isEmpty() || confirmPassword == null || confirmPassword.isEmpty() || selectedType == null) {
            this.showAlert(Alert.AlertType.ERROR, "Registration Error", "All fields must be filled out.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            this.showAlert(Alert.AlertType.ERROR, "Registration Error", "Passwords do not match.");
            return;
        }

        try {
            this.viewModel.registerEmployee();
            this.showAlert(Alert.AlertType.INFORMATION, "Registration Successful", "The employee has been successfully registered.");
        } catch (IllegalArgumentException e) {
            this.showAlert(Alert.AlertType.ERROR, "Registration Error", e.getMessage());
        }
    }

    @FXML
    void cancelRegistration() {
        this.firstNameTextField.clear();
        this.lastNameTextField.clear();
        this.passwordTextField.clear();
        this.confirmPasswordTextField.clear();
        this.employeeTypeComboBox.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
