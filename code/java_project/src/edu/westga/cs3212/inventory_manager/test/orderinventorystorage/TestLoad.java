package edu.westga.cs3212.inventory_manager.test.orderinventorystorage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.storage.OrderInventoryStorage;
import edu.westga.cs3212.inventory_manager.model.warehouse.Order;

class TestLoad {

	@Test
	void testLoadInvalidFile() {
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order());
		
		assertTrue(ArrayList.class.equals(OrderInventoryStorage.load("path").getClass()));
		assertEquals(0, OrderInventoryStorage.load("path").size());
	}
}
