package edu.westga.cs3212.inventory_manager.test.localcomponentinventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;

class TestGetQuantityOfItem {

	private LocalComponentInventory inventory;
	
	@BeforeEach
	void setup() {
		this.inventory = new LocalComponentInventory();
		this.inventory.clear();
	}
	
	@Test
	void testNullComponent() {
		assertThrows(IllegalArgumentException.class,
				() -> this.inventory.getQuantityOfItem(null));
	}
	
	@Test
	void testAddComponentValid() {
		Component component = new Component("component", 1.0);
		this.inventory.addItem(component, 1);

		assertEquals(1, this.inventory.getQuantityOfItem(component));
	}

}
