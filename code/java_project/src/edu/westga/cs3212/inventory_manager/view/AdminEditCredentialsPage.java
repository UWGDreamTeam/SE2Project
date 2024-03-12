package edu.westga.cs3212.inventory_manager.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs3212.inventory_manager.model.EmployeeType;
import edu.westga.cs3212.inventory_manager.viewmodel.AdminEditCredentialsPageViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AdminEditCredentialsPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField lastNameTextField1;

    @FXML
    private ComboBox<EmployeeType> roleComboBox;
    
    private AdminEditCredentialsPageViewModel adminEditVM;

    @FXML
    void cancel(ActionEvent event) {
    	
    }

    @FXML
    void save(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	this.adminEditVM = new AdminEditCredentialsPageViewModel();
    }

}
