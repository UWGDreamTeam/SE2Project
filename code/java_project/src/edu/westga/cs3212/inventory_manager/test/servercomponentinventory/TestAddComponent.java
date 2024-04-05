package edu.westga.cs3212.inventory_manager.test.servercomponentinventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.server.Server;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.ComponentInventory;

class TestAddComponent {

	@Test
	void testAddNullComponentName() {
		assertThrows(IllegalArgumentException.class, () -> {
			ComponentInventory.addComponent(null, 0, 0);
		});
	}
	
	@Test
	void testNullRequest() {
		assertThrows(IllegalArgumentException.class, () -> {
			Server.sendRequest(null);
		});
	}
	
	@Test
	void testErrorRequest() {
		assertThrows(IllegalArgumentException.class, () -> {
			Server.sendRequest("request");
		});
	}
	
	
	@Test
	void testAddBlankComponentName() {
		assertThrows(IllegalArgumentException.class, () -> {
			ComponentInventory.addComponent("", 0, 0);
		});
	}
	
	@Test
	void testAddNegativeProductionCost() {
		assertThrows(IllegalArgumentException.class, () -> {
			ComponentInventory.addComponent("Test", -1, 0);
		});
	}
	
	@Test
	void testAddValidComponent() {
		String response = ComponentInventory.addComponent("Test", 0, 0);
		assertEquals(response.length(), 4);
	}
	
	

}
