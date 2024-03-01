package edu.westga.cs3212.inventory_manager.test.order;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Order;

public class TestGetters {

	@Test
	void testGetID() {
		// Arrange
		Order order = new Order();

		// Act and Assert
		assertNotNull(order.getID());
	}
	
	@Test
	void testGetItems() {
		// Arrange
		Order order = new Order();

		// Act and Assert
		assertNotNull(order.getItems());
	}
	
	@Test
	void testGetCompletionStatus() {
		// Arrange
		Order order = new Order();

		// Act and Assert
		assertNotNull(order.getCompletionStatus());
	}
	
	@Test
	void getSalesPrice() {
		// Arrange
		Order order = new Order();

		// Act and Assert
		assertNotNull(order.getSalePrice());
	}
	
	@Test
	void getCost() {
		// Arrange
		Order order = new Order();

		// Act and Assert
		assertNotNull(order.getProductionCost());
	}
	
	
}
