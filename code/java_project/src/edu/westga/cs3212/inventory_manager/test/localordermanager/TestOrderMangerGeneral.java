package edu.westga.cs3212.inventory_manager.test.localordermanager;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Order;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalOrderManager;

class TestOrderMangerGeneral {

	private LocalOrderManager orderManager;

	@BeforeEach
	public void setUp() {
		this.orderManager = new LocalOrderManager();
	}
	
	@Test
	void testFindOrderByIdNullId() {
		// Act and Assert
		assertThrows(IllegalArgumentException.class, () -> {
			this.orderManager.findOrderById(null);
		});
	}
	
	@Test
	void testFindOrderByIdEmptyId() {
		// Act and Assert
		assertThrows(IllegalArgumentException.class, () -> {
			this.orderManager.findOrderById("");
		});
	}
	
	@Test
	void testFindOrderByIdNoIdExists() {
		// Act and Assert
		assertThrows(IllegalArgumentException.class, () -> {
			this.orderManager.findOrderById("9000");
		});
	}
	
	@Test
	void testFindOrderByIdValidId() {
		// Arrange
		Order order = new Order();
		this.orderManager.addOrder(order);

		// Act
        Order foundOrder = this.orderManager.findOrderById(order.getId());

		// Assert
		assertEquals(order, foundOrder);
	}

	@Test
	void testCompleteOrderNullOrder() {
		// Act and Assert
		assertThrows(IllegalArgumentException.class, () -> {
			this.orderManager.markOrderAsComplete(null);
		});
	}
	
	@Test
	void testCompleteOrderValidOrder() {
		// Arrange
		Order order = new Order();
		this.orderManager.addOrder(order);

		// Act
		this.orderManager.markOrderAsComplete(order);

		// Assert
		assertTrue(this.orderManager.findOrderById(order.getId()).isCompleted());
	}
	
	@Test
	void testCompleteOrderOrderAlreadyComplete() {
		// Arrange
		Order order = new Order();
		this.orderManager.addOrder(order);
		this.orderManager.markOrderAsComplete(order);

		// Act
		this.orderManager.markOrderAsComplete(order);

		// Assert
		assertTrue(this.orderManager.findOrderById(order.getId()).isCompleted());
	}
	
	@Test
	void testUndoOrderCompletionNullOrder() {
		// Act and Assert
		assertThrows(IllegalArgumentException.class, () -> {
			this.orderManager.markOrderAsIncomplete(null);
		});
	}
	
	@Test
	void testUndoOrderCompletionValidOrder() {
		// Arrange
		Order order = new Order();
		this.orderManager.addOrder(order);
		this.orderManager.markOrderAsComplete(order);

		// Act
		this.orderManager.markOrderAsIncomplete(order);

		// Assert
		assertFalse(this.orderManager.findOrderById(order.getId()).isCompleted());
	}
	
	@Test
	void testUndoOrderCompletionOrderAlreadyIncomplete() {
		// Arrange
		Order order = new Order();
		this.orderManager.addOrder(order);

		// Act
		this.orderManager.markOrderAsIncomplete(order);

		// Assert
		assertFalse(this.orderManager.findOrderById(order.getId()).isCompleted());
	}

}
