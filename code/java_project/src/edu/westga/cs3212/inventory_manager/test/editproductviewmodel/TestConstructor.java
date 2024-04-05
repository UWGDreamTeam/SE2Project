package edu.westga.cs3212.inventory_manager.test.editproductviewmodel;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;
import edu.westga.cs3212.inventory_manager.viewmodel.inventory.product.EditProductViewModel;

public class TestConstructor {

	@Test
	void testValidConstructor() {
		EditProductViewModel viewModel = new EditProductViewModel();
		assertNotNull(viewModel);
		assertNotNull(viewModel.getName());
		assertNotNull(viewModel.getProductionCost());
		assertNotNull(viewModel.getQuantity());
		assertNotNull(viewModel.getSellingPrice());
		assertNotNull(viewModel.getObservableComponentList());
		assertNotNull(viewModel.getSelectedComponentProperty());
	}
}
