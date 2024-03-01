package edu.westga.cs3212.inventory_manager.test.editproductviewmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Product;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalComponentInventory;
import edu.westga.cs3212.inventory_manager.model.local_impl.LocalProductInventory;
import edu.westga.cs3212.inventory_manager.viewmodel.EditProductViewModel;

public class TestUpdateProduct {

	@Test
	void testUpdateProduct() {
		LocalProductInventory productInventory = new LocalProductInventory();
		LocalComponentInventory componentInventory = new LocalComponentInventory();
		EditProductViewModel viewModel = new EditProductViewModel(productInventory, componentInventory);
		Map<Component, Integer> recipe = new HashMap<>();
		Component component = new Component("Component", 5.0);
		recipe.put(component, 2);
		Product product = new Product("Product", 10.0, 20.0, recipe);
		productInventory.addItem(product, 50);
		viewModel.setProduct(product);
		viewModel.getName().setValue("Updated Product");
		viewModel.getProductionCost().setValue("15.0");
		viewModel.getSellingPrice().setValue("25.0");
		viewModel.getQuantity().setValue("5");
		viewModel.updateProduct(recipe);
		assertEquals("Updated Product", product.getName());
		assertEquals(15.0, product.getProductionCost());
		assertEquals(25.0, product.getSalePrice());
		assertEquals(5, productInventory.getQuantityOfItem(product));
	}
	
	@Test
	void testUpdateProductWhenProductIsNotInInventory() {
		LocalProductInventory productInventory = new LocalProductInventory();
		LocalComponentInventory componentInventory = new LocalComponentInventory();
		EditProductViewModel viewModel = new EditProductViewModel(productInventory, componentInventory);
		Map<Component, Integer> recipe = new HashMap<>();
		Component component = new Component("Component", 5.0);
		recipe.put(component, 2);
		Product product = new Product("Product", 10.0, 20.0, recipe);
		viewModel.setProduct(product);
		viewModel.getName().setValue("Updated Product");
		viewModel.getProductionCost().setValue("15.0");
		viewModel.getSellingPrice().setValue("25.0");
		viewModel.getQuantity().setValue("5");
		assertThrows(Exception.class, () -> viewModel.updateProduct(recipe));
	}
	
}
