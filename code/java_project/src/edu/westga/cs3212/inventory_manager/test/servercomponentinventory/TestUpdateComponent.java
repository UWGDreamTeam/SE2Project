package edu.westga.cs3212.inventory_manager.test.servercomponentinventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.server_impl.ComponentInventory;

class TestUpdateComponent {

    @Test
    void testUpdateComponentWithNullID() {
	assertThrows(IllegalArgumentException.class, () -> {
	    ComponentInventory.updateComponent(null, "NewName", 0.0, 0);
	});
    }

    @Test
    void testUpdateComponentWithBlankID() {
	assertThrows(IllegalArgumentException.class, () -> {
	    ComponentInventory.updateComponent("", "NewName", 0.0, 0);
	});
    }

    @Test
    void testUpdateComponentWithNullName() {
	assertThrows(IllegalArgumentException.class, () -> {
	    ComponentInventory.updateComponent("ID", null, 0.0, 0);
	});
    }

    @Test
    void testUpdateComponentWithBlankName() {
	assertThrows(IllegalArgumentException.class, () -> {
	    ComponentInventory.updateComponent("ID", "", 0.0, 0);
	});
    }

    @Test
    void testUpdateComponentWithNegativeProductionCost() {
	assertThrows(IllegalArgumentException.class, () -> {
	    ComponentInventory.updateComponent("ID", "NewName", -1.0, 0);
	});
    }

    @Test
    void testUpdateComponentWhenIDNotFound() {
	assertThrows(IllegalArgumentException.class, () -> {
	    ComponentInventory.updateComponent("thiscomponentdoesnoteist", "NewName", 0.0, 0);
	});
    }

    @Test
    void testUpdateComponentWithValidData() {
	String ComponentID = ComponentInventory.addComponent("Name", 0.0, 0);
	ComponentInventory.updateComponent(ComponentID, "NewName", 0.0, 0);
    }

}
