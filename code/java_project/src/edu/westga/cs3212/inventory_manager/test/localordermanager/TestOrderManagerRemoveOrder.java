package edu.westga.cs3212.inventory_manager.test.localordermanager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalOrderManager;
import edu.westga.cs3212.inventory_manager.model.warehouse.Order;

class TestOrderManagerRemoveOrder {

	private LocalOrderManager orderManager;

	@BeforeEach
	public void setUp() {
		this.orderManager = new LocalOrderManager();
		this.orderManager.clearOrders();
	}

	@Test
	void testRemoveOrderNullOrder() {
		// Act and Assert
		assertThrows(IllegalArgumentException.class, () -> {
			this.orderManager.removeOrder(null);
		});
	}
	
	@Test
	void testRemoveOrderValidOrder() {
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
