package edu.westga.cs3212.inventory_manager.test.localproductinventory;

import static org.junit.jupiter.api.Assertions.*;

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

class TestEditItem {

	@BeforeEach
	void setUp() throws IOException {
		Files.deleteIfExists(Paths.get(Constants.PRODUCT_INVENTORY_FILE_LOCATION));
	}

	@Test
	void testEditWhenTheItemIsNull() {
		LocalProductInventory inventory = new LocalProductInventory();
		assertThrows(IllegalArgumentException.class, () -> inventory.editItem(null));
	}

	@Test
	void testEditWhenItemDoesNotExist() {
		LocalProductInventory inventory = new LocalProductInventory();
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(new Component("component", 1.0), 1);
		Product product = new Product("product", 5.0, 20.0, recipe);

		assertThrows(IllegalArgumentException.class, () -> inventory.editItem(product));
	}

	@Test
	void testEditWhenItemExists() {
		LocalProductInventory inventory = new LocalProductInventory();
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(new Component("component", 1.0), 1);
		Product product = new Product("product", 5.0, 20.0, recipe);
		inventory.addItem(product, 1);

		inventory.editItem(product);

		assertTrue(inventory.getProductsWithQuantities().containsKey(product));
	}

	@Test
	void testEditItemFirstOfList() {
		LocalProductInventory inventory = new LocalProductInventory();
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(new Component("component", 1.0), 1);
		
		Product product1 = new Product("product", 5.0, 20.0, recipe);
		Product product2 = new Product("product", 5.0, 20.0, recipe);
		Product product3 = new Product("product", 5.0, 20.0, recipe);
		inventory.addItem(product1, 1);
		inventory.addItem(product2, 1);
		inventory.addItem(product3, 1);

		inventory.editItem(product1);

		assertTrue(inventory.getProductsWithQuantities().containsKey(product1));
	}
	
	@Test
	void testEditItemMiddleOfList() {
		LocalProductInventory inventory = new LocalProductInventory();
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(new Component("component", 1.0), 1);
		
		Product product1 = new Product("product", 5.0, 20.0, recipe);
		Product product2 = new Product("product", 5.0, 20.0, recipe);
		Product product3 = new Product("product", 5.0, 20.0, recipe);
		inventory.addItem(product1, 1);
		inventory.addItem(product2, 1);
		inventory.addItem(product3, 1);

		inventory.editItem(product2);

		assertTrue(inventory.getProductsWithQuantities().containsKey(product2));
	}

	@Test
	void testEditItemLastOfList() {
		LocalProductInventory inventory = new LocalProductInventory();
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(new Component("component", 1.0), 1);
		
		Product product1 = new Product("product", 5.0, 20.0, recipe);
		Product product2 = new Product("product", 5.0, 20.0, recipe);
		Product product3 = new Product("product", 5.0, 20.0, recipe);
		inventory.addItem(product1, 1);
		inventory.addItem(product2, 1);
		inventory.addItem(product3, 1);

		inventory.editItem(product3);

		assertTrue(inventory.getProductsWithQuantities().containsKey(product3));
	}

}
