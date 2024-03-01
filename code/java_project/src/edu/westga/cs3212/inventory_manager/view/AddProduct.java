package edu.westga.cs3212.inventory_manager.view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import edu.westga.cs3212.inventory_manager.Main;
import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;
import edu.westga.cs3212.inventory_manager.viewmodel.AddProductViewModel;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddProduct {

	@FXML
	private TableColumn<Component, String> componentIDColumn;

	@FXML
	private TableColumn<Component, String> componentName;

	@FXML
	private TableView<Component> componentRecipeTableView;

	@FXML
	private Spinner<Integer> currentComponentQuantity;

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField productionCostTextField;

	@FXML
	private TableColumn<Component, Integer> quantityColumn;

	@FXML
	private TextField quantityTextField;

	@FXML
	private TextField sellingPriceTextField;

	private LocalComponentInventory componentInventory;
	private LocalProductInventory productInventory;
	private AddProductViewModel addProductVM;
	private Map<Component, Integer> componentList;

	@FXML
	void addProductOnClick(ActionEvent event) {
		try {
			this.addProductVM.addProduct(this.componentList);
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error Adding Product");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
		this.returnToInventoryPage(event);
	}

	private void returnToInventoryPage(ActionEvent event) {
		try {
			Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Parent parent = FXMLLoader.load(Main.class.getResource(Main.INVENTORY_PAGE));

			// Create a new scene with the parent and get its preferred size
			Scene scene = new Scene(parent);
			double preferredWidth = parent.prefWidth(-1);
			double preferredHeight = parent.prefHeight(preferredWidth);

			// Set the stage's width and height to fit the content
			currentStage.setWidth(preferredWidth);
			currentStage.setHeight(preferredHeight);

			// Set the scene and title of the stage
			currentStage.setScene(scene);
			currentStage.setTitle("Inventory Page");
		} catch (IOException e) {
			// Handle IOException
			Alert errorPopup = new Alert(Alert.AlertType.ERROR);
			errorPopup.setContentText(e.getMessage());
			errorPopup.showAndWait();
		}
	}

	@FXML
	void cancelAddOnButtonClick(ActionEvent event) {
		this.returnToInventoryPage(event);
	}

	@FXML
	void initialize() {
		this.componentInventory = new LocalComponentInventory();
		this.productInventory = new LocalProductInventory();
		this.addProductVM = new AddProductViewModel(this.componentInventory, this.productInventory);
		this.productionCostTextField.textProperty().bindBidirectional(this.addProductVM.getProductionCost());
		this.sellingPriceTextField.textProperty().bindBidirectional(this.addProductVM.getSellingPrice());
		this.quantityTextField.textProperty().bindBidirectional(this.addProductVM.getQuantity());
		this.nameTextField.textProperty().bindBidirectional(this.addProductVM.getName());
		this.componentList = new HashMap<Component, Integer>();
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
		this.currentComponentQuantity.setValueFactory(valueFactory);
		this.addProductVM.getSelectedComponentProperty()
				.bind(this.componentRecipeTableView.getSelectionModel().selectedItemProperty());
		this.setupComponentTableView();
	}

	private void setupComponentTableView() {
		this.refreshComponentTableView();
		this.componentIDColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getID()));
		this.componentName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		this.quantityColumn.setCellValueFactory(cellData -> {
			try {
				int quantity = this.componentList.get(cellData.getValue());
				return new SimpleIntegerProperty(quantity).asObject();
			} catch (Exception e) {
				return new SimpleIntegerProperty(0).asObject();
			}
		});

		this.componentRecipeTableView.getSelectionModel().selectedItemProperty()
				.addListener((obs, oldSelection, newSelection) -> {
					if (newSelection != null) {
						Integer quantity = this.componentList.getOrDefault(newSelection, 0); // Default to 1 if not
																								// found
						// This line assumes your Spinner is configured with a SpinnerValueFactory that
						// allows setting its value
						this.currentComponentQuantity.getValueFactory().setValue(quantity);
					}
				});

		// Update map when spinner value changes
		this.currentComponentQuantity.valueProperty().addListener((obs, oldValue, newValue) -> {
			// Get the selected component
			Component selectedComponent = this.componentRecipeTableView.getSelectionModel().getSelectedItem();
			if (selectedComponent != null && newValue != null) {
				// Update the quantity in the componentList map
				this.componentList.put(selectedComponent, newValue);

				// Refresh the table view to reflect the updated quantity
				this.componentRecipeTableView.refresh();
			}
		});

	}

	private void refreshComponentTableView() {
		this.componentRecipeTableView.setItems(this.addProductVM.getObservableComponentList());
		this.componentRecipeTableView.refresh();
	}

}
