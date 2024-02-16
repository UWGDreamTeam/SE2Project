package edu.westga.cs3212.inventory_manager.test.model.LocalProductInventory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.Product;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;

class RemoveItemTest {

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

}
