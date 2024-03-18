package edu.westga.cs3212.inventory_model.test.serverproductinventory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.server_impl.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server_impl.ProductInventory;

class TestProduceProduct {

	@Test
	void testWhenProductIDIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			ProductInventory.produceProduct(null, 0);
		});
	}
	
	@Test
	void testWhenProductIDIsBlank() {
		assertThrows(IllegalArgumentException.class, () -> {
			ProductInventory.produceProduct("", 0);
		});
	}
	
	@Test
	void testWhenProductIsNotInInventory() {
		assertThrows(IllegalArgumentException.class, () -> {
			ProductInventory.produceProduct("1", 0);
		});
	}
	
	@Test
	void testWhenProductIsInInventory() {
		String componentID = ComponentInventory.addComponent("Component", 10, 10);
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(ComponentInventory.getComponent(componentID), 1);
		String productID = ProductInventory.addProduct("Product", 20, recipe, 10);
		int quantity = ProductInventory.produceProduct(productID, 1);
		assertEquals(11, quantity);
	}
	
	@Test
	void testWhenProductIsInInventoryButNotEnoughComponents() {
		String componentID = ComponentInventory.addComponent("TestProduceProductComponent", 10, 1);
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(ComponentInventory.getComponent(componentID), 10);
		String productID = ProductInventory.addProduct("TestProduceProductComponentProduct", 20, recipe, 10);
		assertThrows(IllegalArgumentException.class, () -> {
			ProductInventory.produceProduct(productID, 50);
		});
	}

}
