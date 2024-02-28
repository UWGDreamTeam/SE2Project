package edu.westga.cs3212.inventory_manager.test.localcomponentinventory;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.Item;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;

class TestClear {

	@BeforeEach
	void setUp() throws IOException {
		Files.deleteIfExists(Paths.get(Constants.COMPONENT_INVENTORY_FILE_LOCATION));
	}
	
	@Test
	void testClearWhenInventoryIsEmpty() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		inventory.clear();
		assertEquals(0, inventory.getQuantity());
	}
	
	@Test
	void testWhenInventoryIsNotEmpty() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Item item = new Component("ID123", "test");
		inventory.addNewItem(item);
		inventory.clear();
		assertEquals(0, inventory.getListOfItems().size());
	}

}
