package edu.westga.cs3212.inventory_manager.test.servercomponentinventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.server_impl.ComponentInventory;

class TestGetQuantity {

	@Test
	void testGetQuantityWithNullID() {
		assertThrows(IllegalArgumentException.class, () -> {
			ComponentInventory.getQuantity(null);
		});
	}
	
	@Test
	void testGetQuantityWithBlankID() {
		assertThrows(IllegalArgumentException.class, () -> {
			ComponentInventory.getQuantity("");
		});
	}
	
	@Test
	void testGetQuantityWithComponentNotInInventory() {
		assertThrows(IllegalArgumentException.class, () -> {
			ComponentInventory.getQuantity("thiscomponentdoesnoteist");
		});
	}
	
	@Test
	void testGetQuantityWithComponentInInventory() {
		String response = ComponentInventory.addComponent("TestComponent", 1.0, 10);
		assertEquals(10, ComponentInventory.getQuantity(response));
	}

}
