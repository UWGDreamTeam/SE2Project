package edu.westga.cs3212.inventory_manager.viewmodel;

import java.util.ArrayList;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Item;
import edu.westga.cs3212.inventory_manager.model.Product;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;
import javafx.beans.property.DoublePropertyBase;
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
	
	private LocalComponentInventory componentsInventory;
	private LocalProductInventory productInventory;
	
	
	/**
	 * Instantiates a new inventory view model.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public InventoryViewModel(LocalComponentInventory componentsInventory, LocalProductInventory productInventory) {
		this.componentsInventory = componentsInventory;
		this.productInventory = productInventory;
		
		this.selectedComponent = new SimpleObjectProperty<Item>();
		this.selectedProduct = new SimpleObjectProperty<Item>();
	}
	
	/**
	 * Gets the observable component list.
	 * 
	 * @precondition none
	 * @postconditione none
	 *
	 * @return the observable component list
	 */
	public ObservableList<Component> getObservableComponentList() {
		ArrayList<Component> items = new ArrayList<Component>();
		for (Item item : this.componentsInventory.getItems()) {
			items.add((Component)item);
		}
		return FXCollections.observableArrayList(items);
	}
	
	/**
	 * Removes the component.
	 * 
	 * @precondition none
	 * @postconditione none
	 * 
	 */
	public void removeComponent() {
		this.componentsInventory.removeItem(this.selectedComponent.getValue());
	}

	/**
	 * Gets the selected component.
	 * 
	 * @precondition none
	 * @postconditione none
	 *
	 * @return the selected component
	 */
	public ObjectProperty<Item> getSelectedComponent() {
		return this.selectedComponent;
	}

	/**
	 * Gets the components inventory.
	 * 
	 * @precondition none
	 * @postconditione none
	 *
	 * @return the components inventory
	 */
	public LocalComponentInventory getComponentsInventory() {
		return this.componentsInventory;
	}

	/**
	 * Gets the product inventory.
	 * 
	 * @precondition none
	 * @postconditione none
	 *
	 * @return the product inventory
	 */
	public LocalProductInventory getProductInventory() {
		return this.productInventory;
	}

	public ObservableList<Product> getObservableProductList() {
		ArrayList<Product> items = new ArrayList<Product>();
        for (Item item : this.productInventory.getItems()) {
            items.add((Product)item);
        }
        return FXCollections.observableArrayList(items);
	}

	public ObjectProperty<Item> getSelectedProduct() {
		return this.selectedProduct;
	}

	public void orderComponent(Component selectedComponent2, int quantity) {
		this.componentsInventory.setQuantityOfItem(selectedComponent2, quantity + this.componentsInventory.getQuantityOfItem(selectedComponent2));
	}
	
	public void removeProduct() {
		this.productInventory.removeItem(this.selectedProduct.getValue());
	}
	
	public void produceProduct(Product selectedProduct, int quantity) {
		this.productInventory.setQuantityOfItem(selectedProduct,
				quantity + this.productInventory.getQuantityOfItem(selectedProduct));
		
		for (Component component : selectedProduct.getRecipe().keySet()) {
			this.componentsInventory.setQuantityOfItem(component, this.componentsInventory.getQuantityOfItem(component)
					- selectedProduct.getRecipe().get(component) * quantity);
		}
	}
}