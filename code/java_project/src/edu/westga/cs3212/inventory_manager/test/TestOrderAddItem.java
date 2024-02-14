package edu.westga.cs3212.inventory_manager.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import edu.westga.cs3212.inventory_manager.model.Order;
import edu.westga.cs3212.inventory_manager.model.Product;

class TestOrderAddItem {

	@Test
	void testAddItemValidInput() {
		// Arrange 
		Order order = new Order();
		Product product = new Product();
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
		Product product = null;
		int quantity = 2;

		// Act and Assert
		assertThrows(IllegalArgumentException.class, () -> {
			order.addItem(product, quantity);
		});
	}
	
	@Test
	void testAddItemQuantityIsZero() {
		// Arrange
		Order order = new Order();
		Product product = new Product();
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
		Product product = new Product();
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
		Product product = new Product();
		int quantity = 1;
		order.addItem(product, quantity);

		// Act
		order.addItem(product, quantity);
		
		// Assert
		assertEquals(2, order.getItems().get(product));
	}
}
