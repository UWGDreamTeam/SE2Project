package edu.westga.cs3212.inventory_manager.test.componentinventoryanalytics;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.ComponentInventoryAnalytics;
import edu.westga.cs3212.inventory_manager.model.Product;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;
import edu.westga.cs3212.inventory_manager.model.server_impl.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server_impl.ProductInventory;

class TestGetMostUsedComponents {
	
	@BeforeEach
	void setup() {
		ProductInventory.clearInventory();
		ComponentInventory.clearInventory();
	}

	@Test
	void testWhenListSizeIsNegative() {
		ComponentInventoryAnalytics testComponentInventoryAnalytics = new ComponentInventoryAnalytics();
		assertThrows(IllegalArgumentException.class, () -> testComponentInventoryAnalytics.getMostUsedComponents(-1));
	}
	
	@Test
	void testWhenListSizeIsZero() {
		ComponentInventoryAnalytics testComponentInventoryAnalytics = new ComponentInventoryAnalytics();
		assertEquals(0, testComponentInventoryAnalytics.getMostUsedComponents(0).size());
	}
	
	@Test
	void testWhenListSizeIsOne() {
		ComponentInventoryAnalytics testComponentInventoryAnalytics = new ComponentInventoryAnalytics();
		Map<Component, Integer> testMap = testComponentInventoryAnalytics.getMostUsedComponents(1);
		assertNotNull(testMap);
	}
	
	@Test
	void testWhenInventoryIsEmpty() {
		ComponentInventoryAnalytics testComponentInventoryAnalytics = new ComponentInventoryAnalytics();
		assertEquals(0, testComponentInventoryAnalytics.getMostUsedComponents(1).size());
	}
	
	@Test
	void testWhenInventoryHasOneItem() {
		ComponentInventoryAnalytics testComponentInventoryAnalytics = new ComponentInventoryAnalytics();
		
		assertEquals(0, testComponentInventoryAnalytics.getMostUsedComponents(1).size());
	}
	
	@Test
	void testComponentsAreSortedByUsageInDescendingOrder() {
	    ComponentInventoryAnalytics testComponentInventoryAnalytics = initializeInventoryWithSampleData();
	    
	    Map<Component, Integer> testMap = testComponentInventoryAnalytics.getMostUsedComponents(3);
	    
	    // Verify the order is maintained
	    assertEquals(3, testMap.size());
	    List<Map.Entry<Component, Integer>> entryList = new ArrayList<>(testMap.entrySet());
	    
	    // Asserting the order is as expected based on usage
	    assertEquals("ComponentA", entryList.get(0).getKey().getName()); // Component A used 25 times
	    assertEquals("ComponentC", entryList.get(1).getKey().getName()); // Component C used 20 times
	    assertEquals("ComponentB", entryList.get(2).getKey().getName()); // Component B used 5 times
	}
	
	private ComponentInventoryAnalytics initializeInventoryWithSampleData() {
	    ComponentInventoryAnalytics analytics = new ComponentInventoryAnalytics();

	    // Create components
	    Component componentA = new Component("ComponentA", 1.0);
	    String componentID = ComponentInventory.addComponent("ComponentA", 1.0, 50);
	    componentA.setID(componentID);
	    Component componentB = new Component("ComponentB", 2.0);
	    componentID = ComponentInventory.addComponent("ComponentB", 2.0, 50);
	    componentB.setID(componentID);
	    Component componentC = new Component("ComponentC", 3.0);
	    componentID = ComponentInventory.addComponent("ComponentC", 3.0, 50);
	    componentC.setID(componentID);
	    
	    // Create product recipes
	    Map<Component, Integer> recipe1 = new HashMap<>();
	    recipe1.put(componentA, 10); // Component A used 10 times
	    recipe1.put(componentB, 5);  // Component B used 5 times

	    Map<Component, Integer> recipe2 = new HashMap<>();
	    recipe2.put(componentA, 15); // Component A used 15 more times, 25 total
	    recipe2.put(componentC, 20); // Component C used 20 times
	    
	    // Create products with these recipes
	    Product product1 = new Product("Product1", 100.0, 150.0, recipe1);
	    String productID = ProductInventory.addProduct("Product1", 100.0, recipe1, 150);
	    product1.setID(productID);
	    Product product2 = new Product("Product2", 200.0, 250.0, recipe2);
	    productID = ProductInventory.addProduct("Product2", 200.0, recipe2, 250);
	    product2.setID(productID);
	    
	    return analytics;
	}

}
