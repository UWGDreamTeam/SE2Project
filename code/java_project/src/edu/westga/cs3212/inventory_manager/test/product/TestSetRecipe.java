package edu.westga.cs3212.inventory_manager.test.product;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Product;

public class TestSetRecipe {

	@Test
	void testSetRecipeWithNull() {
		// Arrange
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(new Component("wood", 10), 1);
		Product product = new Product("bow", 20, 100, recipe);
		// Act
		// Assert
		assertThrows(IllegalArgumentException.class, () -> {
			product.setRecipe(null);
		});
	}
	
	@Test
	void testSetRecipeWithEmptyMap() {
		// Arrange
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(new Component("wood", 10), 1);
		Product product = new Product("bow", 20, 100, recipe);
		// Act
		// Assert
		assertThrows(IllegalArgumentException.class, () -> {
			product.setRecipe(new HashMap<>());
		});
	}
	
	@Test
	void testSetRecipeWithNullComponent() {
		// Arrange
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(new Component("wood", 10), 1);
		Product product = new Product("bow", 20, 100, recipe);
		// Act
		// Assert
		assertThrows(IllegalArgumentException.class, () -> {
			recipe.put(null, 1);
			product.setRecipe(recipe);
		});
	}
	
	@Test
	void testSetRecipeWithEmptyComponent() {
		// Arrange
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(new Component("wood", 10), 1);
		Product product = new Product("bow", 20, 100, recipe);
		// Act
		// Assert
		assertThrows(IllegalArgumentException.class, () -> {
			recipe.put(new Component("", 10), 1);
			product.setRecipe(recipe);
		});
	}
	
	@Test
	void testSetRecipeWithValidComponent() {
		// Arrange
		Map<Component, Integer> recipe = new HashMap<>();
		Component component = new Component("wood", 10);
		recipe.put(component, 1);
		Product product = new Product("bow", 20, 100, recipe);
		// Act
		// Assert
		recipe.put(new Component("wood", 10), 1);
		product.setRecipe(recipe);
		assert(recipe.containsKey(component));
	}
}
