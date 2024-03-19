package edu.westga.cs3212.inventory_manager.test.serverproductinventory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.server_impl.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server_impl.ProductInventory;

class TestUpdateProduct {

	@Test
	void testWhenProductIDIsNull() {
		Map<Component, Integer> recipe = new HashMap<>();
		assertThrows(IllegalArgumentException.class, () -> {
			ProductInventory.updateProduct(null, "Name", 0, recipe, 0);
		});
	}
	
	@Test
	void testWhenProductIDIsBlank() {
		Map<Component, Integer> recipe = new HashMap<>();
		assertThrows(IllegalArgumentException.class, () -> {
			ProductInventory.updateProduct("", "Name", 0, recipe, 0);
		});
	}
	
	@Test
	void testWhenProductNameIsNull() {
		Map<Component, Integer> recipe = new HashMap<>();
		assertThrows(IllegalArgumentException.class, () -> {
			ProductInventory.updateProduct("ID", null, 0, recipe, 0);
		});
	}
	
	@Test
	void testWhenProductNameIsBlank() {
		Map<Component, Integer> recipe = new HashMap<>();
		assertThrows(IllegalArgumentException.class, () -> {
			ProductInventory.updateProduct("ID", "", 0, recipe, 0);
		});
	}
	
	@Test
	void testWhenRecipeIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			ProductInventory.updateProduct("ID", "Name", 0, null, 0);
		});
	}
	
	@Test
	void testWhenRecipeIsEmpty() {
		Map<Component, Integer> recipe = new HashMap<>();
		assertThrows(IllegalArgumentException.class, () -> {
			ProductInventory.updateProduct("ID", "Name", 0, recipe, 0);
		});
	}
	
	@Test
	void testWhenQuantityIsNegative() {
		Map<Component, Integer> recipe = new HashMap<>();
		String componentID = ComponentInventory.addComponent("Name", 0, 0);
		recipe.put(ComponentInventory.getComponent(componentID), 1);
		assertThrows(IllegalArgumentException.class, () -> {
			ProductInventory.updateProduct("ID", "Name", 0, recipe, -1);
		});
	}
	
	@Test
	void testWhenSalePriceIsNegative() {
		Map<Component, Integer> recipe = new HashMap<>();
		String componentID = ComponentInventory.addComponent("Name", 0, 0);
		recipe.put(ComponentInventory.getComponent(componentID), 1);
		assertThrows(IllegalArgumentException.class, () -> {
			ProductInventory.updateProduct("ID", "Name", -1, recipe, 0);
		});
	}
	
	@Test
	void testWhenProductIDIsValid() {
		Map<Component, Integer> recipe = new HashMap<>();
		String componentID = ComponentInventory.addComponent("Name", 0, 0);
		recipe.put(ComponentInventory.getComponent(componentID), 1);
		String productID = ProductInventory.addProduct("productName", 15, recipe, 15);
		Boolean result = ProductInventory.updateProduct(productID, "Name", 0, recipe, 0);
		assertTrue(result);
	}
	
	@Test
	void testWhenProductIDIsInvalid() {
		Map<Component, Integer> recipe = new HashMap<>();
		String componentID = ComponentInventory.addComponent("Name", 0, 0);
		recipe.put(ComponentInventory.getComponent(componentID), 1);
		assertThrows(IllegalArgumentException.class, () -> {
			ProductInventory.updateProduct("ID", "Name", 0, recipe, 0);
		});
	}

}
