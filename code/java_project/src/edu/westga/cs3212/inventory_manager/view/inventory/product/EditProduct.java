package edu.westga.cs3212.inventory_manager.view.inventory.product;

import java.util.HashMap;
import java.util.Map;

import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Item;
import edu.westga.cs3212.inventory_manager.model.warehouse.Product;
import edu.westga.cs3212.inventory_manager.viewmodel.inventory.product.EditProductViewModel;
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

public class EditProduct {

	private static final String UNABLE_TO_UPDATE_PRODUCT_PLEASE_CHECK_PRODUCT_INFORMATION_AND_TRY_AGAIN = "Invalid Product, Make sure you enter valid information.";

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

	private EditProductViewModel editProductVM;
	private Map<Component, Integer> componentList;

	@FXML
	void editProductOnClick(ActionEvent event) {
		boolean result = true;
		try {
			this.editProductVM.updateProduct(this.componentList);
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error Updating Product");
			alert.setContentText(
					UNABLE_TO_UPDATE_PRODUCT_PLEASE_CHECK_PRODUCT_INFORMATION_AND_TRY_AGAIN);
			alert.showAndWait();
			result = false;
		}
		if (result) {
			this.returnToInventoryPage(event);
			this.showAlert("Product Edited", "The product was successfully edited.", Alert.AlertType.INFORMATION);
		}
	}

	/**
	 * Initializes the UI with data from the specified product for editing. Sets
	 * the currently selected product in the ViewModel and populates a local map
	 * with the product's components, allowing for modifications to the
	 * product's recipe. This method casts the given Item to a Product and
	 * retrieves its recipe for further manipulation.
	 *
	 * @param item
	 *            The product item whose data is to be loaded into the UI for
	 *            editing. It is expected to be an instance of Product.
	 * @precondition item != null && item instanceof Product
	 * @postcondition The UI is prepared for editing the product, with the
	 *                component list populated from the product's recipe.
	 * @throws ClassCastException
	 *             if the item cannot be cast to a Product.
	 */
	public void initalizeWithItem(Item item) {
		this.editProductVM.setProduct(item);
		Product itemProduct = (Product) item;
		this.componentList = new HashMap<Component, Integer>();
		this.componentList.putAll(itemProduct.getRecipe());
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
		this.editProductVM = new EditProductViewModel();
		this.productionCostTextField.textProperty()
				.bindBidirectional(this.editProductVM.getProductionCost());
		this.sellingPriceTextField.textProperty()
				.bindBidirectional(this.editProductVM.getSellingPrice());
		this.quantityTextField.textProperty()
				.bindBidirectional(this.editProductVM.getQuantity());
		this.nameTextField.textProperty()
				.bindBidirectional(this.editProductVM.getName());
		this.componentList = new HashMap<Component, Integer>();
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(
				0, 100, 0);
		this.currentComponentQuantity.setValueFactory(valueFactory);
		this.editProductVM.getSelectedComponentProperty()
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
		this.setupQuantityColumn();

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

	private void setupQuantityColumn() {
		this.quantityColumn.setCellValueFactory(cellData -> {
			try {
				int quantity = this.componentList.get(cellData.getValue());
				return new SimpleIntegerProperty(quantity).asObject();
			} catch (Exception e) {
				return new SimpleIntegerProperty(0).asObject();
			}
		});
	}

	private void refreshComponentTableView() {
		this.componentRecipeTableView
				.setItems(this.editProductVM.getObservableComponentList());
		this.componentRecipeTableView.refresh();
	}
	
	private void showAlert(String title, String content, Alert.AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setContentText(content);
		alert.showAndWait();
	}

}
