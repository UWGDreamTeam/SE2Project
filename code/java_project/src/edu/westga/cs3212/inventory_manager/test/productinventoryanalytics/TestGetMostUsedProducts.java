package edu.westga.cs3212.inventory_manager.test.productinventoryanalytics;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Order;
import edu.westga.cs3212.inventory_manager.model.Product;
import edu.westga.cs3212.inventory_manager.model.ProductInventoryAnalytics;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalOrderManager;

class TestGetMostUsedProducts {
	
	private LocalOrderManager orderManager;
    private ProductInventoryAnalytics analytics;
    
    @BeforeEach
    void setUp() {
        this.orderManager = new LocalOrderManager();
        this.analytics = new ProductInventoryAnalytics();

	    Component componentA = new Component("ComponentA", 1.0);
	    Component componentB = new Component("ComponentB", 2.0);
	    Component componentC = new Component("ComponentC", 3.0);
	    
	    Map<Component, Integer> recipe1 = new HashMap<>();
	    recipe1.put(componentA, 10);
	    recipe1.put(componentB, 5);

	    Map<Component, Integer> recipe2 = new HashMap<>();
	    recipe2.put(componentA, 15);
	    recipe2.put(componentC, 20);
	    
	    Product product1 = new Product("Product1", 100.0, 150.0, recipe1);
	    Product product2 = new Product("Product2", 200.0, 250.0, recipe2);

        Order order1 = new Order();
        order1.addItem(product1, 5);
        Order order2 = new Order();
        order2.addItem(product2, 3);
        
        this.orderManager.addOrder(order1);
        this.orderManager.addOrder(order2);
    }

	@Test
	public void testGetMostUsedProducts_NegativeListSize_ThrowsException() {
	    ProductInventoryAnalytics analytics = new ProductInventoryAnalytics();
	    assertThrows(IllegalArgumentException.class, () -> analytics.getMostUsedProducts(-1));
	}

	@Test
	public void testGetMostUsedProducts_ZeroListSize_ReturnsEmptyMap() {
	    ProductInventoryAnalytics analytics = new ProductInventoryAnalytics();
	    assertTrue(analytics.getMostUsedProducts(0).isEmpty());
	}

    @Test
    public void testGetMostUsedProducts_PositiveListSize_ReturnsNonEmptyMap() {
        Map<Product, Integer> mostUsedProducts = this.analytics.getMostUsedProducts(2);
        assertFalse(mostUsedProducts.isEmpty(), "Map should not be empty when list size is positive");
    }

}
