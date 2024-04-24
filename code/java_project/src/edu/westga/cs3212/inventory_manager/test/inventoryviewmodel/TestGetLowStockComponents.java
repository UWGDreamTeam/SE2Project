package edu.westga.cs3212.inventory_manager.test.inventoryviewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.server.warehouse.ComponentInventory;
import edu.westga.cs3212.inventory_manager.viewmodel.inventory.InventoryViewModel;

class TestGetLowStockComponents {

	private InventoryViewModel inventoryVM;

    @BeforeEach
    void setUp() {
        this.inventoryVM = new InventoryViewModel();
        ComponentInventory.clearInventory();
    }

    @Test
    void testGetLowStockComponentsWhenEmpty() {
        assertEquals(0, this.inventoryVM.getLowStockComponents().size(), "Expected no components to be low in stock");
    }

    @Test
    void testGetLowStockComponentsWithOneLowStockComponent() {
        ComponentInventory.addComponent("LowStockComponent1", 1, 10);

        assertEquals(1, this.inventoryVM.getLowStockComponents().size(), "Expected one component to be low in stock");
    }

    @Test
    void testGetLowStockComponentsWithMultipleComponentsSomeLowStock() {
        ComponentInventory.addComponent("NormalStockComponent", 1, 20);
        ComponentInventory.addComponent("LowStockComponent1", 1, 10);
        ComponentInventory.addComponent("LowStockComponent2", 2, 5);

        assertEquals(2, this.inventoryVM.getLowStockComponents().size(), "Expected two components to be low in stock");
    }

    @Test
    void testGetLowStockComponentsWithNoLowStock() {
        ComponentInventory.addComponent("Component1", 1, 15);
        ComponentInventory.addComponent("Component2", 2, 25);

        assertEquals(0, this.inventoryVM.getLowStockComponents().size(), "Expected no components to be low in stock");
    }

}
