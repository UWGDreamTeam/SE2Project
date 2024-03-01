package edu.westga.cs3212.inventory_manager.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalOrderManager;

public class ProductInventoryAnalytics {

	private LocalOrderManager orderManager;
	private final static int MINIMUM_LIST_SIZE = 0;
	
	public ProductInventoryAnalytics() {
		this.orderManager = new LocalOrderManager();
    }
	
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
