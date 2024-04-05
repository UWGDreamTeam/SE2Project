package edu.westga.cs3212.inventory_manager.test.localproductinventory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Product;

class TestSetQuantityOfItem {
	private LocalProductInventory inventory;
	private Product product1;
	@BeforeEach
	void setup() {
		this.inventory = new LocalProductInventory();
		inventory.clear();
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(new Component("component", 1.0), 1);
		this.product1 = new Product("product", 5.0, 20.0, recipe);
		this.inventory.addItem(product1, 1);
	}
	
	@Test
	void testSetInValidProduct() {
		assertThrows(IllegalArgumentException.class, 
				() -> this.inventory.setQuantityOfItem(null, 0), 
		"Adding new item with null param should throw IAE");
	}
	
	@Test
	void testSetInValidProductQuantity() {
		assertThrows(IllegalArgumentException.class, 
				() -> this.inventory.setQuantityOfItem(this.product1, -1), 
		"Adding new item with null param should throw IAE");
	}
	
	@Test
	void testSetValidProductQuantity() {
		this.inventory.setQuantityOfItem(this.product1, 10);
		assertEquals(10, this.inventory.getQuantityOfItem(this.product1));
	}

}
