package edu.westga.cs3212.inventory_manager.test.editproductviewmodel;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Product;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;
import edu.westga.cs3212.inventory_manager.model.server_impl.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server_impl.ProductInventory;
import edu.westga.cs3212.inventory_manager.viewmodel.EditProductViewModel;

public class TestSetProduct {

	@Test
	void testSetProduct() {
		EditProductViewModel viewModel = new EditProductViewModel();
		Component component = new Component("test", 1.0);
		String componentID = ComponentInventory.addComponent("test", 1.0, 10);
		component.setID(componentID);
		Map<Component, Integer> recipe = new HashMap<>();
		recipe.put(component, 1);
		
		Product product = new Product("test", 1.0, 2.0, recipe);
		viewModel.setProduct(product);
		assert(viewModel.getName().getValue().equals("test"));
		assert(viewModel.getProductionCost().getValue().equals("1.0"));
		assert(viewModel.getSellingPrice().getValue().equals("2.0"));
		assert(viewModel.getQuantity().getValue().equals("0"));
		assert(viewModel.getRecipe().equals(recipe));
	}
	
	@Test
	void testSetProductWhenProductIsInInventory() {
        EditProductViewModel viewModel = new EditProductViewModel();
        Component component = new Component("test", 1.0);
        Map<Component, Integer> recipe = new HashMap<>();
        recipe.put(component, 1);
        
        Product product = new Product("test", 1.0, 2.0, recipe);
        ProductInventory.addProduct("test", 1.0, 2.0, recipe);
        inventory.addItem(product, 20);
        viewModel.setProduct(product);
        assert(viewModel.getName().getValue().equals("test"));
        assert(viewModel.getProductionCost().getValue().equals("1.0"));
        assert(viewModel.getSellingPrice().getValue().equals("2.0"));
        assert(viewModel.getQuantity().getValue().equals("20"));
        assert(viewModel.getRecipe().equals(recipe));
	}
}
