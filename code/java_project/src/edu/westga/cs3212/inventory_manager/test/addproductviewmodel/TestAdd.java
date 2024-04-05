package edu.westga.cs3212.inventory_manager.test.addproductviewmodel;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.ProductInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.viewmodel.inventory.product.AddProductViewModel;

public class TestAdd {
	
	@BeforeEach
	void setUp() {
		ComponentInventory.clearInventory();
		ProductInventory.clearInventory();
	}

	@Test
	void testValidAdd() {
		AddProductViewModel testViewModel = new AddProductViewModel();
		testViewModel.getName().set("Test Product");
		testViewModel.getProductionCost().set("10.00");
		testViewModel.getSellingPrice().set("20.00");
		testViewModel.getQuantity().set("5");
		Component testComponent = new Component("Test Component", 5);
		String componentID = ComponentInventory.addComponent("Test Component", 5, 0);
		testComponent.setID(componentID);
		Map<Component, Integer> testMap = Map.of(testComponent, 5);
		testViewModel.addProduct(testMap);
	}
	
	@Test
	void testInvalidAdd() {
		AddProductViewModel testViewModel = new AddProductViewModel();
		testViewModel.getName().set("Test Product");
		testViewModel.getProductionCost().set("10.00");
		testViewModel.getSellingPrice().set("20.00");
		testViewModel.getQuantity().set("5");
		assertThrows(IllegalArgumentException.class, () -> testViewModel.addProduct(null));
	}
}
