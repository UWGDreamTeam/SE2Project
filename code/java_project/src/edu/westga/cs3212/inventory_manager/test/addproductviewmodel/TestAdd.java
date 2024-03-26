package edu.westga.cs3212.inventory_manager.test.addproductviewmodel;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;
import edu.westga.cs3212.inventory_manager.viewmodel.AddProductViewModel;

public class TestAdd {

	@Test
	void testValidAdd() {
		LocalComponentInventory componentInventory = new LocalComponentInventory();
		LocalProductInventory productInventory = new LocalProductInventory();
		AddProductViewModel testViewModel = new AddProductViewModel(productInventory);
		testViewModel.getName().set("Test Product");
		testViewModel.getProductionCost().set("10.00");
		testViewModel.getSellingPrice().set("20.00");
		testViewModel.getQuantity().set("5");
		Component testComponent = new Component("Test Component", 5);
		Map<Component, Integer> testMap = Map.of(testComponent, 5);
		testViewModel.addProduct(testMap);
	}
	
	@Test
	void testInvalidAdd() {
		LocalComponentInventory componentInventory = new LocalComponentInventory();
		LocalProductInventory productInventory = new LocalProductInventory();
		AddProductViewModel testViewModel = new AddProductViewModel(productInventory);
		testViewModel.getName().set("Test Product");
		testViewModel.getProductionCost().set("10.00");
		testViewModel.getSellingPrice().set("20.00");
		testViewModel.getQuantity().set("5");
		assertThrows(IllegalArgumentException.class, () -> testViewModel.addProduct(null));
	}
}
