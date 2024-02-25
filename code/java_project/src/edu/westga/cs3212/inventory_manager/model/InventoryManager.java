package edu.westga.cs3212.inventory_manager.model;

import java.util.ArrayList;

/**
 * The Interface InventoryManager.
 * 
 * @author Group 1
 * @version Spring 2024
 */
public interface InventoryManager {
	
	/**
	 * Gets the list of items in the inventory.
	 * 
	 * @preconditon none
	 * @postcondition none
	 *
	 * @return the list of items in the inventory
	 */
	ArrayList<Item> getListOfItems();
	
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
	 * Removes the item from inventory
	 * 
	 * @precondition newItem != null && newItem.isBlank() == false
	 * @postcondition this.getListOfItems().size() == this.getListOfItems().size()@prev - 1 
	 *
	 * @param newItem the newItem of the item to be removed
	 * @return true, if successful
	 */
	boolean removeItem(Item newItem);
	
	/**
	 * Edits the specified item.
	 * 
	 * @precondition newItem != null
	 * @postcondition 	this.getItemById.getId().equals(newItem.getId()) &&
	 * 					this.getItemById.getName().equals(newItem.getName()) &&
	 * 					this.getItemById..getQuantity == newItem().getQuantity() &&
	 * 					this.getItemById.getUnitCost == newItem.getUnitCost() &&
	 * 					this.getItemById.getDateLastModified == newItem.getDateLastModified()
	 * 
	 * @param 	id the id of the item to be edited
	 * @param newItem the item with the new information
	 */
	void editItem(String id, Item newItem);
	
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
	Item getItemByID(String itemID);
	
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
