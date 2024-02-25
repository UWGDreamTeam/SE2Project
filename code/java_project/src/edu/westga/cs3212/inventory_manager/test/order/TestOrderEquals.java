package edu.westga.cs3212.inventory_manager.test.order;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

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
		Product product = new Product();

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

	@Test
	public void testEqualsDifferentIdsDifferentDateCreated() throws NoSuchFieldException, IllegalAccessException {
		// Arrange
		Order order1 = new Order();
		Order order2 = new Order();

		// Act
		this.setDifferentDates(order2);

		// Assert
		assertFalse(order1.equals(order2));
	}

	@Test
	public void testEqualsSameIdSameDateCreated() throws NoSuchFieldException, IllegalAccessException {
		// Arrange
		Order order1 = new Order();
		Order order2 = new Order();

		// Act
		this.setSameIds(order1, order2);

		// Assert
		assertTrue(order1.equals(order2));
	}

	@Test
	public void testEqualsSameIdDifferentDateCreated() throws NoSuchFieldException, IllegalAccessException {
		// Arrange
		Order order1 = new Order();
		Order order2 = new Order();

		// Act
		this.setSameIds(order1, order2);
		this.setDifferentDates(order2);

		// Assert
		assertFalse(order1.equals(order2));
	}

	private void setSameIds(Order order1, Order order2) throws NoSuchFieldException, IllegalAccessException {
		Field idField = Order.class.getDeclaredField("id");
		idField.setAccessible(true);
		String id = order1.getId();
		idField.set(order2, id);
		idField.setAccessible(false);
	}

	private void setDifferentDates(Order order2) throws NoSuchFieldException, IllegalAccessException {
		Field dateCreatedField = Order.class.getDeclaredField("dateCreated");
		dateCreatedField.setAccessible(true);
		LocalDateTime newDate = order2.getDateCreated().plusDays(1);
		dateCreatedField.set(order2, newDate);
		dateCreatedField.setAccessible(false);
	}
}
