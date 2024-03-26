package edu.westga.cs3212.inventory_manager.test.localproductinventory;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Constants;
import edu.westga.cs3212.inventory_manager.model.Product;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;

class TestAddItem {

	@BeforeEach
	void setUp() throws IOException {
		Files.deleteIfExists(Paths.get(Constants.PRODUCT_INVENTORY_FILE_LOCATION));
	}
	
	@Test
	void testAddProductWithNull() {
		LocalProductInventory inventory = new LocalProductInventory();
		
		assertThrows(IllegalArgumentException.class, () -> 
				inventory.addItem(null, 0), 
				"Adding new item with null param should throw IAE");
	}
	
	@Test
	void testAddProductWithDuplicated() {
		LocalProductInventory inventory = new LocalProductInventory();
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(new Component("component", 1.0), 1);
		Product product1 = new Product("product", 5.0, 20.0, recipe);
		
		inventory.addItem(product1, 1);
		
		assertThrows(IllegalArgumentException.class, () -> 
				inventory.addItem(product1, 1));
	}
	
	@Test
	void testAddProductLessThanMinimumQuantityNegative() {
		LocalProductInventory inventory = new LocalProductInventory();
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(new Component("component", 1.0), 1);
		assertThrows(IllegalArgumentException.class, () -> 
				inventory.addItem(new Product("product", 5.0, 20.0, recipe), -1));
	}
	
	@Test
	void testAddProductValid() {
		LocalProductInventory inventory = new LocalProductInventory();
		inventory.clear();
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(new Component("component", 1.0), 1);
		Product product1 = new Product("product", 5.0, 20.0, recipe);
		
		assertTrue(inventory.addItem(product1, 1));
	}
}
