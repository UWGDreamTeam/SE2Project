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

class TestCreateOrder {

	

	@Test
	void testWhenProductsIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			OrderInventory.createOrder(null, CompletionStatus.INCOMPLETE);
		});
	}
	
	@Test
	void testWhenProductsIsEmpty() {
		Map<Product, Integer> products = new HashMap<>();
		assertThrows(IllegalArgumentException.class, () -> {
			OrderInventory.createOrder(products, CompletionStatus.INCOMPLETE);
		});
	}
	
	@Test
	void testWhenCompletionStatusIsNull() {
		Map<Product, Integer> products = new HashMap<>();
		Map<Component, Integer> recipe = new HashMap<>();
		String componentID = ComponentInventory.addComponent("name", 2, 5);
		recipe.put(ComponentInventory.getComponent(componentID), 1);
		String productID = ProductInventory.addProduct("name", 2, recipe, 5);
		products.put(ProductInventory.getProduct(productID), 1);
		assertThrows(IllegalArgumentException.class, () -> {
			OrderInventory.createOrder(products, null);
		});
	}
	
	@Test
	void testWhenOrderIsValid() {
		Map<Product, Integer> products = new HashMap<>();
		Map<Component, Integer> recipe = new HashMap<>();
		String componentID = ComponentInventory.addComponent("name", 2, 5);
		recipe.put(ComponentInventory.getComponent(componentID), 1);
		String productID = ProductInventory.addProduct("name", 2, recipe, 5);
		products.put(ProductInventory.getProduct(productID), 1);
		String orderID = OrderInventory.createOrder(products, CompletionStatus.INCOMPLETE);
		assertNotNull(orderID);
	}
	
	@Test
	void testWhenProductIsNotInInventory() {
		Map<Product, Integer> products = new HashMap<>();
		Map<Component, Integer> recipe = new HashMap<>();
		String componentID = ComponentInventory.addComponent("name", 2, 5);
		recipe.put(ComponentInventory.getComponent(componentID), 1);
		String productID = ProductInventory.addProduct("name", 2, recipe, 5);
		products.put(ProductInventory.getProduct(productID), 1);
		ProductInventory.deleteProduct(productID);
		assertThrows(IllegalArgumentException.class, () -> {
			OrderInventory.createOrder(products, CompletionStatus.INCOMPLETE);
		});
	}
	

}
