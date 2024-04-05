package edu.westga.cs3212.inventory_manager.test.inventoryviewmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.server.warehouse.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.ProductInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Product;
import edu.westga.cs3212.inventory_manager.viewmodel.inventory.InventoryViewModel;

class TestGetObservableProductsList {

	private InventoryViewModel inventoryVM;

	@BeforeEach
	public void setUp() {
		this.inventoryVM = new InventoryViewModel();
	}

	@Test
	void testGetObservableProductsListEmpty() {
		ProductInventory.clearInventory();

		assertEquals(0, this.inventoryVM.getObservableProductList().size());
	}

	@Test
	void testGetObservableProductsListOneProduct() {
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
		
		assertEquals(1, this.inventoryVM.getObservableProductList().size());
	}
}
