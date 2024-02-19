package edu.westga.cs3212.inventory_manager.test.model.LocalProductInventory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.Product;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;

class TestGetProductById {

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

}
