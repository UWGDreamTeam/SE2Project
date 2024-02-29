package edu.westga.cs3212.inventory_manager.model.local_impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.ItemInventoryManager;
import edu.westga.cs3212.inventory_manager.model.Item;
import edu.westga.cs3212.inventory_manager.model.Product;
import edu.westga.cs3212.inventory_manager.model.ProductInventoryStorage;

public class LocalProductInventory implements ItemInventoryManager {
	
	private static final String ITEM_ID_CANNOT_BE_NULL = "Product ID cannot be null";
	private static final String ITEM_ID_CANNOT_BE_BLANK = "Product ID cannot be blank";
	private static final int MINIMUM_QUANTITY = 0;
	private static final String QUANTITY_CANNOT_BE_NEGATIVE = "Quantity cannot be negative";
	private static final String NEW_ITEM_CANNOT_BE_NULL = "New Product Cannot be null";
	private static final String NEW_ITEM_ALREADY_EXISTS = "Product Already Exists";
    private static final String PRODUCT_DOES_NOT_EXIST = "Product does not exist";
	
	private Map<Product, Integer> products;
	
	/**
	 * Instantiates a new LocalProductInventory object
	 * 
	 * @precondition none
	 * @postcondition LocalProductInventory.products != null
	 */
	public LocalProductInventory() {
		if (this.products == null) {
			this.products = ProductInventoryStorage.load(Constants.PRODUCT_INVENTORY_FILE_LOCATION);
		}
	}
	
	public Iterable<Product> getProducts() {
		return new ArrayList<>(this.products.keySet());
	}

	@Override
	public Iterable<Item> getItems() {
		return new ArrayList<>(this.products.keySet());
	}

	@Override
	public boolean removeItem(Item item) {
		if (item == null) {
			throw new IllegalArgumentException(ITEM_ID_CANNOT_BE_NULL);
		}
		if (this.products.remove(item) != null) {
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
		for (Product product : this.products.keySet()) {
			if (product.getId().equals(itemID)) {
				return product;
			}
		}
		return null;
	}
	
	@Override
	public void clear() {
		this.products.clear();
		ProductInventoryStorage.save(this.products, Constants.PRODUCT_INVENTORY_FILE_LOCATION);
	}

	@Override
	public boolean addItem(Item newItem, int quantity) {
		if (newItem == null) {
			throw new IllegalArgumentException(NEW_ITEM_CANNOT_BE_NULL);
		}
		if (this.products.containsKey(newItem)) {
			throw new IllegalArgumentException(NEW_ITEM_ALREADY_EXISTS);
		}
		if (quantity < MINIMUM_QUANTITY) {
			throw new IllegalArgumentException(QUANTITY_CANNOT_BE_NEGATIVE);
		}
		if (this.products.put((Product) newItem, quantity) != null) {
			this.save();
			return true;
		}
		return false;
	}

	@Override
	public boolean editItem(Item newItem) {
		this.validItemAndWithinInventory(newItem);
		if (this.products.put((Product) newItem, this.products.get(newItem)) != null) {
			this.save();
			return true;
		}
		return false;
	}

	@Override
	public int getQuantityOfItem(Item item) {
		return this.products.get(item);
	}

	@Override
	public void setQuantityOfItem(Item item, int quantity) {
		this.validItemAndWithinInventory(item);
		if (quantity < MINIMUM_QUANTITY) {
			throw new IllegalArgumentException(QUANTITY_CANNOT_BE_NEGATIVE);
		}
		this.products.put((Product) item, quantity);
	}
	
	void save() {
		ProductInventoryStorage.save(this.products, Constants.PRODUCT_INVENTORY_FILE_LOCATION);
	}
	
	private void validItemAndWithinInventory(Item item) {
		if (item == null) {
			throw new IllegalArgumentException(ITEM_ID_CANNOT_BE_NULL);
		}
		if (this.products.get(item) == null) {
			throw new IllegalArgumentException(PRODUCT_DOES_NOT_EXIST);
		}
	}

	@Override
	public Map<Item, Integer> getItemsWithQuantities() {
		return new HashMap<>(this.products);
	}

}
