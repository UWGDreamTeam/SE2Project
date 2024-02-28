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

class TestGetItemByID {

	@BeforeEach
	void setUp() throws IOException {
		Files.deleteIfExists(Paths.get(Constants.COMPONENT_INVENTORY_FILE_LOCATION));
	}
	
	@Test
	void testWhenItemIDIsNull() {
		LocalComponentInventory inventory = new LocalComponentInventory();
	    assertThrows(IllegalArgumentException.class, () -> inventory.getItemByID(null));
	}
	
	@Test
	void testWhenItemIDIsBlank() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		assertThrows(IllegalArgumentException.class, () -> inventory.getItemByID(""));
	}
	
	@Test
	void testWhenThereIsNoItem() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		assertNull(inventory.getItemByID("ID1"));
	}
	
	@Test
	void testWhenThereIsOneItem() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Item item = new Component("item1");
		inventory.addNewItem(item);
		assertEquals(item, inventory.getItemByID("ID1"));
	}
	
	@Test
	void testWhenItemIDDoesNotMatch() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Item item = new Component("item1");
		inventory.addNewItem(item);
		assertNull(inventory.getItemByID("ID2"));
	}
	
	@Test
	void testWhenItemIsFirstItem() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Item item1 = new Component("item1");
		Item item2 = new Component("item2");
		Item item3 = new Component("item3");
		inventory.addNewItem(item1);
		inventory.addNewItem(item2);
		inventory.addNewItem(item3);
		assertEquals(item1, inventory.getItemByID("ID1"));
	}
	
	@Test
	void testWhenItemIsLastItem() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Item item1 = new Component("item1");
		Item item2 = new Component("item2");
		Item item3 = new Component("item3");
		inventory.addNewItem(item1);
		inventory.addNewItem(item2);
		inventory.addNewItem(item3);
		assertEquals(item3, inventory.getItemByID("ID3"));
	}
	
	@Test
	void testWhenItemIsMiddleItem() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Item item1 = new Component("item1");
		Item item2 = new Component("item2");
		Item item3 = new Component("item3");
		inventory.addNewItem(item1);
		inventory.addNewItem(item2);
		inventory.addNewItem(item3);
		assertEquals(item2, inventory.getItemByID("ID2"));
	}

}
