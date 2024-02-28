package edu.westga.cs3212.inventory_manager.test.component;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.inventory_manager.model.Component;
import edu.westga.cs3212.inventory_manager.model.Item;

class TestEquals {
	
	@Test
	void testEqualsSelf() {
		//Arrange
		Item product = new Component("arrow");
		
		//Act
		
		//Assert
		assertTrue(product.equals(product));
	}
	
	@Test
	void testEqualsWithNull() {
		//Arrange
		Item product = new Component("feather");
		
		//Act
		
		//Assert
		assertFalse(product.equals(null));
	}
	
	@Test
	void testEqualsWithDifferentClass() {
		//Arrange
		Item product = new Component("furnace");
		Object product1 = new Object();
		
		//Act
		
		//Assert
		assertFalse(product.equals(product1));
	}
	
	@Test
	void testEqualsWithSameClassAndHashCode() {
		//Arrange
		String name = "table";
		
		Item product = new Component(name);
		Item product1 = new Component(name);
		
		//Act & Assert
		assertTrue(product.equals(product1));
	}
	
	@Test
	void testEqualsWithSameClassDifferentHashCode() {
		//Arrange
		Item product = new Component("grass");
		Item product1 = new Component("water");
		
		//Act
		
		//Assert
		assertFalse(product.equals(product1));
	}

}
