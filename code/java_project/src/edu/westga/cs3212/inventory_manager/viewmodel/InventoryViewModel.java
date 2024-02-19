package edu.westga.cs3212.inventory_manager.viewmodel;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;

/**
 * The Class InventoryViewModel.
 * 
 * @author Group 5
 * @version Spring 2024
 */
public class InventoryViewModel {

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
	}
	
	
	/**
	 * Adds the component.
	 */
	public void addComponent() {
		//TODO
	}
}
