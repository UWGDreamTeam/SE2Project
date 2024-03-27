package edu.westga.cs3212.inventory_manager.test.inventoryviewmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Product;
import edu.westga.cs3212.inventory_manager.model.server_impl.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server_impl.ProductInventory;
import edu.westga.cs3212.inventory_manager.viewmodel.InventoryViewModel;

public class TestProduceProduct {
	
	@BeforeEach
	void setup() {
		ComponentInventory.clearInventory();
		ProductInventory.clearInventory();
	}

	@Test
	void testProduceProduct() {
		InventoryViewModel testInventoryViewModel = new InventoryViewModel();
		Map<Component, Integer> recipe = new HashMap<>();
		Component component = new Component("TestComponent", 1);
		String componentID = ComponentInventory.addComponent("TestComponent", 1, 0);
		component.setID(componentID);
		testInventoryViewModel.setSelectedComponent(component);
		recipe.put(component, 1);
		testInventoryViewModel.orderComponent(component, 50);
		Product product = new Product("TestProduct", 2, 2, recipe);
		String productID = ProductInventory.addProduct("TestProduct", 2, recipe, 0);
		product.setID(productID);
		testInventoryViewModel.setSelectedProduct(product);
		testInventoryViewModel.produceProduct(product, 30);
		assertEquals(30, ProductInventory.getQuantity(productID));
		assertEquals(20, ComponentInventory.getQuantity(componentID));
	}
}
