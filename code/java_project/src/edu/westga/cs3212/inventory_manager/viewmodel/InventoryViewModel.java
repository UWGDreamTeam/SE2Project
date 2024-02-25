package edu.westga.cs3212.inventory_manager.viewmodel;

import edu.westga.cs3212.inventory_manager.model.Item;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;
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
	private LocalComponentInventory componentsInventory;
	private LocalProductInventory productInventory;
	
	
	/**
	 * Instantiates a new inventory view model.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public InventoryViewModel() {
		this.componentsInventory = new LocalComponentInventory();
		this.productInventory = new LocalProductInventory();
		
		this.selectedComponent = new SimpleObjectProperty<Item>();
		
	}
	
	/**
	 * Gets the observable component list.
	 * 
	 * @precondition none
	 * @postconditione none
	 *
	 * @return the observable component list
	 */
	public ObservableList<Item> getObservableComponentList() {
		return FXCollections.observableArrayList(this.componentsInventory.getListOfItems());
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
	
	
}
