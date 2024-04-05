package edu.westga.cs3212.inventory_manager.test.serverproductinventory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.server.warehouse.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.ProductInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Product;

class TestSearchProducts {

	@BeforeEach
	void setUp() throws Exception {
		ProductInventory.clearInventory();
	}
	
	@Test
	void testSearchProductsOne() {
		String componentID = ComponentInventory.addComponent("Component1", 1.0, 1);
		Component component = ComponentInventory.getComponent(componentID);
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(component, 1);
		String productID = ProductInventory.addProduct("Product1", 2.0, recipe, 1);
		Product product = ProductInventory.getProduct(productID);
		
		List<Product> products = new ArrayList<>();
		products = ProductInventory.searchProducts("Product1");
		assertEquals(1, products.size());
	}
	
	@Test
	void testSearchProductsMultiple() {
		String componentID = ComponentInventory.addComponent("Component1", 1.0, 1);
		Component component = ComponentInventory.getComponent(componentID);
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(component, 1);
		ProductInventory.addProduct("Product1", 2.0, recipe, 1);
		
		String componentID2 = ComponentInventory.addComponent("Component2", 1.0, 1);
		Component component2 = ComponentInventory.getComponent(componentID2);
		Map<Component, Integer> recipe2 = new HashMap<>();
		recipe2.put(component2, 1);
		ProductInventory.addProduct("Product2", 2.0, recipe2, 1);
		
		String componentID3 = ComponentInventory.addComponent("Component3", 1.0, 1);
		Component component3 = ComponentInventory.getComponent(componentID3);
		Map<Component, Integer> recipe3 = new HashMap<>();
		recipe3.put(component3, 1);
		ProductInventory.addProduct("Product3", 2.0, recipe3, 1);
		
		List<Product> products = new ArrayList<>();
		products = ProductInventory.searchProducts("Product");
		assertEquals(3, products.size());
	}
	
	@Test
	void testSearchProductsMultipleLast() {
		String componentID = ComponentInventory.addComponent("Component1", 1.0, 1);
		Component component = ComponentInventory.getComponent(componentID);
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(component, 1);
		ProductInventory.addProduct("Product1", 2.0, recipe, 1);
		
		String componentID2 = ComponentInventory.addComponent("Component2", 1.0, 1);
		Component component2 = ComponentInventory.getComponent(componentID2);
		Map<Component, Integer> recipe2 = new HashMap<>();
		recipe2.put(component2, 1);
		ProductInventory.addProduct("Product2", 2.0, recipe2, 1);
		
		String componentID3 = ComponentInventory.addComponent("Component3", 1.0, 1);
		Component component3 = ComponentInventory.getComponent(componentID3);
		Map<Component, Integer> recipe3 = new HashMap<>();
		recipe3.put(component3, 1);
		ProductInventory.addProduct("Dry Wall", 2.0, recipe3, 1);
		
		List<Product> products = new ArrayList<>();
		products = ProductInventory.searchProducts("Wall");
		assertEquals(1, products.size());
	}
	
	@Test
	void testSearchProductsEmpty() {
		List<Product> products = ProductInventory.searchProducts("Component1");
		assertEquals(0, products.size());
	}
}
