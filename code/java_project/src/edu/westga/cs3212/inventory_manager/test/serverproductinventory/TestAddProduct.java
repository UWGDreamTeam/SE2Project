package edu.westga.cs3212.inventory_manager.test.serverproductinventory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.server_impl.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server_impl.ProductInventory;

class TestAddProduct {

	@Test
	void testAddProductWithNullName() {
		assertThrows(IllegalArgumentException.class, () -> {
			ProductInventory.addProduct(null, 10.0, Map.of(), 1);
		});
	}
	
	@Test
	void testAddProductWithBlankName() {
		assertThrows(IllegalArgumentException.class, () -> {
			ProductInventory.addProduct("", 10.0, Map.of(), 1);
		});
	}
	
	@Test
	void testAddProductWithNullRecipe() {
		assertThrows(IllegalArgumentException.class, () -> {
			ProductInventory.addProduct("Test", 10.0, null, 1);
		});
	}
	
	@Test
	void testAddProductWithEmptyRecipe() {
		assertThrows(IllegalArgumentException.class, () -> {
			ProductInventory.addProduct("Test", 10.0, Map.of(), 1);
		});
	}
	
	@Test
	void testAddProductWithNegativeQuantity() {
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(new Component("Test", 1.0), 1);
		assertThrows(IllegalArgumentException.class, () -> {
			ProductInventory.addProduct("Test", 10.0, recipe, -1);
		});
	}
	
	@Test
	void testAddProductWithNegativeSalePrice() {
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(new Component("Test", 1.0), 1);
		assertThrows(IllegalArgumentException.class, () -> {
			ProductInventory.addProduct("Test", -10.0, recipe, 1);
		});
	}
	
	@Test
	void testAddProductWithValidProduct() {
		Map<Component, Integer> recipe = new HashMap<>();
		String componentID = ComponentInventory.addComponent("Test", 1.0, 1);
		recipe.put(ComponentInventory.getComponent(componentID), 1);
		String productID = ProductInventory.addProduct("Test", 10.0, recipe, 1);
		assertTrue(!productID.isEmpty());
	}
	
	@Test
	void testAddProductWithValidProductButComponentNotInInventory() {
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(new Component("Test", 1.0), 1);
		assertThrows(IllegalArgumentException.class, () -> {
			ProductInventory.addProduct("Test", 10.0, recipe, 1);
		});
	}

}
