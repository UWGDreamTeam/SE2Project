package edu.westga.cs3212.inventory_manager.test.localcomponentinventory;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.Item;
import edu.westga.cs3212.inventory_manager.model.local_impl.Component;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;

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
		Item item = new Component("ID123", "test");
		boolean result = inventory.removeItem(item);
		assertFalse(result);
		
	}
	
	@Test
	void testRemoveItemWhenItemIsInInventory() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Item item = new Component("ID123", "test");
		inventory.addNewItem(item);
		boolean result = inventory.removeItem(item);
		assertTrue(result);
	}
	
	@Test
	void testRemoveItemWhenItemIsFirstItemInInventory() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Item item = new Component("ID123", "test");
		inventory.addNewItem(item);
		Item item2 = new Component("ID124", "test2");
		inventory.addNewItem(item2);
		boolean result = inventory.removeItem(item);
		assertTrue(result);
	}
	
	@Test
	void testRemoveItemWhenItemIsLastItemInInventory() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Item item = new Component("ID123", "test");
		inventory.addNewItem(item);
		Item item2 = new Component("ID124", "test2");
		inventory.addNewItem(item2);
		boolean result = inventory.removeItem(item2);
		assertTrue(result);
	}
	
	@Test
	void testRemoveItemWhenItemIsMiddleItemInInventory() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Item item = new Component("ID123", "test");
		inventory.addNewItem(item);
		Item item2 = new Component("ID124", "test2");
		inventory.addNewItem(item2);
		Item item3 = new Component("ID125", "test3");
		inventory.addNewItem(item3);
		boolean result = inventory.removeItem(item2);
		assertTrue(result);
	}

}
