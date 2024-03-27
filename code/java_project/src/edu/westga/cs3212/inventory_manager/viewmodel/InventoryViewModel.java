package edu.westga.cs3212.inventory_manager.viewmodel;

import java.util.ArrayList;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Item;
import edu.westga.cs3212.inventory_manager.model.Product;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;
import edu.westga.cs3212.inventory_manager.model.server_impl.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server_impl.ProductInventory;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The Class InventoryViewModel.
 * 
 * @author Group 5
 * @version Spring 2024
 */
public class InventoryViewModel {

	private ObjectProperty<Item> selectedComponent;
	private ObjectProperty<Item> selectedProduct;

	/**
	 * Constructs an InventoryViewModel with specified inventories for
	 * components and products. Initializes properties for tracking selected
	 * items in the UI.
	 *
	 * @param componentsInventory
	 *            The inventory of components to be managed.
	 * @param productInventory
	 *            The inventory of products to be managed.
	 */
	public InventoryViewModel() {

		this.selectedComponent = new SimpleObjectProperty<Item>();
		this.selectedProduct = new SimpleObjectProperty<Item>();
	}

	/**
	 * Creates an observable list of components from the component inventory.
	 * This list can be used for binding to UI elements, enabling dynamic
	 * updates.
	 *
	 * @return An ObservableList containing all components in the component
	 *         inventory.
	 */
	public ObservableList<Component> getObservableComponentList() {
		ArrayList<Component> items = new ArrayList<Component>();
		for (Component currentComponent : ComponentInventory.getComponents()) {
			items.add(currentComponent);
		}
		return FXCollections.observableArrayList(items);
	}

	/**
	 * Removes the currently selected component from the component inventory.
	 * This operation is typically triggered by a user action in the UI.
	 *
	 * @precondition A component must be selected (selectedComponent property is
	 *               not null).
	 * @postcondition The selected component is removed from the inventory if it
	 *                exists.
	 */
	public void removeComponent() {
		ComponentInventory.deleteComponent(this.selectedComponent.get().getID());
	}

	/**
	 * Retrieves the property that tracks the currently selected component in
	 * the UI. This property allows for binding and listening for changes in
	 * selection.
	 *
	 * @return The property containing the currently selected component.
	 */
	public ObjectProperty<Item> getSelectedComponent() {
		return this.selectedComponent;
	}

	/**
	 * Sets the selected component.
	 * 
	 * @precondition none
	 * @postcondition The selected component is set to the provided value.
	 * 
	 * @param selectedComponent
	 *            The selected component.
	 */
	public void setSelectedComponent(Item selectedComponent) {
		this.selectedComponent.set(selectedComponent);
	}

	/**
	 * Retrieves an observable list of all products in the inventory. This list
	 * is used for displaying products in the UI.
	 *
	 * @return An ObservableList of Product objects.
	 * @precondition none
	 * @postcondition none
	 */
	public ObservableList<Product> getObservableProductList() {
		ArrayList<Product> items = new ArrayList<Product>();
		for (Product currentProduct : ProductInventory.getProducts()) {
			items.add(currentProduct);
		}
		return FXCollections.observableArrayList(items);
	}

	/**
	 * Retrieves the property for the currently selected product in the UI. This
	 * property can be used to bind to UI elements or observe for changes.
	 *
	 * @return An ObjectProperty wrapping the currently selected Product item.
	 * @precondition none
	 * @postcondition none
	 */
	public ObjectProperty<Item> getSelectedProduct() {
		return this.selectedProduct;
	}

	/**
	 * Sets the selected component.
	 * 
	 * @precondition none
	 * @postcondition The selected component is set to the provided value.
	 * @param selectedProduct
	 *            The selected component.
	 */
	public void setSelectedProduct(Item selectedProduct) {
		this.selectedProduct.set(selectedProduct);
	}

	/**
	 * Adjusts the inventory quantity for a specified component. This method is
	 * typically used when a component is ordered, incrementing its quantity in
	 * the inventory.
	 *
	 * @param selectedComponent2
	 *            The component for which the quantity is to be adjusted.
	 * @param quantity
	 *            The amount by which to adjust the component's quantity.
	 * @precondition selectedComponent2 != null && quantity >= 0
	 * @postcondition The specified component's quantity is incremented by the
	 *                provided amount.
	 */
	public void orderComponent(Component selectedComponent, int quantity) {
		try {
			ComponentInventory.orderComponent(selectedComponent.getID(), quantity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Removes the currently selected product from the inventory. This method is
	 * invoked typically in response to a user action in the UI.
	 *
	 * @precondition The selectedProduct property must not be null.
	 * @postcondition The selected product is removed from the product
	 *                inventory.
	 */
	public void removeProduct() {
		ProductInventory.deleteProduct(this.selectedProduct.get().getID());
	}

	/**
	 * Manages the production of a specified product, adjusting inventory
	 * quantities as necessary. This includes decrementing the quantities of the
	 * components used in the product's recipe.
	 *
	 * @param selectedProduct
	 *            The product to be produced.
	 * @param quantity
	 *            The quantity of the product to produce.
	 * @precondition selectedProduct != null && quantity > 0
	 * @postcondition The selected product's quantity is increased, and the used
	 *                components' quantities are decreased accordingly.
	 */
	public void produceProduct(Product selectedProduct, int quantity) {
		ProductInventory.produceProduct(selectedProduct.getID(), quantity);
	}
}