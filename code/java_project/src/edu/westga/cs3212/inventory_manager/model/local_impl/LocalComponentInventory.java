package edu.westga.cs3212.inventory_manager.model.local_impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.storage.ComponentInventoryStorage;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Item;
import edu.westga.cs3212.inventory_manager.model.warehouse.ItemInventoryManager;

public class LocalComponentInventory implements ItemInventoryManager {

	private static final String ITEM_ID_CANNOT_BE_NULL = "Component ID cannot be null";
	private static final String QUANTITY_CANNOT_BE_NEGATIVE = "Quantity cannot be negative";
	private static final int MINIMUM_QUANTITY = 0;
	private static final String ITEM_ID_CANNOT_BE_BLANK = "Component ID cannot be blank";
	private static final String NEW_ITEM_CANNOT_BE_NULL = "New Component Cannot be null";
	private static final String NEW_ITEM_ALREADY_EXISTS = "Component Already Exists";
	private static final String COMPONENT_DOES_NOT_EXIST = "Component does not exist";
	/**
	 * The component quantities.
	 * 
	 * @key string ID, the ID of the component
	 * @value int quantity, the quantity of that component
	 */
	private static Map<Component, Integer> components;

	/**
	 * Instantiates a new LocalComponentInventory object
	 * 
	 * @precondition none
	 * @postcondition LocalComponentsInventory.components != null
	 */
	public LocalComponentInventory() {
		if (LocalComponentInventory.components == null) {
			LocalComponentInventory.components = DemoDataUtility
					.createDemoComponents();
		}
	}

	private void save() {
		ComponentInventoryStorage.save(LocalComponentInventory.components,
				Constants.COMPONENT_INVENTORY_FILE_LOCATION);
	}

	@Override
	public Iterable<Item> getItems() {
		return new ArrayList<>(LocalComponentInventory.components.keySet());
	}

	@Override
	public boolean addItem(Item newItem, int quantity) {
		if (newItem == null) {
			throw new IllegalArgumentException(NEW_ITEM_CANNOT_BE_NULL);
		}
		if (LocalComponentInventory.components.containsKey(newItem)) {
			throw new IllegalArgumentException(NEW_ITEM_ALREADY_EXISTS);
		}
		if (quantity < MINIMUM_QUANTITY) {
			throw new IllegalArgumentException(QUANTITY_CANNOT_BE_NEGATIVE);
		}
		if (LocalComponentInventory.components.put((Component) newItem,
				quantity) == null) {
			this.save();
			return true;
		}
		return false;
	}

	@Override
	public boolean removeItem(Item item) {
		if (item == null) {
			throw new IllegalArgumentException(NEW_ITEM_CANNOT_BE_NULL);
		}
		if (LocalComponentInventory.components.remove(item) != null) {
			this.save();
			return true;
		}
		return false;
	}

	@Override
	public Item getItemByID(String itemID) {
		if (itemID == null) {
			throw new IllegalArgumentException(ITEM_ID_CANNOT_BE_NULL);
		}

		if (itemID.isBlank()) {
			throw new IllegalArgumentException(ITEM_ID_CANNOT_BE_BLANK);
		}
		for (Component component : LocalComponentInventory.components
				.keySet()) {
			if (component.getID().equals(itemID)) {
				return component;
			}
		}
		return null;
	}

	@Override
	public void clear() {
		LocalComponentInventory.components = new HashMap<>();
		this.save();
	}

	@Override
	public boolean editItem(Item newItem) {
		this.validItemAndWithinInventory(newItem);
		if (LocalComponentInventory.components.put((Component) newItem,
				LocalComponentInventory.components.get(newItem)) != null) {
			this.save();
			return true;
		}
		return false;
	}

	@Override
	public int getQuantityOfItem(Item item) {
		this.validItemAndWithinInventory(item);
		return LocalComponentInventory.components.get(item);
	}

	@Override
	public void setQuantityOfItem(Item item, int quantity) {
		this.validItemAndWithinInventory(item);
		if (quantity < MINIMUM_QUANTITY) {
			throw new IllegalArgumentException(QUANTITY_CANNOT_BE_NEGATIVE);
		}
		LocalComponentInventory.components.put((Component) item, quantity);
	}

	private void validItemAndWithinInventory(Item item) {
		if (item == null) {
			throw new IllegalArgumentException(ITEM_ID_CANNOT_BE_NULL);
		}
		if (LocalComponentInventory.components.get(item) == null) {
			throw new IllegalArgumentException(COMPONENT_DOES_NOT_EXIST);
		}
	}

	@Override
	public Map<Item, Integer> getItemsWithQuantities() {
		return new HashMap<>(LocalComponentInventory.components);
	}

}
