package edu.westga.cs3212.inventory_manager.model.local_impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.ComponentInventoryStorage;
import edu.westga.cs3212.inventory_manager.model.ComponentQuantityInventoryStorage;
import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.InventoryManager;
import edu.westga.cs3212.inventory_manager.model.Item;

public class LocalComponentInventory implements InventoryManager {
	
	private static final String ITEM_ID_CANNOT_BE_NULL = "Component ID cannot be null";
	private static final String QUANTITY_CANNOT_BE_NEGATIVE = "Quantity cannot be negative";
	private static final int MINIMUM_QUANTITY = 0;
	private static final String ITEM_ID_CANNOT_BE_BLANK = "Component ID cannot be blank";
	private static final String NEW_ITEM_CANNOT_BE_NULL = "New Component Cannot be null";
	private static final String NEW_ITEM_ALREADY_EXISTS = "Component Already Exists";
	private static final String COMPONENT_ID_DOES_NOT_MATCH = "Component ID does not match";
	private static final String COMPONENT_DOES_NOT_EXIST = "Component does not exist";
	
	private List<Component> components;
	/**
	 * The component quantities.
	 * @key string ID, the ID of the component
	 * @value int quantity, the quantity of that component  
	 */
	private Map<String, Integer> componentQuantities; 
	
	/**
	 * Instantiates a new LocalComponentInventory object
	 * 
	 * @precondition none
	 * @postcondition LocalComponentsInventory.components != null
	 */
	public LocalComponentInventory() {
		if (this.components == null) {
			this.components = ComponentInventoryStorage.load(Constants.COMPONENT_INVENTORY_FILE_LOCATION);
			this.componentQuantities = ComponentQuantityInventoryStorage.load(Constants.COMPONENT_QUANTITIES_FILE_LOCATION);
		}
	}

	private void save() {
		ComponentInventoryStorage.save(this.components, Constants.COMPONENT_INVENTORY_FILE_LOCATION);
		ComponentQuantityInventoryStorage.save(this.componentQuantities, Constants.COMPONENT_QUANTITIES_FILE_LOCATION);
	}
	
	@Override
	public ArrayList<Item> getItems() {
		return this.components != null ? new ArrayList<>(this.components) : new ArrayList<>();
	}

	@Override
	public boolean addItem(Item newItem, int quantity) {
		if (newItem == null) {
			throw new IllegalArgumentException(NEW_ITEM_CANNOT_BE_NULL);
		}
		
		if (this.getItemByID(newItem.getId()) != null) {
			throw new IllegalArgumentException(NEW_ITEM_ALREADY_EXISTS);
		}
		
		boolean result = this.components.add((Component) newItem);
		if (result) {
			this.componentQuantities.put(newItem.getId(), quantity);
		}
		this.save();
		return result;
	}

	@Override
	public boolean removeItem(Item item) {
		if (item == null) {
			throw new IllegalArgumentException(NEW_ITEM_CANNOT_BE_NULL);
		}
		
		boolean result = this.components.remove(item);
		if (result) {
			this.componentQuantities.remove(item.getId());
		}
		this.save();
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
		
		Item componentFound = null;
		
		for (Item component : this.components) {
			if (component.getId().equals(itemID)) {
				componentFound = component;
			}
		}
		
		return componentFound;
	}

	@Override
	public void clear() {
		this.components = new ArrayList<>();
		this.componentQuantities = new HashMap<>();
		this.save();
	}

	@Override
	public boolean editItem(Item newItem) {
		this.validItemAndWithinInventory(newItem);
		String currentItemID = newItem.getId();
		Item componentBeingEdited = this.getItemByID(currentItemID);
		
		boolean result = this.components.remove(componentBeingEdited);
		if (result) {
			this.components.add((Component) newItem);
		}
		
		this.save();
		return result;
	}

	@Override
	public int getQuantityOfItem(Item item) {
		this.validItemAndWithinInventory(item);
		return this.componentQuantities.get(item.getId());
	}

	@Override
	public int setQuantityOfItem(Item item, int quantity) {
		this.validItemAndWithinInventory(item);
		if (quantity < MINIMUM_QUANTITY) {
			throw new IllegalArgumentException(QUANTITY_CANNOT_BE_NEGATIVE);
		}
		this.componentQuantities.put(item.getId(), quantity);
		this.save();
		return this.componentQuantities.get(item.getId());
	}

	private void validItemAndWithinInventory(Item item) {
		if (item == null) {
			throw new IllegalArgumentException(ITEM_ID_CANNOT_BE_NULL);
		}
		if (this.getItemByID(item.getId()) == null) {
			throw new IllegalArgumentException(COMPONENT_DOES_NOT_EXIST);
		}
	}

}
