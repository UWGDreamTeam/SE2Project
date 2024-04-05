package edu.westga.cs3212.inventory_manager.test.inventoryviewmodel;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;
import edu.westga.cs3212.inventory_manager.viewmodel.inventory.InventoryViewModel;

public class TestConstructor {

	@Test
	void testConstructor() {
		InventoryViewModel testInventoryViewModel = new InventoryViewModel();
		assertNotNull(testInventoryViewModel.getSelectedComponent());
		assertNotNull(testInventoryViewModel.getSelectedProduct());
		assertNotNull(testInventoryViewModel.getObservableComponentList());
		assertNotNull(testInventoryViewModel.getObservableProductList());	
	}
}
