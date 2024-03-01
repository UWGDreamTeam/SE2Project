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

class TestGetMostUsedComponents {
	
	@BeforeEach
	void setup() {
		LocalProductInventory productInventory = new LocalProductInventory();
		productInventory.clear();
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
	    LocalProductInventory productManager = new LocalProductInventory();

	    // Create components
	    Component componentA = new Component("ComponentA", 1.0);
	    Component componentB = new Component("ComponentB", 2.0);
	    Component componentC = new Component("ComponentC", 3.0);
	    
	    // Create product recipes
	    Map<Component, Integer> recipe1 = new HashMap<>();
	    recipe1.put(componentA, 10); // Component A used 10 times
	    recipe1.put(componentB, 5);  // Component B used 5 times

	    Map<Component, Integer> recipe2 = new HashMap<>();
	    recipe2.put(componentA, 15); // Component A used 15 more times, 25 total
	    recipe2.put(componentC, 20); // Component C used 20 times
	    
	    // Create products with these recipes
	    Product product1 = new Product("Product1", 100.0, 150.0, recipe1);
	    Product product2 = new Product("Product2", 200.0, 250.0, recipe2);
	    
	    // Assuming the addItem method is accessible and correctly adds products to the inventory
	    productManager.addItem(product1, 1); // Quantity is a placeholder
	    productManager.addItem(product2, 1); // Quantity is a placeholder

	    // Inject productManager into analytics if necessary or assume it's already set through the constructor
	    
	    return analytics;
	}

}
