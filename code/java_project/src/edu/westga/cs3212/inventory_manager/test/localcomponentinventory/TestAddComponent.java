package edu.westga.cs3212.inventory_manager.test.localcomponentinventory;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.Item;
import edu.westga.cs3212.inventory_manager.model.local_impl.Component;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;

class TestAddComponent {

	@BeforeEach
	void setUp() throws Exception {
		Files.deleteIfExists(Paths.get(Constants.COMPONENT_INVENTORY_FILE_LOCATION));
	}
	
	@Test
	void testAddItemWhenItemIsNull() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		assertThrows(IllegalArgumentException.class, () -> {
			inventory.addNewItem(null);
		});
	}
	
	@Test
	void testAddItemWhenItemAlreadyExists() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Item item = new Component("ID123", "Arrow");
		inventory.addNewItem(item);
		assertThrows(IllegalArgumentException.class, () -> {
			inventory.addNewItem(item);
		});
	}
	
	@Test
	void testAddItemWhenThereAreMultipleItems() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Item item1 = new Component("ID123", "Arrow");
		Item item2 = new Component("ID124", "Bow");
		inventory.addNewItem(item1);
		inventory.addNewItem(item2);
		assertEquals(2, inventory.getListOfItems().size());
	}
	
}
