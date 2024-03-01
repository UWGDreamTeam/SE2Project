package edu.westga.cs3212.inventory_manager.test.inventoryviewmodel;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;
import edu.westga.cs3212.inventory_manager.viewmodel.InventoryViewModel;

public class TestConstructor {

	@Test
	void testConstructor() {
		LocalComponentInventory localComponentInventory = new LocalComponentInventory();
		LocalProductInventory localProductInventory = new LocalProductInventory();
		InventoryViewModel testInventoryViewModel = new InventoryViewModel(localComponentInventory, localProductInventory);
		assertNotNull(testInventoryViewModel.getComponentsInventory());
		assertNotNull(testInventoryViewModel.getProductInventory());
		assertNotNull(testInventoryViewModel.getSelectedComponent());
		assertNotNull(testInventoryViewModel.getSelectedProduct());
		assertNotNull(testInventoryViewModel.getObservableComponentList());
		assertNotNull(testInventoryViewModel.getObservableProductList());	
	}
}
