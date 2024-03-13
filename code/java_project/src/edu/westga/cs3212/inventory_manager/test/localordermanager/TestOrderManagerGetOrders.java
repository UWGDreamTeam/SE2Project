package edu.westga.cs3212.inventory_manager.test.localordermanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.westga.cs3212.inventory_manager.model.CompletionStatus;
import edu.westga.cs3212.inventory_manager.model.Order;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalOrderManager;

class TestOrderManagerGetOrders {

	private LocalOrderManager orderManager;

	@BeforeEach
	public void setUp() {
		this.orderManager = new LocalOrderManager();
		this.orderManager.clearOrders();
	}

	@Test
	public void testGetOrdersEmptyList() {
		// Act
		List<Order> orders = this.orderManager.getOrders();

		// Assert
		assertEquals(0, orders.size());
	}

	@Test
	public void testGetOrdersOneOrder() {
		// Arrange
		Order order1 = new Order();

		this.orderManager.addOrder(order1);

		// Act
		List<Order> orders = this.orderManager.getOrders();

		// Assert
		assertEquals(1, orders.size());
		assertTrue(orders.contains(order1));
	}

	@Test
	public void testGetOrdersMultipleOrders() {
		// Arrange
		Order order1 = new Order();
		Order order2 = new Order();
		Order order3 = new Order();

		this.orderManager.addOrder(order1);
		this.orderManager.addOrder(order2);
		this.orderManager.addOrder(order3);

		// Act
		List<Order> orders = this.orderManager.getOrders();

		// Assert
		assertEquals(3, orders.size());
	}

	@Test
	public void testGetCompleteOrdersSomeMatch() {
		// Arrange
		Order order1 = new Order();
		Order order2 = new Order();
		Order order3 = new Order();

		order1.setCompletionStatus(CompletionStatus.COMPLETE);
		order2.setCompletionStatus(CompletionStatus.COMPLETE);

		this.orderManager.addOrder(order1);
		this.orderManager.addOrder(order2);
		this.orderManager.addOrder(order3);

		// Act
		List<Order> orders = this.orderManager.getOrdersByCompletionStatus(CompletionStatus.COMPLETE);

		// Assert
		assertEquals(2, orders.size());
		assertTrue(orders.contains(order1));
		assertTrue(orders.contains(order2));
	}

	@Test
	public void testGetCompleteOrdersNoneMatch() {
		// Arrange
		Order order1 = new Order();
		Order order2 = new Order();
		Order order3 = new Order();

		this.orderManager.addOrder(order1);
		this.orderManager.addOrder(order2);
		this.orderManager.addOrder(order3);

		// Act
		List<Order> orders = this.orderManager.getOrdersByCompletionStatus(CompletionStatus.COMPLETE);

		// Assert
		assertEquals(0, orders.size());
	}
	
	@Test
	void testGetCompleteOrdersInvalidStatus() {
		// Arrange
		Order order1 = new Order();
		this.orderManager.addOrder(order1);

		// Act and Assert
		assertThrows(IllegalArgumentException.class, () -> {
			this.orderManager.getOrdersByCompletionStatus(null);
		});
	}

	@Test
	public void testGetIncompleteOrdersSomeMatch() {
		// Arrange
		Order order1 = new Order();
		Order order2 = new Order();
		Order order3 = new Order();

		order1.setCompletionStatus(CompletionStatus.COMPLETE);
		order2.setCompletionStatus(CompletionStatus.COMPLETE);

		this.orderManager.addOrder(order1);
		this.orderManager.addOrder(order2);
		this.orderManager.addOrder(order3);

		// Act
		List<Order> orders = this.orderManager.getOrdersByCompletionStatus(CompletionStatus.INCOMPLETE);

		// Assert
		assertEquals(1, orders.size());
		assertTrue(orders.contains(order3));
	}

	@Test
	public void testGetIncompleteOrdersNoneMatch() {
		// Arrange
		Order order1 = new Order();
		Order order2 = new Order();
		Order order3 = new Order();

		order1.setCompletionStatus(CompletionStatus.COMPLETE);
		order2.setCompletionStatus(CompletionStatus.COMPLETE);
		order3.setCompletionStatus(CompletionStatus.COMPLETE);

		this.orderManager.addOrder(order1);
		this.orderManager.addOrder(order2);
		this.orderManager.addOrder(order3);

		// Act
		List<Order> orders = this.orderManager.getOrdersByCompletionStatus(CompletionStatus.INCOMPLETE);

		// Assert
		assertEquals(0, orders.size());
	}
}
