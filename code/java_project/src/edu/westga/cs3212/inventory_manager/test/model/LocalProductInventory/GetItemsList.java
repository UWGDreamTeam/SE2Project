package edu.westga.cs3212.inventory_manager.test.model.LocalProductInventory;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.Product;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;

class GetItemsList {

	@Test
	void testGetItemsList() {
		LocalProductInventory inventory = new LocalProductInventory();
		inventory.clear();
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
