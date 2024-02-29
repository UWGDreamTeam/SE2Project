package edu.westga.cs3212.inventory_manager.test.localproductinventory;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Product;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;

class TestAddItem {

	@Test
	void testAddProductWithNull() {
		LocalProductInventory inventory = new LocalProductInventory();
		
		assertThrows(IllegalArgumentException.class, () -> 
				inventory.addNewItem(null), 
				"Adding new item with null param should throw IAE");
	}
	
	@Test
	void testAddProductWithDuplicated() {
		LocalProductInventory inventory = new LocalProductInventory();
		Product product1 = new Product("ID01", "name1");
		
		inventory.addNewItem(product1);
		
		assertThrows(IllegalArgumentException.class, () -> 
				inventory.addNewItem(new Product("ID01", "name1")), 
				"Adding duplicated item should throw IAE");
	}
	
	@Test
	void testAddProductValid() {
		LocalProductInventory inventory = new LocalProductInventory();
		Product product1 = new Product("ID02", "name2");
		
		assertTrue(inventory.addNewItem(product1));
	}

}
