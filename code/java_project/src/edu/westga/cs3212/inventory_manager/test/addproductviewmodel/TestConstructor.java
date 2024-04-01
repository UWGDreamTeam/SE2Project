package edu.westga.cs3212.inventory_manager.test.addproductviewmodel;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;
import edu.westga.cs3212.inventory_manager.viewmodel.AddProductViewModel;

public class TestConstructor {

	@Test
	void testConstructor() {
		AddProductViewModel testViewModel = new AddProductViewModel();
		assertNotNull(testViewModel);
		assertNotNull(testViewModel.getSelectedComponentProperty());
		assertNotNull(testViewModel.getObservableComponentList());
		assertNotNull(testViewModel.getName());
		assertNotNull(testViewModel.getProductionCost());
		assertNotNull(testViewModel.getSellingPrice());
		assertNotNull(testViewModel.getQuantity());
	}
}
