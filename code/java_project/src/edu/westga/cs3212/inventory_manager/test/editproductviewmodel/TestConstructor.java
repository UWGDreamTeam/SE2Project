package edu.westga.cs3212.inventory_manager.test.editproductviewmodel;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;
import edu.westga.cs3212.inventory_manager.viewmodel.EditProductViewModel;

public class TestConstructor {

	@Test
	void testValidConstructor() {
		LocalProductInventory inventory = new LocalProductInventory();
		LocalComponentInventory componentInventory = new LocalComponentInventory();
		EditProductViewModel viewModel = new EditProductViewModel(inventory, componentInventory);
		assertNotNull(viewModel);
		assertNotNull(viewModel.getName());
		assertNotNull(viewModel.getProductionCost());
		assertNotNull(viewModel.getQuantity());
		assertNotNull(viewModel.getSellingPrice());
		assertNotNull(viewModel.getObservableComponentList());
		assertNotNull(viewModel.getSelectedComponentProperty());
	}
}
