package edu.westga.cs3212.inventory_manager.test.localproductinventory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.Product;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;

class TestRemoveItem {

	@Test
	void testRemoveItemWithNullItem() {
		LocalProductInventory inventory = new LocalProductInventory();
		
		assertThrows(IllegalArgumentException.class, () -> 
			inventory.removeItem(null), 
			"Remove Item with null item should throw IAE");
		
	}
	
	@Test
	void testRemoveItemWithEmptyList() {
		LocalProductInventory inventory = new LocalProductInventory();
		Product product1 = new Product("ID06", "name6");
		
		assertFalse(inventory.removeItem(product1));
		
	}
	
	@Test
	void testRemoveItemWithItemOnList() {
		LocalProductInventory inventory = new LocalProductInventory();
		Product product1 = new Product("ID07", "name7");
		
		inventory.addNewItem(product1);
		
		assertTrue(inventory.removeItem(product1));
		
	}
	
	@Test
	void testRemoveItemWithItemNotOnList() {
		LocalProductInventory inventory = new LocalProductInventory();
		Product product1 = new Product("ID08", "name8");
		
		inventory.addNewItem(product1);
		
		assertFalse(inventory.removeItem(new Product("ID09", "name9")));
		
	}
	
	@Test
	void testRemoveItemFirstOfList() {
		LocalProductInventory inventory = new LocalProductInventory();
		Product product1 = new Product("ID20", "name20");
		Product product2 = new Product("ID21", "name21");
		Product product3 = new Product("ID22", "name22");
		
		inventory.addNewItem(product1);
		inventory.addNewItem(product2);
		inventory.addNewItem(product3);
		
		assertTrue(inventory.removeItem(new Product("ID20", "name20")));
		
	}
	
	@Test
	void testRemoveItemLastOfList() {
		LocalProductInventory inventory = new LocalProductInventory();
		Product product1 = new Product("ID12", "name12");
		Product product2 = new Product("ID10", "name10");
		Product product3 = new Product("ID11", "name11");
		
		inventory.addNewItem(product1);
		inventory.addNewItem(product2);
		inventory.addNewItem(product3);
		
		assertTrue(inventory.removeItem(new Product("ID11", "name11")));
		
	}
	
	@Test
	void testRemoveItemMiddleOfList() {
		LocalProductInventory inventory = new LocalProductInventory();
		Product product1 = new Product("ID13", "name13");
		Product product2 = new Product("ID14", "name14");
		Product product3 = new Product("ID15", "name15");
		
		inventory.addNewItem(product1);
		inventory.addNewItem(product2);
		inventory.addNewItem(product3);
		
		assertTrue(inventory.removeItem(new Product("ID14", "name14")));
		
	}

}
