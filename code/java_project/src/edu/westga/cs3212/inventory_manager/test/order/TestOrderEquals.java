package edu.westga.cs3212.inventory_manager.test.order;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Order;
import edu.westga.cs3212.inventory_manager.model.Product;

class TestOrderEquals {

	@Test
	public void testEqualsSameObject() {
		// Arrange
		Order order = new Order();

		// Assert
		assertTrue(order.equals(order));
	}

	@Test
	public void testEqualsNull() {
		// Arrange
		Order order = new Order();

		// Assert
		assertFalse(order.equals(null));
	}

	@Test
	public void testEqualsDifferentClass() {
		// Arrange
		Order order = new Order();
		Component component = new Component("Name", 2);
		HashMap<Component, Integer> recipe = new HashMap<>();
		recipe.put(component, 1);
		Product product = new Product("Name", 2, 3, recipe);

		// Assert
		assertFalse(order.equals(product));
	}

	@Test
	public void testEqualsDifferentIds() {
		// Arrange
		Order order1 = new Order();
		Order order2 = new Order();

		// Assert
		assertFalse(order1.equals(order2));
	}
}
