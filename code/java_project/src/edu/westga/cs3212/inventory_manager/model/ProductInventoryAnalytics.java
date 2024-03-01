package edu.westga.cs3212.inventory_manager.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalOrderManager;

/**
 * Provides analytics on product usage within orders in the inventory management system.
 * This class aggregates data on how often products are used in orders and identifies the most frequently used products.
 *
 * @author Group 1
 * @version Spring 2024
 */
public class ProductInventoryAnalytics {

	private LocalOrderManager orderManager;
	private static final int MINIMUM_LIST_SIZE = 0;

	/**
     * Initializes a new instance of ProductInventoryAnalytics with its own LocalOrderManager.
     * 
     * @precondition none
     * @postcondition a new LocalOrderManager instance is associated with this instance
     */
	public ProductInventoryAnalytics() {
		this.orderManager = new LocalOrderManager();
	}
	
	/**
     * Retrieves a map of the most frequently used products in orders, sorted by their usage frequency.
     * The size of the returned map is limited to the specified listSize.
     * 
     * @param listSize The maximum number of products to include in the result.
     * @return A map where each key is a Product and each value is the frequency of that product's usage across all orders. The map is sorted in descending order by frequency.
     * @precondition listSize >= 0
     * @postcondition none
     * @throws IllegalArgumentException if listSize is negative
     */
	public Map<Product, Integer> getMostUsedProducts(int listSize) {
		if (listSize < MINIMUM_LIST_SIZE) {
			throw new IllegalArgumentException("List size cannot be negative");
		}
		Iterable<Order> orders = this.orderManager.getOrders();
		Map<Product, Integer> productCount = new HashMap<>();

		for (Order order : orders) {
			this.updateProductCount(order, productCount);
		}

		return this.getTopProductsSortedByCount(productCount, listSize);
	}

	private Map<Product, Integer> getTopProductsSortedByCount(Map<Product, Integer> productCount, int listSize) {
		return productCount.entrySet().stream().sorted((Map.Entry.<Product, Integer>comparingByValue().reversed()))
				.limit(listSize)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

	private void updateProductCount(Order order, Map<Product, Integer> productCount) {
		Map<Product, Integer> necessaryProducts = order.getItems();
		for (Map.Entry<Product, Integer> entry : necessaryProducts.entrySet()) {
			Product product = entry.getKey();
			int quantity = entry.getValue();
			if (productCount.containsKey(product)) {
				productCount.put(product, productCount.get(product) + quantity);
			} else {
				productCount.put(product, quantity);
			}
		}
	}
}
