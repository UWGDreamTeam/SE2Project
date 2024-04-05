package edu.westga.cs3212.inventory_manager.test.localproductinventory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

class TestRemoveItem {

	@BeforeEach
	void setUp() throws IOException {
		Files.deleteIfExists(Paths.get(Constants.PRODUCT_INVENTORY_FILE_LOCATION));
	}

	@Test
	void testRemoveItemWithNullItem() {
		LocalProductInventory inventory = new LocalProductInventory();

		assertThrows(IllegalArgumentException.class, () -> inventory.removeItem(null));
	}
	
	@Test
	void testRemoveItemWithNotInList() {
		LocalProductInventory inventory = new LocalProductInventory();
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(new Component("component", 1.0), 1);
		Product product = new Product("product", 5.0, 20.0, recipe);
		Product product2 = new Product("product", 5.0, 20.0, recipe);
		inventory.addItem(product, 1);

		assertFalse(inventory.removeItem(product2));
	}

	@Test
	void testRemoveItemWithItemOnList() {
		LocalProductInventory inventory = new LocalProductInventory();
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(new Component("component", 1.0), 1);
		Product product = new Product("product", 5.0, 20.0, recipe);
		inventory.addItem(product, 1);

		inventory.removeItem(product);

		assertTrue(!inventory.getProductsWithQuantities().containsKey(product));
	}

	@Test
	void testRemoveItemFirstOfList() {
		LocalProductInventory inventory = new LocalProductInventory();
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(new Component("component", 1.0), 1);
		
		Product product1 = new Product("product", 5.0, 20.0, recipe);
		Product product2 = new Product("product", 5.0, 20.0, recipe);
		Product product3 = new Product("product", 5.0, 20.0, recipe);
		inventory.addItem(product1, 1);
		inventory.addItem(product2, 1);
		inventory.addItem(product3, 1);

		inventory.removeItem(product1);

		assertFalse(inventory.getProductsWithQuantities().containsKey(product1));

	}
	
	@Test
	void testRemoveItemMiddleOfList() {
		LocalProductInventory inventory = new LocalProductInventory();
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(new Component("component", 1.0), 1);
		
		Product product1 = new Product("product", 5.0, 20.0, recipe);
		Product product2 = new Product("product", 5.0, 20.0, recipe);
		Product product3 = new Product("product", 5.0, 20.0, recipe);
		inventory.addItem(product1, 1);
		inventory.addItem(product2, 1);
		inventory.addItem(product3, 1);

		inventory.removeItem(product2);

		assertFalse(inventory.getProductsWithQuantities().containsKey(product2));
	}

	@Test
	void testRemoveItemLastOfList() {
		LocalProductInventory inventory = new LocalProductInventory();
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(new Component("component", 1.0), 1);
		
		Product product1 = new Product("product", 5.0, 20.0, recipe);
		Product product2 = new Product("product", 5.0, 20.0, recipe);
		Product product3 = new Product("product", 5.0, 20.0, recipe);
		inventory.addItem(product1, 1);
		inventory.addItem(product2, 1);
		inventory.addItem(product3, 1);

		inventory.removeItem(product3);

		assertFalse(inventory.getProductsWithQuantities().containsKey(product3));
	}
}
