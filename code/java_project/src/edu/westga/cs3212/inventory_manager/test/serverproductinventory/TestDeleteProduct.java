package edu.westga.cs3212.inventory_manager.test.serverproductinventory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.server.warehouse.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.ProductInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;

class TestDeleteProduct {

    @Test
    void testWhenProductIDIsNull() {
	assertThrows(IllegalArgumentException.class, () -> {
	    ProductInventory.deleteProduct(null);
	});
    }

    @Test
    void testWhenProductIDIsBlank() {
	assertThrows(IllegalArgumentException.class, () -> {
	    ProductInventory.deleteProduct("");
	});
    }

    @Test
    void testWhenProductIsInInventory() {
	Map<Component, Integer> recipe = new HashMap<>();
	String componentID = ComponentInventory.addComponent("Test Component", 10.0, 1);
	Component component = ComponentInventory.getComponent(componentID);
	recipe.put(component, 1);
	String productID = ProductInventory.addProduct("Test Product", 10.0, recipe, 1);
	assertDoesNotThrow(() -> {
        ProductInventory.deleteProduct(productID);
        });
    }

    @Test
    void testWhenProductIsNotInInventory() {
	assertThrows(IllegalArgumentException.class, () -> {
	    ProductInventory.deleteProduct("Test Product");
	});
    }

}
