package edu.westga.cs3212.inventory_manager.test.product;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Product;

public class TestConstructor {

	@Test
	void testNullRecipe() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Product("Name", 2, 3, null);
		});
	}
	
	@Test
	void testEmptyRecipe() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Product("Name", 2, 3, new HashMap<>());
		});
	}
	
	@Test
	void testValidConstructor() {
		Component component = new Component("Name", 2);
		HashMap<Component, Integer> recipe = new HashMap<>();
		recipe.put(component, 1);
		Product product = new Product("Name", 2, 3, recipe);
		assert (product.getName().equals("Name"));
	}
	
	@Test
	void testNegativeSalePrice() {
		Component component = new Component("Name", 2);
		HashMap<Component, Integer> recipe = new HashMap<>();
		recipe.put(component, 1);
		assertThrows(IllegalArgumentException.class, () -> {
			new Product("Name", 2, -3, recipe);
		});
	}
	
	
}
