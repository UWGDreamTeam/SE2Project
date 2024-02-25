package edu.westga.cs3212.inventory_manager.model.local_impl;

import java.util.ArrayList;
import java.util.List;

import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.InventoryManager;
import edu.westga.cs3212.inventory_manager.model.Item;
import edu.westga.cs3212.inventory_manager.model.ProductInventoryStorage;

public class LocalProductInventory implements InventoryManager {
	
	private static final String ITEM_ID_CANNOT_BE_NULL = "Product ID cannot be null";
	private static final String ITEM_ID_CANNOT_BE_BLANK = "Product ID cannot be blank";
	private static final String NEW_ITEM_CANNOT_BE_NULL = "New Product Cannot be null";
	private static final String NEW_ITEM_ALREADY_EXISTS = "Product Already Exists";
	
	private List<Product> products;
	
	/**
	 * Instantiates a new LocalProductInventory object
	 * 
	 * @precondition none
	 * @postcondition LocalProductInventory.products != null
	 */
	public LocalProductInventory() {
		this.products = new ArrayList<>();
		this.products = ProductInventoryStorage.load(Constants.PRODUCT_INVENTORY_FILE_LOCATION);
	}

	@Override
	public ArrayList<Item> getListOfItems() {
		return new ArrayList<>(this.products);
	}

	@Override
	public boolean addNewItem(Item newItem) {
		if (newItem == null) {
			throw new IllegalArgumentException(NEW_ITEM_CANNOT_BE_NULL);
		}
		
		if (this.products.contains(newItem)) {
			throw new IllegalArgumentException(NEW_ITEM_ALREADY_EXISTS);
		}

		boolean result = this.products.add((Product) newItem);
		ProductInventoryStorage.save(this.products, Constants.PRODUCT_INVENTORY_FILE_LOCATION);
		return result;
	}

	@Override
	public boolean removeItem(Item item) {
		if (item == null) {
			throw new IllegalArgumentException(NEW_ITEM_CANNOT_BE_NULL);
		}
		
		boolean result = this.products.remove(item);
		ProductInventoryStorage.save(this.products, Constants.PRODUCT_INVENTORY_FILE_LOCATION);
		return result;
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
		
		for (Item product : this.products) {
			if (product.getId().equals(itemID)) {
				productFound = product;
			}
		}
		
		return (Product) productFound;
	}

	@Override
	public int getQuantity() {
		return this.products.size();
	}
	
	@Override
	public void clear() {
		this.products = new ArrayList<>();
		ProductInventoryStorage.save(this.products, Constants.PRODUCT_INVENTORY_FILE_LOCATION);
	}

	@Override
	public void editItem(String id, Item newItem) {
		if (!id.equals(newItem.getId())) {
			throw new IllegalArgumentException("Component ID does not match.");
		}
		
		Item componentBeingEdited = this.getItemById(id);
		this.products.remove(componentBeingEdited);
		
		this.addNewItem(newItem);
		ProductInventoryStorage.save(this.products, Constants.PRODUCT_INVENTORY_FILE_LOCATION);
	}

}
