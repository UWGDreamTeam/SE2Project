package edu.westga.cs3212.inventory_manager.test.component;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.warehouse.Component;

class TestSetID {

	@Test
	void testWithNullId() {
		// Arrange
		Component component = new Component("component", 1.0);

        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            component.setID(null);
        });
	}
	
	@Test
	void testWithBlankId() {
		// Arrange
		Component component = new Component("component", 1.0);

        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            component.setID(" ");
        });
	}
	
	@Test
	void testWithEmptyId() {
		// Arrange
		Component component = new Component("component", 1.0);

        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            component.setID("");
        });
	}

}
