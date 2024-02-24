package edu.westga.cs3212.inventory_manager.view;

import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.EmployeeType;
import edu.westga.cs3212.inventory_manager.viewmodel.RegisterPageViewModel;
import javafx.event.ActionEvent;
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

    /**
     * Initializes the controller class.
     * This method is automatically called after the FXML file has been loaded. It sets up the initial bindings and configurations needed for the UI components.
     */
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
            if (newVal != null) {
                this.viewModel.employeeTypeProperty().set(newVal.toString());
            } else {
                this.viewModel.employeeTypeProperty().set(null);
            }
        });
    }

    private void setupEmployeeTypeComboBox() {
        this.employeeTypeComboBox.getItems().setAll(EmployeeType.values());
    }

    @FXML
    void registerEmployee(ActionEvent event) {

        String firstName = this.firstNameTextField.getText();
        String lastName = this.lastNameTextField.getText();
        String password = this.passwordTextField.getText();
        String confirmPassword = this.confirmPasswordTextField.getText();
        EmployeeType selectedType = this.employeeTypeComboBox.getValue();

        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty() || password == null || password.isEmpty() || confirmPassword == null || confirmPassword.isEmpty() || selectedType == null) {
            this.showAlert(Alert.AlertType.ERROR, Constants.REGISTRATION_ERROR, Constants.MUST_FILL_OUT_ALL_FIELDS);
            return;
        }

        if (!password.equals(confirmPassword)) {
            this.showAlert(Alert.AlertType.ERROR, Constants.REGISTRATION_ERROR, Constants.PASSWORDS_DO_NOT_MATCH);
            return;
        }

        try {
            this.viewModel.registerEmployee();
            this.showAlert(Alert.AlertType.INFORMATION, Constants.REGISTRATION_SUCCESSFUL, Constants.EMPLOYEE_REGISTRATION_SUCCESSFUL);
        } catch (IllegalArgumentException e) {
            this.showAlert(Alert.AlertType.ERROR, Constants.REGISTRATION_ERROR, e.getMessage());
        }
    }

    @FXML
    void cancelRegistration(ActionEvent action) {
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
