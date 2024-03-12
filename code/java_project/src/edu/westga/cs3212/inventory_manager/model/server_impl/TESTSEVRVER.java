package edu.westga.cs3212.inventory_manager.model.server_impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.CompletionStatus;
import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Order;
import edu.westga.cs3212.inventory_manager.model.Product;

class TESTSEVRVER {

	//Add
	//Delete
	//Update
	//Order/Produce
	//Get
	//Get Quantity
	
	
	@Test
	void testAddComponent() {
		String response = ComponentInventory.addComponent("testAddComponent", 1.0, 1);
        assertEquals(response.length(), 4);
	}
	
	@Test
	void testDeleteComponent() {
		String componentID = ComponentInventory.addComponent("testDeleteComponent", 1.0, 1);
		boolean response = ComponentInventory.deleteComponent(componentID);
		assertTrue(response);
	}
	
	@Test
	void testUpdateComponent() {
		String componentID = ComponentInventory.addComponent("testUpdateComponent", 1.0, 1);
		boolean response = ComponentInventory.updateComponent(componentID, "testUpdateComponent2", 2.0, 2);
		Component component = ComponentInventory.getComponent(componentID);
		assertTrue(response);
		assertEquals(component.getName(), "testUpdateComponent2");
		assertEquals(component.getProductionCost(), 2.0);
	}
	
	@Test
	void testOrderComponent() {
		String componentID = ComponentInventory.addComponent("testOrderComponent", 1.0, 1);
		int response = ComponentInventory.orderComponent(componentID, 2);
		assertEquals(response, 3);
	}
	
	@Test
	void testGetComponent() {
		String componentID = ComponentInventory.addComponent("testGetComponent", 1.0, 1);
		Component component = ComponentInventory.getComponent(componentID);
		assertEquals(component.getName(), "testGetComponent");
		assertEquals(component.getProductionCost(), 1.0);
	}
	
	@Test
	void testGetQuantityOfComponent() {
		String componentID = ComponentInventory.addComponent("testGetQuantityOfComponent", 1.0, 1);
		int quantity = ComponentInventory.getQuantity(componentID);
		assertEquals(quantity, 1);
	}
	
	
	/*
	 * Tests for product inventory
	 */
	
	@Test
	void testAddProduct() {
		Map<Component, Integer> recipe = new HashMap<>();
		Component component = new Component("testAddProduct", 1.0);
		String componentID =  ComponentInventory.addComponent(component.getName(), component.getProductionCost(), 1);
		component.setID(componentID);
		recipe.put(component, 1);
		String response = ProductInventory.addProduct("testAddProduct", 5, recipe, 1);
		assertEquals(response.length(), 4);
	}
	
	@Test
	void testGetProduct() {
		Map<Component, Integer> recipe = new HashMap<>();
		Component component = new Component("testGetProduct", 1.0);
		String componentID = ComponentInventory.addComponent(component.getName(), component.getProductionCost(), 1);
		component.setID(componentID);
		recipe.put(component, 1);
		String productID = ProductInventory.addProduct("testGetProduct", 5, recipe, 1);
		Product product = ProductInventory.getProduct(productID);
		assertEquals(product.getName(), "testGetProduct");
		assertEquals(product.getSalePrice(), 5);
	}
	
	@Test
	void testProduceProduct() {
		Map<Component, Integer> recipe = new HashMap<>();
		Component component = new Component("testProduceProduct", 1.0);
		String componentID = ComponentInventory.addComponent(component.getName(), component.getProductionCost(), 1);
		component.setID(componentID);
		recipe.put(component, 1);
		String productID = ProductInventory.addProduct("testProduceProduct", 5, recipe, 1);
		int response = ProductInventory.produceProduct(productID, 1);
		assertEquals(response, 2);
	}
	
	@Test
	void testDeleteProduct() {
		Map<Component, Integer> recipe = new HashMap<>();
		Component component = new Component("testDeleteProduct", 1.0);
		String componentID = ComponentInventory.addComponent(component.getName(), component.getProductionCost(), 1);
		component.setID(componentID);
		recipe.put(component, 1);
		String productID = ProductInventory.addProduct("testDeleteProduct", 5, recipe, 1);
		boolean response = ProductInventory.deleteProduct(productID);
		assertTrue(response);
	}
	
	@Test
	void testUpdateProduct() {
		Map<Component, Integer> recipe = new HashMap<>();
		Component component = new Component("testUpdateProduct", 1.0);
		String componentID = ComponentInventory.addComponent(component.getName(), component.getProductionCost(), 1);
		component.setID(componentID);
		recipe.put(component, 1);
		String productID = ProductInventory.addProduct("testUpdateProduct", 5, recipe, 1);
		boolean response = ProductInventory.updateProduct(productID, "testUpdateProduct2", 6, recipe, 2);
		Product product = ProductInventory.getProduct(productID);
		assertTrue(response);
		assertEquals(product.getName(), "testUpdateProduct2");
		assertEquals(product.getSalePrice(), 6);
	}
	
	
	/*
	 * Tests for order inventory
	 */
	
	@Test
	void testAddOrder() {
		Map<Component, Integer> recipe = new HashMap<>();
        Component component = new Component("testAddOrder", 1.0);
        String componentID = ComponentInventory.addComponent(component.getName(), component.getProductionCost(), 1);
        component.setID(componentID);
        recipe.put(component, 1);
        String productID = ProductInventory.addProduct("testAddOrder", 5, recipe, 1);
        Product product = ProductInventory.getProduct(productID);
        Map<Product, Integer> products = new HashMap<>();
        products.put(product, 1);
        String response = OrderInventory.createOrder(products, CompletionStatus.INCOMPLETE);
        assertEquals(response.length(), 4);
	}
	
	@Test
	void testDeleteOrder() {
		Map<Component, Integer> recipe = new HashMap<>();
		Component component = new Component("testDeleteOrder", 1.0);
		String componentID = ComponentInventory.addComponent(component.getName(), component.getProductionCost(), 1);
		component.setID(componentID);
		recipe.put(component, 1);
		String productID = ProductInventory.addProduct("testDeleteOrder", 5, recipe, 1);
		Product product = ProductInventory.getProduct(productID);
		Map<Product, Integer> products = new HashMap<>();
		products.put(product, 1);
		String orderID = OrderInventory.createOrder(products, CompletionStatus.INCOMPLETE);
		boolean response = OrderInventory.deleteOrder(orderID);
		assertTrue(response);
	}
	
	@Test
	void testUpdateOrder() {
		Map<Component, Integer> recipe = new HashMap<>();
		Component component = new Component("testUpdateOrder", 1.0);
		String componentID = ComponentInventory.addComponent(component.getName(), component.getProductionCost(), 1);
		component.setID(componentID);
		recipe.put(component, 1);
		String productID = ProductInventory.addProduct("testUpdateOrder", 5, recipe, 1);
		Product product = ProductInventory.getProduct(productID);
		Map<Product, Integer> products = new HashMap<>();
		products.put(product, 1);
		String orderID = OrderInventory.createOrder(products, CompletionStatus.INCOMPLETE);
		boolean response = OrderInventory.updateOrder(orderID, products, CompletionStatus.COMPLETE);
		assertTrue(response);
	}
	
	@Test
	void testGetOrder() {
		Map<Component, Integer> recipe = new HashMap<>();
		Component component = new Component("testGetOrder", 1.0);
		String componentID = ComponentInventory.addComponent(component.getName(), component.getProductionCost(), 1);
		component.setID(componentID);
		recipe.put(component, 1);
		String productID = ProductInventory.addProduct("testGetOrder", 5, recipe, 1);
		Product product = ProductInventory.getProduct(productID);
		Map<Product, Integer> products = new HashMap<>();
		products.put(product, 1);
		String orderID = OrderInventory.createOrder(products, CompletionStatus.INCOMPLETE);
		Order response = OrderInventory.getOrder(orderID);
		assert(response.getID().equals(orderID));
		assert(response.getCompletionStatus().equals(CompletionStatus.INCOMPLETE));
		assert(response.getItems().size() == 1);
	}
}
