package edu.westga.cs3212.inventory_manager.test.inventoryviewmodel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.server.warehouse.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.OrderInventory;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.ProductInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Product;
import edu.westga.cs3212.inventory_manager.viewmodel.inventory.InventoryViewModel;

class TestRemoveProduct {

	private InventoryViewModel inventoryVM;

    @BeforeEach
    public void setUp() {
        this.inventoryVM = new InventoryViewModel();
        ProductInventory.clearInventory();
        ComponentInventory.clearInventory();
        OrderInventory.clearOrders();
    }

    @Test
    void testRemoveProductComponentInUse() {
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
		this.inventoryVM.orderComponent(null, 50);
		assertEquals(1, 1);
    }

    @Test
    void testRemoveProductComponentNotInUse() {
    	Map<Component, Integer> recipe = new HashMap<>();
		Component component = new Component("TestComponent", 1);
		String componentID = ComponentInventory.addComponent("TestComponent", 1, 0);
		component.setID(componentID);
		Component component2 = new Component("TestComponent2", 1);
		this.inventoryVM.setSelectedComponent(component2);
		recipe.put(component, 1);
		this.inventoryVM.orderComponent(component, 50);
		Product product = new Product("TestProduct", 2, 2, recipe);
		String productID = ProductInventory.addProduct("TestProduct", 2, recipe, 0);
		product.setID(productID);
        this.inventoryVM.setSelectedProduct(product);

        this.inventoryVM.removeProduct();
        assertThrows(IllegalArgumentException.class, () -> {
        	ProductInventory.getProduct(productID);
        });
    }
    
    @Test
    void testRemoveComponent() {
    	Map<Component, Integer> recipe = new HashMap<>();
		Component component = new Component("TestComponent", 1);
		String componentID = ComponentInventory.addComponent("TestComponent", 1, 0);
		component.setID(componentID);
		this.inventoryVM.setSelectedComponent(component);
    	
		this.inventoryVM.removeComponent();
    	assertThrows(IllegalArgumentException.class, () -> {
        	ComponentInventory.getComponent(componentID);
        });
    }
}