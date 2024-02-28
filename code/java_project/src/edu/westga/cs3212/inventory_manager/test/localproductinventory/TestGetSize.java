package edu.westga.cs3212.inventory_manager.test.localproductinventory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.Product;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;

class TestGetSize {

	@BeforeEach
	void setUp() throws IOException {
		Files.deleteIfExists(Paths.get(Constants.PRODUCT_INVENTORY_FILE_LOCATION));
	}
	
	@Test
	void testGetQuantityEmptyAndNotEmpty() {
		LocalProductInventory inventory = new LocalProductInventory();
		inventory.clear();
		Product product1 = new Product("ID60", "6name");
		
		inventory.addNewItem(product1);
		
		assertEquals(1, inventory.getQuantity());
		
		inventory.clear();
		
		assertEquals(0, inventory.getQuantity());
		
		
	} 

}
