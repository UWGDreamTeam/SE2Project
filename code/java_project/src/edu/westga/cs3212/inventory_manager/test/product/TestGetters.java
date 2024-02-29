package edu.westga.cs3212.inventory_manager.test.product;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Product;

public class TestGetters {

	@Test
	void testGetSalePrice() {
		Map<Component, Integer> components = new HashMap<>();
		Component component = new Component("test", 1.0);
		components.put(component, 1);
		Product product = new Product("test", 1.0, 1.0, components);
		assert(product.getSalePrice() == 1.0);
	}
	
	@Test
	void testGetRecipe() {
		Map<Component, Integer> components = new HashMap<>();
		Component component = new Component("test", 1.0);
		components.put(component, 1);
		Product product = new Product("test", 1.0, 1.0, components);
		assert (product.getRecipe().equals(components));
	}
	
	@Test
	void testGetProductionCost() {
		Map<Component, Integer> components = new HashMap<>();
		Component component = new Component("test", 1.0);
		components.put(component, 1);
		Product product = new Product("test", 1.0, 1.0, components);
		assert (product.getProductionCost() == 1.0);
	}
}
