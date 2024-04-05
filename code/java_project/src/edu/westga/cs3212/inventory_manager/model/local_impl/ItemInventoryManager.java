package edu.westga.cs3212.inventory_manager.model.local_impl;

import java.util.Map;

import edu.westga.cs3212.inventory_manager.model.warehouse.Item;

/**
 * The Interface InventoryManager.
 * 
 * @author Group 1
 * @version Spring 2024
 */
public interface ItemInventoryManager {

    /**
     * Gets the list of items in the inventory.
     * 
     * @preconditon none
     * @postcondition none
     *
     * @return the list of items in the inventory
     */
    Iterable<Item> getItems();

    /**
     * Gets the items with their quantities.
     *
     * @return the map of items with their quantities
     */
    Map<Item, Integer> getItemsWithQuantities();

    /**
     * Adds the newItem to the Inventory
     * 
     * @precondition newItem != null
     * @postcondition this.getListOfItems().size() ==
     *                this.getListOfItems().size()@prev + 1
     *
     * @param newItem  the item to be added
     * @param quantity quantity of items to add
     * @return true, if successful
     */
    boolean addItem(Item newItem, int quantity);

    /**
     * Removes the item from inventory
     * 
     * @precondition newItem != null && newItem.isBlank() == false
     * @postcondition this.getListOfItems().size() ==
     *                this.getListOfItems().size()@prev - 1
     *
     * @param newItem the newItem of the item to be removed
     * @return true, if successful
     */
    boolean removeItem(Item newItem);

    /**
     * Edits the specified item.
     * 
     * @precondition newItem != null
     * @postcondition this.getItemById.getId().equals(newItem.getId()) &&
     *                this.getItemById.getName().equals(newItem.getName()) &&
     *                this.getItemById..getQuantity == newItem().getQuantity() &&
     *                this.getItemById.getUnitCost == newItem.getUnitCost() &&
     *                this.getItemById.getDateLastModified ==
     *                newItem.getDateLastModified()
     * 
     * @param newItem the item with the new information
     * 
     * @return true if Item was edited successfully
     */
    boolean editItem(Item newItem);

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
     * @param item gets the quantity of the specified item
     *
     * @precondition none
     * @postcondition none
     * 
     * @return the quantity of items in the inventory
     */
    int getQuantityOfItem(Item item);

    /**
     * Sets the quantity of item.
     *
     * @param item     the item to set the quantity
     * @param quantity the quantity of the item
     */
    void setQuantityOfItem(Item item, int quantity);

    /**
     * Clears the inventory
     * 
     * @precondition none
     * @postcondifiton none
     */
    void clear();

}
