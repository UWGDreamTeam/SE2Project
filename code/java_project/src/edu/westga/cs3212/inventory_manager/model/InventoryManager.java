package edu.westga.cs3212.inventory_manager.model;

import java.util.List;

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
	 * Adds the newItem to the Inventory
	 * 
	 * @precondition newItem != null
	 * @postcondition this.getListOfItems().size() == this.getListOfItems().size()@prev + 1 
	 *
	 * @param newItem the item to be added
	 * @return true, if successful
	 */
	boolean addNewItem(Item newItem);
	
	/**
	 * Removes the item from invenotry
	 * 
	 * @precondition newItem != null && newItem.isBlank() == false
	 * @postcondition this.getListOfItems().size() == this.getListOfItems().size()@prev - 1 
	 *
	 * @param newItem the newItem of the item to be removed
	 * @return true, if successful
	 */
	boolean removeItem(Item newItem);
	
	/**
	 * Gets the item by an ID.
	 * 
	 * @precondition itemID != null && itemID.isBlank() == false
	 * @postcondition none
	 *
	 * @param itemID the item ID to be searched for in the inventory 
	 * 
	 * @return the item if contained in the inventory, null otherwise
	 */
	Item getItemById(String itemID);
	
	/**
	 * Gets the quantity.
	 *
	 * @precondition none
	 * @postcondition none 
	 * 
	 * @return the quantity  of items in the inventory
	 */
	int getQuantity();
	
	
	/**
	 * Clears the inventory
	 * 
	 * @precondition none
	 * @postcondifiton none
	 */
	void clear();
	
}
