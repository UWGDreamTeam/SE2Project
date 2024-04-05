package edu.westga.cs3212.inventory_manager.test.localcomponentinventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;

class TestSetQuantityOfItem {

	private LocalComponentInventory inventory;
	
	@BeforeEach
	void setup() {
		this.inventory = new LocalComponentInventory();
		this.inventory.clear();
	}
	
	@Test
	void testNullComponent() {
		assertThrows(IllegalArgumentException.class,
				() -> this.inventory.setQuantityOfItem(null, 0));
	}
	
	@Test
	void testEditComponentValid() {
		Component component = new Component("component", 1.0);
		this.inventory.addItem(component, 1);

		assertEquals(1, this.inventory.getQuantityOfItem(component));
		
		this.inventory.setQuantityOfItem(component, 5);
		
		assertEquals(5, this.inventory.getQuantityOfItem(component));
	}

}
