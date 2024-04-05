package edu.westga.cs3212.inventory_manager.test.localcomponentinventory;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;

class TestRemoveComponent {

	@BeforeEach
	void setUp() throws IOException {
		Files.deleteIfExists(Paths.get(Constants.COMPONENT_INVENTORY_FILE_LOCATION));
	}

	@Test
	void testRemoveItemWhenItemIsNull() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		assertThrows(IllegalArgumentException.class, () -> {
			inventory.removeItem(null);
		});
	}

	@Test
	void testRemoveItemWhenItemIsNotInInventory() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Component component = new Component("component", 1.0);
		
		assertFalse(inventory.removeItem(component));
	}

	@Test
	void testRemoveItemWhenItemIsInInventory() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Component component = new Component("component", 1.0);
		inventory.addItem(component, 1);
		
		assertTrue(inventory.removeItem(component));
	}

	@Test
	void testRemoveItemWhenItemIsFirstItemInInventory() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Component component1 = new Component("component", 1.0);
		Component component2 = new Component("component2", 1.0);
		Component component3 = new Component("component3", 1.0);
		
		inventory.addItem(component1, 1);
		inventory.addItem(component2, 1);
		inventory.addItem(component3, 1);
		
		assertTrue(inventory.removeItem(component1));
	}

	@Test
	void testRemoveItemWhenItemIsLastItemInInventory() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Component component1 = new Component("component", 1.0);
		Component component2 = new Component("component2", 1.0);
		Component component3 = new Component("component3", 1.0);
		
		inventory.addItem(component1, 1);
		inventory.addItem(component2, 1);
		inventory.addItem(component3, 1);
		
		assertTrue(inventory.removeItem(component2));
	}

	@Test
	void testRemoveItemWhenItemIsMiddleItemInInventory() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Component component1 = new Component("component", 1.0);
		Component component2 = new Component("component2", 1.0);
		Component component3 = new Component("component3", 1.0);
		
		inventory.addItem(component1, 1);
		inventory.addItem(component2, 1);
		inventory.addItem(component3, 1);
		
		assertTrue(inventory.removeItem(component3));
	}

}
