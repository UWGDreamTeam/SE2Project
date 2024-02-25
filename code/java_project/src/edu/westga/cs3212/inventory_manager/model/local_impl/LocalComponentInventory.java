package edu.westga.cs3212.inventory_manager.model.local_impl;

import java.util.ArrayList;
import java.util.List;

import edu.westga.cs3212.inventory_manager.model.ComponentInventoryStorage;
import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.InventoryManager;
import edu.westga.cs3212.inventory_manager.model.Item;

public class LocalComponentInventory implements InventoryManager {
	
	private static final String ITEM_ID_CANNOT_BE_NULL = "Component ID cannot be null";
	private static final String ITEM_ID_CANNOT_BE_BLANK = "Component ID cannot be blank";
	private static final String NEW_ITEM_CANNOT_BE_NULL = "New Component Cannot be null";
	private static final String NEW_ITEM_ALREADY_EXISTS = "Component Already Exists";
	private static final String COMPONENT_ID_DOES_NOT_MATCH = "Component ID does not match";
	
	private List<Component> components;
	
	/**
	 * Instantiates a new LocalComponentInventory object
	 * 
	 * @precondition none
	 * @postcondition LocalComponentsInventory.components != null
	 */
	public LocalComponentInventory() {
		this.components = new ArrayList<>();
		this.components = ComponentInventoryStorage.load(Constants.COMPONENT_INVENTORY_FILE_LOCATION);
	}

	@Override
	public ArrayList<Item> getListOfItems() {
		return new ArrayList<>(this.components);
	}

	@Override
	public boolean addNewItem(Item newItem) {
		if (newItem == null) {
			throw new IllegalArgumentException(NEW_ITEM_CANNOT_BE_NULL);
		}
		
		if (this.components.contains(newItem)) {
			throw new IllegalArgumentException(NEW_ITEM_ALREADY_EXISTS);
		}
		
		boolean result = this.components.add((Component) newItem);
		ComponentInventoryStorage.save(this.components, Constants.COMPONENT_INVENTORY_FILE_LOCATION);
		return result;
	}

	@Override
	public boolean removeItem(Item item) {
		if (item == null) {
			throw new IllegalArgumentException(NEW_ITEM_CANNOT_BE_NULL);
		}
		
		boolean result = this.components.remove(item);
		ComponentInventoryStorage.save(this.components, Constants.COMPONENT_INVENTORY_FILE_LOCATION);
		return result;
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
		
		for (Item product : this.components) {
			if (product.getId().equals(itemID)) {
				productFound = product;
			}
		}
		
		return productFound;
	}

	@Override
	public int getQuantity() {
		return this.components.size();
	}

	@Override
	public void clear() {
		this.components = new ArrayList<>();
		ComponentInventoryStorage.save(this.components, Constants.COMPONENT_INVENTORY_FILE_LOCATION);
	}

	@Override
	public void editItem(String id, Item newItem) {
		if (!id.equals(newItem.getId())) {
			throw new IllegalArgumentException(COMPONENT_ID_DOES_NOT_MATCH);
		}
		
		Item componentBeingEdited = this.getItemById(id);
		this.components.remove(componentBeingEdited);
		
		this.addNewItem(newItem);
		ComponentInventoryStorage.save(this.components, Constants.COMPONENT_INVENTORY_FILE_LOCATION);
	}

}
