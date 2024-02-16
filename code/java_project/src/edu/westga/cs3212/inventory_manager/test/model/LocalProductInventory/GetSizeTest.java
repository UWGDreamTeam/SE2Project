package edu.westga.cs3212.inventory_manager.test.model.LocalProductInventory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.Product;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;

class GetSizeTest {

	@Test
	void testGetQuantityEmptyAndNotEmpty() {
		LocalProductInventory inventory = new LocalProductInventory();
		Product product1 = new Product("ID60", "6name");
		
		inventory.addNewItem(product1);
		
		assertEquals(1, inventory.getQuantity());
		
		inventory.clear();
		
		assertEquals(0, inventory.getQuantity());
		
		
	} 

}
