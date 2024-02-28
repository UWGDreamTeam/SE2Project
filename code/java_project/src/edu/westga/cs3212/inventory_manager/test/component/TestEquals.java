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
		Item product = new Component("ID528", "arrow");
		
		//Act
		
		//Assert
		assertTrue(product.equals(product));
	}
	
	@Test
	void testEqualsWithNull() {
		//Arrange
		Item product = new Component("ID527", "feather");
		
		//Act
		
		//Assert
		assertFalse(product.equals(null));
	}
	
	@Test
	void testEqualsWithDifferentClass() {
		//Arrange
		Item product = new Component("ID523", "furnace");
		Object product1 = new Object();
		
		//Act
		
		//Assert
		assertFalse(product.equals(product1));
	}
	
	@Test
	void testEqualsWithSameClassAndHashCode() {
		//Arrange
		String id = "ID529";
		String name = "table";
		
		Item product = new Component(id, name);
		Item product1 = new Component(id, name);
		//Act
		
		//Assert
		assertTrue(product.equals(product1));
	}
	
	@Test
	void testEqualsWithSameClassDifferentHashCode() {
		//Arrange
		Item product = new Component("ID526", "grass");
		Item product1 = new Component("ID991", "water");
		
		//Act
		
		//Assert
		assertFalse(product.equals(product1));
	}

}
