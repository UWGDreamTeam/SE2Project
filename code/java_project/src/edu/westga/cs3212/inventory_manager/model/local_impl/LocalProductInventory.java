package edu.westga.cs3212.inventory_manager.model.local_impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.ItemInventoryManager;
import edu.westga.cs3212.inventory_manager.model.Item;
import edu.westga.cs3212.inventory_manager.model.Product;
import edu.westga.cs3212.inventory_manager.model.ProductInventoryStorage;

/**
 * Manages the inventory of products locally. Provides functionalities to add,
 * remove, edit, and query products and their quantities in the inventory.
 * 
 * @author Group 1
 * @version Spring 2024
 */
public class LocalProductInventory implements ItemInventoryManager {

	private static final String ITEM_ID_CANNOT_BE_NULL = "Product ID cannot be null";
	private static final String ITEM_ID_CANNOT_BE_BLANK = "Product ID cannot be blank";
	private static final int MINIMUM_QUANTITY = 0;
	private static final String QUANTITY_CANNOT_BE_NEGATIVE = "Quantity cannot be negative";
	private static final String NEW_ITEM_CANNOT_BE_NULL = "New Product Cannot be null";
	private static final String NEW_ITEM_ALREADY_EXISTS = "Product Already Exists";
	private static final String PRODUCT_DOES_NOT_EXIST = "Product does not exist";

	private static Map<Product, Integer> products;

	/**
	 * Initializes a new instance of the LocalProductInventory class. Loads
	 * existing products from storage or initializes the inventory with demo
	 * data if no existing data is found.
	 * 
	 * @precondition none
	 * @postcondition products are loaded from storage or initialized with demo
	 *                data
	 */
	public LocalProductInventory() {
		if (LocalProductInventory.products == null) {
			LocalProductInventory.products = ProductInventoryStorage
					.load(Constants.PRODUCT_INVENTORY_FILE_LOCATION);
			if (LocalProductInventory.products.isEmpty()) {
				LocalComponentInventory componentInventory = new LocalComponentInventory();
				LocalProductInventory.products = DemoDataUtility
						.createDemoProducts(
								componentInventory.getItemsWithQuantities());
				this.save();
			}
		}
	}

	/**
	 * Gets an iterable collection of all products in the inventory.
	 * 
	 * @return An iterable collection of products
	 * @precondition none
	 * @postcondition none
	 */
	public Iterable<Product> getProducts() {
		return new ArrayList<>(LocalProductInventory.products.keySet());
	}

	/**
	 * Retrieves a map of products with their associated quantities in the
	 * inventory.
	 * 
	 * @return A map of products and their quantities
	 * @precondition none
	 * @postcondition none
	 */
	public Map<Product, Integer> getProductsWithQuantities() {
		return new HashMap<>(LocalProductInventory.products);
	}

	@Override
	public Iterable<Item> getItems() {
		return new ArrayList<>(LocalProductInventory.products.keySet());
	}

	@Override
	public boolean removeItem(Item item) {
		if (item == null) {
			throw new IllegalArgumentException(ITEM_ID_CANNOT_BE_NULL);
		}
		if (LocalProductInventory.products.remove(item) != null) {
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
		for (Product product : LocalProductInventory.products.keySet()) {
			if (product.getID().equals(itemID)) {
				return product;
			}
		}
		return null;
	}

	@Override
	public void clear() {
		LocalProductInventory.products.clear();
		ProductInventoryStorage.save(LocalProductInventory.products,
				Constants.PRODUCT_INVENTORY_FILE_LOCATION);
	}

	@Override
	public boolean addItem(Item newItem, int quantity) {
		if (newItem == null) {
			throw new IllegalArgumentException(NEW_ITEM_CANNOT_BE_NULL);
		}
		if (LocalProductInventory.products.containsKey(newItem)) {
			throw new IllegalArgumentException(NEW_ITEM_ALREADY_EXISTS);
		}
		if (quantity < MINIMUM_QUANTITY) {
			throw new IllegalArgumentException(QUANTITY_CANNOT_BE_NEGATIVE);
		}
		if (LocalProductInventory.products.put((Product) newItem,
				quantity) == null) {
			this.save();
			return true;
		}
		return false;
	}

	@Override
	public boolean editItem(Item newItem) {
		this.validItemAndWithinInventory(newItem);
		if (LocalProductInventory.products.put((Product) newItem,
				LocalProductInventory.products.get(newItem)) != null) {
			this.save();
			return true;
		}
		return false;
	}

	@Override
	public int getQuantityOfItem(Item item) {
		return LocalProductInventory.products.get(item);
	}

	@Override
	public void setQuantityOfItem(Item item, int quantity) {
		this.validItemAndWithinInventory(item);
		if (quantity < MINIMUM_QUANTITY) {
			throw new IllegalArgumentException(QUANTITY_CANNOT_BE_NEGATIVE);
		}
		LocalProductInventory.products.put((Product) item, quantity);
	}

	void save() {
		ProductInventoryStorage.save(LocalProductInventory.products,
				Constants.PRODUCT_INVENTORY_FILE_LOCATION);
	}

	private void validItemAndWithinInventory(Item item) {
		if (item == null) {
			throw new IllegalArgumentException(ITEM_ID_CANNOT_BE_NULL);
		}
		if (LocalProductInventory.products.get(item) == null) {
			throw new IllegalArgumentException(PRODUCT_DOES_NOT_EXIST);
		}
	}

	@Override
	public Map<Item, Integer> getItemsWithQuantities() {
		return new HashMap<>(LocalProductInventory.products);
	}

}
