package edu.westga.cs3212.inventory_manager.test.product;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Product;

class TestAddComponent {

	@Test
	void testAddComponentWithNull() {
        // Arrange
		Map<Component, Integer> recipe = new HashMap<Component, Integer>();
		recipe.put(new Component("arrow", 50), 1);
        Product product = new Product("bow", 20, 100, recipe);

        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            product.addComponent(null, 1);
        });
    }
	
	@Test
	void testAddComponentWithNegativeQuantity() {
		// Arrange
		Map<Component, Integer> recipe = new HashMap<Component, Integer>();
		recipe.put(new Component("arrow", 50), 1);
		Product product = new Product("bow", 20, 100, recipe);

		// Act
		// Assert
		assertThrows(IllegalArgumentException.class, () -> {
			product.addComponent(new Component("feather", 50), -1);
		});
	}
	
	@Test
	void testAddComponentWithZeroQuantity() {
		// Arrange
		Map<Component, Integer> recipe = new HashMap<Component, Integer>();
		recipe.put(new Component("arrow", 50), 1);
		Product product = new Product("bow", 20, 100, recipe);

		// Act
		// Assert
		assertThrows(IllegalArgumentException.class, () -> {
			product.addComponent(new Component("feather", 50), 0);
		});
	}
	
	@Test
	void testAddComponentWhenComponentAlreadyExists() {
		// Arrange
		Map<Component, Integer> recipe = new HashMap<Component, Integer>();
		Component arrow = new Component("arrow", 50);
		recipe.put(arrow, 1);
		Product product = new Product("bow", 20, 100, recipe);

		// Act
		boolean result = product.addComponent(arrow, 1);

		// Assert
		assertFalse(result);
	}
	
	@Test
	void testAddComponentWhenComponentDoesNotExist() {
		// Arrange
		Map<Component, Integer> recipe = new HashMap<Component, Integer>();
		Component arrow = new Component("arrow", 50);
		recipe.put(arrow, 1);
		Product product = new Product("bow", 20, 100, recipe);

		// Act
		boolean result = product.addComponent(new Component("feather", 50), 1);

		// Assert
		assertTrue(result);
	}

}
