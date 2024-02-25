package edu.westga.cs3212.inventory_manager.test.localproductinventory;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.Product;
import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;

class TestGetItemsList {

	@BeforeEach
	void setUp() throws IOException {
		Files.deleteIfExists(Paths.get(Constants.PRODUCT_INVENTORY_FILE_LOCATION));
	}
	
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
