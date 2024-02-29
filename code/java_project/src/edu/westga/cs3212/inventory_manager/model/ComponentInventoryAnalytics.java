package edu.westga.cs3212.inventory_manager.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;

public class ComponentInventoryAnalytics {

	private LocalProductInventory productManager;
	private LocalComponentInventory componentManager;
	private final int MINIMUM_LIST_SIZE = 0;
	
	public ComponentInventoryAnalytics() {
		this.productManager = new LocalProductInventory();
		this.componentManager = new LocalComponentInventory();
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
	public Map<Item, Integer> getMostUsedComponents(int listSize) {
		if (listSize < this.MINIMUM_LIST_SIZE) {
            throw new IllegalArgumentException("List size cannot be negative");
		}
	    Iterable<Product> products = this.productManager.getProducts();
	    Map<Item, Integer> componentCount = new HashMap<>();

	    for (Product product : products) {
	        this.updateComponentCount(product, componentCount);
	    }

	    return this.getTopComponentsSortedByCount(componentCount, listSize);
	}

	private void updateComponentCount(Product product, Map<Item, Integer> componentCount) {
	    Map<String, Integer> necessaryComponents = product.getRecipe();
	    for (Map.Entry<String, Integer> entry : necessaryComponents.entrySet()) {
	        Item component = this.componentManager.getItemByID(entry.getKey());
	        int quantity = entry.getValue();
	        componentCount.merge(component, quantity, Integer::sum);
	    }
	}

	private Map<Item, Integer> getTopComponentsSortedByCount(Map<Item, Integer> componentCount, int listSize) {
	    return componentCount.entrySet().stream()
	            .sorted(Map.Entry.<Item, Integer>comparingByValue().reversed())
	            .limit(listSize)
	            .collect(Collectors.toMap(
	                Map.Entry::getKey, 
	                Map.Entry::getValue, 
	                (e1, e2) -> e1, 
	                LinkedHashMap::new));
	}
}
