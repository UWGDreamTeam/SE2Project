package edu.westga.cs3212.inventory_manager.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs3212.inventory_manager.Main;
import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentials;
import edu.westga.cs3212.inventory_manager.viewmodel.AdminPageViewModel;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdminPage {
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<LocalEmployeeCredentials, String> idCol;
    
    @FXML
    private TableColumn<LocalEmployeeCredentials, String> firstNameCol;


    @FXML
    private TableColumn<LocalEmployeeCredentials, String> lastNameCol;

    @FXML
    private TableColumn<LocalEmployeeCredentials, String> roleCol;
    
    @FXML
    private TableView<LocalEmployeeCredentials> usersTableView;
    
    private AdminPageViewModel adminVM;
    
    private void setupUsersTableView() {
		this.refreshUsersTableView();
		
		this.idCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmployeeID()));
		this.firstNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
		this.lastNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
		this.roleCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmployeeType().toString()));
	}

    @FXML
    void addUser(ActionEvent event) throws IOException {
    	Stage modalStage = new Stage();
		Parent parent = FXMLLoader.load(Main.class.getResource(Main.REGISTER_PAGE));
		Scene scene = new Scene(parent);

		modalStage.setTitle(Main.WINDOW_TITLE);
		modalStage.setScene(scene);
		modalStage.initModality(Modality.WINDOW_MODAL);
		modalStage.initOwner(((Node) event.getSource()).getScene().getWindow());
		modalStage.showAndWait();
		
		this.refreshUsersTableView();
    }

    @FXML
    void editUser(ActionEvent event) {
    	
    }

    @FXML
    void removeUser(ActionEvent event) {
    	this.adminVM.removeUser();
    }

    @FXML
    void initialize() {
    	
    	this.adminVM = new AdminPageViewModel();
    	this.adminVM.getSelectedUser().bind(this.usersTableView.getSelectionModel().selectedItemProperty());
    	this.setupUsersTableView();
    }
    
    

	private void refreshUsersTableView() {
		this.usersTableView.setItems(this.adminVM.getObservableUsersList());
		this.usersTableView.refresh();
	}

}
