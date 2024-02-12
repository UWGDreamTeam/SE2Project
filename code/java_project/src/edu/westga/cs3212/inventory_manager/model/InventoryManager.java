package edu.westga.cs3212.inventory_manager.model;

import java.util.List;

import edu.westga.cs3212.inventory_manager.model.local_impl.Product;

/**
 * The Interface InventoryManager.
 * 
 * @author Group 1
 * @version Spring 2024
 */
public interface InventoryManager {
	
	/**
	 * Gets the list of items.
	 *
	 * @return the list of items
	 */
	List<Item> getListOfItems();
	
	/**
	 * Adds the Item to the Inventory
	 * 
	 * @precondition item != null
	 * @postcondition this.getListOfItems().size() == this.getListOfItems().size()@prev + 1 
	 *
	 * @param item the item to be added
	 * @return true, if successful
	 */
	boolean addNewItemToInventory(Item item);
	
	/**
	 * Removes the item from invenotry
	 * 
	 * @precondition id != null && id.isBlank() == false
	 * @postcondition this.getListOfItems().size() == this.getListOfItems().size()@prev - 1 
	 *
	 * @param id the id of the item to be removed
	 * @return true, if successful
	 */
	boolean removeItemFromInventory(String id);
	
	/**
	 * Gets the item by id.
	 * 
	 * @precondition id != null && id.isBlank() == false
	 * @postcondition none
	 *
	 * @param id the id
	 * @return the item by id
	 */
	Product getItemById(String id);
	
	/**
	 * Gets the quantity.
	 *
	 * @precondition id != null && id.isBlank() == false
	 * @postcondition none 
	 * 
	 * @param id the id
	 * @return the quantity
	 */
	int getQuantity(String id);
	
}
