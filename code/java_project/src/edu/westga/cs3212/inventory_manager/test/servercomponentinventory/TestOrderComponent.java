package edu.westga.cs3212.inventory_manager.test.servercomponentinventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.server_impl.ComponentInventory;

class TestOrderComponent {

	@Test
	void testOrderComponentWithNullID() {
		assertThrows(IllegalArgumentException.class, () -> {
			ComponentInventory.orderComponent(null, 0);
		});
	}
	
	@Test
	void testOrderComponentWithBlankID() {
		assertThrows(IllegalArgumentException.class, () -> {
			ComponentInventory.orderComponent("", 0);
		});
	}
	
	@Test 
	void testOrderComponentWhenCompoentIDIsNotInInventory() {
        assertThrows(IllegalArgumentException.class, () -> {
            ComponentInventory.orderComponent("123", 0);
        });
    }
	
	@Test
	void testOrderComponentWithValidComponent() {
		String ComponentID = ComponentInventory.addComponent("TestComponent", 0.0, 1);
        int result = ComponentInventory.orderComponent(ComponentID, 1);
        assertEquals(2, result);
	}
}
