package edu.westga.cs3212.inventory_manager.view;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentialsManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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
		} catch (Exception e) {
			result = false;
		}
    }
}
