package edu.westga.cs3212.inventory_manager.test.inventoryviewmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.server.warehouse.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.ProductInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Product;
import edu.westga.cs3212.inventory_manager.viewmodel.inventory.InventoryViewModel;

public class TestProduceProduct {
	
	private InventoryViewModel inventoryVM;
	
	@BeforeEach
	void setup() {
		this.inventoryVM = new InventoryViewModel();
		ComponentInventory.clearInventory();
		ProductInventory.clearInventory();
	}

	@Test
	void testProduceProduct() {
		Map<Component, Integer> recipe = new HashMap<>();
		Component component = new Component("TestComponent", 1);
		String componentID = ComponentInventory.addComponent("TestComponent", 1, 0);
		component.setID(componentID);
		this.inventoryVM.setSelectedComponent(component);
		recipe.put(component, 1);
		this.inventoryVM.orderComponent(component, 50);
		Product product = new Product("TestProduct", 2, 2, recipe);
		String productID = ProductInventory.addProduct("TestProduct", 2, recipe, 0);
		product.setID(productID);
		this.inventoryVM.setSelectedProduct(product);
		this.inventoryVM.produceProduct(product, 30);
		assertEquals(30, ProductInventory.getQuantity(productID));
		assertEquals(20, ComponentInventory.getQuantity(componentID));
		assertTrue(this.inventoryVM.isManager());
	}
	
	@Test
    void testOrderComponentWithNullComponent() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.inventoryVM.orderComponent(null, 10);
        });
    }

    @Test
    void testOrderComponentWithNegativeQuantity() {
        Component component = new Component("TestComponent", 1);
        assertThrows(IllegalArgumentException.class, () -> {
            this.inventoryVM.orderComponent(component, -10);
        });
    }
}
