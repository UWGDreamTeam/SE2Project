package edu.westga.cs3212.inventory_manager.test.localproductinventory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Product;

class TestGetQuantity {

	@BeforeEach
	void setUp() throws IOException {
		Files.deleteIfExists(Paths.get(Constants.PRODUCT_INVENTORY_FILE_LOCATION));
	}
	
	@Test
	void testGetQuantityValidProduct() {
		LocalProductInventory inventory = new LocalProductInventory();
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(new Component("component", 1.0), 1);
		Product product = new Product("product", 5.0, 20.0, recipe);
		inventory.addItem(product, 1);
		
		
		assertEquals(1, inventory.getQuantityOfItem(product));
	} 
}
