package edu.westga.cs3212.inventory_manager.test.localcomponentinventory;

import static org.junit.Assert.assertFalse;
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

class TestEditItem {

	@BeforeEach
	void setUp() throws IOException {
		Files.deleteIfExists(Paths.get(Constants.COMPONENT_INVENTORY_FILE_LOCATION));
	}
	
	@Test
	void testWhenTheIDIsNull() {
		LocalComponentInventory inventory = new LocalComponentInventory();
        assertThrows(IllegalArgumentException.class, () -> inventory.editItem(null, null));
	}
	
	@Test
	void testWhenTheIDIsBlank() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		assertThrows(IllegalArgumentException.class, () -> inventory.editItem("", null));
	}
	
	@Test
	void testWhenTheItemIsNull() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		assertThrows(IllegalArgumentException.class, () -> inventory.editItem("1", null));
	}
	
	@Test
	void testWhenItemIDAndComponentIDDoNotMatch() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Item item = new Component("test", "test");
		assertThrows(IllegalArgumentException.class, () -> inventory.editItem("1", item));
	} 
	
	@Test
	void testWhenItemDoesNotExist() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Item item = new Component("1", "test");
		assertThrows(IllegalArgumentException.class, () -> inventory.editItem("1", item));
	}
	
	@Test
	void testWhenItemExists() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Item item = new Component("1", "test");
		inventory.addNewItem(item);
		inventory.editItem("1", new Component("1", "test"));
		assertEquals("test", inventory.getListOfItems().get(0).getName());
	}
	
	@Test
	void testWhenThereAreMultipleItems() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Item item = new Component("1", "test");
		Item item2 = new Component("2", "test2");
		inventory.addNewItem(item);
		inventory.addNewItem(item2);
		inventory.editItem("1", new Component("1", "test"));
		assertEquals("test", inventory.getItemByID("1").getName());
	}
	
	@Test
	void testWhenItemIsFirstItem() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Item item = new Component("1", "test");
		Item item2 = new Component("2", "test2");
		inventory.addNewItem(item);
		inventory.addNewItem(item2);
		inventory.editItem("1", new Component("1", "test"));
		assertEquals("test", inventory.getItemByID("1").getName());
	}
	
	@Test
	void testWhenItemIsLastItem() {
		LocalComponentInventory inventory = new LocalComponentInventory();
		Item item = new Component("1", "test");
		Item item2 = new Component("2", "test2");
		inventory.addNewItem(item);
		inventory.addNewItem(item2);
		inventory.editItem("2", new Component("2", "test"));
		assertEquals("test", inventory.getItemByID("2").getName());
	}

}
