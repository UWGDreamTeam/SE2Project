package edu.westga.cs3212.inventory_manager.test.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.Product;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;

class ProductInventoryTest {
	
	@BeforeEach
	void setup() {
		LocalProductInventory inventory = new LocalProductInventory();
		inventory.clear();
	}
	
	/*ADD ITEM*/
	
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

	/*GET PRODUCT BY ID*/
	
	@Test
	void testGetProductByIdWithNull() {
		LocalProductInventory inventory = new LocalProductInventory();
		
		assertThrows(IllegalArgumentException.class, () -> 
				inventory.getItemById(null), 
				"Get Item By Id with null param should throw IAE");
	}
	
	@Test
	void testGetProductByIdWithEmpty() {
		LocalProductInventory inventory = new LocalProductInventory();
		
		assertThrows(IllegalArgumentException.class, () -> 
				inventory.getItemById(""), 
				"Get Item By Id with empty string param should throw IAE");
	}
	
	@Test
	void testGetProductByIdWithBlank() {
		LocalProductInventory inventory = new LocalProductInventory();
		
		assertThrows(IllegalArgumentException.class, () -> 
				inventory.getItemById(" "), 
				"Get Item By Id with blank string param should throw IAE");
	}
	
	@Test
	void testGetProductByIdWithValidIdInSystem() {
		LocalProductInventory inventory = new LocalProductInventory();
		Product product1 = new Product("ID03", "name3");
		
		inventory.addNewItem(product1);
		
		Product expected = product1;
		Product actual = inventory.getItemById("ID03");
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetProductByIdWithValidIdNotInSystem() {
		LocalProductInventory inventory = new LocalProductInventory();
		Product product1 = new Product("ID04", "name4");
		
		inventory.addNewItem(product1);
		
		Product actual = inventory.getItemById("ID05");
		
		assertNull(actual);
	}
	
	/* GET SIZE*/
	
	@Test
	void testGetQuantityEmptyAndNotEmpty() {
		LocalProductInventory inventory = new LocalProductInventory();
		Product product1 = new Product("ID60", "6name");
		
		inventory.addNewItem(product1);
		
		assertEquals(1, inventory.getQuantity());
		
		inventory.clear();
		
		assertEquals(0, inventory.getQuantity());
		
		
	} 
	
	/* REMOVE ITEM */
	
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
	
	/* GET ITEMS LIST */
	
	@Test
	void testGetItemsList() {
		LocalProductInventory inventory = new LocalProductInventory();
		Product product1 = new Product("ID999", "name999");
		
		assertEquals(0, inventory.getListOfItems().size());
		
		inventory.addNewItem(product1);
		
		String id = inventory.getListOfItems().get(0).getId();
		String name = inventory.getListOfItems().get(0).getName();
		
		
		assertAll(
				() -> assertEquals(1, 0, inventory.getListOfItems().size()),
				() -> assertEquals("ID999", id),
				() -> assertEquals("name999", name)
		);
		
	}

}
