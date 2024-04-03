package edu.westga.cs3212.inventory_manager.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs3212.inventory_manager.Main;
import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentials;
import edu.westga.cs3212.inventory_manager.viewmodel.AdminPageViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
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
    
    @FXML
	private Button editButton;
    
    @FXML
	private Button removeButton;
    
    @FXML
	private Text fullnameLabel;
	
	@FXML
	private Text employeeIdLabel;
	
	@FXML
	private Text workerTypeLabel;

	private static final String COMMA_SEPERATION = ", ";

	@FXML
	private Text employeeRoleLabel;
    
    private AdminPageViewModel adminVM;
    
    private void setupButtonsVisibility() {
		this.refreshUsersTableView();
		
		this.idCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmployeeID()));
		this.firstNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
		this.lastNameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
		this.roleCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmployeeType().toString()));
		
	}
    
	private void setupProductButtons() {
		this.editButton.disableProperty()
				.bind(Bindings.isNull(this.usersTableView.getSelectionModel().selectedItemProperty()));
		this.removeButton.disableProperty()
				.bind(Bindings.isNull(this.usersTableView.getSelectionModel().selectedItemProperty()));
	}
	
	private void setCurrentUserSummary() {
		LocalEmployeeCredentials user = Main.getLoggedInEmployee();
		this.fullnameLabel.setText(user.getLastName() + COMMA_SEPERATION + user.getFirstName());
		this.employeeIdLabel.setText(user.getEmployeeID().toString());
		this.workerTypeLabel.setText(user.getEmployeeType().toString());
	}
	
	@FXML
	void homePageButtonOnClick(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Parent parent = FXMLLoader.load(Main.class.getResource(Main.HOME_PAGE));
		Scene scene = stage.getScene();
		scene.setRoot(parent);
		stage.setTitle(Constants.HOME_PAGE_TITLE);
	}

	@FXML
	void inventoryPageButtonOnClick(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Parent parent = FXMLLoader
				.load(Main.class.getResource(Main.INVENTORY_PAGE));
		Scene scene = stage.getScene();
		scene.setRoot(parent);
		stage.setTitle(Constants.INVENTORY_PAGE_TITLE);
	}
	
	@FXML
	void ordersPageButtonOnClick(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Parent parent = FXMLLoader.load(Main.class.getResource(Main.ORDER_PAGE));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle(Constants.ORDER_PAGE_TITLE);
		stage.show();
	}

	@FXML
	void logOutButtonOnClick(ActionEvent event) {
		try {
			Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Parent parent = FXMLLoader.load(Main.class.getResource(Main.LOGIN_PAGE));
			Scene currentScene = currentStage.getScene();
			currentScene.setRoot(parent);
			currentStage.setTitle(Main.WINDOW_TITLE);
			currentStage.sizeToScene();
		} catch (IOException e) {
			Alert errorPopup = new Alert(AlertType.ERROR);
			errorPopup.setContentText(e.getMessage());
			errorPopup.showAndWait();
		}
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
    void editUser(ActionEvent event) throws IOException {
    	
    	LocalEmployeeCredentials userSelected = this.usersTableView.getSelectionModel().getSelectedItem();
    	
    	FXMLLoader loader = new FXMLLoader(Main.class.getResource(Main.ADMIN_EDIT_CREDENTIALS_PAGE));
		Parent parent = loader.load();

		AdminEditCredentialsPage editCredentialsPage = loader.getController();
		editCredentialsPage.initializeWithItem(userSelected);

		Stage modalStage = new Stage();
		Scene scene = new Scene(parent);
		modalStage.setTitle(Main.WINDOW_TITLE);
		modalStage.setScene(scene);
		modalStage.initModality(Modality.WINDOW_MODAL);
		modalStage.initOwner(((Node) event.getSource()).getScene().getWindow());
		modalStage.showAndWait();
		
		this.refreshUsersTableView();
    }

    @FXML
    void removeUser(ActionEvent event) {
    	String id = this.usersTableView.getSelectionModel().selectedItemProperty().get().getEmployeeID();
    	String firstName = this.usersTableView.getSelectionModel().selectedItemProperty().get().getFirstName();
    	String lastName = this.usersTableView.getSelectionModel().selectedItemProperty().get().getLastName();
    	
    	Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText(
        		"Are you sure you want to remove the selected user?" + System.lineSeparator()
        		+ System.lineSeparator()
        		+ "ID: " + id + System.lineSeparator()
        		+ "First Name: " + firstName + System.lineSeparator()
        		+ "Last Name: " + lastName);
        confirmationDialog.setContentText("This cannot be undone");
        
        confirmationDialog.initModality(Modality.APPLICATION_MODAL);
        confirmationDialog.initOwner(((Node) event.getSource()).getScene().getWindow());

        confirmationDialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                this.adminVM.removeUser();
                this.refreshUsersTableView();
            }
        });
    }

    @FXML
    void initialize() {
    	this.adminVM = new AdminPageViewModel();
    	this.adminVM.getSelectedUser().bind(this.usersTableView.getSelectionModel().selectedItemProperty());
    	this.setupProductButtons();
    	this.setupButtonsVisibility();
    	this.setCurrentUserSummary();
    }
    
	private void refreshUsersTableView() {
		this.usersTableView.setItems(this.adminVM.getObservableUsersList());
		this.usersTableView.refresh();
	}

}
