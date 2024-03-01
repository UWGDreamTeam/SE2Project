package edu.westga.cs3212.inventory_manager.test.inventoryviewmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Product;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;
import edu.westga.cs3212.inventory_manager.viewmodel.InventoryViewModel;

public class TestProduceProduct {

	@Test
	void testProduceProduct() {
		LocalComponentInventory localComponentInventory = new LocalComponentInventory();
		LocalProductInventory localProductInventory = new LocalProductInventory();
		InventoryViewModel testInventoryViewModel = new InventoryViewModel(localComponentInventory, localProductInventory);
		Map<Component, Integer> components = new HashMap<>();
		Component component = new Component("TestComponent", 1);
		components.put(component, 1);
		testInventoryViewModel.setSelectedComponent(component);
		testInventoryViewModel.removeComponent();
		localComponentInventory.addItem(component, 30);
		testInventoryViewModel.orderComponent(component, 50);
		Product product = new Product("TestProduct", 2, 2, components);
		testInventoryViewModel.setSelectedProduct(product);
		testInventoryViewModel.removeProduct();
		localProductInventory.addItem(product, 20);
		testInventoryViewModel.produceProduct(product, 30);
		assertEquals(50, localProductInventory.getQuantityOfItem(product));
	}
}
