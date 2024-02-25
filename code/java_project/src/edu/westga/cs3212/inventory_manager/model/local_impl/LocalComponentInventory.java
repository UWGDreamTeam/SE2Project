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
	private static final String COMPONENT_DOES_NOT_EXIST = "Component does not exist";
	
	private static List<Component> components;
	
	/**
	 * Instantiates a new LocalComponentInventory object
	 * 
	 * @precondition none
	 * @postcondition LocalComponentsInventory.components != null
	 */
	public LocalComponentInventory() {
		if (LocalComponentInventory.components == null) {
			LocalComponentInventory.components = new ArrayList<>();
			LocalComponentInventory.components = ComponentInventoryStorage.load(Constants.COMPONENT_INVENTORY_FILE_LOCATION);
		}
	}

	@Override
	public ArrayList<Item> getListOfItems() {
		return new ArrayList<>(LocalComponentInventory.components);
	}

	@Override
	public boolean addNewItem(Item newItem) {
		if (newItem == null) {
			throw new IllegalArgumentException(NEW_ITEM_CANNOT_BE_NULL);
		}
		
		if (LocalComponentInventory.components.contains(newItem)) {
			throw new IllegalArgumentException(NEW_ITEM_ALREADY_EXISTS);
		}
		
		boolean result = LocalComponentInventory.components.add((Component) newItem);
		ComponentInventoryStorage.save(LocalComponentInventory.components, Constants.COMPONENT_INVENTORY_FILE_LOCATION);
		return result;
	}

	@Override
	public boolean removeItem(Item item) {
		if (item == null) {
			throw new IllegalArgumentException(NEW_ITEM_CANNOT_BE_NULL);
		}
		
		boolean result = LocalComponentInventory.components.remove(item);
		ComponentInventoryStorage.save(LocalComponentInventory.components, Constants.COMPONENT_INVENTORY_FILE_LOCATION);
		return result;
	}

	@Override
	public Item getItemByID(String itemID) {
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
		
		return productFound;
	}

	@Override
	public int getQuantity() {
		return LocalComponentInventory.components.size();
	}

	@Override
	public void clear() {
		LocalComponentInventory.components = new ArrayList<>();
		ComponentInventoryStorage.save(LocalComponentInventory.components, Constants.COMPONENT_INVENTORY_FILE_LOCATION);
	}

	@Override
	public void editItem(String id, Item newItem) {
		if (id == null) {
			throw new IllegalArgumentException(ITEM_ID_CANNOT_BE_NULL);
		}
		if (id.isBlank()) {
			throw new IllegalArgumentException(ITEM_ID_CANNOT_BE_BLANK);
		}
		if (newItem == null) {
			throw new IllegalArgumentException(NEW_ITEM_CANNOT_BE_NULL);
		}
		if (!id.equals(newItem.getId())) {
			throw new IllegalArgumentException(COMPONENT_ID_DOES_NOT_MATCH);
		}
		if (!LocalComponentInventory.components.contains(this.getItemByID(id))) {
			throw new IllegalArgumentException(COMPONENT_DOES_NOT_EXIST);
		}
		Item componentBeingEdited = this.getItemByID(id);
		LocalComponentInventory.components.remove(componentBeingEdited);
		
		this.addNewItem(newItem);
		ComponentInventoryStorage.save(LocalComponentInventory.components, Constants.COMPONENT_INVENTORY_FILE_LOCATION);
	}

}
