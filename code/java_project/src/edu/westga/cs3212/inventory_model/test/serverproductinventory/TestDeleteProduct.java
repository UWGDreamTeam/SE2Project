package edu.westga.cs3212.inventory_model.test.serverproductinventory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.server_impl.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server_impl.ProductInventory;

class TestDeleteProduct {

	@Test
	void testWhenProductIDIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			ProductInventory.deleteProduct(null);
		});
	}
	
	@Test
	void testWhenProductIDIsBlank() {
		assertThrows(IllegalArgumentException.class, () -> {
			ProductInventory.deleteProduct("");
		});
	}
	
	@Test
	void testWhenProductIsInInventory() {
		Map<Component, Integer> recipe = new HashMap<>();
		String componentID = ComponentInventory.addComponent("Test Component", 10.0, 1);
		Component component = ComponentInventory.getComponent(componentID);
		recipe.put(component, 1);
		String productID = ProductInventory.addProduct("Test Product", 10.0, recipe, 1);
		Boolean response = ProductInventory.deleteProduct(productID);
		assertTrue(response);
	}
	
	@Test
	void testWhenProductIsNotInInventory() {
		assertThrows(IllegalArgumentException.class, () -> {
			ProductInventory.deleteProduct("Test Product");
		});
	}	

}
