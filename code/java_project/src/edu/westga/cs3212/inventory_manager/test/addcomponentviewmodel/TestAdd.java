package edu.westga.cs3212.inventory_manager.test.addcomponentviewmodel;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.server_impl.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server_impl.ProductInventory;
import edu.westga.cs3212.inventory_manager.viewmodel.AddComponentViewModel;

public class TestAdd {

	@BeforeEach
	void setUp() {
		ComponentInventory.clearInventory();
		ProductInventory.clearInventory();
	}
	
	@Test
	void testValidAdd() {
		AddComponentViewModel testAddComponentViewModel = new AddComponentViewModel();
		testAddComponentViewModel.getName().set("TestName");
		testAddComponentViewModel.getCost().set("1.00");
		testAddComponentViewModel.getQuantity().set("1");
		assertTrue(testAddComponentViewModel.add());
	}
	
	@Test
	void testInvalidAdd() {
		AddComponentViewModel testAddComponentViewModel = new AddComponentViewModel();
		testAddComponentViewModel.getName().set(null);
		testAddComponentViewModel.getCost().set("1.00");
		testAddComponentViewModel.getQuantity().set("-50");
		assertThrows(IllegalArgumentException.class, () -> {
			testAddComponentViewModel.add();
		});
	}
	
	
}
