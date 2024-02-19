package edu.westga.cs3212.inventory_manager.model.local_impl;

import java.util.ArrayList;
import java.util.List;

import edu.westga.cs3212.inventory_manager.model.InventoryManager;
import edu.westga.cs3212.inventory_manager.model.Item;

public class LocalProductInventory implements InventoryManager {
	
	private static final String ITEM_ID_CANNOT_BE_NULL = "Product ID cannot be null";
	private static final String ITEM_ID_CANNOT_BE_BLANK = "Product ID cannot be blank";
	private static final String NEW_ITEM_CANNOT_BE_NULL = "New Product Cannot be null";
	private static final String NEW_ITEM_ALREADY_EXISTS = "Product Already Exists";
	
	private static List<Product> products;
	
	/**
	 * Instantiates a new LocalProductInventory object
	 * 
	 * @precondition none
	 * @postcondition LocalProductInventory.products != null
	 */
	public LocalProductInventory() {
		if (LocalProductInventory.products == null) {
			LocalProductInventory.products = new ArrayList<Product>();
		}
	}

	@Override
	public ArrayList<Item> getListOfItems() {
		return new ArrayList<Item>(LocalProductInventory.products);
	}

	@Override
	public boolean addNewItem(Item newItem) {
		if (newItem == null) {
			throw new IllegalArgumentException(NEW_ITEM_CANNOT_BE_NULL);
		}
		
		if (LocalProductInventory.products.contains(newItem)) {
			throw new IllegalArgumentException(NEW_ITEM_ALREADY_EXISTS);
		}
		
		return LocalProductInventory.products.add((Product) newItem);
	}

	@Override
	public boolean removeItem(Item item) {
		if (item == null) {
			throw new IllegalArgumentException(NEW_ITEM_CANNOT_BE_NULL);
		}
		
		return LocalProductInventory.products.remove(item);
	}
	
	

	@Override
	public Product getItemById(String itemID) {
		if (itemID == null) {
			throw new IllegalArgumentException(ITEM_ID_CANNOT_BE_NULL);
		}
		
		if (itemID.isBlank()) {
			throw new IllegalArgumentException(ITEM_ID_CANNOT_BE_BLANK);
		}
		
		Item productFound = null;
		
		for (Item product : LocalProductInventory.products) {
			if (product.getId().equals(itemID)) {
				productFound = product;
			}
		}
		
		return (Product) productFound;
	}

	@Override
	public int getQuantity() {
		return LocalProductInventory.products.size();
	}
	
	@Override
	public void clear() {
		LocalProductInventory.products = new ArrayList<Product>();
	}

	@Override
	public void editItem(String id, Item newItem) {
		if (!id.equals(newItem.getId())) {
			throw new IllegalArgumentException("Component ID does not match.");
		}
		
		Item componentBeingEdited = this.getItemById(id);
		LocalProductInventory.products.remove(componentBeingEdited);
		
		this.addNewItem(newItem);
		
	}

}
