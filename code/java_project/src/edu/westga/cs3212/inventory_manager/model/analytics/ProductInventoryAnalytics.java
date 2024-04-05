package edu.westga.cs3212.inventory_manager.model.analytics;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import edu.westga.cs3212.inventory_manager.model.server.warehouse.OrderInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.Order;
import edu.westga.cs3212.inventory_manager.model.warehouse.Product;

/**
 * Provides analytics on product usage within orders in the inventory management
 * system. This class aggregates data on how often products are used in orders
 * and identifies the most frequently used products.
 *
 * @author Group 1
 * @version Spring 2024
 */
public final class ProductInventoryAnalytics {

	private static final int MINIMUM_LIST_SIZE = 0;

	/**
	 * Initializes a new instance of ProductInventoryAnalytics with its own
	 * LocalOrderManager.
	 * 
	 * @precondition none
	 * @postcondition a new LocalOrderManager instance is associated with this
	 *                instance
	 */
	private ProductInventoryAnalytics() {
	}

	/**
	 * Retrieves a map of the most frequently used products in orders, sorted by
	 * their usage frequency. The size of the returned map is limited to the
	 * specified listSize.
	 * 
	 * @param listSize
	 *            The maximum number of products to include in the result.
	 * @return A map where each key is a Product and each value is the frequency
	 *         of that product's usage across all orders. The map is sorted in
	 *         descending order by frequency.
	 * @precondition listSize >= 0
	 * @postcondition none
	 * @throws IllegalArgumentException
	 *             if listSize is negative
	 */
	public static Map<Product, Integer> getMostUsedProducts(int listSize) {
		if (listSize < MINIMUM_LIST_SIZE) {
			throw new IllegalArgumentException("List size cannot be negative");
		}
		Iterable<Order> orders = OrderInventory.getOrders();
		Map<Product, Integer> productCount = new HashMap<>();

		for (Order order : orders) {
			updateProductCount(order, productCount);
		}

		return getTopProductsSortedByCount(productCount, listSize);
	}

	private static Map<Product, Integer> getTopProductsSortedByCount(
			Map<Product, Integer> productCount, int listSize) {
		return productCount.entrySet().stream().sorted(
				(Map.Entry.<Product, Integer>comparingByValue().reversed()))
				.limit(listSize).collect(
						Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
								(e1, e2) -> e1, LinkedHashMap::new));
	}

	private static void updateProductCount(Order order,
			Map<Product, Integer> productCount) {
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
