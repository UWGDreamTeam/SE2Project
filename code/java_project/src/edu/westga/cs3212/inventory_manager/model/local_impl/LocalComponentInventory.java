package edu.westga.cs3212.inventory_manager.model.local_impl;

import java.util.ArrayList;
import java.util.List;

import edu.westga.cs3212.inventory_manager.model.InventoryManager;
import edu.westga.cs3212.inventory_manager.model.Item;

public class LocalComponentInventory implements InventoryManager {
	
	private static final String ITEM_ID_CANNOT_BE_NULL = "Component ID cannot be null";
	private static final String ITEM_ID_CANNOT_BE_BLANK = "Component ID cannot be blank";
	private static final String NEW_ITEM_CANNOT_BE_NULL = "New Component Cannot be null";
	private static final String NEW_ITEM_ALREADY_EXISTS = "Component Already Exists";
	
	private static List<Component> components;
	
	/**
	 * Instantiates a new LocalComponentInventory object
	 * 
	 * @precondition none
	 * @postcondition LocalComponentsInventory.components != null
	 */
	public LocalComponentInventory() {
		if (LocalComponentInventory.components == null) {
			LocalComponentInventory.components = new ArrayList<Component>();
		}
	}

	@Override
	public ArrayList<Item> getListOfItems() {
		return new ArrayList<Item>(LocalComponentInventory.components);
	}

	@Override
	public boolean addNewItem(Item newItem) {
		if (newItem == null) {
			throw new IllegalArgumentException(NEW_ITEM_CANNOT_BE_NULL);
		}
		
		if (LocalComponentInventory.components.contains(newItem)) {
			throw new IllegalArgumentException(NEW_ITEM_ALREADY_EXISTS);
		}
		
		return LocalComponentInventory.components.add((Component) newItem);
	}

	@Override
	public boolean removeItem(Item item) {
		if (item == null) {
			throw new IllegalArgumentException(NEW_ITEM_CANNOT_BE_NULL);
		}
		
		return LocalComponentInventory.components.remove(item);
	}

	@Override
	public Item getItemById(String itemID) {
		if (itemID == null) {
			throw new IllegalArgumentException(ITEM_ID_CANNOT_BE_NULL);
		}
		
		if (itemID.isBlank()) {
			throw new IllegalArgumentException(ITEM_ID_CANNOT_BE_BLANK);
		}
		
		Item productFound = null;
		
		for (Item product : LocalComponentInventory.components) {
			if (product.getId().equals(itemID)) {
				productFound = product;
			}
		}
		
		return (Component) productFound;
	}

	@Override
	public int getQuantity() {
		return LocalComponentInventory.components.size();
	}

	@Override
	public void clear() {
		LocalComponentInventory.components = new ArrayList<Component>();
	}

	@Override
	public void editItem(String id, Item newItem) {
		if (!id.equals(newItem.getId())) {
			throw new IllegalArgumentException("Component ID does not match.");
		}
		
		Item componentBeingEdited = this.getItemById(id);
		LocalComponentInventory.components.remove(componentBeingEdited);
		
		this.addNewItem(newItem);
	}

}
