package edu.westga.cs3212.inventory_manager.viewmodel;

import java.util.ArrayList;
import java.util.Map;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Item;
import edu.westga.cs3212.inventory_manager.model.Product;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AddProductViewModel {

	private ObjectProperty<Component> selectedComponent;
	private LocalComponentInventory componentInventory;
	private StringProperty name;
	private StringProperty productionCost;
	private StringProperty sellingPrice;
	private StringProperty quantity;
	private LocalProductInventory productInventory;

	/**
	 * Constructs an AddProductViewModel with references to the component and
	 * product inventories.
	 *
	 * @param componentInventory
	 *            The inventory of components available for creating a product.
	 * @param productInventory
	 *            The inventory where the new product will be added.
	 */
	public AddProductViewModel(LocalComponentInventory componentInventory,
			LocalProductInventory productInventory) {
		this.componentInventory = componentInventory;
		this.productInventory = productInventory;
		this.name = new SimpleStringProperty();
		this.productionCost = new SimpleStringProperty();
		this.sellingPrice = new SimpleStringProperty();
		this.quantity = new SimpleStringProperty();
		this.selectedComponent = new SimpleObjectProperty<Component>();
	}

	/**
	 * Returns the property for the selected component in the UI.
	 * 
	 * @return The selected component as an observable property.
	 */
	public ObjectProperty<Component> getSelectedComponentProperty() {
		return this.selectedComponent;
	}

	/**
	 * Provides an observable list of components for the UI, derived from the
	 * component inventory.
	 * 
	 * @return An observable list of all components in the inventory.
	 */
	public ObservableList<Component> getObservableComponentList() {
		ArrayList<Component> items = new ArrayList<Component>();
		for (Item item : this.componentInventory.getItems()) {
			items.add((Component) item);
		}
		return FXCollections.observableArrayList(items);
	}

	/**
	 * Returns the property for the name of the product being added.
	 * 
	 * @return The name property of the new product.
	 */
	public StringProperty getName() {
		return this.name;
	}

	/**
	 * Returns the property for the production cost of the product being added.
	 * 
	 * @return The production cost property of the new product.
	 */
	public StringProperty getProductionCost() {
		return this.productionCost;
	}

	/**
	 * Returns the property for the selling price of the product being added.
	 * 
	 * @return The selling price property of the new product.
	 */
	public StringProperty getSellingPrice() {
		return this.sellingPrice;
	}

	/**
	 * Returns the property for the quantity of the product being added.
	 * 
	 * @return The quantity property of the new product.
	 */
	public StringProperty getQuantity() {
		return this.quantity;
	}

	/**
	 * Adds a new product to the product inventory using the provided recipe and
	 * the data bound to this ViewModel.
	 * 
	 * @param recipe
	 *            A map of components and their quantities required for the new
	 *            product.
	 * @throws NumberFormatException
	 *             If the production cost, selling price, or quantity cannot be
	 *             parsed to their respective numeric types.
	 */
	public void addProduct(Map<Component, Integer> recipe) {
		String productName = this.name.getValue();
		Double productionCost = Double
				.parseDouble(this.productionCost.getValue().trim());
		Double sellingPrice = Double
				.parseDouble(this.sellingPrice.getValue().trim());
		int quantity = Integer.parseInt(this.quantity.getValue().trim());

		Product newProduct = new Product(productName, productionCost,
				sellingPrice, recipe);

		this.productInventory.addItem(newProduct, quantity);
	}

}
