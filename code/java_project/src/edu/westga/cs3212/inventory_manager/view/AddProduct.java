package edu.westga.cs3212.inventory_manager.view;

import java.util.HashMap;
import java.util.Map;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;
import edu.westga.cs3212.inventory_manager.viewmodel.AddProductViewModel;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddProduct {

	private static final String UNABLE_TO_ADD_PRODUCT_PLEASE_CHECK_PRODUCT_INFORMATION_AND_TRY_AGAIN = "Invalid Product, Make sure you enter valid information.";

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
	private AddProductViewModel addProductVM;
	private Map<Component, Integer> componentList;

	@FXML
	void addProductOnClick(ActionEvent event) {
		boolean result = true;
		try {
			this.addProductVM.addProduct(this.componentList);
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error Adding Product");
			alert.setContentText(
					UNABLE_TO_ADD_PRODUCT_PLEASE_CHECK_PRODUCT_INFORMATION_AND_TRY_AGAIN);
			alert.showAndWait();
			result = false;
		}
		if (result) {
			this.showAlert("Product Added", "The product was successfully added.", Alert.AlertType.INFORMATION);
			this.returnToInventoryPage(event);
		}
	}

	private void returnToInventoryPage(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}

	@FXML
	void cancelAddOnButtonClick(ActionEvent event) {
		this.returnToInventoryPage(event);
	}

	@FXML
	void initialize() {
		this.addProductVM = new AddProductViewModel();
		this.productionCostTextField.textProperty()
				.bindBidirectional(this.addProductVM.getProductionCost());
		this.sellingPriceTextField.textProperty()
				.bindBidirectional(this.addProductVM.getSellingPrice());
		this.quantityTextField.textProperty()
				.bindBidirectional(this.addProductVM.getQuantity());
		this.nameTextField.textProperty()
				.bindBidirectional(this.addProductVM.getName());
		this.componentList = new HashMap<Component, Integer>();
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(
				Constants.MINIMUM_QUANTITY_SPINNER_VALUE,
				Constants.MAXIMUM_QUANTITY_SPINNER_VALUE, 0);
		this.currentComponentQuantity.setValueFactory(valueFactory);
		this.addProductVM.getSelectedComponentProperty()
				.bind(this.componentRecipeTableView.getSelectionModel()
						.selectedItemProperty());
		this.setupComponentTableView();
	}

	private void setupComponentTableView() {
		this.refreshComponentTableView();
		this.componentIDColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(
						cellData.getValue().getID()));
		this.componentName
				.setCellValueFactory(cellData -> new SimpleStringProperty(
						cellData.getValue().getName()));
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
						Integer quantity = this.componentList
								.getOrDefault(newSelection, 0);
						this.currentComponentQuantity.getValueFactory()
								.setValue(quantity);
					}
				});

		this.currentComponentQuantity.valueProperty()
				.addListener((obs, oldValue, newValue) -> {
					Component selectedComponent = this.componentRecipeTableView
							.getSelectionModel().getSelectedItem();
					if (selectedComponent != null && newValue != null) {
						this.componentList.put(selectedComponent, newValue);

						this.componentRecipeTableView.refresh();
					}
				});

	}

	private void refreshComponentTableView() {
		this.componentRecipeTableView
				.setItems(this.addProductVM.getObservableComponentList());
		this.componentRecipeTableView.refresh();
	}
	
	private void showAlert(String title, String content, Alert.AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setContentText(content);
		alert.showAndWait();
	}

}
