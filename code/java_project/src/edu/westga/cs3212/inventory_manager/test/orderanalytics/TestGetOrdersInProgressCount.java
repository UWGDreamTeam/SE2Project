package edu.westga.cs3212.inventory_manager.test.orderanalytics;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.CompletionStatus;
import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.OrderAnalytics;
import edu.westga.cs3212.inventory_manager.model.Product;
import edu.westga.cs3212.inventory_manager.model.server_impl.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server_impl.OrderInventory;
import edu.westga.cs3212.inventory_manager.model.server_impl.ProductInventory;

class TestGetOrdersInProgressCount {
	
	@BeforeEach
	void setUp() throws Exception {
		OrderInventory.clearOrders();
		ProductInventory.clearInventory();
		ComponentInventory.clearInventory();
	}

	@Test
	void testWithNoOrders() {
		OrderAnalytics testOrderAnalytics = new OrderAnalytics();
		testOrderAnalytics.clearOrders();
		
		assertTrue(testOrderAnalytics.getOrdersInProgressCount() == 0);
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
		OrderAnalytics testOrderAnalytics = new OrderAnalytics();
		assertTrue(testOrderAnalytics.getOrdersInProgressCount() == 1);
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
		OrderAnalytics testOrderAnalytics = new OrderAnalytics();
		assertTrue(testOrderAnalytics.getOrdersInProgressCount() == 0);
	}
	
	@Test
	void testWithTwoOrdersIncomplete() {
		String componentID = ComponentInventory.addComponent("test", 1.0, 10);
		Component component = ComponentInventory.getComponent(componentID);
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(component, 1);
		String productID = ProductInventory.addProduct("test", 1.0, recipe, 10);
		Product product = ProductInventory.getProduct(productID);
		Map<Product, Integer> orders = new HashMap<>();
		orders.put(product, 1);
		OrderInventory.createOrder(orders, CompletionStatus.INCOMPLETE);
		OrderInventory.createOrder(orders, CompletionStatus.INCOMPLETE);
		
		OrderAnalytics testOrderAnalytics = new OrderAnalytics();
		assertTrue(testOrderAnalytics.getOrdersInProgressCount() == 2);
	}

}
