package edu.westga.cs3212.inventory_manager.test.order;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.warehouse.Order;

class TestSetID {

	@Test
	void testNullId() {
		Order newOrder = new Order();
		assertThrows(IllegalArgumentException.class, () -> {
			newOrder.setID(null);
		});
	}
	
	@Test
	void testBlankId() {
		Order newOrder = new Order();
		assertThrows(IllegalArgumentException.class, () -> {
			newOrder.setID(" ");
		});
	}
	
	@Test
	void testEmptyId() {
		Order newOrder = new Order();
		assertThrows(IllegalArgumentException.class, () -> {
			newOrder.setID("");
		});
	}
	
	@Test
	void testValidId() {
		Order newOrder = new Order();
		newOrder.setID("ID001");
		
		assertEquals("ID001", newOrder.getID());
	}

}
