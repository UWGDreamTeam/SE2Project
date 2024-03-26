package edu.westga.cs3212.inventory_manager.test.serverproductinventory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.server_impl.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server_impl.ProductInventory;

class TestGetQuantity {

    @Test
    void testWhenProductIDIsNull() {
	assertThrows(IllegalArgumentException.class, () -> {
	    ProductInventory.getQuantity(null);
	});
    }

    @Test
    void testWhenProductIDIsBlank() {
	assertThrows(IllegalArgumentException.class, () -> {
	    ProductInventory.getQuantity("");
	});
    }

    @Test
    void testWhenProductIsNotInInventory() {
	assertThrows(IllegalArgumentException.class, () -> {
	    ProductInventory.getQuantity("1");
	});
    }

    @Test
    void testWhenProductIsInInventory() {
	String componentID = ComponentInventory.addComponent("TestGetQuantityComponent", 1.0, 1);
	Component component = ComponentInventory.getComponent(componentID);
	Map<Component, Integer> recipe = new HashMap<>();
	recipe.put(component, 1);
	String productID = ProductInventory.addProduct("TestGetQuantityProduct", 2.0, recipe, 1);
	int quantity = ProductInventory.getQuantity(productID);
	assert (quantity == 1);
    }

}
