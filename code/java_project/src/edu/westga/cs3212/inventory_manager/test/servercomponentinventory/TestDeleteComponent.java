package edu.westga.cs3212.inventory_manager.test.servercomponentinventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.server.warehouse.ComponentInventory;

class TestDeleteComponent {

	@Test
	void testDeleteComponentWithNullComponentID() {
		assertThrows(IllegalArgumentException.class, () -> {
			ComponentInventory.deleteComponent(null);
		});
	}

	@Test
	void testDeleteComponentWithBlankComponentID() {
		assertThrows(IllegalArgumentException.class, () -> {
			ComponentInventory.deleteComponent("");
		});
	}

	@Test
	void testDeleteComponentWithValidComponentID() {
		String ComponentID = ComponentInventory
				.addComponent("TestDeleteComponent", 1.0, 1);
		assertDoesNotThrow(() -> {
			ComponentInventory.deleteComponent(ComponentID);
		});
	}

	@Test
	void testDeleteComponentWithInvalidComponentID() {
		assertThrows(IllegalArgumentException.class, () -> {
			ComponentInventory.deleteComponent("thiscomponentdoesnoteist");
		});
	}

}
