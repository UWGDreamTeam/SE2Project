package edu.westga.cs3212.inventory_manager.test.orderanalytics;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.analytics.OrderAnalytics;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.OrderInventory;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.ProductInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.CompletionStatus;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Product;

class TestGetOrdersCompletedCount {

	@BeforeEach
	void setUp() throws Exception {
		OrderInventory.clearOrders();
		ProductInventory.clearInventory();
		ComponentInventory.clearInventory();
	}
	
	@Test
	void testWithNoOrders() {
		OrderAnalytics.clearOrders();
		
		assertTrue(OrderAnalytics.getOrdersCompletedCount() == 0);
	}
	
	@Test
	void testWithOneOrderCompleted() {
		String componentID = ComponentInventory.addComponent("test", 1.0, 10);
		Component component = ComponentInventory.getComponent(componentID);
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(component, 1);
		String productID = ProductInventory.addProduct("test", 1.0, recipe, 10);
		Product product = ProductInventory.getProduct(productID);
		Map<Product, Integer> orders = new HashMap<>();
		orders.put(product, 1);
		OrderInventory.createOrder(orders, CompletionStatus.COMPLETE);
		
		assertTrue(OrderAnalytics.getOrdersCompletedCount() == 1);
	}
	
	@Test
	void testWithOneOrderIncompleted() {
		String componentID = ComponentInventory.addComponent("test", 1.0, 10);
		Component component = ComponentInventory.getComponent(componentID);
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(component, 1);
		String productID = ProductInventory.addProduct("test", 1.0, recipe, 10);
		Product product = ProductInventory.getProduct(productID);
		Map<Product, Integer> orders = new HashMap<>();
		orders.put(product, 1);
		OrderInventory.createOrder(orders, CompletionStatus.INCOMPLETE);
		assertTrue(OrderAnalytics.getOrdersCompletedCount() == 0);
	}
	
	@Test
	void testWithTwoOrdersComplete() {
		String componentID = ComponentInventory.addComponent("test", 1.0, 10);
		Component component = ComponentInventory.getComponent(componentID);
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(component, 1);
		String productID = ProductInventory.addProduct("test", 1.0, recipe, 10);
		Product product = ProductInventory.getProduct(productID);
		Map<Product, Integer> orders = new HashMap<>();
		orders.put(product, 1);
		OrderInventory.createOrder(orders, CompletionStatus.COMPLETE);
		OrderInventory.createOrder(orders, CompletionStatus.COMPLETE);
		assertTrue(OrderAnalytics.getOrdersCompletedCount() == 2);
	}

}
