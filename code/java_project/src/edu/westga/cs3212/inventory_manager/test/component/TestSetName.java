package edu.westga.cs3212.inventory_manager.test.component;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.warehouse.Component;

class TestSetName {

	@Test
	void testWithNullName() {
		// Arrange
		Component component = new Component("component", 1.0);

        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            component.setName(null);
        });
	}
	
	@Test
	void testWithBlankName() {
		// Arrange
		Component component = new Component("component", 1.0);

        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            component.setName(" ");
        });
	}
	
	@Test
	void testWithEmptyName() {
		// Arrange
		Component component = new Component("component", 1.0);

        // Act
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            component.setName("");
        });
	}

}
