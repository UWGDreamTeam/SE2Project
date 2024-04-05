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

class TestGetItemsList {

	@BeforeEach
	void setUp() throws IOException {
		Files.deleteIfExists(Paths.get(Constants.PRODUCT_INVENTORY_FILE_LOCATION));
	}

	@Test
	void testGetItemsListEmpty() {
		LocalProductInventory inventory = new LocalProductInventory();
		inventory.clear();
		
		assertEquals(0, inventory.getProductsWithQuantities().size());
	}
	
	@Test
	void testGetItemsListSomeItems() {
		LocalProductInventory inventory = new LocalProductInventory();
		inventory.clear();
		
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(new Component("component", 1.0), 1);
		Product product1 = new Product("product", 5.0, 20.0, recipe);
		Product product2 = new Product("product", 5.0, 20.0, recipe);
		Product product3 = new Product("product", 5.0, 20.0, recipe);
		
		inventory.addItem(product1, 1);
		inventory.addItem(product2, 1);
		inventory.addItem(product3, 1);
		
		assertEquals(3, inventory.getProductsWithQuantities().size());
	}
}
