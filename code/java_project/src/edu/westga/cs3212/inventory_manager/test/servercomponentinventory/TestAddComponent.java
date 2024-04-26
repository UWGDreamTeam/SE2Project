package edu.westga.cs3212.inventory_manager.test.servercomponentinventory;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

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
		Server server = new Server();
		server.sendRequest("hello bad test");
		String response = ComponentInventory.addComponent("Test", 0, 0);
		assertEquals(response.length(), 4);
	}
	

}
