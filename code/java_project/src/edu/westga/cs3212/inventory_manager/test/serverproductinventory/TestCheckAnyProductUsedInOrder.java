package edu.westga.cs3212.inventory_manager.test.serverproductinventory;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.server.warehouse.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.OrderInventory;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.ProductInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.CompletionStatus;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Product;

class TestCheckAnyProductUsedInOrder {

	@BeforeEach
	void setUp() throws Exception {
		ProductInventory.clearInventory();
		ComponentInventory.clearInventory();
		OrderInventory.clearOrders();
	}
	
	@Test
	void testWhenProductIDIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			ProductInventory.checkAnyProductUsedInOrder(null);
		});
	}
	
	@Test
	void testWhenThereIsOneOrder() {
		Map<Component, Integer> recipe = new HashMap<>();
		String componentID = ComponentInventory.addComponent("Test", 1.0, 1);
		recipe.put(ComponentInventory.getComponent(componentID), 1);
		String productID = ProductInventory.addProduct("Test", 10.0, recipe, 1);
		Product product = ProductInventory.getProduct(productID);
		Map<Product, Integer> products = new HashMap<>();
		products.put(product, 1);
		OrderInventory.createOrder(products, CompletionStatus.COMPLETE);
		ProductInventory.checkAnyProductUsedInOrder(productID);
	}

}
