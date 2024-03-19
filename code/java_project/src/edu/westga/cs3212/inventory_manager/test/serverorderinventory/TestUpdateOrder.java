package edu.westga.cs3212.inventory_manager.test.serverorderinventory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.CompletionStatus;
import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Product;
import edu.westga.cs3212.inventory_manager.model.server_impl.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server_impl.OrderInventory;
import edu.westga.cs3212.inventory_manager.model.server_impl.ProductInventory;

class TestUpdateOrder {

    @Test
    void testWhenOrderIDIsNull() {
	        assertThrows(IllegalArgumentException.class, () -> {
            OrderInventory.updateOrder(null, null, CompletionStatus.INCOMPLETE);
        });
    }
    
    @Test
    void testWhenOrderIDIsBlank() {
	assertThrows(IllegalArgumentException.class, () -> {
	    OrderInventory.updateOrder("", null, CompletionStatus.INCOMPLETE);
	});
    }
    
    @Test
    void testWhenProductsIsNull() {
	assertThrows(IllegalArgumentException.class, () -> {
        OrderInventory.updateOrder("valid", null, CompletionStatus.INCOMPLETE);
        });
    }
    
    @Test
    void testWhenProductsIsEmpty() {
	Map<Product, Integer> products = new HashMap<>();
	assertThrows(IllegalArgumentException.class, () -> {
        OrderInventory.updateOrder("valid", products, CompletionStatus.INCOMPLETE);
        });
    }
    
    @Test
    void testWhenCompletionStatusIsNull() {
	Map<Product, Integer> products = new HashMap<>();
	Map<Component, Integer> components = new HashMap<>();
	components.put(new Component("name", 2), 1);
	products.put(new Product("name", 2, 5, components), 1);
	assertThrows(IllegalArgumentException.class, () -> {
        OrderInventory.updateOrder("valid", products, null);
        });
    }
    
    @Test
    void testWhenOrderDoesNotExist() {
	Map<Product, Integer> products = new HashMap<>();
	Map<Component, Integer> recipe = new HashMap<>();
	String componentID = ComponentInventory.addComponent("name", 2, 5);
	recipe.put(ComponentInventory.getComponent(componentID), 1);
	String productID = ProductInventory.addProduct("name", 2, recipe, 5);
	products.put(ProductInventory.getProduct(productID), 1);
	String orderID = OrderInventory.createOrder(products, CompletionStatus.INCOMPLETE);
	assertThrows(IllegalArgumentException.class, () -> {
	    OrderInventory.updateOrder("RattaRatta", products, CompletionStatus.COMPLETE);
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
	boolean result = OrderInventory.updateOrder(orderID, products, CompletionStatus.COMPLETE);
	assertTrue(result);
    }

}
