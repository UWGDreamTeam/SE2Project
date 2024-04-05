package edu.westga.cs3212.inventory_manager.test.productinventoryanalytics;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.analytics.ProductInventoryAnalytics;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalOrderManager;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.OrderInventory;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.ProductInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.CompletionStatus;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Order;
import edu.westga.cs3212.inventory_manager.model.warehouse.Product;

class TestGetMostUsedProducts {
    
    @BeforeEach
    void setUp() {
		OrderInventory.clearOrders();
		ProductInventory.clearInventory();
		ComponentInventory.clearInventory();
		
		String componentID = ComponentInventory.addComponent("test", 0, 10);
		Component component = ComponentInventory.getComponent(componentID);
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(component, 1);
		String productID = ProductInventory.addProduct("test", 2, recipe, 10);
		Product product = ProductInventory.getProduct(productID);
		Map<Product, Integer> orders = new HashMap<>();
		orders.put(product, 1);
		OrderInventory.createOrder(orders, CompletionStatus.COMPLETE);
		OrderInventory.createOrder(orders, CompletionStatus.INCOMPLETE);
    }

	@Test
	public void testGetMostUsedProducts_NegativeListSize_ThrowsException() {
	    assertThrows(IllegalArgumentException.class, () -> ProductInventoryAnalytics.getMostUsedProducts(-1));
	}

	@Test
	public void testGetMostUsedProducts_ZeroListSize_ReturnsEmptyMap() {
	    assertTrue(ProductInventoryAnalytics.getMostUsedProducts(0).isEmpty());
	}

    @Test
    public void testGetMostUsedProducts_PositiveListSize_ReturnsNonEmptyMap() {
        Map<Product, Integer> mostUsedProducts = ProductInventoryAnalytics.getMostUsedProducts(2);
        assertFalse(mostUsedProducts.isEmpty(), "Map should not be empty when list size is positive");
    }

}
