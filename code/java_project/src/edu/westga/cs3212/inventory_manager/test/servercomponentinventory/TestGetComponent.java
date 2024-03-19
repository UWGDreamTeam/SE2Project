package edu.westga.cs3212.inventory_manager.test.servercomponentinventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.server_impl.ComponentInventory;

class TestGetComponent {

	@Test
	void testGetComponentWithNullComponentID() {
		assertThrows(IllegalArgumentException.class, () -> {
			ComponentInventory.getComponent(null);
		});
	}
	
	@Test
	void testGetComponentWithBlankComponentID() {
		assertThrows(IllegalArgumentException.class, () -> {
			ComponentInventory.getComponent("");
		});
	}
	
	@Test
	void testGetComponentWithWhenComponentIDIsNotFound() {
		assertThrows(IllegalArgumentException.class, () -> {
			ComponentInventory.getComponent("123");
		});
	}
	
	@Test
	void testGetComponentWithValidComponentID() {
		String componentID = ComponentInventory.addComponent("testGetComponent", 10.0, 10);
		Component component = ComponentInventory.getComponent(componentID);
		assertEquals("testGetComponent", component.getName());
	}
}
