package edu.westga.cs3212.inventory_manager.test.inventoryviewmodel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.ComponentInventory;
import edu.westga.cs3212.inventory_manager.model.server.warehouse.ProductInventory;
import edu.westga.cs3212.inventory_manager.model.warehouse.Component;
import edu.westga.cs3212.inventory_manager.model.warehouse.Product;
import edu.westga.cs3212.inventory_manager.viewmodel.inventory.InventoryViewModel;
import javafx.collections.ObservableList;

class TestSearchItem {

	private InventoryViewModel inventoryVM;

    @BeforeEach
    public void setUp() {
        this.inventoryVM = new InventoryViewModel();
        ComponentInventory.clearInventory();
		ProductInventory.clearInventory();
    }

    @Test
    void testSearchComponents() {
        Component component1 = new Component("Component1", 1);
        Component component2 = new Component("Component2", 1);
        Component component3 = new Component("Component3", 1);
        ComponentInventory.addComponent(component1.getName(), 10, 1);
        ComponentInventory.addComponent(component2.getName(), 10, 1);
        ComponentInventory.addComponent(component3.getName(), 10, 1);

        ObservableList<Component> searchResults = this.inventoryVM.searchComponents("Component");
        assertEquals(3, searchResults.size());
    }

    @Test
    void testSearchProducts() {
    	Map<Component, Integer> recipe = new HashMap<>();
		Component component = new Component("TestComponent", 1);
		String componentID = ComponentInventory.addComponent("TestComponent", 1, 0);
		component.setID(componentID);
		recipe.put(component, 1);
        Product product1 = new Product("Product1", 1, 1, recipe);
        Product product2 = new Product("Product2", 2, 2, recipe);
        Product product3 = new Product("Product3", 3, 3, recipe);
        ProductInventory.addProduct(product1.getName(), 10, recipe, 1);
        ProductInventory.addProduct(product2.getName(), 10, recipe, 1);
        ProductInventory.addProduct(product3.getName(), 10, recipe, 1);

        ObservableList<Product> searchResults = this.inventoryVM.searchProducts("Product");
        assertEquals(3, searchResults.size());
    }
}
