package edu.westga.cs3212.inventory_manager.model.analytics;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import edu.westga.cs3212.inventory_manager.model.server.warehouse.ProductInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Product;

/**
 * Provides analytics on component usage across all products in the inventory.
 * This class enables the identification of the most frequently used components
 * in product manufacturing, allowing for targeted inventory management and
 * procurement strategies.
 * 
 * @version Spring 2024
 * @author Group 1
 */
public class ComponentInventoryAnalytics {

	private static final int MINIMUM_LIST_SIZE = 0;

	/**
	 * Initializes a new ComponentInventoryAnalytics instance with a fresh
	 * LocalProductInventory.
	 * 
	 * @precondition none
	 * @postcondition a new LocalProductInventory is created and associated with
	 *                this instance
	 */
	private ComponentInventoryAnalytics() {
	}

	/**
	 * Retrieves a map of the most frequently used components in the production
	 * of products, sorted by the frequency of their usage. The size of the
	 * returned map is determined by the listSize parameter, allowing for
	 * flexibility in the amount of data retrieved.
	 * 
	 * @param listSize
	 *            the maximum number of components to include in the result,
	 *            determining the size of the returned map
	 * @precondition listSize >= 0
	 * @postcondition none
	 * 
	 * @return a Map<Component, Integer> where each key is a Component and each
	 *         value is the frequency of that component's use across all
	 *         products. The map is sorted in descending order by value
	 *         (frequency of use).
	 * @throws IllegalArgumentException
	 *             if listSize is negative
	 */
	public static Map<Component, Integer> getMostUsedComponents(int listSize) {
		if (listSize < MINIMUM_LIST_SIZE) {
			throw new IllegalArgumentException("List size cannot be negative");
		}
		Product[] products = ProductInventory.getProducts();
		Map<Component, Integer> componentCount = new HashMap<>();

		for (Product product : products) {
			updateComponentCount(product, componentCount);
		}

		return getTopComponentsSortedByCount(componentCount, listSize);
	}

	private static Map<Component, Integer> getTopComponentsSortedByCount(
	        Map<Component, Integer> componentCount, int listSize) {
	    return componentCount.entrySet().stream()
	            .sorted(Map.Entry.<Component, Integer>comparingByValue().reversed())
	            .limit(listSize)
	            .collect(Collectors.toMap(
	                    Map.Entry::getKey,
	                    Map.Entry::getValue,
	                    (oldValue, newValue) -> oldValue, // In case of a key collision, keep the old value
	                    LinkedHashMap::new)); // Preserve the order
	}


	private static void updateComponentCount(Product product,
			Map<Component, Integer> componentCount) {
		Map<Component, Integer> necessaryComponents = product.getRecipe();
		for (Map.Entry<Component, Integer> entry : necessaryComponents
				.entrySet()) {
			Component component = entry.getKey();
			int quantity = entry.getValue();
			if (componentCount.containsKey(component)) {
				componentCount.put(component,
						componentCount.get(component) + quantity);
			} else {
				componentCount.put(component, quantity);
			}
		}
	}
}
