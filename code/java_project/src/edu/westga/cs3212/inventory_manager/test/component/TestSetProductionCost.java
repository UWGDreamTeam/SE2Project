package edu.westga.cs3212.inventory_manager.test.component;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.warehouse.Component;

class TestSetProductionCost {

	@Test
	void testWithNegativeProductionCost() {
		// Arrange
		Component component = new Component("component", 1.0);

        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            component.setProductionCost(-1.0);
        });
	}

}
