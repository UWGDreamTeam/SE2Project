package edu.westga.cs3212.inventory_manager.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;

public class ComponentInventoryAnalytics {

	private LocalProductInventory productManager;
	private final static int MINIMUM_LIST_SIZE = 0;

	public ComponentInventoryAnalytics() {
		this.productManager = new LocalProductInventory();
	}

	/**
	 * Returns a map of the most used components in the products
	 * 
	 * @precondition listSize >= 0
	 * @postcondition none
	 * 
	 * @param listSize the number of components to return
	 * @return a map of the most used components
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

	private Map<Component, Integer> getTopComponentsSortedByCount(Map<Component, Integer> componentCount,
			int listSize) {
		return componentCount.entrySet().stream().sorted((Map.Entry.<Component, Integer>comparingByValue().reversed()))
				.limit(listSize)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

	private void updateComponentCount(Product product, Map<Component, Integer> componentCount) {
		Map<Component, Integer> necessaryComponents = product.getRecipe();
		for (Map.Entry<Component, Integer> entry : necessaryComponents.entrySet()) {
			Component component = entry.getKey();
			int quantity = entry.getValue();
			if (componentCount.containsKey(component)) {
				componentCount.put(component, componentCount.get(component) + quantity);
			} else {
				componentCount.put(component, quantity);
			}
		}
	}
}
