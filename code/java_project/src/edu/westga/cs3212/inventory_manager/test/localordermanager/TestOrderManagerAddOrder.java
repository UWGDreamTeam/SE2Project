package edu.westga.cs3212.inventory_manager.test.localordermanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalOrderManager;
import edu.westga.cs3212.inventory_manager.model.warehouse.Order;

class TestOrderManagerAddOrder {

	private LocalOrderManager orderManager;

	@BeforeEach
	public void setUp() {
		this.orderManager = new LocalOrderManager();
		this.orderManager.clearOrders();
	}

	@Test
	void testAddOrderNullOrder() {
		// Act and Assert
		assertThrows(IllegalArgumentException.class, () -> {
			this.orderManager.addOrder(null);
		});
	}
	
	@Test
	void testAddOrderValidOrder() {
		// Arrange
		Order order = new Order();

		// Act
		this.orderManager.addOrder(order);

		// Assert
		assertEquals(1, this.orderManager.getOrders().size());
		assertEquals(order, this.orderManager.findOrderById(order.getID()));
	}

	@Test
	void testAddOrderOrderAlreadyExists() {
		// Arrange
		Order order = new Order();
		this.orderManager.addOrder(order);

		// Act and Assert
		assertThrows(IllegalArgumentException.class, () -> {
			this.orderManager.addOrder(order);
		});
	}
}
