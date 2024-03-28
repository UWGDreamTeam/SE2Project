package edu.westga.cs3212.inventory_manager.test.serverproductinventory;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Product;
import edu.westga.cs3212.inventory_manager.model.server_impl.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server_impl.ProductInventory;

class TestGetProduct {

	@Test
	void testWhenProductIDIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			ProductInventory.getProduct(null);
		});
	}
	
	@Test
	void testWhenProductIDIsBlank() {
		assertThrows(IllegalArgumentException.class, () -> {
			ProductInventory.getProduct("");
		});
	}
	
	@Test
	void testWhenProductIsNotInInventory() {
		assertThrows(IllegalArgumentException.class, () -> {
			ProductInventory.getProduct("1");
		});
	}
	
	@Test
	void testWhenProductIsInInventory() {
		String componentID = ComponentInventory.addComponent("Component1", 1.0, 1);
		Component component = ComponentInventory.getComponent(componentID);
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(component, 1);
		String productID = ProductInventory.addProduct("Product1", 2.0, recipe, 1);
		Product product = ProductInventory.getProduct(productID);
		assert(product.getName().equals("Product1"));
		assert(product.getSalePrice() == 2.0);
		assert(product.getProductionCost() == 1.0);
	}

}
