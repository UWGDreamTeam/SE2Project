package edu.westga.cs3212.inventory_manager.test.serverorderinventory;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.CompletionStatus;
import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Order;
import edu.westga.cs3212.inventory_manager.model.Product;
import edu.westga.cs3212.inventory_manager.model.server_impl.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server_impl.OrderInventory;
import edu.westga.cs3212.inventory_manager.model.server_impl.ProductInventory;

class TestGetOrder {

    @Test
    void testWhenOrderIDIsNull() {
	assertThrows(IllegalArgumentException.class, () -> {
	    OrderInventory.getOrder(null);
	});
    }

    @Test
    void testWhenOrderIDIsBlank() {
	assertThrows(IllegalArgumentException.class, () -> {
	    OrderInventory.getOrder("");
	});
    }
    
    @Test
    void testWhenOrderIDIsInvalid() {
	assertThrows(IllegalArgumentException.class, () -> {
        OrderInventory.getOrder("Invalid");
        });
    }
    
    @Test
    void testWhenOrderIDIsValid() {
	Map<Product, Integer> products = new HashMap<>();
	Map<Component, Integer> recipe = new HashMap<>();
	String componentID = ComponentInventory.addComponent("name", 2, 5);
	recipe.put(ComponentInventory.getComponent(componentID), 1);
	String productID = ProductInventory.addProduct("name", 2, recipe, 5);
	products.put(ProductInventory.getProduct(productID), 1);
	String orderID = OrderInventory.createOrder(products, CompletionStatus.INCOMPLETE);
	Order order = OrderInventory.getOrder(orderID);
	assert(order != null);
    }

}
