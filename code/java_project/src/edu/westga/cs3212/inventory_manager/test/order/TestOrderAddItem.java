package edu.westga.cs3212.inventory_manager.test.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Order;
import edu.westga.cs3212.inventory_manager.model.warehouse.Product;

class TestOrderAddItem {

	@Test
	void testAddItemValidInput() {
		// Arrange 
		Order order = new Order();
		Component component = new Component("Name", 2);
		HashMap<Component, Integer> recipe = new HashMap<>();
		recipe.put(component, 1);
		Product product = new Product("Name", 2, 3, recipe);
		int quantity = 2;
		
		// Act
		order.addItem(product, quantity);
		
		// Assert
		assertEquals(1, order.getItems().size());
		assertEquals(quantity, order.getItems().get(product));
	}
	
	@Test
	void testAddItemNullProduct() {
		// Arrange
		Order order = new Order();
		int quantity = 2;

		// Act and Assert
		assertThrows(IllegalArgumentException.class, () -> {
			order.addItem(null, quantity);
		});
	}
	
	@Test
	void testAddItemQuantityIsZero() {
		// Arrange
		Order order = new Order();
		Component component = new Component("Name", 2);
		HashMap<Component, Integer> recipe = new HashMap<>();
		recipe.put(component, 1);
		Product product = new Product("Name", 2, 3, recipe);
		int quantity = 0;

		// Act and Assert
		assertThrows(IllegalArgumentException.class, () -> {
			order.addItem(product, quantity);
		});
	}
	
	@Test
	void testAddItemQuantityIsLessThanZero() {
		// Arrange
		Order order = new Order();
		Component component = new Component("Name", 2);
		HashMap<Component, Integer> recipe = new HashMap<>();
		recipe.put(component, 1);
		Product product = new Product("Name", 2, 3, recipe);
		int quantity = -1;

		// Act and Assert
		assertThrows(IllegalArgumentException.class, () -> {
			order.addItem(product, quantity);
		});
	}
	
	@Test
	void testAddItemItemAlreadyExists() {
		// Arrange
		Order order = new Order();
		Component component = new Component("Name", 2);
		HashMap<Component, Integer> recipe = new HashMap<>();
		recipe.put(component, 1);
		Product product = new Product("Name", 2, 3, recipe);
		int quantity = 1;
		order.addItem(product, quantity);

		// Act
		order.addItem(product, quantity);
		
		// Assert
		assertEquals(2, order.getItems().get(product));
	}
}
