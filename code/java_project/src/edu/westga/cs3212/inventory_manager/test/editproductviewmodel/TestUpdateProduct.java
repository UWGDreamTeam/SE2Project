package edu.westga.cs3212.inventory_manager.test.editproductviewmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Product;
import edu.westga.cs3212.inventory_manager.model.server_impl.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server_impl.ProductInventory;
import edu.westga.cs3212.inventory_manager.viewmodel.EditProductViewModel;

public class TestUpdateProduct {

	@BeforeEach
	void setUp() {
		ProductInventory.clearInventory();
		ComponentInventory.clearInventory();
	}
	
	@Test
	void testUpdateProduct() {
		EditProductViewModel viewModel = new EditProductViewModel();
		Map<Component, Integer> recipe = new HashMap<>();
		Component component = new Component("Component", 5.0);
		String componentID = ComponentInventory.addComponent("Component", 5.0, 20);
		component.setID(componentID);
		recipe.put(component, 2);
		Product product = new Product("Product", 10.0, 20.0, recipe);
		String productID = ProductInventory.addProduct("Product", 10.0, recipe, 5);
		product.setID(productID);
		viewModel.setProduct(product);
		viewModel.getName().setValue("Updated Product");
		viewModel.getProductionCost().setValue("15.0");
		viewModel.getSellingPrice().setValue("25.0");
		viewModel.getQuantity().setValue("5");
		viewModel.updateProduct(recipe);
		assertEquals("Updated Product", viewModel.getName().getValue());
		assertEquals(15.0, Double.parseDouble(viewModel.getProductionCost().getValue()));
		assertEquals(25.0, Double.parseDouble(viewModel.getSellingPrice().getValue()));
		assertEquals(5, Integer.parseInt(viewModel.getQuantity().getValue()));
	}
	
	@Test
	void testUpdateProductWhenProductIsNotInInventory() {
		EditProductViewModel viewModel = new EditProductViewModel();
		Map<Component, Integer> recipe = new HashMap<>();
		Component component = new Component("Component", 5.0);
		String componentID = ComponentInventory.addComponent("Component", 5.0, 20);
		component.setID(componentID);
		recipe.put(component, 2);
		assertThrows(Exception.class, () -> viewModel.updateProduct(recipe));
	}
	
}
