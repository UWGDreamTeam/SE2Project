package edu.westga.cs3212.inventory_manager.view;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentialsManager;
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

    @FXML
    void attemptLogin(ActionEvent event) {
    	String employeeID = this.employeeIDTextField.getText();
    	String password = this.passwordTextField.getText();
    	LocalEmployeeCredentialsManager manager = new LocalEmployeeCredentialsManager();
    	boolean result;
		try {
			result = manager.attemptLogin(employeeID, password);
		} catch (Exception exception) {
			result = false;
		}
		if (result) {
			this.showSuccessPopup(event);
		} else {
			this.showErrorPopup();
		}
	}

	private void showErrorPopup() {
		Alert errorPopup = new Alert(AlertType.ERROR);
		errorPopup.setContentText("Login failed, please check your credentials and try again");
		errorPopup.showAndWait();
	}

	private void showSuccessPopup(ActionEvent event) {
		Alert successPopup = new Alert(AlertType.CONFIRMATION);
		successPopup.setContentText("Login successful");
		successPopup.showAndWait();
		this.closeWindow(event);
	}

	@FXML
	void initialize() {
		assert this.companyTitleLabel != null
				: "fx:id=\"companyTitleLabel\" was not injected: check your FXML file 'LoginPage.fxml'.";
		assert this.employeeIDTextField != null
				: "fx:id=\"employeeIDTextField\" was not injected: check your FXML file 'LoginPage.fxml'.";
		assert this.passwordTextField != null
				: "fx:id=\"passwordTextField\" was not injected: check your FXML file 'LoginPage.fxml'.";
	}
    
    private void closeWindow(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	stage.close();
	}
}
