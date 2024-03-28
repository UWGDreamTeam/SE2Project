package edu.westga.cs3212.inventory_manager.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;

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

	private LocalProductInventory productManager;
	private static final int MINIMUM_LIST_SIZE = 0;

	/**
	 * Initializes a new ComponentInventoryAnalytics instance with a fresh
	 * LocalProductInventory.
	 * 
	 * @precondition none
	 * @postcondition a new LocalProductInventory is created and associated with
	 *                this instance
	 */
	public ComponentInventoryAnalytics() {
		this.productManager = new LocalProductInventory();
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
	public Map<Component, Integer> getMostUsedComponents(int listSize) {
		if (listSize < MINIMUM_LIST_SIZE) {
			throw new IllegalArgumentException("List size cannot be negative");
		}
		Iterable<Product> products = this.productManager.getProducts();
		Map<Component, Integer> componentCount = new HashMap<>();

		for (Product product : products) {
			this.updateComponentCount(product, componentCount);
		}

		return this.getTopComponentsSortedByCount(componentCount, listSize);
	}

	private Map<Component, Integer> getTopComponentsSortedByCount(
			Map<Component, Integer> componentCount, int listSize) {
		return componentCount.entrySet().stream().sorted(
				(Map.Entry.<Component, Integer>comparingByValue().reversed()))
				.limit(listSize).collect(
						Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
								(e1, e2) -> e1, LinkedHashMap::new));
	}

	private void updateComponentCount(Product product,
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
