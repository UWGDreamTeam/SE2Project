package edu.westga.cs3212.inventory_manager.test.localproductinventory;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.Item;
import edu.westga.cs3212.inventory_manager.model.local_impl.Product;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;

class TestEditItem {

	@BeforeEach
	void setUp() throws IOException {
		Files.deleteIfExists(Paths.get(Constants.PRODUCT_INVENTORY_FILE_LOCATION
				));
	}
	
	@Test
	void testWhenTheIDIsNull() {
		LocalProductInventory inventory = new LocalProductInventory();
        assertThrows(IllegalArgumentException.class, () -> inventory.editItem(null, null));
	}
	
	@Test
	void testWhenTheIDIsBlank() {
		LocalProductInventory inventory = new LocalProductInventory();
		assertThrows(IllegalArgumentException.class, () -> inventory.editItem("", null));
	}
	
	@Test
	void testWhenTheItemIsNull() {
		LocalProductInventory inventory = new LocalProductInventory();
		assertThrows(IllegalArgumentException.class, () -> inventory.editItem("1", null));
	}
	
	@Test
	void testWhenItemIDAndProductIDDoNotMatch() {
		LocalProductInventory inventory = new LocalProductInventory();
		Item item = new Product("test", "test");
		assertThrows(IllegalArgumentException.class, () -> inventory.editItem("1", item));
	} 
	
	@Test
	void testWhenItemDoesNotExist() {
		LocalProductInventory inventory = new LocalProductInventory();
		Item item = new Product("1", "test");
		assertThrows(IllegalArgumentException.class, () -> inventory.editItem("1", item));
	}
	
	@Test
	void testWhenItemExists() {
		LocalProductInventory inventory = new LocalProductInventory();
		Item item = new Product("1", "test");
		inventory.addNewItem(item);
		inventory.editItem("1", new Product("1", "test"));
		assertEquals("test", inventory.getListOfItems().get(0).getName());
	}
	
	@Test
	void testWhenThereAreMultipleItems() {
		LocalProductInventory inventory = new LocalProductInventory();
		Item item = new Product("1", "test");
		Item item2 = new Product("2", "test2");
		inventory.addNewItem(item);
		inventory.addNewItem(item2);
		inventory.editItem("1", new Product("1", "test"));
		assertEquals("test", inventory.getItemByID("1").getName());
	}
	
	@Test
	void testWhenItemIsFirstItem() {
		LocalProductInventory inventory = new LocalProductInventory();
		Item item = new Product("1", "test");
		Item item2 = new Product("2", "test2");
		inventory.addNewItem(item);
		inventory.addNewItem(item2);
		inventory.editItem("1", new Product("1", "test"));
		assertEquals("test", inventory.getItemByID("1").getName());
	}
	
	@Test
	void testWhenItemIsLastItem() {
		LocalProductInventory inventory = new LocalProductInventory();
		Item item = new Product("1", "test");
		Item item2 = new Product("2", "test2");
		inventory.addNewItem(item);
		inventory.addNewItem(item2);
		inventory.editItem("2", new Product("2", "test"));
		assertEquals("test", inventory.getItemByID("2").getName());
	}

}
