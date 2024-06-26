package edu.westga.cs3212.inventory_manager.test.serverorderinventory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.server.warehouse.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.OrderInventory;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.ProductInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.CompletionStatus;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Product;

class TestDeleteOrder {

	@Test
	void testWhenOrderIDIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			OrderInventory.deleteOrder(null);
		});
	}
	
	@Test
	void testWhenOrderIDIsBlank() {
		assertThrows(IllegalArgumentException.class, () -> {
			OrderInventory.deleteOrder("");
		});
	}
	
	@Test
	void testWhenOrderIDIsValid() {
		Map<Product, Integer> products = new HashMap<>();
		Map<Component, Integer> recipe = new HashMap<>();
		String componentID = ComponentInventory.addComponent("name", 2, 5);
		recipe.put(ComponentInventory.getComponent(componentID), 1);
		String productID = ProductInventory.addProduct("name", 2, recipe, 5);
		products.put(ProductInventory.getProduct(productID), 1);
		String orderID = OrderInventory.createOrder(products, CompletionStatus.INCOMPLETE);
		assertDoesNotThrow(() -> {
		    OrderInventory.deleteOrder(orderID);
		});
	}
	
	@Test
	void testWhenOrderIDIsInvalid() {
		assertThrows(IllegalArgumentException.class, () -> {
			OrderInventory.deleteOrder("invalid");
		});
	}

}
