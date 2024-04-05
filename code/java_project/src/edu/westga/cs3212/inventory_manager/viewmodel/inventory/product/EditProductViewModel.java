package edu.westga.cs3212.inventory_manager.viewmodel.inventory.product;

import java.util.ArrayList;
import java.util.Map;

import edu.westga.cs3212.inventory_manager.model.server.warehouse.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.ProductInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Item;
import edu.westga.cs3212.inventory_manager.model.warehouse.Product;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EditProductViewModel {

	private Product product;
	private StringProperty name;
	private StringProperty productionCost;
	private StringProperty sellingPrice;
	private StringProperty quantity;
	private ObjectProperty<Component> selectedComponent;

	/**
	 * Initializes a new instance of the EditProductViewModel. Sets up property
	 * bindings for product attributes and initializes connections to the
	 * product and component inventories.
	 *
	 *@precondition none
	 *@postcondition getName() == "" && getProductionCost() == "" && getSellingPrice() == "" && getQuantity() == ""
	 */
	public EditProductViewModel() {
		this.name = new SimpleStringProperty();
		this.productionCost = new SimpleStringProperty();
		this.sellingPrice = new SimpleStringProperty();
		this.quantity = new SimpleStringProperty();
		this.selectedComponent = new SimpleObjectProperty<Component>();
	}

	/**
	 * Loads the product into the ViewModel for editing, setting the initial
	 * values for the product's properties.
	 *
	 *@precondition none
	 *@postcondition none
	 * @param product
	 *            The product to be edited.
	 */
	public void setProduct(Item product) {
		this.product = (Product) product;
		this.name.setValue(product.getName());
		this.productionCost
				.setValue(String.valueOf(product.getProductionCost()));
		this.sellingPrice.setValue(String.valueOf(this.product.getSalePrice()));
		try {
			this.quantity.setValue(String.valueOf(
					ProductInventory.getQuantity(this.product.getID())));
		} catch (NullPointerException e) {
			this.quantity.setValue("0");
		}
	}

	/**
	 * Retrieves the recipe of the currently selected product.
	 *
	 * @precondition none
	 * @postcondition none
	 * @return A map representing the recipe, where each key is a Component and
	 *         each value is the quantity needed for the product.
	 */
	public Map<Component, Integer> getRecipe() {
		return this.product.getRecipe();
	}

	/**
	 * Gets the name property of the product being edited.
	 *
	 * @precondition none
	 * @postcondition none
	 * @return A StringProperty representing the product's name.
	 */
	public StringProperty getName() {
		return this.name;
	}

	/**
	 * Gets the production cost property of the product being edited.
	 *
	 * @precondition none
	 * @postcondition none
	 * @return A StringProperty representing the product's production cost.
	 */
	public StringProperty getProductionCost() {
		return this.productionCost;
	}

	/**
	 * Gets the selling price property of the product being edited.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return A StringProperty representing the product's selling price.
	 */
	public StringProperty getSellingPrice() {
		return this.sellingPrice;
	}

	/**
	 * Gets the quantity property of the product in inventory.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return A StringProperty representing the quantity of the product in
	 *         inventory.
	 */
	public StringProperty getQuantity() {
		return this.quantity;
	}

	/**
	 * Gets the property for the component selected in the product's recipe.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return An ObjectProperty representing the currently selected component
	 *         in the product's recipe.
	 */
	public ObjectProperty<Component> getSelectedComponentProperty() {
		return this.selectedComponent;
	}

	/**
	 * Updates the product in the inventory with the changes made in the
	 * ViewModel. Applies modifications to the product's name, production cost,
	 * selling price, and recipe.
	 *
	 * @precondition none
	 * @postcondition The product's details are updated in the inventory.
	 * 
	 * @param recipe
	 *            The updated map of components and their quantities for the
	 *            product's recipe.
	 */
	public void updateProduct(Map<Component, Integer> recipe) {
		String productID = this.product.getID();
		String productName = this.name.getValue();
		double salesPirce = Double.parseDouble(this.sellingPrice.getValue());
		int quantity = ProductInventory.getQuantity(productID);
		ProductInventory.updateProduct(productID, productName, salesPirce,
				recipe, quantity);
		this.product = ProductInventory.getProduct(productID);
	}

	/**
	 * Retrieves an observable list of components for selection in the UI.
	 *
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return An ObservableList of Component objects available in the component
	 *         inventory.
	 */
	public ObservableList<Component> getObservableComponentList() {
		ArrayList<Component> items = new ArrayList<Component>();
		for (Component currentComponent : ComponentInventory.getComponents()) {
			items.add(currentComponent);
		}
		return FXCollections.observableArrayList(items);
	}
}
