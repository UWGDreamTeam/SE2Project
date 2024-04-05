package edu.westga.cs3212.inventory_manager.test.localproductinventory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Item;
import edu.westga.cs3212.inventory_manager.model.warehouse.Product;

class TestGetItemById {

	@Test
	void testGetItemByIdNullId() {
		LocalProductInventory inventory = new LocalProductInventory();
		
		assertThrows(IllegalArgumentException.class, () -> 
				inventory.getItemByID(null), 
				"Get Item By Id with null param should throw IAE");
	}
	
	@Test
	void testGetItemByIdEmptyId() {
		LocalProductInventory inventory = new LocalProductInventory();
		
		assertThrows(IllegalArgumentException.class, () -> 
				inventory.getItemByID(""), 
				"Get Item By Id with empty string param should throw IAE");
	}
	
	@Test
	void testGetItemByIdBlankId() {
		LocalProductInventory inventory = new LocalProductInventory();
		
		assertThrows(IllegalArgumentException.class, () -> 
				inventory.getItemByID(" "), 
				"Get Item By Id with blank string param should throw IAE");
	}
	
	@Test
	void testGetItemByIdWithValidIdInSystem() {
		LocalProductInventory inventory = new LocalProductInventory();
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(new Component("component", 1.0), 1);
		Product product = new Product("product", 5.0, 20.0, recipe);
		
		inventory.addItem(product, 1);
		
		Item expected = product;
		Item actual = inventory.getItemByID(product.getID());
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetItemByIdWithValidIdNotInSystem() {
		LocalProductInventory inventory = new LocalProductInventory();
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(new Component("component", 1.0), 1);
		Product product = new Product("product", 5.0, 20.0, recipe);
		
		inventory.addItem(product, 1);
		
		Item actual = inventory.getItemByID("test");
		
		assertNull(actual);
	}

}
