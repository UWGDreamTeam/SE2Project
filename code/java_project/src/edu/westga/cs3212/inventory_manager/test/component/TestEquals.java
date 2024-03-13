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
		Item product = new Component("arrow", 50);
		
		//Act
		
		//Assert
		assertTrue(product.equals(product));
	}
	
	@Test
	void testEqualsWithNull() {
		//Arrange
		Item product = new Component("feather", 50);
		
		//Act
		
		//Assert
		assertFalse(product.equals(null));
	}
	
	@Test
	void testEqualsWithDifferentClass() {
		//Arrange
		Item product = new Component("furnace", 20);
		Object product1 = new Object();
		
		//Act
		
		//Assert
		assertFalse(product.equals(product1));
	}
	
	@Test
	void testEqualsWithSameClassAndHashCode() {
		//Arrange
		String name = "table";
		
		Item product = new Component(name, 20);
		Item product1 = new Component(name, 20);
		product1.setID(product.getID());
		
		//Act & Assert
		assertTrue(product.equals(product1));
	}
	
	@Test
	void testEqualsWithSameClassDifferentHashCode() {
		//Arrange
		Item product = new Component("grass", 90);
		Item product1 = new Component("water", 90);
		
		//Act
		
		//Assert
		assertFalse(product.equals(product1));
	}

}
