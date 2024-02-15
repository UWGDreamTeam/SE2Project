package edu.westga.cs3212.inventory_manager.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Order;
import edu.westga.cs3212.inventory_manager.model.OrderManager;

class TestOrderManagerRemoveOrder {

	private OrderManager orderManager;

	@BeforeEach
	public void setUp() {
		this.orderManager = new OrderManager();
	}

	@Test
	void testRemoveOrderNullOrder() {
		// Act and Assert
		assertThrows(IllegalArgumentException.class, () -> {
			this.orderManager.removeOrder(null);
		});
	}
	
	@Test
	void testRemoveOrderValidInput() {
		// Arrange
		Order order = new Order();
		this.orderManager.addOrder(order);

		// Act
		this.orderManager.removeOrder(order);

		// Assert
		assertEquals(0, this.orderManager.getOrders().size());
	}

	@Test
	void testRemoveOrderOrderDoesNotExist() {
		// Arrange
		Order order = new Order();

		// Act and Assert
		assertThrows(IllegalArgumentException.class, () -> {
			this.orderManager.removeOrder(order);
		});
	}

}
