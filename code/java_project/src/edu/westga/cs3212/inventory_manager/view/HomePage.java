package edu.westga.cs3212.inventory_manager.view;

import java.io.IOException;

import edu.westga.cs3212.inventory_manager.Main;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
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

	private HomePageViewModel viewModel;

	@FXML
	void initialize() {
		this.bindToViewModel();
		this.viewModel.getComponentSummary();
		this.viewModel.getProductSummary();
		this.viewModel.getOrderSummary();

	}

	private void bindToViewModel() {
		this.viewModel = new HomePageViewModel();
		this.componentSummaryTextArea.textProperty()
				.bind(this.viewModel.getComponentSumarryTextArea());
		this.productSummaryTextArea.textProperty()
				.bind(this.viewModel.getProductSumarryTextArea());
		this.orderSummaryTextArea.textProperty()
				.bind(this.viewModel.getOrderSumarryTextArea());
	}

	@FXML
	void inventoryPageButtonOnClick(MouseEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Parent parent = FXMLLoader
				.load(Main.class.getResource(Main.INVENTORY_PAGE));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Home Page");
		stage.show();
	}

	@FXML
	void ordersPageButtonOnClick(MouseEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Parent parent = FXMLLoader
				.load(Main.class.getResource(Main.ORDER_PAGE));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Home Page");
		stage.show();
	}

    @FXML
    void openAdminPage(ActionEvent event) throws IOException {
    	Stage modalStage = new Stage();
		Parent parent = FXMLLoader.load(Main.class.getResource(Main.ADMIN_PAGE));
		Scene scene = new Scene(parent);

		modalStage.setTitle(Main.WINDOW_TITLE);
		modalStage.setScene(scene);
		modalStage.initModality(Modality.WINDOW_MODAL);
		modalStage.initOwner(((Node) event.getSource()).getScene().getWindow());
		modalStage.showAndWait();
    }

	@FXML
	void logOutButton(ActionEvent event) {
		try {
			Stage currentStage = (Stage) ((Node) event.getSource()).getScene()
					.getWindow();
			Parent parent = FXMLLoader
					.load(Main.class.getResource(Main.LOGIN_PAGE));
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
