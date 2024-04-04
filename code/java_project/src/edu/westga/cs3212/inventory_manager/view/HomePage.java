package edu.westga.cs3212.inventory_manager.view;

import java.io.IOException;

import edu.westga.cs3212.inventory_manager.Main;
import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalEmployeeCredentials;
import edu.westga.cs3212.inventory_manager.viewmodel.HomePageViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class HomePage {

	@FXML
	private Button adminButton;

	@FXML
	private TextArea componentSummaryTextArea;

	@FXML
	private Tab componentTab;

	@FXML
	private Button inventoryButton;

	@FXML
	private Button logOutButton;

	@FXML
	private TextArea orderSummaryTextArea;

	@FXML
	private Tab orderTab;

	@FXML
	private Button ordersButton;

	@FXML
	private TextArea productSummaryTextArea;

	@FXML
	private Tab productTab;

	@FXML
	private Text fullNameLabel;
	
	@FXML
	private Text employeeIdLabel;
	
	@FXML
	private Text workerTypeLabel;
	
	private HomePageViewModel viewModel;

	@FXML
	void initialize() {
		this.bindToViewModel();
		this.viewModel.getComponentSummary();
		this.viewModel.getProductSummary();
		this.viewModel.getOrderSummary();
		this.setPermissions();
		Main.createSummary(fullNameLabel, employeeIdLabel, workerTypeLabel);
	}

	private void bindToViewModel() {
		this.viewModel = new HomePageViewModel();
		this.componentSummaryTextArea.textProperty().bind(this.viewModel.getComponentSumarryTextArea());
		this.productSummaryTextArea.textProperty().bind(this.viewModel.getProductSumarryTextArea());
		this.orderSummaryTextArea.textProperty().bind(this.viewModel.getOrderSumarryTextArea());
	}

	private void setPermissions() {
		if (!this.viewModel.isManager()) {
			this.adminButton.setDisable(true);
		}
	}

	@FXML
	void inventoryPageButtonOnClick(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Parent parent = FXMLLoader.load(Main.class.getResource(Main.INVENTORY_PAGE));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle(Constants.INVENTORY_PAGE_TITLE);
		stage.show();
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
	void adminPageButtonOnClick(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Parent parent = FXMLLoader.load(Main.class.getResource(Main.ADMIN_PAGE));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle(Constants.ADMIN_PAGE_TITLE);
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
}
