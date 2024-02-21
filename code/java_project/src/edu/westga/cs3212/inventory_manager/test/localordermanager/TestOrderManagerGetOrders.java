package edu.westga.cs3212.inventory_manager.test.localordermanager;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Order;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalOrderManager;

class TestOrderManagerGetOrders {

	private LocalOrderManager orderManager;

	@BeforeEach
	public void setUp() {
		this.orderManager = new LocalOrderManager();
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

		order1.setCompleted();
		order2.setCompleted();

		this.orderManager.addOrder(order1);
		this.orderManager.addOrder(order2);
		this.orderManager.addOrder(order3);

		// Act
		List<Order> orders = this.orderManager.getCompleteOrders();

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
		List<Order> orders = this.orderManager.getCompleteOrders();

		// Assert
		assertEquals(0, orders.size());
	}

	@Test
	public void testGetIncompleteOrdersSomeMatch() {
		// Arrange
		Order order1 = new Order();
		Order order2 = new Order();
		Order order3 = new Order();

		order1.setCompleted();
		order2.setCompleted();

		this.orderManager.addOrder(order1);
		this.orderManager.addOrder(order2);
		this.orderManager.addOrder(order3);

		// Act
		List<Order> orders = this.orderManager.getIncompleteOrders();

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

		order1.setCompleted();
		order2.setCompleted();
		order3.setCompleted();

		this.orderManager.addOrder(order1);
		this.orderManager.addOrder(order2);
		this.orderManager.addOrder(order3);

		// Act
		List<Order> orders = this.orderManager.getIncompleteOrders();

		// Assert
		assertEquals(0, orders.size());
	}

	@Test
	public void testGetOrdersByDateSomeMatch() {
		// Arrange
		LocalDateTime date = LocalDateTime.now();
		Order order1 = new Order();
		Order order2 = new Order();
		this.orderManager.addOrder(order1);
		this.orderManager.addOrder(order2);

		// Act
		List<Order> orders = this.orderManager.getOrdersByDate(date);

		// Assert
		assertEquals(2, orders.size());
		assertTrue(orders.contains(order1));
		assertTrue(orders.contains(order2));
	}

	@Test
	public void testGetOrdersByDateNoneMatch() {
		// Arrange
		LocalDateTime date = LocalDateTime.MAX;
		Order order1 = new Order();
		Order order2 = new Order();
		this.orderManager.addOrder(order1);
		this.orderManager.addOrder(order2);

		// Act
		List<Order> orders = this.orderManager.getOrdersByDate(date);

		// Assert
		assertEquals(0, orders.size());
	}

}
